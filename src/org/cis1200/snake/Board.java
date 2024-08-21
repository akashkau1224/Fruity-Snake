package org.cis1200.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Arrays;

public class Board extends JComponent {

    // main 2D array for the board
    private static int[][] board;

    // stores the last pixel in the snake
    private static GameObj tail;

    // the direction the snake is moving
    private static Direction direction;

    // the main snake used in the game (this just stores the head)
    private SnakeHead snake;

    // boolean saying if the game is running or not
    private boolean playing;

    private final JLabel status;

    // determines the speed
    private static int interval = 60;

    // determines the color
    private static Color snakeColor = Color.BLUE;

    private static Timer timer;

    public Board(JLabel status) {

        this.status = status;

        // adds the key functionality
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (direction != Direction.RIGHT) {
                        direction = Direction.LEFT;
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (direction != Direction.LEFT) {
                        direction = Direction.RIGHT;
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (direction != Direction.UP) {
                        direction = Direction.DOWN;
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (direction != Direction.DOWN) {
                        direction = Direction.UP;
                    }
                }
            }
        });

        // initially sets the timer
        timer = new Timer(interval, e -> tick());
        timer.start();

        setFocusable(true);

        board = new int[40][40];

        // adds the walls and the main board to the 2D array
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (i == 0 || j == 0 || i == board.length - 1 || j == board.length - 1
                        || i == 1 || j == 1 || i == board.length - 2 || j == board.length - 2) {
                    board[i][j] = 1;
                } else {
                    board[i][j] = 0;
                }
            }
        }

        // sets the direction
        direction = Direction.RIGHT;
    }

    // getters and setters for this class
    public static int getBoardPixel(int px, int py) {
        int x = board[px][py];
        return x;
    }

    public static void setBoardPixel(int px, int py, int type) {
        board[px][py] = type;
    }

    public static GameObj getTail() {
        return tail;
    }

    public static void setTail(GameObj tails) {
        tail = tails;
    }

    public static Direction getDirection() {
        return direction;
    }

    public static void setDirection(Direction directions) {
        direction = directions;
    }

    public static Color getSnakeColor() {
        return snakeColor;
    }

    public static void setSnakeColor(Color color) {
        snakeColor = color;
    }

    public static int getSpeed() {
        return interval;
    }

    public static void setSpeed(int speed) {
        interval = speed;
        timer.setDelay(interval);
    }

    public void reset() {

        // creates a new snake
        snake = new SnakeHead(15, 20);

        tail = snake;

        // creates a new board
        board = new int[40][40];

        snakeColor = Color.GREEN;

        setSpeed(60);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (i == 0 || j == 0 || i == board.length - 1 || j == board.length - 1
                        || i == 1 || j == 1 || i == board.length - 2 || j == board.length - 2) {
                    board[i][j] = 1;
                } else {
                    board[i][j] = 0;
                }
            }
        }

        direction = Direction.RIGHT;

        // creates the initial apple
        Apple apple = new Apple(2);
        snake.setFruit(apple);

        repaint();

        status.setText("Running...");

        requestFocusInWindow();

        playing = true;
    }

    void tick() {
        if (playing) {
            snake.move();
            if (snake.isIntersect()) {
                playing = false;
                status.setText("You Lose!");
            }
        }

        repaint();

    }

    public void save() {
        try {

            playing = false;

            FileWriter fw = new FileWriter("files/snake.txt", false);

            BufferedWriter bw = new BufferedWriter(fw);

            // saves the 2D array to the text file
            for (int i = 0; i < 40; i++) {
                String s = "";
                for (int j = 0; j < 40; j++) {
                    s = s + board[j][i] + " ";
                }
                bw.write(s);
                bw.newLine();
            }

            // saves the direction
            bw.write(direction.toString());
            bw.newLine();

            // saves the head position
            bw.write(snake.getPx() + " " + snake.getPy());
            bw.newLine();

            // saves the positions of the body pixels
            String bodyPx = "";

            for (GameObj b : snake.getBody()) {
                String temp = Arrays.toString(b.getPrevPos());
                temp = temp.substring(1, temp.length() - 1);
                bodyPx = bodyPx + b.getPx() + ", " + b.getPy() + ", " + temp + "; ";
            }

            bw.write(bodyPx);
            bw.newLine();

            // saves the snake color
            bw.write(
                    snakeColor.getRed() + " " + snakeColor.getGreen() + " " + snakeColor.getBlue()
            );
            bw.newLine();

            // saves the snake speed
            bw.write(interval + "");
            bw.newLine();

            // saves the fruit on the board
            bw.write(snake.getFruit().getType());
            bw.newLine();

            // fruit location
            bw.write(snake.getFruit().getPx() + " " + snake.getFruit().getPy());
            bw.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "IO ERROR"); // throw a popup
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR"); // throw a popup
        }
    }

    public void load() {
        try {
            FileReader fr = new FileReader("files/snake.txt");
            BufferedReader br = new BufferedReader(fr);
            FileLineIterator fli = new FileLineIterator(br);
            String str = "";

            // loads the board onto the 2D array
            for (int i = 0; i < 40; i++) {
                str = fli.next();
                String[] arr = str.split(" ");
                for (int j = 0; j < 40; j++) {
                    int num = Integer.valueOf(arr[j]);
                    board[j][i] = num;
                }
            }

            str = fli.next();

            // sets the direction
            if (str.equals("UP")) {
                direction = Direction.UP;
            } else if (str.equals("DOWN")) {
                direction = Direction.DOWN;
            } else if (str.equals("LEFT")) {
                direction = Direction.LEFT;
            } else {
                direction = Direction.RIGHT;
            }

            // sets a new position for the head
            str = fli.next();
            String[] coor = str.split(" ");
            snake = new SnakeHead(Integer.valueOf(coor[0]), Integer.valueOf(coor[1]));

            // sets the positions and all of the required instance variables for the body
            // pixels
            str = fli.next();
            String[] bodies = str.split("; ");

            GameObj prev = snake;

            for (String s : bodies) {
                String[] stuff = s.split(", ");
                SnakeBody sb = new SnakeBody(
                        prev, Integer.valueOf(stuff[0]), Integer.valueOf(stuff[1]),
                        Integer.valueOf(stuff[2]), Integer.valueOf(stuff[3])
                );
                snake.addBody(sb);
                prev = sb;
            }

            // sets the color
            str = fli.next();
            String[] colors = str.split(" ");
            snakeColor = new Color(
                    Integer.valueOf(colors[0]), Integer.valueOf(colors[1]),
                    Integer.valueOf(colors[2])
            );

            // sets the speed
            str = fli.next();
            setSpeed(Integer.valueOf(str));

            // sets the fruit saved before
            str = fli.next();
            String fruitType = str;
            str = fli.next();
            String[] fruitCoor = str.split(" ");

            if (fruitType.equals("Apple")) {
                snake.setFruit(
                        new Apple(Integer.valueOf(fruitCoor[0]), Integer.valueOf(fruitCoor[1]), 2)
                );
            } else if (fruitType.equals("Banana")) {
                snake.setFruit(
                        new Banana(Integer.valueOf(fruitCoor[0]), Integer.valueOf(fruitCoor[1]))
                );
            } else {
                snake.setFruit(
                        new Blueberry(Integer.valueOf(fruitCoor[0]), Integer.valueOf(fruitCoor[1]))
                );
            }

            // repaints the new 2D array
            repaint();

            requestFocusInWindow();

            playing = true;

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "FILE NOT FOUND ERROR"); // throw a popup
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "IO ERROR"); // throw a popup
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR"); // throw a popup
        }
    }

    // this draws the board with different colors corresponding to the different
    // integer values
    @Override
    public void paintComponent(Graphics g) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    g.setColor(Color.WHITE);
                } else if (board[i][j] == 2) {
                    g.setColor(Color.RED);
                } else if (board[i][j] == 1) {
                    g.setColor(Color.BLACK);
                } else if (board[i][j] == 5) {
                    g.setColor(Color.YELLOW);
                } else if (board[i][j] == 6) {
                    g.setColor(Color.CYAN);
                } else {
                    g.setColor(snakeColor);
                }
                g.fillRect(10 * i, 10 * j, 10, 10);
            }
        }
    }

    // sets the board size to the desired size
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }
}

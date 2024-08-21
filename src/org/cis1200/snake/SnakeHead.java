package org.cis1200.snake;

import java.util.LinkedList;
import java.util.Random;

public class SnakeHead extends GameObj {

    // boolean that is true if the snake head intersects the body or a wall
    private static boolean intersect;

    // linked list storing the body objects
    private static LinkedList<GameObj> body;

    // Random generator to generate random fruit
    private Random numGen = new Random();

    // the current fruit on the board
    private Apple fruit;

    public SnakeHead(int px, int py) {
        super(px, py, 3);
        intersect = false;
        body = new LinkedList<>();
        int[] temp = { this.getPx(), this.getPy() };
        super.setPrevPos(temp);
    }

    // misc. getters and setters
    public LinkedList<GameObj> getBody() {
        return this.body;
    }

    public boolean isIntersect() {
        return this.intersect;
    }

    public void addBody(GameObj body) {
        this.body.add(body);

        Board.setTail(body);
    }

    public Apple getFruit() {
        return fruit;
    }

    public void setFruit(Apple a) {
        fruit = a;
    }

    // creates a random fruit on the board
    public void generateFruit() {
        int num = numGen.nextInt(3);
        if (num == 0) {
            fruit = new Apple(2);
        } else if (num == 1) {
            fruit = new Banana();
        } else {
            fruit = new Blueberry();
        }

    }

    @Override
    public void move() {
        // sets previous position with the current position
        int[] temp = { super.getPx(), super.getPy() };
        super.setPrevPos(temp);

        // cases the predicted next position based on the direction
        if (Board.getDirection() == Direction.UP) {

            // if the next position is a wall or snake body it will intersect
            if (Board.getBoardPixel(super.getPx(), super.getPy() - 1) == 1 ||
                    Board.getBoardPixel(super.getPx(), super.getPy() - 1) == 4) {
                intersect = true;
            } else if (Board.getBoardPixel(super.getPx(), super.getPy() - 1) == 2 ||
                    Board.getBoardPixel(super.getPx(), super.getPy() - 1) == 5 ||
                    Board.getBoardPixel(super.getPx(), super.getPy() - 1) == 6) {

                // if the next position is a fruit it will make the snake longer and generate
                // a new fruit
                SnakeBody s = new SnakeBody(Board.getTail());
                Board.setTail(s);
                body.add(s);
                fruit.action();
                generateFruit();
            }

            // sets the new position of the head
            super.setPy(super.getPy() - 1, 3);

            // moves the body
            if (!body.isEmpty()) {
                for (GameObj b : body) {
                    b.move();
                }
            }

        } else if (Board.getDirection() == Direction.DOWN) {
            if (Board.getBoardPixel(super.getPx(), super.getPy() + 1) == 1 ||
                    Board.getBoardPixel(super.getPx(), super.getPy() + 1) == 4) {
                intersect = true;
            } else if (Board.getBoardPixel(super.getPx(), super.getPy() + 1) == 2 ||
                    Board.getBoardPixel(super.getPx(), super.getPy() + 1) == 5 ||
                    Board.getBoardPixel(super.getPx(), super.getPy() + 1) == 6) {
                SnakeBody s = new SnakeBody(Board.getTail());
                Board.setTail(s);
                body.add(s);
                fruit.action();
                generateFruit();
            }

            super.setPy(super.getPy() + 1, 3);
            // if down move the body in the direction of the block in front of it
            if (!body.isEmpty()) {
                for (GameObj b : body) {
                    b.move();
                }
            }

        } else if (Board.getDirection() == Direction.LEFT) {
            if (Board.getBoardPixel(super.getPx() - 1, super.getPy()) == 1 ||
                    Board.getBoardPixel(super.getPx() - 1, super.getPy()) == 4) {
                intersect = true;
            } else if (Board.getBoardPixel(super.getPx() - 1, super.getPy()) == 2 ||
                    Board.getBoardPixel(super.getPx() - 1, super.getPy()) == 5 ||
                    Board.getBoardPixel(super.getPx() - 1, super.getPy()) == 6) {
                SnakeBody s = new SnakeBody(Board.getTail());
                Board.setTail(s);
                body.add(s);
                fruit.action();
                generateFruit();

            }

            super.setPx(super.getPx() - 1, 3);
            if (!body.isEmpty()) {
                for (GameObj b : body) {
                    b.move();
                }
            }

        } else {
            if (Board.getBoardPixel(super.getPx() + 1, super.getPy()) == 1 ||
                    Board.getBoardPixel(super.getPx() + 1, super.getPy()) == 4) {
                intersect = true;
            } else if (Board.getBoardPixel(super.getPx() + 1, super.getPy()) == 2 ||
                    Board.getBoardPixel(super.getPx() + 1, super.getPy()) == 5 ||
                    Board.getBoardPixel(super.getPx() + 1, super.getPy()) == 6) {
                SnakeBody s = new SnakeBody(Board.getTail());
                Board.setTail(s);
                body.add(s);
                fruit.action();
                generateFruit();
            }
            super.setPx(super.getPx() + 1, 3);
            if (!body.isEmpty()) {
                for (GameObj b : body) {
                    b.move();
                }
            }

        }
    }

}

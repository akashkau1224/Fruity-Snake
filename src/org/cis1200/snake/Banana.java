package org.cis1200.snake;

import java.awt.*;
import java.util.TreeMap;
import java.util.Random;

public class Banana extends Apple {

    // map used to store colors corresponding to integers
    TreeMap<Integer, Color> map = new TreeMap<Integer, Color>();

    // initializes the map
    public Banana() {
        super(5);
        map.put(0, Color.GRAY);
        map.put(1, Color.RED);
        map.put(2, Color.ORANGE);
        map.put(3, Color.YELLOW);
        map.put(4, Color.GREEN);
        map.put(5, Color.BLUE);
        map.put(6, Color.MAGENTA);
    }

    // alternate constructor used for saving the game
    public Banana(int px, int py) {
        super(px, py, 5);
        map.put(0, Color.GRAY);
        map.put(1, Color.RED);
        map.put(2, Color.ORANGE);
        map.put(3, Color.YELLOW);
        map.put(4, Color.GREEN);
        map.put(5, Color.BLUE);
        map.put(6, Color.MAGENTA);
    }

    @Override
    public void action() {
        // generates a random color that is not the same as the current color
        Color color = Board.getSnakeColor();
        while (color == Board.getSnakeColor()) {
            Random numGen = new Random();
            int num = numGen.nextInt(7);
            color = map.get(num);
        }

        // sets the snake to that color
        Board.setSnakeColor(color);
    }

    // method used for saving the game
    @Override
    public String getType() {
        return "Banana";
    }

}

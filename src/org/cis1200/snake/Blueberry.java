package org.cis1200.snake;

import javax.swing.*;

public class Blueberry extends Apple {

    public Blueberry() {
        super(6);
    }

    // constructor used for saving the game
    public Blueberry(int px, int py) {
        super(px, py, 6);
    }

    // action speeds up the snake gradually until it is too fast and it will slow
    // the snake down
    @Override
    public void action() {
        if (Board.getSpeed() == 60) {
            Board.setSpeed(50);
        } else if (Board.getSpeed() == 50) {
            Board.setSpeed(40);
        } else {
            Board.setSpeed(60);
        }
    }

    // method used for saving the game
    @Override
    public String getType() {
        return "Blueberry";
    }

}

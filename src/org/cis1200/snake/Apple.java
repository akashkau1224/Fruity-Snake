package org.cis1200.snake;

import java.util.Random;

public class Apple extends GameObj {

    // generates a random x and y coordinate
    // this will not give a coordinate taken up by some other type of block
    private int[] generateRandomCoor() {
        int[] coor = { 0, 0 };
        Random numGen = new Random();
        while (Board.getBoardPixel(coor[0], coor[1]) != 0) {
            coor[0] = 2 + numGen.nextInt(36);
            coor[1] = 2 + numGen.nextInt(36);
        }
        return coor;
    }

    // creates a fruit based on a random location on the board
    public Apple(int type) {
        super(2, 2, type);
        int[] coor = generateRandomCoor();
        super.setPx(coor[0], type);
        super.setPy(coor[1], type);
    }

    // alternate constructor for the purposes of saving the game
    public Apple(int px, int py, int type) {
        super(px, py, type);
    }

    // method inherited from the gameobj class but never implemented because fruit
    // don't move
    @Override
    public void move() {

    }

    // method that subclasses use but apple doesn't do anything
    public void action() {

    }

    // used for saving a loading the game
    public String getType() {
        return "Apple";
    }
}

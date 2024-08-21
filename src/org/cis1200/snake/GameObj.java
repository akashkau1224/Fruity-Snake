package org.cis1200.snake;

public abstract class GameObj {

    // the x position of the object
    private int px;

    // the y position of the object
    private int py;

    // the previous position of this object
    // the zero index represents the x coordinate and the first index represents the
    // y coordinate
    private int[] prevPos;

    // initializes the object while also setting the type on the board
    public GameObj(int px, int py, int type) {
        this.px = px;
        this.py = py;

        Board.setBoardPixel(px, py, type);
    }

    // getters for this class
    public int getPx() {
        return this.px;
    }

    public int getPy() {
        return this.py;
    }

    public int[] getPrevPos() {
        return this.prevPos;
    }

    // setters for the position of the object
    // depending on the type of object, the setters will make the previous position
    // of the object an empty space
    public void setPx(int px, int type) {
        if (Board.getBoardPixel(this.px, this.py) == 2 ||
                Board.getBoardPixel(this.px, this.py) == 5 ||
                Board.getBoardPixel(this.px, this.py) == 6) {
            Board.setBoardPixel(this.px, this.py, 0);
        } else if (Board.getTail().getPx() == this.px && Board.getTail().getPy() == this.py) {
            int[] prevPos = Board.getTail().getPrevPos();
            Board.setBoardPixel(prevPos[0], prevPos[1], 0);
        }
        Board.setBoardPixel(px, this.py, type);
        this.px = px;
    }

    public void setPy(int py, int type) {
        if (Board.getBoardPixel(this.px, this.py) == 2 ||
                Board.getBoardPixel(this.px, this.py) == 5 ||
                Board.getBoardPixel(this.px, this.py) == 6) {
            Board.setBoardPixel(this.px, this.py, 0);
        } else if (Board.getTail().getPx() == this.px && Board.getTail().getPy() == this.py) {
            int[] prevPos = Board.getTail().getPrevPos();
            Board.setBoardPixel(prevPos[0], prevPos[1], 0);
        }
        Board.setBoardPixel(this.px, py, type);
        this.py = py;
    }

    public void setPrevPos(int[] prevPos) {
        this.prevPos = prevPos;
    }

    // a move function that will be implemented in the subtypes
    public abstract void move();

}

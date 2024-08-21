package org.cis1200.snake;

public class SnakeBody extends GameObj {

    // stores the snake object in front of it
    private final GameObj leader;

    public SnakeBody(GameObj g) {
        super(g.getPx(), g.getPy(), 4);
        int[] prev = g.getPrevPos();
        super.setPx(prev[0], 4);
        super.setPy(prev[1], 4);

        leader = g;
        int[] temp = { this.getPx(), this.getPy() };
        super.setPrevPos(temp);
    }

    // alternate constructor for the purpose of saving the game
    public SnakeBody(GameObj g, int px, int py, int prevPx, int prevPy) {
        super(px, py, 4);
        leader = g;
        int[] temp = { prevPx, prevPy };
        super.setPrevPos(temp);
    }

    @Override
    public void move() {
        // sets current position as previous position
        int[] temp = { this.getPx(), this.getPy() };
        super.setPrevPos(temp);

        // moves to leaders previous position
        int[] prevCoord = leader.getPrevPos();
        super.setPx(prevCoord[0], 4);
        super.setPy(prevCoord[1], 4);
    }
}

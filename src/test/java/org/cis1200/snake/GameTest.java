package org.cis1200.snake;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void testCollisionWithWalls() {
        JLabel status = new JLabel();
        Board board = new Board(status);
        SnakeHead s = new SnakeHead(38, 3);
        board.setTail(s);
        s.move();
        assertTrue(s.isIntersect());
    }

    @Test
    public void testCollisionWithBody() {
        JLabel status = new JLabel();
        Board board = new Board(status);
        SnakeHead s = new SnakeHead(15, 15);
        SnakeBody s1 = new SnakeBody(s,15,14,16,14);
        SnakeBody s2 = new SnakeBody(s1, 16,14,16,15);
        SnakeBody s3 = new SnakeBody(s2, 16,15,16,16);
        board.setTail(s3);
        s.move();
        assertTrue(s.isIntersect());
    }

    @Test
    public void testLengthenSnakeApple() {
        JLabel status = new JLabel();
        Board board = new Board(status);
        SnakeHead s = new SnakeHead(15, 15);
        board.setTail(s);
        Apple a = new Apple(16,15,2);
        s.setFruit(a);
        s.move();
        assertEquals(4, board.getBoardPixel(15,15));
    }

    @Test
    public void testLengthenSnakeBanana() {
        JLabel status = new JLabel();
        Board board = new Board(status);
        SnakeHead s = new SnakeHead(15, 15);
        board.setTail(s);
        Banana a = new Banana(16,15);
        s.setFruit(a);
        s.move();
        assertEquals(4, board.getBoardPixel(15,15));
    }

    @Test
    public void testLengthenSnakeBlueberry() {
        JLabel status = new JLabel();
        Board board = new Board(status);
        SnakeHead s = new SnakeHead(15, 15);
        board.setTail(s);
        Blueberry a = new Blueberry(16,15);
        s.setFruit(a);
        s.move();
        assertEquals(4, board.getBoardPixel(15,15));
    }

    @Test
    public void testBananaEffect() {
        JLabel status = new JLabel();
        Board board = new Board(status);
        SnakeHead s = new SnakeHead(15, 15);
        board.setTail(s);

        Banana b = new Banana(16,15);
        s.setFruit(b);
        s.move();
        Color c = board.getSnakeColor();
        assertNotEquals(Color.GREEN, c);

        Banana b1 = new Banana(17,15);
        s.setFruit(b1);
        s.move();
        assertNotEquals(c, board.getSnakeColor());
    }

    @Test
    public void testBlueberryEffect() {
        JLabel status = new JLabel();
        Board board = new Board(status);
        SnakeHead s = new SnakeHead(15, 15);
        board.setTail(s);
        board.setSpeed(60);

        Blueberry b = new Blueberry(16,15);
        s.setFruit(b);
        s.move();
        assertEquals(50, board.getSpeed());

        Blueberry b1 = new Blueberry(17,15);
        s.setFruit(b1);
        s.move();
        assertEquals(40, board.getSpeed());

        Blueberry b2 = new Blueberry(18,15);
        s.setFruit(b2);
        s.move();
        assertEquals(60, board.getSpeed());
    }

}

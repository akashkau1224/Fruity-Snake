package org.cis1200.snake;

import javax.swing.*;
import java.awt.*;

public class RunSnake implements Runnable {

    //instructions window
    public void run() {
        final JFrame frame1 = new JFrame("TOP LEVEL SNAKE INSTRUCTIONS");
        frame1.setLocation(300, 300);

        final JPanel instructions = new JPanel();
        frame1.add(instructions, BorderLayout.CENTER);
        final JTextArea text = new JTextArea("How to play this game: \n" +
                "Use arrow keys to move the snake.\n" +
                "The goal is to collect as many fruit as possible.\n" +
                "The fruit will lengthen the snake\n" +
                "Red apples only lengthen the snake\n" +
                "Yellow bananas change the color of the snake\n" +
                "Blue blueberries change the speed of the snake\n" +
                "Have fun!");
        instructions.add(text);

        final JPanel buttonArea = new JPanel();
        frame1.add(buttonArea, BorderLayout.SOUTH);
        final JButton play = new JButton("Play");
        play.addActionListener(e -> runMain());
        buttonArea.add(play);

        // Put the frame on the screen
        frame1.pack();
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
    }

    //runs main game
    public void runMain() {

        final JFrame frame = new JFrame("TOP LEVEL SNAKE");
        frame.setLocation(300, 300);

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);

        // Main playing area
        final Board court = new Board(status);
        frame.add(court, BorderLayout.CENTER);

        // Control Panel
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        // Buttons
        final JButton reset = new JButton("Reset");
        final JButton save = new JButton("Save");
        final JButton load = new JButton("Load");
        reset.addActionListener(e -> court.reset());
        save.addActionListener(e -> court.save());
        load.addActionListener(e -> court.load());
        control_panel.add(reset);
        control_panel.add(save);
        control_panel.add(load);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        court.reset();

    }
}

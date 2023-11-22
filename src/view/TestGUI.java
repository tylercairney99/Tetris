package view;

import model.TetrisPiece;

import javax.swing.*;
import java.awt.*;

public class TestGUI {

    public static void main(String[] args) {

        final JLabel nextPieceLabel = new JLabel();
        nextPieceLabel.setText("Next Piece");

        final JLabel actionsLabel = new JLabel();
        actionsLabel.setText("User actions");

        final JLabel mainBoardLabel = new JLabel();
        mainBoardLabel.setText("Main Tetris Board");

        final JLabel scoreLabel = new JLabel();
        scoreLabel.setText("Score");

        final JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(1000, 1000);
        frame.setVisible(true);

        final Menu menuBar = new Menu();
        frame.setJMenuBar(menuBar); // Here we add the menu bar to the frame

        //this is the main panel which contains all the other panels.
        final JPanel main = new MainPanel();
        frame.add(main, BorderLayout.CENTER);

        //these are blank panels used to create an outer border.
        final JPanel left = new JPanel();
        final JPanel right = new JPanel();
        final JPanel top = new JPanel();
        final JPanel bottom = new JPanel();
        frame.add(left, BorderLayout.WEST);
        frame.add(right, BorderLayout.EAST);
        frame.add(top, BorderLayout.NORTH);
        frame.add(bottom, BorderLayout.SOUTH);

    }

}

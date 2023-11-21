package view;

import model.TetrisPiece;

import javax.swing.*;
import java.awt.*;

public class TestGUI {

    public static void main(String[] args) {

        JLabel nextPieceLabel = new JLabel();
        nextPieceLabel.setText("Next Piece");

        JLabel actionsLabel = new JLabel();
        actionsLabel.setText("User actions");

        JLabel mainBoardLabel = new JLabel();
        mainBoardLabel.setText("Main Tetris Board");

        JLabel scoreLabel = new JLabel();
        scoreLabel.setText("Score");



        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.RED);
        redPanel.setBounds(15, 15, 340, 630);

        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(Color.BLUE);
        bluePanel.setBounds(395, 15, 200, 200);

        JPanel greenPanel = new JPanel();
        greenPanel.setBackground(Color.GREEN);
        greenPanel.setBounds(395, 300, 200, 150);

        JPanel greenPanel2 = new JPanel();
        greenPanel2.setBackground(Color.GREEN);
        greenPanel2.setBounds(395, 495, 200, 150);

        JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(625,725);

        Menu menuBar = new Menu();
        frame.setJMenuBar(menuBar); // Here we add the menu bar to the frame

        frame.setVisible(true);
        frame.add(redPanel);
        frame.add(bluePanel);
        frame.add(greenPanel);
        frame.add(greenPanel2);

        redPanel.add(mainBoardLabel);
        bluePanel.add(nextPieceLabel);
        greenPanel.add(actionsLabel);
        greenPanel2.add(scoreLabel);

        bluePanel.add(new NextPiece(TetrisPiece.getRandomPiece(), Color.GREEN));

    }

}

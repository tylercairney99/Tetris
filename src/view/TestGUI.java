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


        final JPanel mainBoard = new MainBoard();
        final JPanel nextPieceBoard = new NextPieceBoard();
        final JPanel controlBoard = new ControlBoard();
        final JPanel scoreBoard = new ScoreBoard();

        JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(625,725);

        Menu menuBar = new Menu();
        frame.setJMenuBar(menuBar); // Here we add the menu bar to the frame

        frame.setVisible(true);
        frame.add(mainBoard);
        frame.add(nextPieceBoard);
        frame.add(controlBoard);
        frame.add(scoreBoard);

        mainBoard.add(mainBoardLabel);
        nextPieceBoard.add(nextPieceLabel);
        controlBoard.add(actionsLabel);
        scoreBoard.add(scoreLabel);

        nextPieceBoard.add(new NextPiece(TetrisPiece.getRandomPiece(), Color.GREEN));

    }

}

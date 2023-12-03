package view.Layout;

import static model.Board.PROPERTY_BOARD_CHANGES;
import static model.Board.PROPERTY_ROW_CLEARED;
import static model.Board.PROPERTY_NEXT_PIECE_CHANGES;
import static model.Board.PROPERTY_GAME_OVER;

import model.Board;
import model.TetrisPiece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static model.Board.PROPERTY_GAME_OVER;
import static model.Board.PROPERTY_NEXT_PIECE_CHANGES;


public class MainPanel extends JPanel implements PropertyChangeListener {
    private static int count = 0;
    private int timerCounter = 0; // DELETE LATER (ONLY USED FOR TESTING)!!!
    private static final int MILLIS_PER_SEC = 100; // CHANGE BACK TO 1000 (1 second per tick of myBoard.step)

    private JPanel mySecondaryPanel;
    private JPanel myGamePanel;
    private NextPiecePanel myNextPiecePanel;
    private JPanel myControlPanel;
    private JPanel myScorePanel;
    private Board myBoard;
    /**
     * Timer to manage game updates at regular intervals.
     */
    private Timer myGameTimer;

    public MainPanel(final Board theBoard, final Timer theGameTimer, final NextPiecePanel theNextPiecePanel, final GamePanel theGamePanel) {
        super();

        if (count > 0) {
            throw new IllegalArgumentException("Only one MainPanel allowed");
        }
        count++;

        this.myBoard = theBoard;
        this.myGameTimer = theGameTimer;
        this.myNextPiecePanel = theNextPiecePanel;
        this.myGamePanel = theGamePanel;
        buildComponents();
        layoutComponents();
        addListeners();

    }

    private void buildComponents() {
        mySecondaryPanel = new JPanel();
        //myGamePanel = new GamePanel(myBoard);
        //myNextPiecePanel = new NextPiecePanel();
        myControlPanel= new ControlPanel();
        myScorePanel = new ScorePanel();

        myGamePanel.setPreferredSize(new Dimension(200, 400));
        myNextPiecePanel.setPreferredSize(new Dimension(160, 160));
        myControlPanel.setPreferredSize(new Dimension(160, 110));
        myScorePanel.setPreferredSize(new Dimension(160, 110));
    }
    private void layoutComponents() {
        setLayout(new BorderLayout(5, 10));
        add(myGamePanel, BorderLayout.WEST);
        add(mySecondaryPanel, BorderLayout.EAST);

        mySecondaryPanel.setLayout(new BorderLayout(10, 10));
        mySecondaryPanel.add(myNextPiecePanel, BorderLayout.NORTH);
        mySecondaryPanel.add(myControlPanel, BorderLayout.CENTER);
        mySecondaryPanel.add(myScorePanel, BorderLayout.SOUTH);
    }
    private void addListeners() {
        myBoard.addPropertyChangeListener(theEvent -> {
            if (PROPERTY_GAME_OVER.equals(theEvent.getPropertyName()) && (Boolean) theEvent.getNewValue()) {
                myGameTimer.stop();
                JOptionPane.showMessageDialog(null, "Game Over U Suck!");
            } else if (PROPERTY_NEXT_PIECE_CHANGES.equals(theEvent.getPropertyName())) {
                final TetrisPiece nextPiece = (TetrisPiece) theEvent.getNewValue();
            }
        });
        addKeyListener(new ControlKeyListener());
        setFocusable(true);
        requestFocus();
    }

    class ControlKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(final KeyEvent theEvent) {

            if (myGameTimer.isRunning()) {
                if (theEvent.getKeyCode() == KeyEvent.VK_LEFT) {
                    myBoard.left();
                    System.out.println("left");
                } else if (theEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                    myBoard.right();
                    System.out.println("right");
                } else if (theEvent.getKeyCode() == KeyEvent.VK_DOWN) {
                    myBoard.down();
                    System.out.println("down");
                } else if (theEvent.getKeyCode() == KeyEvent.VK_Z) {
                    myBoard.rotateCCW();
                    System.out.println("rotate-counter-clockwise");
                } else if (theEvent.getKeyCode() == KeyEvent.VK_X) {
                    myBoard.rotateCW();
                    System.out.println("rotate-clockwise");
                } else if (theEvent.getKeyCode() == KeyEvent.VK_SPACE) {
                    myBoard.drop();
                    System.out.println("drop");
                }
            }
            if (theEvent.getKeyCode() == KeyEvent.VK_P) {
                if (myGameTimer.isRunning()) {
                    myGameTimer.stop();
                    System.out.println("pause");
                } else {
                    myGameTimer.start();
                    System.out.println("unpause");
                }
            }
        }
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
    }
}
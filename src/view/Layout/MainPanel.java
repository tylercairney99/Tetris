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

    public MainPanel(final Board theBoard) {
        super();
        buildComponents(theBoard);
        layoutComponents();
        setupGameTimer();
        addListeners();
    }

    private void buildComponents(final Board theBoard) {
        mySecondaryPanel = new JPanel();
        myGamePanel = new GamePanel();
        myNextPiecePanel = new NextPiecePanel();
        myControlPanel = new ControlPanel();
        myScorePanel = new ScorePanel();
        myBoard = theBoard;

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
            } else if (PROPERTY_NEXT_PIECE_CHANGES.equals(theEvent.getPropertyName())) {
                final TetrisPiece nextPiece = (TetrisPiece) theEvent.getNewValue();
                myNextPiecePanel.getNextTetrisPiece(nextPiece); // Update the NextPiecePanel
            }
        });
        addKeyListener(new ControlKeyListener());
        setFocusable(true);
        requestFocus();
    }

    /**
     * Sets up the game timer.
     * Initializes the timer to trigger every second.
     */
    private void setupGameTimer() {
        myGameTimer = new Timer(MILLIS_PER_SEC, theEvent -> {
            timerCounter++; // DELETE LATER (USED FOR TESTING)
            System.out.print(timerCounter + "\n"); // DELETE LATER (USED FOR TESTING)
            myBoard.step();
        });
    }

    class ControlKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(final KeyEvent theEvent) {

            if (theEvent.getKeyCode() == KeyEvent.VK_LEFT) {
                myBoard.left();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                myBoard.right();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_DOWN) {
                myBoard.down();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_Z) {
                myBoard.rotateCCW();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_X) {
                myBoard.rotateCW();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_SPACE) {
                myBoard.drop();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_P) {
                if (myGameTimer.isRunning()) {
                    myGameTimer.stop();
                } else {
                    myGameTimer.start();
                }
            }
        }
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
    }

    public NextPiecePanel getNextPiecePanel() {
        return myNextPiecePanel;
    }
}
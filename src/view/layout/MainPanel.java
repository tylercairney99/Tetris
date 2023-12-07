package view.layout;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.Board;
import model.TetrisPiece;

import static model.Board.*;

/**
 * A class representing the main panel for a Tetris game.
 *
 * @author Group 7
 * @version Autumn 2023
 */
public class MainPanel extends JPanel {

    /**
     * Ensures only one panel is instantiated.
     */
    private static int count;

    /**
     * The number of milliseconds in one second.
     * This constant defines the interval for the game's timer tick.
     */
    private static final int MILLIS_PER_SEC = 100; // CHANGE BACK TO 1000 (1 second per tick of myBoard.step)
    private int timerCounter = 0; // DELETE LATER (ONLY USED FOR TESTING)!!!

    /**
     * The Panel which contains the NextPiecePanel, ControlPanel, and ScorePanel.
     */
    private JPanel mySecondaryPanel;
    /**
     * The Panel which contains the game board.
     */
    private final GamePanel myGamePanel;
    /**
     * The Panel which contains the next piece.
     */
    private final NextPiecePanel myNextPiecePanel;
    /**
     * The Panel which contains the controls.
     */
    private JPanel myControlPanel;
    /**
     * The Panel which contains the score.
     */
    private JPanel myScorePanel;
    /**
     * The game board associated with this menu.
     * It represents the current state of the Tetris game, including the arrangement
     * of Tetris blocks and handling game logic.
     */
    private final Board myBoard;
    /**
     * Timer to manage game updates at regular intervals.
     */
    private final Timer myGameTimer;

    /**
     * Clip of music to be played.
     */
    private Clip myClip;


    /**
     * Constructs a MainPanel object.
     *
     * @param theBoard          theBoard
     * @param theGameTimer      theGameTimer
     * @param theNextPiecePanel theNextPiecePanel which has already been constructed
     * @param theGamePanel      theGamePanel which has already been constructed
     * @throws IllegalArgumentException if more than one ControlPanel is instantiated.
     */

    public MainPanel(final Board theBoard, final Timer theGameTimer,
                     final NextPiecePanel theNextPiecePanel, final GamePanel theGamePanel,
                     final File theMusicFile) {
        super();

        if (count > 0) {
            throw new IllegalArgumentException("Only one MainPanel allowed");
        }
        count++;

        this.myBoard = theBoard;  //Can we remove the this.s
        this.myGameTimer = theGameTimer;
        this.myNextPiecePanel = theNextPiecePanel;
        this.myGamePanel = theGamePanel;

        createMusic(theMusicFile);

        buildComponents();
        layoutComponents();
        addListeners();
    }

    /**
     * Initializes the components of the MainPanel.
     */
    private void buildComponents() {
        mySecondaryPanel = new JPanel();
        myControlPanel = new ControlPanel();
        myScorePanel = new ScorePanel(myGameTimer);

        myGamePanel.setPreferredSize(new Dimension(200, 400));
        myNextPiecePanel.setPreferredSize(new Dimension(160, 160));
        myControlPanel.setPreferredSize(new Dimension(160, 110));
        myScorePanel.setPreferredSize(new Dimension(160, 110));
    }

    /**
     * Lays out the components of the MainPanel.
     */
    private void layoutComponents() {
        setLayout(new BorderLayout(5, 10));
        add(myGamePanel, BorderLayout.WEST);
        add(mySecondaryPanel, BorderLayout.EAST);

        mySecondaryPanel.setLayout(new BorderLayout(10, 10));
        mySecondaryPanel.add(myNextPiecePanel, BorderLayout.NORTH);
        mySecondaryPanel.add(myControlPanel, BorderLayout.CENTER);
        mySecondaryPanel.add(myScorePanel, BorderLayout.SOUTH);
    }

    /**
     * Adds listeners to the MainPanel.
     */
    private void addListeners() {
        myBoard.addPropertyChangeListener(theEvent -> {
            if (PROPERTY_GAME_OVER.equals(theEvent.getPropertyName()) && (Boolean) theEvent.getNewValue()) {
                myGameTimer.stop();
                pauseMusic();

                JOptionPane.showMessageDialog(null, "Game Over U Suck!");
            } else if (PROPERTY_NEXT_PIECE_CHANGES.equals(theEvent.getPropertyName())) {
                final TetrisPiece nextPiece = (TetrisPiece) theEvent.getNewValue();
            }
            if (PROPERTY_NEW_GAME.equals(theEvent.getPropertyName())) {
                myClip.setMicrosecondPosition(0);
                myClip.start();
            }
        });
        addKeyListener(new ControlKeyListener());
        setFocusable(true);
        requestFocus();
    }

    class ControlKeyListener extends KeyAdapter {

        ControlKeyListener() {
            super();
        }

        @Override
        public void keyPressed(final KeyEvent theEvent) {
            if (myGameTimer.isRunning()) {
                if (theEvent.getKeyCode() == KeyEvent.VK_RIGHT || theEvent.getKeyCode() == KeyEvent.VK_D) {
                    myBoard.right();
                    System.out.println("right");
                } else if (theEvent.getKeyCode() == KeyEvent.VK_LEFT || theEvent.getKeyCode() == KeyEvent.VK_A) {
                    myBoard.left();
                    System.out.println("left");
                } else if (theEvent.getKeyCode() == KeyEvent.VK_DOWN || theEvent.getKeyCode() == KeyEvent.VK_S) {
                    myBoard.down();
                    System.out.println("down");
                } else if (theEvent.getKeyCode() == KeyEvent.VK_UP || theEvent.getKeyCode() == KeyEvent.VK_W) {
                    myBoard.rotateCW();
                    System.out.println("rotate");
                } else if (theEvent.getKeyCode() == KeyEvent.VK_SPACE) {
                    myBoard.drop();
                    System.out.println("drop");
                }
            }
            if (theEvent.getKeyCode() == KeyEvent.VK_P) {
                System.out.println("pause");
                if (myGameTimer.isRunning()) {
                    myGameTimer.stop();
                    pauseMusic();
                } else {
                    myGameTimer.start();
                    playMusic();
                }
            }
        }
    }

//    class ControlKeyListener extends KeyAdapter {
//
//        private final Map<Integer, Runnable> myKeyMappings;
//
//        ControlKeyListener() {
//            super();
//            myKeyMappings = new HashMap<>();
//            mapKeys();
//        }
//
//        private void mapKeys() {
//            myKeyMappings.put(KeyEvent.VK_RIGHT, myBoard::right);
//            myKeyMappings.put(KeyEvent.VK_D, myBoard::right);
//            myKeyMappings.put(KeyEvent.VK_LEFT, myBoard::left);
//            myKeyMappings.put(KeyEvent.VK_A, myBoard::left);
//            myKeyMappings.put(KeyEvent.VK_DOWN, myBoard::down);
//            myKeyMappings.put(KeyEvent.VK_S, myBoard::down);
//            myKeyMappings.put(KeyEvent.VK_UP, myBoard::rotateCW);
//            myKeyMappings.put(KeyEvent.VK_W, myBoard::rotateCW);
//            myKeyMappings.put(KeyEvent.VK_SPACE, myBoard::drop);
//            myKeyMappings.put(KeyEvent.VK_P, () -> {
//                if (myGameTimer.isRunning()) {
//                    myGameTimer.stop();
//                    pauseMusic();
//                } else {
//                    myGameTimer.start();
//                    playMusic();
//                }
//            });
//        }
//
//        @Override
//        public void keyPressed(final KeyEvent theEvent) {
//            if (myKeyMappings.containsKey(theEvent.getKeyCode()) && myGameTimer.isRunning()) {
//                myKeyMappings.get(theEvent.getKeyCode()).run();
//            } else if (theEvent.getKeyCode() == KeyEvent.VK_P) {
//                myKeyMappings.get(theEvent.getKeyCode()).run();
//            }
//        }
//     }

    private void createMusic(final File theMusicFile) {
        try {
            final AudioInputStream audioInput = AudioSystem.getAudioInputStream(theMusicFile);
            myClip = AudioSystem.getClip();
            myClip.open(audioInput);
        } catch (final Exception e) {
            System.out.println(e);
        }
    }

    private void playMusic() {
        myClip.start();
        System.out.println("music play");
    }

    private void pauseMusic() {
        myClip.stop();
    }
}
package view.layout;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
     * Music-
     * Pixel Story by Roa Music | <a href="https://soundcloud.com/roa_music1031">...</a>
     * Music promoted by <a href="https://www.free-stock-music.com">...</a>
     * Creative Commons / Attribution 3.0 Unported License (CC BY 3.0)
     * <a href="https://creativecommons.org/licenses/by/3.0/deed.en_US">...</a>
     */
    private Clip myMusicClip;

    /**
     * Clip of sound effect to be played.
     */
    private Clip mySoundClip;

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
                     final ScorePanel theScorePanel, final File theMusicFile, 
                     final File theSoundFile) {
        super();

        if (count > 0) {
            throw new IllegalArgumentException("Only one MainPanel allowed");
        }
        count++;

        this.myBoard = theBoard;  //Can we remove the this.s
        this.myGameTimer = theGameTimer;
        this.myNextPiecePanel = theNextPiecePanel;
        this.myGamePanel = theGamePanel;
        this.myScorePanel = theScorePanel;

        createMusic(theMusicFile);

        createMusic(theMusicFile, theSoundFile);
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

                JOptionPane.showMessageDialog(null,
                        "              Game Over!");
            } else if (PROPERTY_NEXT_PIECE_CHANGES.equals(theEvent.getPropertyName())) {
                final TetrisPiece nextPiece = (TetrisPiece) theEvent.getNewValue();
            }
            if (PROPERTY_NEW_GAME.equals(theEvent.getPropertyName())) {
                myMusicClip.setMicrosecondPosition(0);
                myMusicClip.start();
            }
            if (PROPERTY_ROW_CLEARED.equals(theEvent.getPropertyName())) {
                mySoundClip.setMicrosecondPosition(0);
                mySoundClip.start();
            }
        });
        addKeyListener(new ControlKeyListener());
        setFocusable(true);
        requestFocus();
    }

    /**
     * Creates audio clip from .wav audio file.
     * @param theMusicFile .wav audio file to turn into clip.
     * @param theSoundFile .wav audio file to turn into clip.
     */
    private void createMusic(final File theMusicFile, final File theSoundFile) {
        try {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(theMusicFile);
            myMusicClip = AudioSystem.getClip();
            myMusicClip.open(audioInput);
            audioInput = AudioSystem.getAudioInputStream(theSoundFile);
            mySoundClip = AudioSystem.getClip();
            mySoundClip.open(audioInput);
        } catch (final Exception e) {
            System.out.println(e);
        }

    }

    /**
     * Starts music.
     */
    private void playMusic() {
        myMusicClip.start();
        System.out.println("music play");
    }

    /**
     * Pauses music playing
     */
    private void pauseMusic() {
        myMusicClip.stop();
    }

    class ControlKeyListener extends KeyAdapter {

        ControlKeyListener() {
            super();
        }

        @Override
        public void keyPressed(final KeyEvent theEvent) {
            if (myGameTimer.isRunning()) {
                switch (theEvent.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D:
                        myBoard.right();
                        System.out.println("right");
                        break;
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_A:
                        myBoard.left();
                        System.out.println("left");
                        break;
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
                        myBoard.down();
                        myGamePanel.repaint();
                        System.out.println("down");
                        myGameTimer.restart();
                        break;
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_W:
                        myBoard.rotateCW();
                        System.out.println("rotate");
                        break;
                    case KeyEvent.VK_SPACE:
                        myBoard.drop();
                        System.out.println("drop");
                        myGamePanel.repaint();
                        break;
                    default:
                        break;
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
}
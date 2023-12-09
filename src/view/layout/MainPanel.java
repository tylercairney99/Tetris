package view.layout;

import static model.Board.PROPERTY_GAME_OVER;
import static model.Board.PROPERTY_NEW_GAME;
import static model.Board.PROPERTY_NEXT_PIECE_CHANGES;
import static model.Board.PROPERTY_ROW_CLEARED;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.Board;
import model.TetrisPiece;
import view.controller.TetrisGUI;



/**
 * A class representing the main panel for a Tetris game.
 *
 * @author Group 7
 * @version Autumn 2023
 */
public class MainPanel extends JPanel {

    /**
     * Constant width of panel.
     */
    private static final int WIDTH1 = 200;

    /**
     * Constant height of panel.
     */
    private static final int HEIGHT1 = 400;

    /**
     * Constant width of panel.
     */
    private static final int WIDTH2 = 160;

    /**
     * Constant height of panel.
     */
    private static final int HEIGHT2 = 110;

    /**
     * Constant used for gap.
     */
    private static final int GAP = 10;

    /**
     * Ensures only one panel is instantiated.
     */
    private static int count;

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
    private final JPanel myScorePanel;
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
     * Current difficulty level of the game.
     */
    private int myCurrentDifficulty;

    /**
     * GUI to display game.
     */
    private final TetrisGUI myTetrisGUI;

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
                     final ScorePanel theScorePanel, final List<File> theSoundList,
                     final int theCurrentDifficulty, final TetrisGUI theTetrisGUI) {
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
        this.myTetrisGUI = theTetrisGUI;
        constructorHelper(theSoundList, theCurrentDifficulty);
    }

    private void constructorHelper(final List<File> theSoundList,
                                   final int theCurrentDifficulty) {
        buildComponents();
        layoutComponents();
        addListeners();
        createMusic(theSoundList);
        myCurrentDifficulty = theCurrentDifficulty;
    }

    /**
     * Initializes the components of the MainPanel.
     */
    @SuppressWarnings("SuspiciousNameCombination")
    /*
    Too many constants to add another Height when matching Width exists.
     */
    private void buildComponents() {
        mySecondaryPanel = new JPanel();
        myControlPanel = new ControlPanel();

        myGamePanel.setPreferredSize(new Dimension(WIDTH1, HEIGHT1));
        myNextPiecePanel.setPreferredSize(new Dimension(WIDTH2, WIDTH2));
        myControlPanel.setPreferredSize(new Dimension(WIDTH2, HEIGHT2));
        myScorePanel.setPreferredSize(new Dimension(WIDTH2, HEIGHT2));
    }

    /**
     * Lays out the components of the MainPanel.
     */
    private void layoutComponents() {
        setLayout(new BorderLayout(GAP / 2, GAP));
        add(myGamePanel, BorderLayout.WEST);
        add(mySecondaryPanel, BorderLayout.EAST);

        mySecondaryPanel.setLayout(new BorderLayout(GAP, GAP));
        mySecondaryPanel.add(myNextPiecePanel, BorderLayout.NORTH);
        mySecondaryPanel.add(myControlPanel, BorderLayout.CENTER);
        mySecondaryPanel.add(myScorePanel, BorderLayout.SOUTH);
    }

    /**
     * Adds listeners to the MainPanel.
     */
    private void addListeners() {
        myBoard.addPropertyChangeListener(this::propertyChange);
        addKeyListener(new ControlKeyListener());
        setFocusable(true);
        requestFocus();
    }

    /**
     * Creates audio clip from .wav audio file.
     * @param theSoundFile File containting .wav audio files to turn into clips.
     */
    private void createMusic(final List<File> theSoundFile) {
        final File musicFile = theSoundFile.get(0);
        final File soundFile = theSoundFile.get(1);
        try {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            myMusicClip = AudioSystem.getClip();
            myMusicClip.open(audioInput);
            audioInput = AudioSystem.getAudioInputStream(soundFile);
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
    }

    /**
     * Pauses music playing
     */
    private void pauseMusic() {
        myMusicClip.stop();
    }

    private void propertyChange(final PropertyChangeEvent theEvent) {
        if (PROPERTY_GAME_OVER.equals(theEvent.getPropertyName())
                && (Boolean) theEvent.getNewValue()) {
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
            if ((int) theEvent.getNewValue() > 0) {
                mySoundClip.setMicrosecondPosition(0);
                mySoundClip.start();
            }
        }
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
                        break;
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_A:
                        myBoard.left();
                        break;
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
                        myBoard.down();
                        myGamePanel.repaint();

                        if ("Hard".equals(myTetrisGUI.getCurrentDifficulty())) {
                            myBoard.step();
                        } else {
                            myGameTimer.restart();
                        }
                        break;
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_W:
                        myBoard.rotateCW();
                        break;
                    case KeyEvent.VK_SPACE:
                        myBoard.drop();
                        myGamePanel.repaint();
                        break;
                    default:
                        break;
                }
            }
            if (theEvent.getKeyCode() == KeyEvent.VK_P) {
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
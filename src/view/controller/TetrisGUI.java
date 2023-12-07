package view.controller;

import java.awt.*;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import model.Board;
import model.MyDifficultyChanger;
import view.layout.GamePanel;
import view.layout.MainPanel;
import view.layout.NextPiecePanel;
import view.menu.Menu;


/**
 * This class represents the graphical user interface for the Tetris game.
 * It handles the initialization and display of the game window, including setting up
 * the game board, menu, timer, and various GUI components.
 *
 * @author Group 7
 * @version 1.0.2
 *
 */
public class TetrisGUI implements MyDifficultyChanger {

    /**
     * Sets the mode to easy difficulty (base difficulty).
     */
    public static final int EASY_DIFFICULTY = 1000;

    /**
     * Sets the mode to medium difficulty.
     */
    public static final int MEDIUM_DIFFICULTY = 500;

    /**
     * Sets the mode to hard difficulty.
     */
    public static final int HARD_DIFFICULTY = 100;
    /**
     * The height of the border around the game window.
     */
    private static final int BORDER_SIZE_HEIGHT = 10;

    /**
     * The width of the border around the game window.
     */
    private static final int BORDER_SIZE_WIDTH = 10;

    /**
     * The current difficulty set to easy originally.
     */
    private int myCurrentDifficulty = EASY_DIFFICULTY;

    private int timerCounter = 0; // DELETE LATER (ONLY USED FOR TESTING)!!!

    /**
     * The primary model object representing the Tetris game board.
     */
    private final Board myBoard;

    /**
     * next piece panel object representing the panel where the next piece is shown.
     */
    private final NextPiecePanel myNextPiecePanel;

    /**
     * game panel object representing the panel where game is played.
     */
    private final GamePanel myGamePanel;

    /**
     * Timer to manage game updates at regular intervals.
     */
    private final Timer myGameTimer;

    /**
     * Location of music in the file.
     */
    private final File myMusicFile;

    //private final TetrisGUI myTetrisGUI;

    /**
     * Constructs a new TetrisGUI object.
     * Initializes the game board, sets up GUI components, and adds necessary listeners.
     */
    public TetrisGUI() {
        super();

        myBoard = new Board();
        myGamePanel = new GamePanel(myBoard);
        myNextPiecePanel = new NextPiecePanel(myBoard);
        //myGamePanel = new GamePanel(myBoard);

        myMusicFile = new File("src/music/music.wav");


        myBoard.addPropertyChangeListener(myNextPiecePanel);
        myBoard.addPropertyChangeListener(myGamePanel);

        myGameTimer = new Timer(EASY_DIFFICULTY, theEvent -> {
            timerCounter++; // DELETE LATER (USED FOR TESTING)
            System.out.print(timerCounter + "\n"); // DELETE LATER (USED FOR TESTING)
            myBoard.step();
            //myGamePanel.repaint();
        });

        setUpComponents();
    }

    /**
     * Sets up the main components of the GUI.
     * Initializes the frame, sets up the layout, adds a menu bar, main panel, and borders.
     */
    private void setUpComponents() {
        final JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        final MainPanel mainPanel = new MainPanel(myBoard, myGameTimer,
                myNextPiecePanel, myGamePanel, myMusicFile);

        frame.setJMenuBar(new Menu(myBoard, myGameTimer, this));
        frame.add(mainPanel, BorderLayout.CENTER);

        addBorders(frame);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /**
     * Adds border panels to the frame.
     * @param theFrame The JFrame to which the borders are added.
     */
    private void addBorders(final JFrame theFrame) {
        final JPanel left = new JPanel();
        final JPanel right = new JPanel();
        final JPanel top = new JPanel();
        final JPanel bottom = new JPanel();

        left.setPreferredSize(new Dimension(BORDER_SIZE_WIDTH, BORDER_SIZE_HEIGHT));
        right.setPreferredSize(new Dimension(BORDER_SIZE_WIDTH, BORDER_SIZE_HEIGHT));
        top.setPreferredSize(new Dimension(BORDER_SIZE_WIDTH, BORDER_SIZE_HEIGHT));
        bottom.setPreferredSize(new Dimension(BORDER_SIZE_WIDTH, BORDER_SIZE_HEIGHT));

        theFrame.add(left, BorderLayout.WEST);
        theFrame.add(right, BorderLayout.EAST);
        theFrame.add(top, BorderLayout.NORTH);
        theFrame.add(bottom, BorderLayout.SOUTH);
    }

    /**
     * This method sets the new difficulty for the game,
     * adjusts the game timer's delay accordingly, restarts
     * the game board to apply the new difficulty, and restarts the game timer.
     *
     * @param theNewDifficulty The new difficulty level to set.
     *                      This should be one of the predefined constants: EASY_DIFFICULTY,
     *                      MEDIUM_DIFFICULTY, or HARD_DIFFICULTY.
     */
    @Override
    public void changeDifficulty(final int theNewDifficulty) {
        if (myCurrentDifficulty != theNewDifficulty) {
            myCurrentDifficulty = theNewDifficulty;
            myGameTimer.setDelay(theNewDifficulty);

        }
        //myBoard.newGame();
        JOptionPane.showMessageDialog(null, "Current Difficulty: " + getCurrentDifficulty());
    }

    /**
     * This method returns a string representation of the current difficulty level.
     *
     * @return Easy / Medium / Hard / if none are chosen defaults to easy.
     */
    @Override
    public String getCurrentDifficulty() {
        return switch (myCurrentDifficulty) {
            case EASY_DIFFICULTY -> "Easy";
            case MEDIUM_DIFFICULTY -> "Medium";
            case HARD_DIFFICULTY -> "Hard";
            default -> "Default difficulty is Easy";
        };
    }


    /**
     * The main method to run the Tetris GUI.
     * @param theArgs Command line arguments (not used).
     */
    public static void main(final String[] theArgs) {
        SwingUtilities.invokeLater(TetrisGUI::new);
    }
}

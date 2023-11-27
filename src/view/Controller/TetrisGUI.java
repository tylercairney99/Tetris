package view.Controller;

import static model.Board.PROPERTY_GAME_OVER;
import view.Layout.MainPanel;
import view.Menu.Menu;
import model.Board;
import model.MyBoard;
import javax.swing.*;
import java.awt.*;

/**
 * This class represents the graphical user interface for the Tetris game.
 * It handles the initialization and display of the game window, including setting up
 * the game board, menu, timer, and various GUI components.
 *
 * @author Group 7
 * @version 1.0.2
 *
 */
public class TetrisGUI {

    /**
     * The primary model object representing the Tetris game board.
     */
    private final MyBoard myBoard;

    /**
     * Timer to manage game updates at regular intervals.
     */
    private Timer myGameTimer;

    /**
     * The height of the border around the game window.
     */
    private static final int BORDER_SIZE_HEIGHT = 10;

    /**
     * The width of the border around the game window.
     */
    private static final int BORDER_SIZE_WIDTH = 10;

    /**
     * The number of milliseconds in one second.
     * This constant defines the interval for the game's timer tick.
     */
    private static final int MILLIS_PER_SEC = 1000;

    /**
     * Constructs a new TetrisGUI object.
     * Initializes the game board, sets up GUI components, and adds necessary listeners.
     */
    public TetrisGUI() {
        super();
        myBoard = new Board();
        setUpComponents();
        addListeners();
    }

    /**
     * Sets up the main components of the GUI.
     * Initializes the frame, sets up the layout, adds a menu bar, main panel, and borders.
     */
    private void setUpComponents() {
        final JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.setJMenuBar(new Menu());
        frame.add(new MainPanel(), BorderLayout.CENTER);
        addBorders(frame);

        frame.pack();
        frame.setLocationRelativeTo(null); // centers GUI on screen when ran
        setupGameTimer();
        frame.setVisible(true);
    }

    /**
     * Adds action listeners to components.
     * This includes the game timer and property change listener for the game board.
     */
    private void addListeners() {
        myGameTimer.addActionListener(e -> myBoard.step());

        myBoard.addPropertyChangeListener(evt -> {
            if (PROPERTY_GAME_OVER.equals(evt.getPropertyName()) && (Boolean) evt.getNewValue()) {
                myGameTimer.stop();
            }
        });
    }

    /**
     * Sets up the game timer.
     * Initializes the timer to trigger every second.
     */
    private void setupGameTimer() {
        myGameTimer = new Timer(MILLIS_PER_SEC, null);
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
     * The main method to run the Tetris GUI.
     * @param theArgs Command line arguments (not used).
     */
    public static void main(final String[] theArgs) {
        SwingUtilities.invokeLater(TetrisGUI::new);
    }
}

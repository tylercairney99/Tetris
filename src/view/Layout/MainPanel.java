package view.Layout;

import model.Board;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;

/**
 *
 *
 * @author James
 * @author Josh
 * @author Tyler
 * @author Cam
 * @version 2.0
 */
public class MainPanel extends JPanel implements PropertyChangeListener {

    /**
     *
     */
    private JPanel mySecondaryPanel;

    /**
     *
     */
    private JPanel myGamePanel;

    /**
     * Shows next tetromino to be played.
     */
    private NextPiecePanel myNextPiecePanel;

    /**
     * Displays game controls.
     */
    private JPanel myControlPanel;

    /**
     * Shows the player's score.
     */
    private JPanel myScorePanel;

    /**
     *
     */
    private final Board myBoard;

    /**
     * Calls methods to build game.
     *
     * @param theBoard ???
     */
    public MainPanel(final Board theBoard) {
        super();
        buildComponents();
        layoutComponents();
        addListeners();

        myBoard = theBoard;
    }

    /**
     *
     */
    private void buildComponents() {
        mySecondaryPanel = new JPanel();
        myGamePanel = new GamePanel();
        myNextPiecePanel = new NextPiecePanel();
        myControlPanel = new ControlPanel();
        myScorePanel = new ScorePanel();

        myGamePanel.setPreferredSize(new Dimension(200, 400));
        myNextPiecePanel.setPreferredSize(new Dimension(160, 160));
        myControlPanel.setPreferredSize(new Dimension(160, 110));
        myScorePanel.setPreferredSize(new Dimension(160, 110));
    }

    /**
     *
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
     *
     */
    private void addListeners() {
        addKeyListener(new ControlKeyListener());
        setFocusable(true);
        requestFocus();
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
    }

    /**
     * ???
     *
     * @return Next piece panel displays next tetromino to be played.
     */
    public NextPiecePanel getNextPiecePanel() {
        return myNextPiecePanel;
    }

    /**
     * Creates key press events.
     */
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
                //pause timer here
            }
        }
    }
}
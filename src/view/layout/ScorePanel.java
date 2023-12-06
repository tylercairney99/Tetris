package view.layout;

import static model.Board.PROPERTY_ROW_CLEARED;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;


/**
 * Panel to display player's score.
 * Implements PCL to update score.
 *
 * @author James
 * @author Tyler
 * @author Josh
 * @author Cam
 * @version 3.0
 */
public final class ScorePanel extends JPanel implements PropertyChangeListener {

    /**
     * x coord of score text.
     */
    private static final int TEXT_X = 15;

    /**
     * y coord of score text.
     */
    private static final int TEXT_Y = 55;

    /**
     * Size of score text.
     */
    private static final int TEXT_SIZE = 20;

    /**
     * Score gained for each row cleared.
     */
    private static final int SCORE_GAIN = 10;

    /**
     * Ensures only one panel is instantiated.
     */
    private static int count;

    /**
     * Score of the player. Updates when rows are cleared.
     */
    private int myScore;

    /**
     * Number of lines cleared by the player.
     */
    private int myLinesCleared;

    /**
     * The level of the game.
     */
    private int myLevel;
    /**
     * Score gained for one row cleared.
     */
    private final int ONE_LINE_CLEARED = 40;
    /**
     * Score gained for two rows cleared.
     */
    private final int TWO_LINES_CLEARED = 100;
    /**
     * Score gained for three rows cleared.
     */
    private final int THREE_LINES_CLEARED = 300;
    /**
     * Score gained for four rows cleared.
     */
    private final int FOUR_LINES_CLEARED = 1200;


    /**
     * Panel used to display the player's score.
     * Sets background color.
     *
     * @throws IllegalArgumentException if more than one ScorePanel is instantiated.
     */
    public ScorePanel() {
        super();
        myScore = 0;
        myLinesCleared = 0;
        myLevel = 1;

        if (count > 0) {
            throw new IllegalArgumentException("Only one ScorePanel allowed");
        }
        count++;

        setBackground(Color.GREEN);
        setLayout(new BorderLayout());
        final JPanel scorePanel = new JPanel(new FlowLayout());
        scorePanel.setOpaque(false);
    }

    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        theGraphics.setFont(new Font("" + theGraphics.getFont(), Font.PLAIN, TEXT_SIZE));
        showScore(theGraphics);
    }

    /**
     * Displays player's score in score panel.
     *
     * @param theGraphics Graphics object used to display text.
     */
    private void showScore(final Graphics theGraphics) {
        theGraphics.drawString("Score: " + myScore, TEXT_X, TEXT_Y);
    }

    private void calculateScore() {
        int score = 0;
        switch (myLinesCleared) {
            case 1:
                score = ONE_LINE_CLEARED;
                break;
            case 2:
                score = TWO_LINES_CLEARED;
                break;
            case 3:
                score = THREE_LINES_CLEARED;
                break;
            case 4:
                score = FOUR_LINES_CLEARED;
                break;
            default: //this should never happen
                break;
        }
        myScore += score * myLevel;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (PROPERTY_ROW_CLEARED.equals(theEvent.getPropertyName())) {
            //TODO need to update the level and the number of lines cleared here?
            calculateScore();
            repaint();
        }
    }
}
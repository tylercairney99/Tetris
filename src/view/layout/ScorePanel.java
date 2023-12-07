package view.layout;

import static model.Board.PROPERTY_NUMBER_OF_ROWS_CLEARED;
import static model.Board.PROPERTY_ROW_CLEARED;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;


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
     * Score gained for one row cleared.
     */
    private static final int ONE_LINE_CLEARED = 40;
    /**
     * Score gained for two rows cleared.
     */
    private static final int TWO_LINES_CLEARED = 100;
    /**
     * Score gained for three rows cleared.
     */
    private static final int THREE_LINES_CLEARED = 300;
    /**
     * Score gained for four rows cleared.
     */
    private static final int FOUR_LINES_CLEARED = 1200;

    /**
     * Level up every 5 rows cleared.
     */
    private static final int LEVEL_UP = 5;

    private static final int LEVEL_UP_TIMER_CHANGE = 2;

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
    private final int myLinesCleared;

    /**
     * The level of the game.
     */
    private int myLevel;

    /**
     * Timer to manage game updates at regular intervals.
     */
    private final Timer myGameTimer;

    /**
     * Panel used to display the player's score.
     * Sets background color.
     *
     * @throws IllegalArgumentException if more than one ScorePanel is instantiated.
     */
    public ScorePanel(final Timer theGameTimer) {
        super();
        myScore = 0;
        myLinesCleared = 0;
        myLevel = 1;
        this.myGameTimer = theGameTimer;

        if (count > 0) {
            throw new IllegalArgumentException("Only one ScorePanel allowed");
        }
        count++;

        setBackground(Color.ORANGE);
        setLayout(new BorderLayout());
        final JPanel scorePanel = new JPanel(new FlowLayout());
        scorePanel.setOpaque(false);
    }

    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        final int w = getWidth();
        final int h = getHeight();
        final Color color1 = Color.YELLOW;
        final Color color2 = Color.CYAN;
        final GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

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

    /**
     * Updates the score and timer when a 5 rows are cleared.
     */
    private void calculateLevel() {
        if (myLinesCleared % LEVEL_UP == 0) {
            myLevel++;
            myGameTimer.setDelay(myGameTimer.getDelay() / LEVEL_UP_TIMER_CHANGE);
        }
    }

    /**
     * Updates the score when a row is cleared.
     */
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
            calculateLevel();
            calculateScore();
            repaint();
        }
    }
//    @Override
//    public void propertyChange(final PropertyChangeEvent theEvent) {
//        if (PROPERTY_NUMBER_OF_ROWS_CLEARED.equals(theEvent.)) {
//            calculateLevel();
//            calculateScore();
//            repaint();
//        }
//    }
}
package view.layout;

import javax.swing.*;
import java.awt.*;

/**
 * Score panel shows the score of the game.
 */
public final class ScorePanel extends JPanel {
    /**
     * static int shared amongst all score panel objects to ensure that
     * there is only ever one instance.
     */
    private static int count;
    /**
     * The number of rows in the score panel.
     */
    private static final int ROWS = 7;
    /**
     * The number of columns in the score panel.
     */
    private static final int COLS = 2;

    /**
     * Constructs a ScorePanel object.
     *
     * @throws IllegalArgumentException if more than one ScorePanel is instantiated.
     */
    public ScorePanel() {
        super();

        if (count > 0) {
            throw new IllegalArgumentException("Only one ScorePanel allowed");
        }
        count++;

        setBackground(Color.GREEN);
        setLayout(new BorderLayout());

        final JPanel scorePanel = new JPanel(new GridLayout(ROWS,COLS));

        scorePanel.setOpaque(false);
        scorePanel.add(new JLabel("Score:" + 0));
        add(scorePanel, BorderLayout.PAGE_START);
    }
}

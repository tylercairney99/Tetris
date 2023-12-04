package view.layout;

import javax.swing.*;
import java.awt.*;

public final class ScorePanel extends JPanel {
    private static int count = 0;
    public ScorePanel() {
        super();

        if (count > 0) {
            throw new IllegalArgumentException("Only one ScorePanel allowed");
        }
        count++;

        setBackground(Color.GREEN);
        setLayout(new BorderLayout());

        JPanel scorePanel = new JPanel(new GridLayout(7,2));

        scorePanel.setOpaque(false);
        scorePanel.add(new JLabel("Score:" + 0));
        add(scorePanel, BorderLayout.PAGE_START);
    }
}

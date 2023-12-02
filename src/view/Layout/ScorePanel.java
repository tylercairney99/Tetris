package view.Layout;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
    private static int count = 0;
    public ScorePanel() {
        super();

        if (count > 0) {
            throw new IllegalArgumentException("Only one ScorePanel allowed");
        }
        count++;

        setBackground(Color.GREEN);

    }
}

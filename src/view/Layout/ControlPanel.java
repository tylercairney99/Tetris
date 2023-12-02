package view.Layout;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private static int count = 0;
    public ControlPanel() {
        super();

        if (count > 0) {
            throw new IllegalArgumentException("Only one ControlPanel allowed");
        }
        count++;

        setBackground(Color.GREEN);
    }
}

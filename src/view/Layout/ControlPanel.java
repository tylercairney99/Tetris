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

        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel(new GridLayout(7,2));
        controlPanel.setOpaque(false);
        controlPanel.add(new JLabel("Move Left:"));
        controlPanel.add(new JLabel("left arrow"));
        controlPanel.add(new JLabel("Move Right"));
        controlPanel.add(new JLabel("right arrow"));
        controlPanel.add(new JLabel("Move Down"));
        controlPanel.add(new JLabel("down arrow"));
        controlPanel.add(new JLabel("Rotate CCW:"));
        controlPanel.add(new JLabel("Z"));
        controlPanel.add(new JLabel("Rotate CW:"));
        controlPanel.add(new JLabel("X"));
        controlPanel.add(new JLabel("Drop:"));
        controlPanel.add(new JLabel("space"));
        controlPanel.add(new JLabel("Pause:"));
        controlPanel.add(new JLabel("P"));

        add(controlPanel, BorderLayout.PAGE_START);
    }
}

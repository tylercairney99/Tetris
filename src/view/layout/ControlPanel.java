package view.layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Control panel shows the controls for the game.
 *
 * @author Group 7
 * @version Autumn 2023
 */
public class ControlPanel extends JPanel {
    /**
     * static int shared amongst all control panel objects to ensure that
     * there is only ever one instance.
     */
    private static int count;

    /**
     * Constructs a ControlPanel object.
     *
     * @throws IllegalArgumentException if more than one ControlPanel is instantiated.
     */
    public ControlPanel() {
        super();

        if (count > 0) {
            throw new IllegalArgumentException("Only one ControlPanel allowed");
        }
        count++;
        setUpPanel();
    }

    /**
     * Sets up the labels for the control panel.
     */
    private void setUpPanel() {
        setBackground(Color.GREEN);

        setLayout(new BorderLayout());

        final JPanel controlPanel = new JPanel(new GridLayout(7, 2));
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

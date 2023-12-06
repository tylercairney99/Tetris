package view.layout;

import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.font.TextAttribute;

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

        final JPanel leftPanel = new JPanel(new GridLayout(7, 1));
        final JPanel rightPanel = new JPanel(new GridLayout(7,1));
        final JLabel controlLabel = new JLabel("<HTML><U>Controls:</U></HTML>");

        add (controlLabel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        leftPanel.setPreferredSize(new Dimension(50, 100));
        rightPanel.setPreferredSize(new Dimension(110, 100));
        leftPanel.setOpaque(false);
        rightPanel.setOpaque(false);

        leftPanel.add(new JLabel(" Left:"));
        leftPanel.add(new JLabel(" Right:"));
        leftPanel.add(new JLabel(" Down:"));
        leftPanel.add(new JLabel(" Rotate:"));
        leftPanel.add(new JLabel(" Drop:"));
        leftPanel.add(new JLabel(" Pause:"));

        rightPanel.add(new JLabel("left arrow / A"));
        rightPanel.add(new JLabel("right arrow / D"));
        rightPanel.add(new JLabel("down arrow / S"));
        rightPanel.add(new JLabel("up arrow / W"));
        rightPanel.add(new JLabel("space"));
        rightPanel.add(new JLabel("P"));

        //add(controlPanel, BorderLayout.PAGE_START);
    }
}

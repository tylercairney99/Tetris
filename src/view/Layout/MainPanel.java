package view.Layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainPanel extends JPanel implements PropertyChangeListener {

    private JPanel mySecondaryPanel;
    private JPanel myGamePanel;
    private JPanel myNextPiecePanel;
    private JPanel myControlPanel;
    private JPanel myScorePanel;

    public MainPanel() {
        super();
        buildComponents();
        layoutComponents();
        addListeners();
    }

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
    private void layoutComponents() {
        setLayout(new BorderLayout(5, 10));
        add(myGamePanel, BorderLayout.WEST);
        add(mySecondaryPanel, BorderLayout.EAST);

        mySecondaryPanel.setLayout(new BorderLayout(10, 10));
        mySecondaryPanel.add(myNextPiecePanel, BorderLayout.NORTH);
        mySecondaryPanel.add(myControlPanel, BorderLayout.CENTER);
        mySecondaryPanel.add(myScorePanel, BorderLayout.SOUTH);
    }
    private void addListeners() {
        addKeyListener(new ControlKeyListener());
        setFocusable(true);
        requestFocus();
    }

    class ControlKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(final KeyEvent theEvent) {
            if (theEvent.getKeyCode() == KeyEvent.VK_LEFT) {
                System.out.println("left");
            } else if (theEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                System.out.println("right");
            } else if (theEvent.getKeyCode() == KeyEvent.VK_DOWN) {
                System.out.println("down");
            } else if (theEvent.getKeyCode() == KeyEvent.VK_Z) {
                System.out.println("z");
            } else if (theEvent.getKeyCode() == KeyEvent.VK_X) {
                System.out.println("x");
            } else if (theEvent.getKeyCode() == KeyEvent.VK_SPACE) {
                System.out.println("space");
            }
        }
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
    }
}
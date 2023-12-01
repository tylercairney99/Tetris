package view.Layout;

import model.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainPanel extends JPanel implements PropertyChangeListener {

    private JPanel mySecondaryPanel;
    private JPanel myGamePanel;
    private NextPiecePanel myNextPiecePanel;

    private JPanel myControlPanel;
    private JPanel myScorePanel;
    private Board myBoard;

    public MainPanel(final Board theBoard) {
        super();
        buildComponents(theBoard);
        layoutComponents();
        addListeners();
    }

    private void buildComponents(final Board theBoard) {
        mySecondaryPanel = new JPanel();
        myGamePanel = new GamePanel();
        myNextPiecePanel = new NextPiecePanel();
        myControlPanel = new ControlPanel();
        myScorePanel = new ScorePanel();
        myBoard = theBoard;

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
                myBoard.left();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                myBoard.right();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_DOWN) {
                myBoard.down();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_Z) {
                myBoard.rotateCCW();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_X) {
                myBoard.rotateCW();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_SPACE) {
                myBoard.drop();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_P) {
                //pause
            }
        }
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
    }

    public NextPiecePanel getNextPiecePanel() {
        return myNextPiecePanel;
    }
}
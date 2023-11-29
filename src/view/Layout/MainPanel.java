package view.Layout;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private JPanel mySecondaryPanel;
    private JPanel myGamePanel;
    private JPanel myNextPiecePanel;
    private JPanel myControlPanel;
    private JPanel myScorePanel;

    public MainPanel() {
        super();
        buildComponents();
        layoutComponents();
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
}
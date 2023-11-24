package view.Layout;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private JPanel mySecondary;
    private JPanel myGame;
    private JPanel myNextPiece;
    private JPanel myControl;
    private JPanel myScore;

    public MainPanel() {
        super();
        buildComponents();
        layoutComponents();
    }

    private void buildComponents() {
        mySecondary = new JPanel();
        myGame = new GamePanel();
        myNextPiece = new NextPiecePanel();
        myControl = new ControlPanel();
        myScore = new ScorePanel();

        myGame.setPreferredSize(new Dimension(200, 400));
        myNextPiece.setPreferredSize(new Dimension(160, 160));
        myControl.setPreferredSize(new Dimension(160, 110));
        myScore.setPreferredSize(new Dimension(160, 110));
    }
    private void layoutComponents() {
        setLayout(new BorderLayout(5, 10));
        add(myGame, BorderLayout.WEST);
        add(mySecondary, BorderLayout.EAST);

        mySecondary.setLayout(new BorderLayout(10, 10));
        mySecondary.add(myNextPiece, BorderLayout.NORTH);
        mySecondary.add(myControl, BorderLayout.CENTER);
        mySecondary.add(myScore, BorderLayout.SOUTH);
    }
}
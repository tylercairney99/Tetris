package view;

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
    }

    private void layoutComponents() {
        setLayout(new GridLayout(1, 2, 10, 10));
        add(myGame);
        add(mySecondary);

        mySecondary.setLayout(new GridLayout(3, 1, 10, 10));
        mySecondary.add(myNextPiece);
        mySecondary.add(myControl);
        mySecondary.add(myScore);
    }
}
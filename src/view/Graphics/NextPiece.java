package view.Graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.TetrisPiece;

/**
 *
 * @author James jsimp33@uw.edu
 * @version v1
 */
public final class NextPiece extends JLabel {

    /**
     *
     */
    private static final int WIDTH = 50;

    /**
     *
     */
    private static final int HEIGHT = 100;

    /**
     *
     */
    private static final Dimension MIN_SIZE = new Dimension(WIDTH, HEIGHT);

    /**
     *
     */
    public NextPiece(final TetrisPiece theNextPiece, final Color theColor) {
        super();
        setBackground(theColor);
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        add(buildNextPiecePanel(theNextPiece));
    }

    private JPanel buildNextPiecePanel(final TetrisPiece theNextPiece) {
        final JPanel nextPiecePanel = new JPanel();

        ImageIcon icon = new ImageIcon("Piece.png");     //(theNextPiece.name() + ".gif");
        final Image sizedIcon = icon.getImage().getScaledInstance(
                                              30, -30, Image.SCALE_SMOOTH);
        icon = new ImageIcon(sizedIcon);
        final JLabel iconLabel = new JLabel(icon);
        nextPiecePanel.add(iconLabel);
        return nextPiecePanel;
    }

    @Override
    public Dimension getMinimumSize() {
        return MIN_SIZE;
    }

    @Override
    public Dimension getPreferredSize() {
        return MIN_SIZE;
    }
}

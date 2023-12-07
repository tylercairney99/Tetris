package view.layout;

import static model.Board.PROPERTY_NEXT_PIECE_CHANGES;
import static model.paint.PaintI.createIShape;
import static model.paint.PaintJ.createJShape;
import static model.paint.PaintL.createLShape;
import static model.paint.PaintO.createOShape;
import static model.paint.PaintS.createSShape;
import static model.paint.PaintT.createTShape;
import static model.paint.PaintZ.createZShape;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

import model.Board;
import model.Rotation;
import model.TetrisPiece;

/**
 *Next piece panel shows the next tetromino to be played.
 *
 * @author James Simpson jsimp33@uw.edu
 * @author Tyler
 * @author Josh
 * @author Cam
 * @version 3.0
 */
public final class NextPiecePanel extends JPanel implements PropertyChangeListener {

    /**
     * The width of each block being displayed.
     */
    private static final int BLOCK_WIDTH = 16;

    /**
     * The height of each block being displayed.
     */
    private static final int BLOCK_HEIGHT = 16;

    /**
     * Used for the width of Graphics2d.
     */
    private static final int STROKE_WIDTH = 1;

    /**
     * The rotation of the next piece
     */
    private static final Rotation ROTATION = Rotation.NONE;

    /**
     * Ensures only one panel is instantiated.
     */
    private static int count = 0;

    /**
     * The next piece to be displayed.
     */
    private TetrisPiece myNextTetrisPiece;

    /**
     * The game board used to display next piece.
     */
    private Board myBoard;

    /**
     * Panel that will show the next Tetromino to be played.
     * Sets background to assigned color.
     */
    public NextPiecePanel(final Board theBoard) {
        super();

        if (count > 0) {
            throw new IllegalArgumentException("Only one NextPiecePanel allowed");
        }
        count++;

        this.myBoard = theBoard;
        this.myBoard.addPropertyChangeListener(this);
        setBackground(Color.BLUE);
        final JLabel nextPieceLabel = new JLabel("Next Piece");
        add(nextPieceLabel, BorderLayout.NORTH);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (PROPERTY_NEXT_PIECE_CHANGES.equals(theEvent.getPropertyName())) {
            myNextTetrisPiece = (TetrisPiece) theEvent.getNewValue();
            repaint();
        }
    }

    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setStroke(new BasicStroke(STROKE_WIDTH));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        final int w = getWidth();
        final int h = getHeight();
        final Color color1 = Color.CYAN;
        final Color color2 = Color.YELLOW;
        final GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        if (myNextTetrisPiece != null) {
            switch (myNextTetrisPiece) {
                case I:
                    createIShape(g2d, BLOCK_HEIGHT, (getHeight() - BLOCK_HEIGHT) / 2
                            , (getWidth() - 4 * BLOCK_HEIGHT) / 2, ROTATION);
                    break;
                case L:
                    createLShape(g2d, BLOCK_HEIGHT, (getHeight() - 2 * BLOCK_HEIGHT) / 2,
                            (getWidth() - 3 * BLOCK_HEIGHT) / 2, ROTATION);
                    break;
                case J:
                    createJShape(g2d, BLOCK_HEIGHT, (getHeight()  - 2 * BLOCK_HEIGHT) / 2,
                            (getWidth() - 3 * BLOCK_HEIGHT) / 2, ROTATION);
                    break;
                case O:
                    createOShape(g2d, BLOCK_HEIGHT, (getHeight()  - 2 * BLOCK_HEIGHT) / 2,
                            (getWidth() - 2 * BLOCK_HEIGHT) / 2, ROTATION);
                    break;
                case S:
                    createSShape(g2d, BLOCK_HEIGHT, (getHeight() - 2 * BLOCK_HEIGHT) / 2,
                            (getWidth() - 3 * BLOCK_HEIGHT) / 2, ROTATION);
                    break;
                case T:
                    createTShape(g2d, BLOCK_HEIGHT, (getHeight() - 2 * BLOCK_HEIGHT) / 2,
                            (getWidth() - 3 * BLOCK_HEIGHT) / 2, ROTATION);
                    break;
                case Z:
                    createZShape(g2d, BLOCK_HEIGHT, (getHeight() - 2 * BLOCK_HEIGHT) / 2,
                            (getWidth() - 3 * BLOCK_HEIGHT) / 2, ROTATION);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + myNextTetrisPiece);
            }
        }
    }
}

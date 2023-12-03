package view.Layout;

import static model.Board.PROPERTY_NEXT_PIECE_CHANGES;
import static model.PaintTetromino.createIShape;
import static model.PaintTetromino.createJShape;
import static model.PaintTetromino.createLShape;
import static model.PaintTetromino.createOShape;
import static model.PaintTetromino.createSShape;
import static model.PaintTetromino.createTShape;
import static model.PaintTetromino.createZShape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;
import model.TetrisPiece;


/**
 *Next piece panel shows the next tetromino to be played.
 *
 * @author James Simpson jsimp33@uw.edu
 * @author Tyler
 * @author Josh
 * @author Cam
 * @version 2.0
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
     * Ensures only one panel is instantiated.
     */
    private static int count = 0;

    /**
     * The next piece to be displayed.
     */
    private TetrisPiece myNextTetrisPiece;


    /**
     * Panel that will show the next Tetromino to be played.
     * Sets background to assigned color.
     */
    public NextPiecePanel() {
        super();

        if (count > 0) {
            throw new IllegalArgumentException("Only one NextPiecePanel allowed");
        }
        count++;

        setBackground(Color.BLUE);
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param theEvent A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (PROPERTY_NEXT_PIECE_CHANGES.equals(theEvent.getPropertyName())) {
//            GamePanel.getNextTetrisPiece(myNextTetrisPiece);
            myNextTetrisPiece = (TetrisPiece) theEvent.getNewValue();
            repaint();
        }
    }

    /**
     * Used to aquire the next Tetromino that will be displayed in the next piece panel.
     *
     * @param theNextPiece The next piece to be displayed.
     */
    public void getNextTetrisPiece(final TetrisPiece theNextPiece) {
        myNextTetrisPiece = theNextPiece;
        repaint();
    }

    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setStroke(new BasicStroke(STROKE_WIDTH));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);



        if (myNextTetrisPiece != null) {
            switch (myNextTetrisPiece) {
                case I:
                    createIShape(g2d, BLOCK_HEIGHT, (getHeight() - BLOCK_HEIGHT) / 2
                            , (getWidth() - 4 * BLOCK_HEIGHT) / 2);
                    break;
                case L:
                    createLShape(g2d, BLOCK_HEIGHT, (getHeight() - 2 * BLOCK_HEIGHT) / 2,
                            (getWidth() - 3 * BLOCK_HEIGHT) / 2);
                    break;
                case J:
                    createJShape(g2d, BLOCK_HEIGHT, (getHeight()  - 2 * BLOCK_HEIGHT) / 2,
                            (getWidth() - 3 * BLOCK_HEIGHT) / 2);
                    break;
                case O:
                    createOShape(g2d, BLOCK_HEIGHT, (getHeight()  - 2 * BLOCK_HEIGHT) / 2,
                            (getWidth() - 2 * BLOCK_HEIGHT) / 2);
                    break;
                case S:
                    createSShape(g2d, BLOCK_HEIGHT, (getHeight() - 2 * BLOCK_HEIGHT) / 2,
                            (getWidth() - 3 * BLOCK_HEIGHT) / 2);
                    break;
                case T:
                    createTShape(g2d, BLOCK_HEIGHT, (getHeight() - 2 * BLOCK_HEIGHT) / 2,
                            (getWidth() - 3 * BLOCK_HEIGHT) / 2);
                    break;
                case Z:
                    createZShape(g2d, BLOCK_HEIGHT, (getHeight() - 2 * BLOCK_HEIGHT) / 2,
                            (getWidth() - 3 * BLOCK_HEIGHT) / 2);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + myNextTetrisPiece);
            }
        }
    }
}

package view.Layout;

import model.Board;
import static model.Board.PROPERTY_NEXT_PIECE_CHANGES;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;
import model.TetrisPiece;


// TODO seems like shapes are completely random and it generating new shapes after time

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
     * The next piece to be displayed.
     */
    private TetrisPiece myNextTetrisPiece = null;

    /**
     * Panel that will show the next Tetromino to be played.
     * Sets background to assigned color.
     */
    public NextPiecePanel() {
        super();
        setBackground(Color.BLUE);
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
                    createIShape(g2d);
                    break;
                case L:
                    createLShape(g2d);
                    break;
                case J:
                    createJShape(g2d);
                    break;
                case O:
                    createOShape(g2d);
                    break;
                case S:
                    createSShape(g2d);
                    break;
                case T:
                    createTShape(g2d);
                    break;
                case Z:
                    createZShape(g2d);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + myNextTetrisPiece);
            }
        }
    }

    /**
     * Paints tetromino at location. Fills shape with assigned color and adds black border.
     *
     * @param theG2d Graphics2d object used for painting shapes.
     */
    private void createIShape(final Graphics2D theG2d) {
        final int offsetX = (getWidth() - 4 * BLOCK_WIDTH) / 2;
        final int offsetY = (getHeight() - BLOCK_HEIGHT) / 2;

        final Shape tetrisBlock1 = new Rectangle2D.Double(offsetX, offsetY,
                BLOCK_WIDTH - 1, BLOCK_HEIGHT - 1);
        final Shape tetrisBlock2 = new Rectangle2D.Double(offsetX + 16, offsetY,
                BLOCK_WIDTH - 1, BLOCK_HEIGHT - 1);
        final Shape tetrisBlock3 = new Rectangle2D.Double(offsetX + 2 * 16, offsetY,
                BLOCK_WIDTH - 1, BLOCK_HEIGHT - 1);
        final Shape tetrisBlock4 = new Rectangle2D.Double(offsetX + 3 * 16, offsetY,
                BLOCK_WIDTH - 1, BLOCK_HEIGHT - 1);

        theG2d.setPaint(Color.CYAN);
        paintShape(theG2d, tetrisBlock1, tetrisBlock2, tetrisBlock3, tetrisBlock4);
    }

    /**
     * Paints tetromino at location. Fills shape with assigned color and adds black border.
     *
     * @param theG2d Graphics2d object used for painting shapes.
     */
    private void createLShape(final Graphics2D theG2d) {
        final int startX = (getWidth() - 3 * BLOCK_WIDTH) / 2;
        final int startY = (getHeight() - 2 * BLOCK_HEIGHT) / 2;


        final Shape tetrisBlock1 = new Rectangle2D.Double(startX, startY + BLOCK_HEIGHT,
                BLOCK_WIDTH - 1, BLOCK_HEIGHT - 1);
        final Shape tetrisBlock2 = new Rectangle2D.Double(startX + BLOCK_WIDTH , startY + BLOCK_HEIGHT,
                BLOCK_WIDTH - 1, BLOCK_HEIGHT - 1);
        final Shape tetrisBlock3 = new Rectangle2D.Double(startX + BLOCK_WIDTH * 2, startY,
                BLOCK_WIDTH - 1, BLOCK_HEIGHT - 1);
        final Shape tetrisBlock4 = new Rectangle2D.Double(startX + BLOCK_WIDTH * 2, startY + BLOCK_HEIGHT,
                BLOCK_WIDTH - 1, BLOCK_HEIGHT - 1);

        theG2d.setPaint(Color.ORANGE);
        paintShape(theG2d, tetrisBlock1, tetrisBlock2, tetrisBlock3, tetrisBlock4);
    }

    /**
     * Paints tetromino at location. Fills shape with assigned color and adds black border.
     *
     * @param theG2d Graphics2d object used for painting shapes.
     */
    private void createJShape(final Graphics2D theG2d) {
        final int startX = (getWidth() - 3 * BLOCK_WIDTH) / 2;
        final int startY = (getHeight() - 2 * BLOCK_HEIGHT) / 2;

        final Shape tetrisBlock1 = new Rectangle2D.Double(startX, startY,
                BLOCK_WIDTH - 1, BLOCK_HEIGHT - 1);
        final Shape tetrisBlock2 = new Rectangle2D.Double(startX + BLOCK_WIDTH, startY + BLOCK_HEIGHT,
                BLOCK_WIDTH - 1, BLOCK_HEIGHT - 1);
        final Shape tetrisBlock3 = new Rectangle2D.Double(startX + 2 * BLOCK_WIDTH, startY + BLOCK_HEIGHT,
                BLOCK_WIDTH - 1, BLOCK_HEIGHT - 1);
        final Shape tetrisBlock4 = new Rectangle2D.Double(startX, startY + BLOCK_HEIGHT,
                BLOCK_WIDTH - 1, BLOCK_HEIGHT - 1);

        theG2d.setPaint(Color.BLUE);
        paintShape(theG2d, tetrisBlock1, tetrisBlock2, tetrisBlock3, tetrisBlock4);
    }

    /**
     * Paints tetromino at location. Fills shape with assigned color and adds black border.
     *
     * @param theG2d Graphics2d object used for painting shapes.
     */
    private void createOShape(final Graphics2D theG2d) {
        final int startX = (getWidth() - 2 * BLOCK_WIDTH) / 2;
        final int startY = (getHeight() - 2 * BLOCK_HEIGHT) / 2;

        final Shape tetrisBlock1 = new Rectangle2D.Double(startX, startY,
                BLOCK_WIDTH - 1, BLOCK_HEIGHT - 1);
        final Shape tetrisBlock2 = new Rectangle2D.Double(startX + BLOCK_WIDTH, startY,
                BLOCK_WIDTH - 1, BLOCK_HEIGHT - 1);
        final Shape tetrisBlock3 = new Rectangle2D.Double(startX, startY + BLOCK_HEIGHT,
                BLOCK_WIDTH - 1, BLOCK_HEIGHT - 1);
        final Shape tetrisBlock4 = new Rectangle2D.Double(startX + BLOCK_WIDTH, startY + BLOCK_HEIGHT,
                BLOCK_WIDTH - 1, BLOCK_HEIGHT - 1);

        theG2d.setPaint(Color.YELLOW);
        paintShape(theG2d, tetrisBlock1, tetrisBlock2, tetrisBlock3, tetrisBlock4);
    }


    /**
     * Paints tetromino at location. Fills shape with assigned color and adds black border.
     *
     * @param theG2d Graphics2d object used for painting shapes.
     */
    private void createSShape(final Graphics2D theG2d) {
        final int offsetX = (getWidth() - 3 * BLOCK_WIDTH) / 2;
        final int offsetY = (getHeight() - 2 * BLOCK_HEIGHT) / 2;

        final Shape tetrisBlock1 = new Rectangle2D.Double(offsetX + BLOCK_WIDTH, offsetY,
                BLOCK_WIDTH - 1, BLOCK_HEIGHT - 1);
        final Shape tetrisBlock2 = new Rectangle2D.Double(offsetX + 2 * BLOCK_WIDTH, offsetY,
                BLOCK_WIDTH - 1, BLOCK_HEIGHT - 1);
        final Shape tetrisBlock3 = new Rectangle2D.Double(offsetX, offsetY + BLOCK_HEIGHT,
                BLOCK_WIDTH - 1, BLOCK_HEIGHT - 1);
        final Shape tetrisBlock4 = new Rectangle2D.Double(offsetX + BLOCK_WIDTH, offsetY + BLOCK_HEIGHT,
                BLOCK_WIDTH - 1, BLOCK_HEIGHT - 1);

        theG2d.setPaint(Color.GREEN);
        paintShape(theG2d, tetrisBlock1, tetrisBlock2, tetrisBlock3, tetrisBlock4);
    }

    /**
     * Paints tetromino at location. Fills shape with assigned color and adds black border.
     *
     * @param theG2d Graphics2d object used for painting shapes.
     */
    private void createTShape(final Graphics2D theG2d) { // WRONG SHAPE
        final int offsetX = (getWidth() - 3 * BLOCK_WIDTH) / 2;
        final int offsetY = (getHeight() - 2 * BLOCK_HEIGHT) / 2;


        final Shape tetrisBlock1 = new Rectangle2D.Double(offsetX, offsetY + BLOCK_HEIGHT,
                BLOCK_WIDTH - 1, BLOCK_HEIGHT - 1);
        final Shape tetrisBlock2 = new Rectangle2D.Double(offsetX + BLOCK_WIDTH, offsetY,
                BLOCK_WIDTH - 1, BLOCK_HEIGHT - 1);
        final Shape tetrisBlock3 = new Rectangle2D.Double(offsetX + BLOCK_WIDTH, offsetY + BLOCK_HEIGHT,
                BLOCK_WIDTH - 1, BLOCK_HEIGHT - 1);
        final Shape tetrisBlock4 = new Rectangle2D.Double(offsetX + 2 * BLOCK_WIDTH, offsetY + BLOCK_HEIGHT,
                BLOCK_WIDTH - 1, BLOCK_HEIGHT - 1);

        theG2d.setPaint(Color.MAGENTA);
        paintShape(theG2d, tetrisBlock1, tetrisBlock2, tetrisBlock3, tetrisBlock4);
    }

    /**
     * Paints tetromino at location. Fills shape with assigned color and adds black border.
     *
     * @param theG2d Graphics2d object used for painting shapes.
     */
    private void createZShape(final Graphics2D theG2d) {
        final int offsetX = (getWidth() - 3 * BLOCK_WIDTH) / 2;
        final int offsetY = (getHeight() - 2 * BLOCK_HEIGHT) / 2;

        final Shape tetrisBlock1 = new Rectangle2D.Double(offsetX, offsetY,
                BLOCK_WIDTH - 1, BLOCK_HEIGHT - 1);
        final Shape tetrisBlock2 = new Rectangle2D.Double(offsetX + BLOCK_WIDTH, offsetY,
                BLOCK_WIDTH - 1, BLOCK_HEIGHT - 1);
        final Shape tetrisBlock3 = new Rectangle2D.Double(offsetX + BLOCK_WIDTH, offsetY + BLOCK_HEIGHT,
                BLOCK_WIDTH - 1, BLOCK_HEIGHT - 1);
        final Shape tetrisBlock4 = new Rectangle2D.Double(offsetX + 2 * BLOCK_WIDTH, offsetY + BLOCK_HEIGHT,
                BLOCK_WIDTH - 1, BLOCK_HEIGHT - 1);

        theG2d.setPaint(Color.PINK);
        paintShape(theG2d, tetrisBlock1, tetrisBlock2, tetrisBlock3, tetrisBlock4);
    }

    /**
     * Used to paint and fill tetrominos.
     *
     * @param theG2d Graphics 2d object used to paint.
     * @param theS1 Shape 1 to be painted.
     * @param theS2 Shape 2 to be painted.
     * @param theS3 Shape 3 to be painted.
     * @param theS4 Shape 4 to be painted.
     */
    private void paintShape(final Graphics2D theG2d, final Shape theS1, final Shape theS2,
                            final Shape theS3, final Shape theS4) {
        theG2d.fill(theS1);
        theG2d.fill(theS2);
        theG2d.fill(theS3);
        theG2d.fill(theS4);

        theG2d.setPaint(Color.BLACK);
        theG2d.draw(theS1);
        theG2d.draw(theS2);
        theG2d.draw(theS3);
        theG2d.draw(theS4);
    }

    /**
     * Used to aquire the next Tetromino that will be displayed in the next piece panel.
     *
     * @param nextPiece The next piece to be displayed.
     */
    public void getNextTetrisPiece(TetrisPiece nextPiece) {
        myNextTetrisPiece = nextPiece;
        repaint();
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (PROPERTY_NEXT_PIECE_CHANGES.equals(theEvent.getPropertyName())) {
            myNextTetrisPiece = (TetrisPiece) theEvent.getNewValue();
            repaint();
        }
    }
}

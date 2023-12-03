package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 * Utility class used to create shapes and prints them according to the parameters given.
 *
 * @author James Simpson jsimp33@uw.edu
 * @version 2.0
 */
public final class PaintTetromino extends JPanel {

    /**
     * Private constructor to prevent instantiation of this utility class.
     *
     * @throws AssertionError (if instantiation is attempted)
     */
    private PaintTetromino() {
        super();
        throw new AssertionError("Utility classes cannot be instantiated");
    }

    /**
     * Paints tetromino at location. Fills shape with assigned color and adds black border.
     *
     * @param theG2d Graphics2d object used for painting shapes.
     */
    public static void createIShape(final Graphics2D theG2d, final int theBlockHeight,
                                    final int theY, final int theX) {

        final Shape tetrisBlock1 = new Rectangle2D.Double(theX, theY,
                theBlockHeight - 1, theBlockHeight - 1);
        final Shape tetrisBlock2 = new Rectangle2D.Double(theX + theBlockHeight, theY,
                theBlockHeight - 1, theBlockHeight - 1);
        final Shape tetrisBlock3 = new Rectangle2D.Double(theX + 2 * theBlockHeight, theY,
                theBlockHeight - 1, theBlockHeight - 1);
        final Shape tetrisBlock4 = new Rectangle2D.Double(theX + 3 * theBlockHeight, theY,
                theBlockHeight - 1, theBlockHeight - 1);

        theG2d.setPaint(Color.CYAN);
        paintShape(theG2d, tetrisBlock1, tetrisBlock2, tetrisBlock3, tetrisBlock4);
    }

    /**
     * Paints tetromino at location. Fills shape with assigned color and adds black border.
     *
     * @param theG2d Graphics2d object used for painting shapes.
     */
    public static void createLShape(final Graphics2D theG2d, final int theBlockHeight,
                                     final int theY, final int theX) {

        final Shape tetrisBlock1 = new Rectangle2D.Double(theX, theY + theBlockHeight,
                theBlockHeight - 1, theBlockHeight - 1);
        final Shape tetrisBlock2 = new Rectangle2D.Double(theX + theBlockHeight , theY + theBlockHeight,
                theBlockHeight - 1, theBlockHeight - 1);
        final Shape tetrisBlock3 = new Rectangle2D.Double(theX + theBlockHeight * 2, theY,
                theBlockHeight - 1, theBlockHeight - 1);
        final Shape tetrisBlock4 = new Rectangle2D.Double(theX + theBlockHeight * 2, theY + theBlockHeight,
                theBlockHeight - 1, theBlockHeight - 1);

        theG2d.setPaint(Color.ORANGE);
        paintShape(theG2d, tetrisBlock1, tetrisBlock2, tetrisBlock3, tetrisBlock4);
    }

    /**
     * Paints tetromino at location. Fills shape with assigned color and adds black border.
     *
     * @param theG2d Graphics2d object used for painting shapes.
     */
    public static void createJShape(final Graphics2D theG2d, final int theBlockHeight,
                                     final int theY, final int theX) {

        final Shape tetrisBlock1 = new Rectangle2D.Double(theX, theY,
                theBlockHeight - 1, theBlockHeight - 1);
        final Shape tetrisBlock2 = new Rectangle2D.Double(theX + theBlockHeight, theY + theBlockHeight,
                theBlockHeight - 1, theBlockHeight - 1);
        final Shape tetrisBlock3 = new Rectangle2D.Double(theX + 2 * theBlockHeight, theY + theBlockHeight,
                theBlockHeight - 1, theBlockHeight - 1);
        final Shape tetrisBlock4 = new Rectangle2D.Double(theX, theY + theBlockHeight,
                theBlockHeight - 1, theBlockHeight - 1);

        theG2d.setPaint(Color.BLUE);
        paintShape(theG2d, tetrisBlock1, tetrisBlock2, tetrisBlock3, tetrisBlock4);
    }

    /**
     * Paints tetromino at location. Fills shape with assigned color and adds black border.
     *
     * @param theG2d Graphics2d object used for painting shapes.
     */
    public static void createOShape(final Graphics2D theG2d, final int theBlockHeight,
                                     final int theY, final int theX) {

        final Shape tetrisBlock1 = new Rectangle2D.Double(theX, theY,
                theBlockHeight - 1, theBlockHeight - 1);
        final Shape tetrisBlock2 = new Rectangle2D.Double(theX + theBlockHeight, theY,
                theBlockHeight - 1, theBlockHeight - 1);
        final Shape tetrisBlock3 = new Rectangle2D.Double(theX, theY + theBlockHeight,
                theBlockHeight - 1, theBlockHeight - 1);
        final Shape tetrisBlock4 = new Rectangle2D.Double(theX + theBlockHeight, theY + theBlockHeight,
                theBlockHeight - 1, theBlockHeight - 1);

        theG2d.setPaint(Color.YELLOW);
        paintShape(theG2d, tetrisBlock1, tetrisBlock2, tetrisBlock3, tetrisBlock4);
    }

    /**
     * Paints tetromino at location. Fills shape with assigned color and adds black border.
     *
     * @param theG2d Graphics2d object used for painting shapes.
     */
    public static void createSShape(final Graphics2D theG2d, final int theBlockHeight,
                                     final int theY, final int theX) {

        final Shape tetrisBlock1 = new Rectangle2D.Double(theX + theBlockHeight, theY,
                theBlockHeight - 1, theBlockHeight - 1);
        final Shape tetrisBlock2 = new Rectangle2D.Double(theX + 2 * theBlockHeight, theY,
                theBlockHeight - 1, theBlockHeight - 1);
        final Shape tetrisBlock3 = new Rectangle2D.Double(theX, theY + theBlockHeight,
                theBlockHeight - 1, theBlockHeight - 1);
        final Shape tetrisBlock4 = new Rectangle2D.Double(theX + theBlockHeight, theY + theBlockHeight,
                theBlockHeight - 1, theBlockHeight - 1);

        theG2d.setPaint(Color.GREEN);
        paintShape(theG2d, tetrisBlock1, tetrisBlock2, tetrisBlock3, tetrisBlock4);
    }

    /**
     * Paints tetromino at location. Fills shape with assigned color and adds black border.
     *
     * @param theG2d Graphics2d object used for painting shapes.
     */
    public static void createTShape(final Graphics2D theG2d, final int theBlockHeight,
                                     final int theY, final int theX) { // WRONG SHAPE


        final Shape tetrisBlock1 = new Rectangle2D.Double(theX, theY + theBlockHeight,
                theBlockHeight - 1, theBlockHeight - 1);
        final Shape tetrisBlock2 = new Rectangle2D.Double(theX + theBlockHeight, theY,
                theBlockHeight - 1, theBlockHeight - 1);
        final Shape tetrisBlock3 = new Rectangle2D.Double(theX + theBlockHeight, theY + theBlockHeight,
                theBlockHeight - 1, theBlockHeight - 1);
        final Shape tetrisBlock4 = new Rectangle2D.Double(theX + 2 * theBlockHeight, theY + theBlockHeight,
                theBlockHeight - 1, theBlockHeight - 1);

        theG2d.setPaint(Color.MAGENTA);
        paintShape(theG2d, tetrisBlock1, tetrisBlock2, tetrisBlock3, tetrisBlock4);
    }

    /**
     * Paints tetromino at location. Fills shape with assigned color and adds black border.
     *
     * @param theG2d Graphics2d object used for painting shapes.
     */
    public static void createZShape(final Graphics2D theG2d, final int theBlockHeight,
                                     final int theY, final int theX) {

        final Shape tetrisBlock1 = new Rectangle2D.Double(theX, theY,
                theBlockHeight - 1, theBlockHeight - 1);
        final Shape tetrisBlock2 = new Rectangle2D.Double(theX + theBlockHeight, theY,
                theBlockHeight - 1, theBlockHeight - 1);
        final Shape tetrisBlock3 = new Rectangle2D.Double(theX + theBlockHeight, theY + theBlockHeight,
                theBlockHeight - 1, theBlockHeight - 1);
        final Shape tetrisBlock4 = new Rectangle2D.Double(theX + 2 * theBlockHeight, theY + theBlockHeight,
                theBlockHeight - 1, theBlockHeight - 1);

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
    private static void paintShape(final Graphics2D theG2d, final Shape theS1, final Shape theS2,
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
}

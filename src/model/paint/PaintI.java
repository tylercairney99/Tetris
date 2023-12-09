package model.paint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import model.Rotation;

/**
 * Utility class used to create shapes and prints them according to the parameters given.
 *
 * @author James Simpson jsimp33@uw.edu
 * @version 3.0
 */
public final class PaintI implements model.paint.PaintTetromino {

    /**
     * Private constructor to prevent instantiation of this utility class.
     *
     * @throws AssertionError (if instantiation is attempted)
     */
    private PaintI() {
        super();

        throw new AssertionError("Utility classes cannot be instantiated");
    }

    /**
     * Paints tetromino at location. Fills shape with assigned color and adds black border.
     *
     * @param theG2d Graphics2d object used for painting shapes.
     */
    @SuppressWarnings("Labeled code block is redudant") // Warning Suppresesed because it only reduces redundancy not functionality.
    public static void createIShape(final Graphics2D theG2d, final int theBlockHeight,
                                    final int theY, final int theX,
                                    final Rotation theRotation) {
        switch (theRotation) {
            case NONE -> {
                rotation0(theG2d, theBlockHeight,
                        theY, theX);
            }
            case QUARTER -> {
                rotation90(theG2d, theBlockHeight,
                        theY, theX);
            }
            case HALF -> {
                rotation180(theG2d, theBlockHeight,
                        theY, theX);
            }
            case THREEQUARTER -> {
                rotation270(theG2d, theBlockHeight,
                        theY, theX);
            }
            default -> throw new IllegalStateException("Unexpected value: " + theRotation);
        }
    }

    /**
     * Paints tetromino with no rotation.
     */
    public static void rotation0(final Graphics2D theG2d, final int theBlockHeight,
                          final int theY, final int theX) {
        final Shape tetrisBlock1 = new Rectangle2D.Double(theX, theY,
                theBlockHeight, theBlockHeight);
        final Shape tetrisBlock2 = new Rectangle2D.Double(theX + theBlockHeight, theY,
                theBlockHeight, theBlockHeight);
        final Shape tetrisBlock3 = new Rectangle2D.Double(theX + 2 * theBlockHeight, theY,
                theBlockHeight, theBlockHeight);
        final Shape tetrisBlock4 = new Rectangle2D.Double(theX + 3 * theBlockHeight, theY,
                theBlockHeight, theBlockHeight);

        theG2d.setPaint(Color.CYAN);
        model.paint.PaintTetromino.paintShape(theG2d, tetrisBlock1,
                tetrisBlock2, tetrisBlock3, tetrisBlock4);
    }

    /**
     * Paints tetromino with 90 degree rotation.
     */
    public static void rotation90(final Graphics2D theG2d, final int theBlockHeight,
                           final int theY, final int theX) {
        final Shape tetrisBlock1 = new Rectangle2D.Double(
                theX + 2 * theBlockHeight, theY + 2 * theBlockHeight,
                theBlockHeight, theBlockHeight);
        final Shape tetrisBlock2 = new Rectangle2D.Double(
                theX + 2 * theBlockHeight, theY + theBlockHeight,
                theBlockHeight, theBlockHeight);
        final Shape tetrisBlock3 = new Rectangle2D.Double(
                theX + 2 * theBlockHeight, theY,
                theBlockHeight, theBlockHeight);
        final Shape tetrisBlock4 = new Rectangle2D.Double(
                theX + 2 * theBlockHeight, theY - theBlockHeight,
                theBlockHeight, theBlockHeight);

        theG2d.setPaint(Color.CYAN);
        model.paint.PaintTetromino.paintShape(theG2d, tetrisBlock1,
                tetrisBlock2, tetrisBlock3, tetrisBlock4);
    }

    /**
     * Paints tetromino with 180 degree rotation.
     */
    public static void rotation180(final Graphics2D theG2d, final int theBlockHeight,
                            final int theY, final int theX) {
        final Shape tetrisBlock1 = new Rectangle2D.Double(theX, theY - 1,
                theBlockHeight, theBlockHeight);
        final Shape tetrisBlock2 = new Rectangle2D.Double(theX + theBlockHeight, theY - 1,
                theBlockHeight, theBlockHeight);
        final Shape tetrisBlock3 = new Rectangle2D.Double(theX + 2 * theBlockHeight, theY - 1,
                theBlockHeight, theBlockHeight);
        final Shape tetrisBlock4 = new Rectangle2D.Double(theX + 3 * theBlockHeight, theY - 1,
                theBlockHeight, theBlockHeight);

        theG2d.setPaint(Color.CYAN);
        model.paint.PaintTetromino.paintShape(theG2d, tetrisBlock1,
                tetrisBlock2, tetrisBlock3, tetrisBlock4);
    }

    /**
     * Paints tetromino with 270 degree rotation.
     */
    public static void rotation270(final Graphics2D theG2d, final int theBlockHeight,
                            final int theY, final int theX) {
        final Shape tetrisBlock1 = new Rectangle2D.Double(
                theX + theBlockHeight, theY + 2 * theBlockHeight,
                theBlockHeight, theBlockHeight);
        final Shape tetrisBlock2 = new Rectangle2D.Double(
                theX + theBlockHeight, theY + theBlockHeight,
                theBlockHeight, theBlockHeight);
        final Shape tetrisBlock3 = new Rectangle2D.Double(
                theX + theBlockHeight, theY,
                theBlockHeight, theBlockHeight);
        final Shape tetrisBlock4 = new Rectangle2D.Double(
                theX + theBlockHeight, theY - theBlockHeight,
                theBlockHeight, theBlockHeight);

        theG2d.setPaint(Color.CYAN);
        model.paint.PaintTetromino.paintShape(theG2d, tetrisBlock1,
                tetrisBlock2, tetrisBlock3, tetrisBlock4);
    }
}

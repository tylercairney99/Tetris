package model.paint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import model.PaintTetromino;
import model.Rotation;


/**
 * Utility class used to create shapes and prints them according to the parameters given.
 *
 * @author James Simpson jsimp33@uw.edu
 * @version 3.0
 */
public final class PaintO implements model.PaintTetromino {

    /**
     * Private constructor to prevent instantiation of this utility class.
     *
     * @throws AssertionError (if instantiation is attempted)
     */
    private PaintO() {
        super();
        throw new AssertionError("Utility classes cannot be instantiated");
    }

    /**
     * Paints tetromino at location. Fills shape with assigned color and adds black border.
     *
     * @param theG2d Graphics2d object used for painting shapes.
     */
    public static void createOShape(final Graphics2D theG2d, final int theBlockHeight,
                                    final int theY, final int theX, final Rotation theRotation) {
        rotation0(theG2d, theBlockHeight, theY, theX);
    }

    /**
     * Paints tetromino with no rotation.
     */
    public static void rotation0(final Graphics2D theG2d, final int theBlockHeight,
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
        PaintTetromino.paintShape(theG2d, tetrisBlock1, tetrisBlock2, tetrisBlock3, tetrisBlock4);
    }
}

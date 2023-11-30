package view.Layout;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.Rectangle2D;


// CAN MAKE INTO UTILITY CLASS TO BE USED BY NEXTPIECEPANEL FOR DRAWING SHAPES ***

/**
 * Game panel will be used to display the Tetris game being played.
 *
 * @author James Simpson jsimp33@uw.edu
 * @author Tyler
 * @author Cam
 * @author Josh
 * @version 2.0
 */
public final class GamePanel extends JPanel {

    /**
     * Width used for Graphics2d painting.
     */
    private static final int STROKE_WIDTH = 1;

    /**
     * The height of each tetromino.
     */
    private static final int BLOCK_HEIGHT = 19;

    /**
     * The width of each tetromino.
     */
    private static final int BLOCK_WIDTH = 19;

    /**
     * Game panel will be used to display the Tetris game being played.
     * Sets background to assigned color.
     */
    public GamePanel() {
        super();
        setBackground(Color.RED);
    }


    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setStroke(new BasicStroke(STROKE_WIDTH));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        createIShape(g2d);
        createLShape(g2d);
        createJShape(g2d);
        createOShape(g2d);
        createSShape(g2d);
        createTShape(g2d);
        createZShape(g2d);
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
     * Paints tetromino at location. Fills shape with assigned color and adds black border.
     *
     * @param theG2d Graphics2d object used for painting shapes.
     */
    private void createIShape(final Graphics2D theG2d) {
        final Shape tetrisBlock1 =
                new Rectangle2D.Double(0, 380, 19, 19);
        final Shape tetrisBlock2 =
                new Rectangle2D.Double(20, 380, 19, 19);
        final Shape tetrisBlock3 =
                new Rectangle2D.Double(40, 380, 19, 19);
        final Shape tetrisBlock4 =
                new Rectangle2D.Double(60, 380, 19, 19);
        theG2d.setPaint(Color.CYAN);
        paintShape(theG2d, tetrisBlock1, tetrisBlock2, tetrisBlock3, tetrisBlock4);
    }

    /**
     * Paints tetromino at location. Fills shape with assigned color and adds black border.
     *
     * @param theG2d Graphics2d object used for painting shapes.
     */
    private void createLShape(final Graphics2D theG2d) {
        final Shape tetrisBlock1 =
                new Rectangle2D.Double(80, 380, 19, 19);
        final Shape tetrisBlock2 =
                new Rectangle2D.Double(100, 380, 19, 19);
        final Shape tetrisBlock3 =
                new Rectangle2D.Double(120, 380, 19, 19);
        final Shape tetrisBlock4 =
                new Rectangle2D.Double(120, 360, 19, 19);
        theG2d.setPaint(Color.ORANGE);
        paintShape(theG2d, tetrisBlock1, tetrisBlock2, tetrisBlock3, tetrisBlock4);
    }

    /**
     * Paints tetromino at location. Fills shape with assigned color and adds black border.
     *
     * @param theG2d Graphics2d object used for painting shapes.
     */
    private void createJShape(final Graphics2D theG2d) {
        final Shape tetrisBlock1 =
                new Rectangle2D.Double(140, 380, 19, 19);
        final Shape tetrisBlock2 =
                new Rectangle2D.Double(160, 380, 19, 19);
        final Shape tetrisBlock3 =
                new Rectangle2D.Double(180, 380, 19, 19);
        final Shape tetrisBlock4 =
                new Rectangle2D.Double(140, 360, 19, 19);
        theG2d.setPaint(Color.BLUE);
        paintShape(theG2d, tetrisBlock1, tetrisBlock2, tetrisBlock3, tetrisBlock4);
    }

    /**
     * Paints tetromino at location. Fills shape with assigned color and adds black border.
     *
     * @param theG2d Graphics2d object used for painting shapes.
     */
    private void createOShape(final Graphics2D theG2d) {
        final Shape tetrisBlock1 =
                new Rectangle2D.Double(160, 360, 19, 19);
        final Shape tetrisBlock2 =
                new Rectangle2D.Double(180, 360, 19, 19);
        final Shape tetrisBlock3 =
                new Rectangle2D.Double(160, 340, 19, 19);
        final Shape tetrisBlock4 =
                new Rectangle2D.Double(180, 340, 19, 19);
        theG2d.setPaint(Color.YELLOW);
        paintShape(theG2d, tetrisBlock1, tetrisBlock2, tetrisBlock3, tetrisBlock4);
    }

    /**
     * Paints tetromino at location. Fills shape with assigned color and adds black border.
     *
     * @param theG2d Graphics2d object used for painting shapes.
     */
    private void createSShape(final Graphics2D theG2d) {
        final Shape tetrisBlock1 =
                new Rectangle2D.Double(80, 360, 19, 19);
        final Shape tetrisBlock2 =
                new Rectangle2D.Double(100, 360, 19, 19);
        final Shape tetrisBlock3 =
                new Rectangle2D.Double(100, 340, 19, 19);
        final Shape tetrisBlock4 =
                new Rectangle2D.Double(120, 340, 19, 19);
        theG2d.setPaint(Color.GREEN);
        paintShape(theG2d, tetrisBlock1, tetrisBlock2, tetrisBlock3, tetrisBlock4);
    }

    /**
     * Paints tetromino at location. Fills shape with assigned color and adds black border.
     *
     * @param theG2d Graphics2d object used for painting shapes.
     */
    private void createTShape(final Graphics2D theG2d) {
        final Shape tetrisBlock1 =
                new Rectangle2D.Double(80, 340, 19, 19);
        final Shape tetrisBlock2 =
                new Rectangle2D.Double(80, 320, 19, 19);
        final Shape tetrisBlock3 =
                new Rectangle2D.Double(100, 320, 19, 19);
        final Shape tetrisBlock4 =
                new Rectangle2D.Double(60, 320, 19, 19);
        theG2d.setPaint(Color.MAGENTA);
        paintShape(theG2d, tetrisBlock1, tetrisBlock2, tetrisBlock3, tetrisBlock4);
    }

    /**
     * Paints tetromino at location. Fills shape with assigned color and adds black border.
     *
     * @param theG2d Graphics2d object used for painting shapes.
     */
    private void createZShape(final Graphics2D theG2d) {
        final Shape tetrisBlock1 =
                new Rectangle2D.Double(140, 340, 19, 19);
        final Shape tetrisBlock2 =
                new Rectangle2D.Double(140, 320, 19, 19);
        final Shape tetrisBlock3 =
                new Rectangle2D.Double(160, 320, 19, 19);
        final Shape tetrisBlock4 =
                new Rectangle2D.Double(160, 300, 19, 19);
        theG2d.setPaint(Color.PINK);
        paintShape(theG2d, tetrisBlock1, tetrisBlock2, tetrisBlock3, tetrisBlock4);
    }
}

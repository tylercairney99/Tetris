package view.Layout;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;


// CAN MAKE INTO UTILITY CLASS TO BE USED BY NEXTPIECEPANEL FOR DRAWING SHAPES ***

public class GamePanel extends JPanel {

    private final int STROKE_WIDTH = 1;


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

    private void createIShape(final Graphics2D theG2d) {
        final Shape tetrisBlock1 =
                new Rectangle2D.Double(0, 379, 20, 20);
        final Shape tetrisBlock2 =
                new Rectangle2D.Double(20, 379, 20, 20);
        final Shape tetrisBlock3 =
                new Rectangle2D.Double(40, 379, 20, 20);
        final Shape tetrisBlock4 =
                new Rectangle2D.Double(60, 379, 20, 20);
        theG2d.setPaint(Color.CYAN);

        theG2d.fill(tetrisBlock1);
        theG2d.fill(tetrisBlock2);
        theG2d.fill(tetrisBlock3);
        theG2d.fill(tetrisBlock4);

        theG2d.setPaint(Color.BLACK);

        theG2d.draw(tetrisBlock1);
        theG2d.draw(tetrisBlock2);
        theG2d.draw(tetrisBlock3);
        theG2d.draw(tetrisBlock4);
    }

    private void createLShape(final Graphics2D theG2d) {
        final Shape tetrisBlock1 =
                new Rectangle2D.Double(80, 379, 20, 20);
        final Shape tetrisBlock2 =
                new Rectangle2D.Double(100, 379, 20, 20);
        final Shape tetrisBlock3 =
                new Rectangle2D.Double(120, 379, 20, 20);
        final Shape tetrisBlock4 =
                new Rectangle2D.Double(120, 359, 20, 20);
        theG2d.setPaint(Color.ORANGE);

        theG2d.fill(tetrisBlock1);
        theG2d.fill(tetrisBlock2);
        theG2d.fill(tetrisBlock3);
        theG2d.fill(tetrisBlock4);

        theG2d.setPaint(Color.BLACK);

        theG2d.draw(tetrisBlock1);
        theG2d.draw(tetrisBlock2);
        theG2d.draw(tetrisBlock3);
        theG2d.draw(tetrisBlock4);
    }

    private void createJShape(final Graphics2D theG2d) {
        final Shape tetrisBlock1 =
                new Rectangle2D.Double(140, 379, 20, 20);
        final Shape tetrisBlock2 =
                new Rectangle2D.Double(160, 379, 20, 20);
        final Shape tetrisBlock3 =
                new Rectangle2D.Double(180, 379, 20, 20);
        final Shape tetrisBlock4 =
                new Rectangle2D.Double(140, 359, 20, 20);
        theG2d.setPaint(Color.BLUE);

        theG2d.fill(tetrisBlock1);
        theG2d.fill(tetrisBlock2);
        theG2d.fill(tetrisBlock3);
        theG2d.fill(tetrisBlock4);

        theG2d.setPaint(Color.BLACK);

        theG2d.draw(tetrisBlock1);
        theG2d.draw(tetrisBlock2);
        theG2d.draw(tetrisBlock3);
        theG2d.draw(tetrisBlock4);
    }

    private void createOShape(final Graphics2D theG2d) {
        final Shape tetrisBlock1 =
                new Rectangle2D.Double(160, 359, 20, 20);
        final Shape tetrisBlock2 =
                new Rectangle2D.Double(180, 359, 20, 20);
        final Shape tetrisBlock3 =
                new Rectangle2D.Double(160, 339, 20, 20);
        final Shape tetrisBlock4 =
                new Rectangle2D.Double(180, 339, 20, 20);
        theG2d.setPaint(Color.YELLOW);

        theG2d.fill(tetrisBlock1);
        theG2d.fill(tetrisBlock2);
        theG2d.fill(tetrisBlock3);
        theG2d.fill(tetrisBlock4);

        theG2d.setPaint(Color.BLACK);

        theG2d.draw(tetrisBlock1);
        theG2d.draw(tetrisBlock2);
        theG2d.draw(tetrisBlock3);
        theG2d.draw(tetrisBlock4);
    }

    private void createSShape(final Graphics2D theG2d) {
        final Shape tetrisBlock1 =
                new Rectangle2D.Double(80, 359, 20, 20);
        final Shape tetrisBlock2 =
                new Rectangle2D.Double(100, 359, 20, 20);
        final Shape tetrisBlock3 =
                new Rectangle2D.Double(100, 339, 20, 20);
        final Shape tetrisBlock4 =
                new Rectangle2D.Double(120, 339, 20, 20);
        theG2d.setPaint(Color.GREEN);

        theG2d.fill(tetrisBlock1);
        theG2d.fill(tetrisBlock2);
        theG2d.fill(tetrisBlock3);
        theG2d.fill(tetrisBlock4);

        theG2d.setPaint(Color.BLACK);

        theG2d.draw(tetrisBlock1);
        theG2d.draw(tetrisBlock2);
        theG2d.draw(tetrisBlock3);
        theG2d.draw(tetrisBlock4);
    }

    private void createTShape(final Graphics2D theG2d) {
        final Shape tetrisBlock1 =
                new Rectangle2D.Double(80, 339, 20, 20);
        final Shape tetrisBlock2 =
                new Rectangle2D.Double(80, 319, 20, 20);
        final Shape tetrisBlock3 =
                new Rectangle2D.Double(100, 319, 20, 20);
        final Shape tetrisBlock4 =
                new Rectangle2D.Double(60, 319, 20, 20);
        theG2d.setPaint(Color.MAGENTA);

        theG2d.fill(tetrisBlock1);
        theG2d.fill(tetrisBlock2);
        theG2d.fill(tetrisBlock3);
        theG2d.fill(tetrisBlock4);

        theG2d.setPaint(Color.BLACK);

        theG2d.draw(tetrisBlock1);
        theG2d.draw(tetrisBlock2);
        theG2d.draw(tetrisBlock3);
        theG2d.draw(tetrisBlock4);
    }

    private void createZShape(final Graphics2D theG2d) {
        final Shape tetrisBlock1 =
                new Rectangle2D.Double(140, 339, 20, 20);
        final Shape tetrisBlock2 =
                new Rectangle2D.Double(140, 319, 20, 20);
        final Shape tetrisBlock3 =
                new Rectangle2D.Double(160, 319, 20, 20);
        final Shape tetrisBlock4 =
                new Rectangle2D.Double(160, 299, 20, 20);
        theG2d.setPaint(Color.PINK);

        theG2d.fill(tetrisBlock1);
        theG2d.fill(tetrisBlock2);
        theG2d.fill(tetrisBlock3);
        theG2d.fill(tetrisBlock4);

        theG2d.setPaint(Color.BLACK);

        theG2d.draw(tetrisBlock1);
        theG2d.draw(tetrisBlock2);
        theG2d.draw(tetrisBlock3);
        theG2d.draw(tetrisBlock4);
    }
}

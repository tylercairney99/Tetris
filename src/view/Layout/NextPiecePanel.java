package view.Layout;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class NextPiecePanel extends JPanel {

    private final int STROKE_WIDTH = 1;


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

        createTShape(g2d);
    }

    private void createTShape(final Graphics2D theG2d) {
        final Shape tetrisBlock1 =
                new Rectangle2D.Double(72, 64, 16, 16);
        final Shape tetrisBlock2 =
                new Rectangle2D.Double(72, 80, 16, 16);
        final Shape tetrisBlock3 =
                new Rectangle2D.Double(88, 80, 16, 16);
        final Shape tetrisBlock4 =
                new Rectangle2D.Double(56, 80, 16, 16);

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
}

package view.Layout;

import model.Board;
import model.TetrisPiece;

import static model.Board.PROPERTY_NEXT_PIECE_CHANGES;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;


// TODO seems like shapes are completely random and it generating new shapes after time

public class NextPiecePanel extends JPanel implements PropertyChangeListener {

    private TetrisPiece myNextTetrisPiece = null;

    private final int STROKE_WIDTH = 1;

    private static final int BLOCK_WIDTH = 20;

    private static final int BLOCK_HEIGHT = 20;


    public NextPiecePanel() {
        super();
        setBackground(Color.BLUE);
    }

    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setStroke(new BasicStroke(STROKE_WIDTH));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

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
            }
        }
    }
    private void createIShape(final Graphics2D theG2d) {
        int offsetX = (getWidth() - 4 * BLOCK_WIDTH) / 2;
        int offsetY = (getHeight() - BLOCK_HEIGHT) / 2;

        final Shape tetrisBlock1 = new Rectangle2D.Double(offsetX, offsetY, BLOCK_WIDTH, BLOCK_HEIGHT);
        final Shape tetrisBlock2 = new Rectangle2D.Double(offsetX + 20, offsetY, BLOCK_WIDTH, BLOCK_HEIGHT);
        final Shape tetrisBlock3 = new Rectangle2D.Double(offsetX + 2 * 20, offsetY, BLOCK_WIDTH, BLOCK_HEIGHT);
        final Shape tetrisBlock4 = new Rectangle2D.Double(offsetX + 3 * 20, offsetY, BLOCK_WIDTH, BLOCK_HEIGHT);

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
        int startX = (getWidth() - 3 * BLOCK_WIDTH) / 2;
        int startY = (getHeight() - 2 * BLOCK_HEIGHT) / 2;


        final Shape tetrisBlock1 = new Rectangle2D.Double(startX, startY, BLOCK_WIDTH, BLOCK_HEIGHT);
        final Shape tetrisBlock2 = new Rectangle2D.Double(startX + 20, startY, BLOCK_WIDTH, BLOCK_HEIGHT);
        final Shape tetrisBlock3 = new Rectangle2D.Double(startX + 40, startY, BLOCK_WIDTH, BLOCK_HEIGHT);

        final Shape tetrisBlock4 = new Rectangle2D.Double(startX + 40, startY - 20, BLOCK_WIDTH, BLOCK_HEIGHT);

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
        int startX = (getWidth() - 3 * BLOCK_WIDTH) / 2;
        int startY = (getHeight() - 2 * BLOCK_HEIGHT) / 2;

        final Shape tetrisBlock1 = new Rectangle2D.Double(startX, startY, BLOCK_WIDTH, BLOCK_HEIGHT);
        final Shape tetrisBlock2 = new Rectangle2D.Double(startX + BLOCK_WIDTH, startY, BLOCK_WIDTH, BLOCK_HEIGHT);
        final Shape tetrisBlock3 = new Rectangle2D.Double(startX + 2 * BLOCK_WIDTH, startY, BLOCK_WIDTH, BLOCK_HEIGHT);
        final Shape tetrisBlock4 = new Rectangle2D.Double(startX, startY - BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);

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
        int startX = (getWidth() - 2 * BLOCK_WIDTH) / 2;
        int startY = (getHeight() - 2 * BLOCK_HEIGHT) / 2;

        final Shape tetrisBlock1 = new Rectangle2D.Double(startX, startY, BLOCK_WIDTH, BLOCK_HEIGHT);
        final Shape tetrisBlock2 = new Rectangle2D.Double(startX + BLOCK_WIDTH, startY, BLOCK_WIDTH, BLOCK_HEIGHT);
        final Shape tetrisBlock3 = new Rectangle2D.Double(startX, startY + BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
        final Shape tetrisBlock4 = new Rectangle2D.Double(startX + BLOCK_WIDTH, startY + BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);

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
        int offsetX = (getWidth() - 3 * BLOCK_WIDTH) / 2;
        int offsetY = (getHeight() - 2 * BLOCK_HEIGHT) / 2;

        final Shape tetrisBlock1 = new Rectangle2D.Double(offsetX + BLOCK_WIDTH, offsetY, BLOCK_WIDTH, BLOCK_HEIGHT);
        final Shape tetrisBlock2 = new Rectangle2D.Double(offsetX + 2 * BLOCK_WIDTH, offsetY, BLOCK_WIDTH, BLOCK_HEIGHT);
        final Shape tetrisBlock3 = new Rectangle2D.Double(offsetX, offsetY + BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
        final Shape tetrisBlock4 = new Rectangle2D.Double(offsetX + BLOCK_WIDTH, offsetY + BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);

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
        int offsetX = (getWidth() - 3 * BLOCK_WIDTH) / 2;
        int offsetY = (getHeight() - 2 * BLOCK_HEIGHT) / 2;

        final Shape tetrisBlock1 = new Rectangle2D.Double(offsetX + BLOCK_WIDTH, offsetY + BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
        final Shape tetrisBlock2 = new Rectangle2D.Double(offsetX, offsetY, BLOCK_WIDTH, BLOCK_HEIGHT);
        final Shape tetrisBlock3 = new Rectangle2D.Double(offsetX + BLOCK_WIDTH, offsetY, BLOCK_WIDTH, BLOCK_HEIGHT);
        final Shape tetrisBlock4 = new Rectangle2D.Double(offsetX + 2 * BLOCK_WIDTH, offsetY, BLOCK_WIDTH, BLOCK_HEIGHT);

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
        int offsetX = (getWidth() - 3 * BLOCK_WIDTH) / 2;
        int offsetY = (getHeight() - 2 * BLOCK_HEIGHT) / 2;

        final Shape tetrisBlock1 = new Rectangle2D.Double(offsetX, offsetY, BLOCK_WIDTH, BLOCK_HEIGHT);
        final Shape tetrisBlock2 = new Rectangle2D.Double(offsetX + BLOCK_WIDTH, offsetY, BLOCK_WIDTH, BLOCK_HEIGHT);
        final Shape tetrisBlock3 = new Rectangle2D.Double(offsetX + BLOCK_WIDTH, offsetY + BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
        final Shape tetrisBlock4 = new Rectangle2D.Double(offsetX + 2 * BLOCK_WIDTH, offsetY + BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);

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


    public void getNextTetrisPiece(final TetrisPiece theNextPiece) {
        myNextTetrisPiece = theNextPiece;
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

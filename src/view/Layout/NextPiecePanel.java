package view.Layout;

import model.Board;
import model.TetrisPiece;

import static model.Board.PROPERTY_NEXT_PIECE_CHANGES;
import static model.PaintTetromino.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;

import model.PaintTetromino;
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
                    createIShape(g2d, BLOCK_HEIGHT, getHeight(), getWidth());
                    break;
                case L:
                    createLShape(g2d, BLOCK_HEIGHT, getHeight(), getWidth());
                    break;
                case J:
                    createJShape(g2d, BLOCK_HEIGHT, getHeight(), getWidth());
                    break;
                case O:
                    createOShape(g2d, BLOCK_HEIGHT, getHeight(), getWidth());
                    break;
                case S:
                    createSShape(g2d, BLOCK_HEIGHT, getHeight(), getWidth());
                    break;
                case T:
                    createTShape(g2d, BLOCK_HEIGHT, getHeight(), getWidth());
                    break;
                case Z:
                    createZShape(g2d, BLOCK_HEIGHT, getHeight(), getWidth());
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + myNextTetrisPiece);
            }
        }
    }
}

package view.Layout;

import static model.Board.PROPERTY_BOARD_CHANGES;

import model.Board;
import model.TetrisPiece;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static model.Board.PROPERTY_NEXT_PIECE_CHANGES;
import static model.PaintTetromino.*;


public class GamePanel extends JPanel implements PropertyChangeListener {

    /**
     * The width of each block being displayed.
     */
    private static final int BLOCK_WIDTH = 20;

    /**
     * The height of each block being displayed.
     */
    private static final int BLOCK_HEIGHT = 20;

    /**
     * Ensures only one panel is instantiated.
     */
    private static int count = 0;

    /**
     * Used for the width of Graphics2d.
     */
    private static final int STROKE_WIDTH = 1;

    /**
     *
     */
    private final Board myBoard;


    /**
     * Current tetromino in play.
     */
    private TetrisPiece myCurrentTetrisPiece;

    /**
     * Panel that will show the game board with tetrominos in play.
     * Sets background to assigned color.
     */

    public GamePanel(final Board theBoard) {
        super();

        if (count > 0) {
            throw new IllegalArgumentException("Only one GamePanel allowed");
        }
        count++;
        this.myBoard = theBoard;
        this.myBoard.addPropertyChangeListener(this);
        setBackground(Color.RED);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (PROPERTY_BOARD_CHANGES.equals(theEvent.getPropertyName())) {
            repaint();
        }
    }

    /**
     *
     * @param theNextPiece
     */
    public void getNextTetrisPiece(final TetrisPiece theNextPiece) {
        myCurrentTetrisPiece = theNextPiece;
        repaint();
    }


    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setStroke(new BasicStroke(STROKE_WIDTH));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (myCurrentTetrisPiece != null) {
            switch (myCurrentTetrisPiece) {
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
                    throw new IllegalStateException("Unexpected value: "
                            + myCurrentTetrisPiece);
            }
        }
    }

//    @Override
//    public void propertyChange(final PropertyChangeEvent theEvent) {
//        if (PROPERTY_NEXT_PIECE_CHANGES.equals(theEvent.getPropertyName())) {
//            myCurrentTetrisPiece = (TetrisPiece) theEvent.getNewValue();
//            repaint();
//        }
//    }

}

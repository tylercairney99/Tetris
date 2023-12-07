package view.layout;

import static model.Board.*;
import static model.paint.PaintI.createIShape;
import static model.paint.PaintJ.createJShape;
import static model.paint.PaintL.createLShape;
import static model.paint.PaintO.createOShape;
import static model.paint.PaintS.createSShape;
import static model.paint.PaintT.createTShape;
import static model.paint.PaintZ.createZShape;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;

import model.*;

/**
 * Panel that displays the Tetris game.
 *
 * @author James
 * @author Josh
 * @author Tyler
 * @version 3.0
 */
public final class GamePanel extends JPanel implements PropertyChangeListener {

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
     * The game board associated with this menu.
     */
    private final Board myBoard;

    /**
     * Current tetromino in play of type MovableTetrisPiece.
     */
    private MovableTetrisPiece myMovableTetrisPiece;

    /**
     * Current tetromino in play of type TetrisPiece.
     */
    private TetrisPiece myCurrentTetrisPiece;

    private int myX;

    private int myY;

    private Rotation myRotation = Rotation.NONE;

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
        this.myBoard.addPropertyChangeListener( this);
        setBackground(Color.BLACK);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (PROPERTY_CURRENT_PIECE_CHANGES.equals(theEvent.getPropertyName())) {
            myMovableTetrisPiece = (MovableTetrisPiece) theEvent.getNewValue();
            myCurrentTetrisPiece = myMovableTetrisPiece.getTetrisPiece();
            Point tempPoint = myMovableTetrisPiece.getPosition();
            myX = tempPoint.x();
            myY = tempPoint.y();
            myRotation = myMovableTetrisPiece.getRotation();
            repaint();
        }
        if (PROPERTY_BOARD_CHANGES.equals(theEvent.getPropertyName())) {
            setBackground(Color.PINK);
            repaint();
        }
        if (PROPERTY_GAME_OVER.equals(theEvent.getPropertyName())) {
            setBackground(Color.BLACK);
        }
        if (PROPERTY_ROW_CLEARED.equals(theEvent.getPropertyName())) {
            setBackground(Color.ORANGE);
        }
        if (PROPERTY_PIECE_ROTATES.equals(theEvent.getPropertyName())) {
            myRotation = (Rotation) theEvent.getNewValue();
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

        if (myCurrentTetrisPiece != null) {
            switch (myCurrentTetrisPiece) {
                case I:
                    createIShape(g2d, BLOCK_HEIGHT, getHeight() - (myY + 3) * BLOCK_HEIGHT,
                            myX * BLOCK_HEIGHT, myRotation);
                    break;
                case L:
                    createLShape(g2d, BLOCK_HEIGHT, getHeight() - (myY + 3) * BLOCK_HEIGHT,
                            myX * BLOCK_HEIGHT, myRotation);
                    break;
                case J:
                    createJShape(g2d, BLOCK_HEIGHT, getHeight() - (myY + 3) * BLOCK_HEIGHT,
                            myX * BLOCK_HEIGHT, myRotation);
                    break;
                case O:
                    createOShape(g2d, BLOCK_HEIGHT, getHeight() - (myY + 3) * BLOCK_HEIGHT,
                            (myX + 1) * BLOCK_HEIGHT, myRotation);
                    break;
                case S:
                    createSShape(g2d, BLOCK_HEIGHT, getHeight() - (myY + 3) * BLOCK_HEIGHT,
                            myX * BLOCK_HEIGHT, myRotation);
                    break;
                case T:
                    createTShape(g2d, BLOCK_HEIGHT, getHeight() - (myY + 3) * BLOCK_HEIGHT,
                            myX * BLOCK_HEIGHT, myRotation);
                    break;
                case Z:
                    createZShape(g2d, BLOCK_HEIGHT, getHeight() - (myY + 3) * BLOCK_HEIGHT,
                            myX * BLOCK_HEIGHT, myRotation);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: "
                            + myCurrentTetrisPiece);
            }
        }
    }
}
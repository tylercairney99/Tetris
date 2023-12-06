package view.layout;

import static model.Board.PROPERTY_BOARD_CHANGES;
import static model.Board.PROPERTY_CURRENT_PIECE_CHANGES;
import static model.Board.PROPERTY_GAME_OVER;
import static model.Board.PROPERTY_ROW_CLEARED;
import static model.PaintTetromino.createIShape;
import static model.PaintTetromino.createJShape;
import static model.PaintTetromino.createLShape;
import static model.PaintTetromino.createOShape;
import static model.PaintTetromino.createSShape;
import static model.PaintTetromino.createTShape;
import static model.PaintTetromino.createZShape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;
import model.Board;
import model.MovableTetrisPiece;
import model.Point;
import model.TetrisPiece;

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
    }

    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setStroke(new BasicStroke(STROKE_WIDTH));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (myCurrentTetrisPiece != null) {
            System.out.println(myX);
            System.out.println(myY);

            switch (myCurrentTetrisPiece) {
                case I:
                    createIShape(g2d, BLOCK_HEIGHT, getHeight() - (myY + 1) * BLOCK_HEIGHT,
                            myX * BLOCK_HEIGHT);
                    break;
                case L:
                    createLShape(g2d, BLOCK_HEIGHT, getHeight() - (myY + 1) * BLOCK_HEIGHT,
                            myX * BLOCK_HEIGHT);
                    break;
                case J:
                    createJShape(g2d, BLOCK_HEIGHT, getHeight() - (myY + 1) * BLOCK_HEIGHT,
                            myX * BLOCK_HEIGHT);
                    break;
                case O:
                    createOShape(g2d, BLOCK_HEIGHT, getHeight() - (myY + 1) * BLOCK_HEIGHT,
                            myX * BLOCK_HEIGHT);
                    break;
                case S:
                    createSShape(g2d, BLOCK_HEIGHT, getHeight() - (myY + 1) * BLOCK_HEIGHT,
                            myX * BLOCK_HEIGHT);
                    break;
                case T:
                    createTShape(g2d, BLOCK_HEIGHT, getHeight() - (myY + 1) * BLOCK_HEIGHT,
                            myX * BLOCK_HEIGHT);
                    break;
                case Z:
                    createZShape(g2d, BLOCK_HEIGHT, getHeight() - (myY + 1) * BLOCK_HEIGHT,
                            myX * BLOCK_HEIGHT);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: "
                            + myCurrentTetrisPiece);
            }
        }
    }
}
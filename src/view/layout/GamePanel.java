package view.layout;

import static model.Board.PROPERTY_CURRENT_PIECE_CHANGES;
import static model.Board.PROPERTY_PIECE_ROTATES;
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
import java.awt.LinearGradientPaint;
import java.awt.RenderingHints;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import model.Block;
import model.Board;
import model.MovableTetrisPiece;
import model.Point;
import model.Rotation;
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
     * Makes the shape appear above the rendered game panel.
     */
    private static final int HEIGHT_ABOVE_BOARD = 3;

    /**
     * Ensures only one panel is instantiated.
     */
    private static int count;

    /**
     * Used for the width of Graphics2d.
     */
    private static final int STROKE_WIDTH = 1;

    /**
     * The game board associated with this menu.
     */
    private final Board myBoard;


    /**
     * Current tetromino in play of type TetrisPiece.
     */
    private TetrisPiece myCurrentTetrisPiece;

    /**
     * x coordinate of shape.
     */
    private int myX;

    /**
     * y coordinate of shape.
     */
    private int myY;

    /**
     * The current rotation state of the Tetris piece.
     */
    private Rotation myRotation = Rotation.NONE;


    /**
     * Panel that will show the game board with tetrominos in play.
     * Sets background to assigned color.
     */
    @SuppressWarnings("AssignmentToStaticFieldFromInstanceMethod")
    /*
     * warning is suppressed because count is used to ensure only one
     * GamePanel is instantiated.
     */
    public GamePanel(final Board theBoard) {
        super();

        if (count > 0) {
            throw new IllegalArgumentException("Only one GamePanel allowed");
        }
        count++;

        myBoard = theBoard;

    }

    @Override
    @SuppressWarnings("LawOfDemeter")
    /*
     * LawOfDemeter warning is suppressed because it is necessary.
     */
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (PROPERTY_CURRENT_PIECE_CHANGES.equals(theEvent.getPropertyName())) {
            final MovableTetrisPiece movableTetrisPiece = (MovableTetrisPiece)
                    theEvent.getNewValue();
            myCurrentTetrisPiece = movableTetrisPiece.getTetrisPiece();
            final Point tempPoint = movableTetrisPiece.getPosition();
            myX = tempPoint.x();
            myY = tempPoint.y();
            myRotation = movableTetrisPiece.getRotation();
            repaint();

        }
        if (PROPERTY_PIECE_ROTATES.equals(theEvent.getPropertyName())) {
            myRotation = (Rotation) theEvent.getNewValue();
            repaint();
        }
    }

    @SuppressWarnings({"PublicMethodNotExposedInInterface"})
    /*
     * PublicMethodNotExposedInInterface warning is suppressed because paint component is
     * inherited from JComponent
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setStroke(new BasicStroke(STROKE_WIDTH));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        paintGradient(g2d);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        createShape(g2d);

        final List<Block[]> frozenBlocks = myBoard.getFrozenBlocks();
        if (frozenBlocks != null) {
            for (int row = 0; row < frozenBlocks.size(); row++) {
                for (int col = 0; col < frozenBlocks.get(row).length; col++) {
                    final Block block = frozenBlocks.get(row)[col];
                    if (block != null) {
                        g2d.setColor(getColorForBlock(block));
                        final int x = col * BLOCK_WIDTH;
                        final int y = (frozenBlocks.size() - row - 1) * BLOCK_HEIGHT;
                        g2d.fillRect(x, y, BLOCK_WIDTH, BLOCK_HEIGHT);
                        g2d.setColor(Color.BLACK);
                        g2d.drawRect(x, y, BLOCK_WIDTH, BLOCK_HEIGHT);
                    }
                }
            }
        }
    }

    /**
     * Paints the gradient background.
     * @param theGraphics the graphics to be painted.
     */
    private void paintGradient(final Graphics2D theGraphics) {
        final int w = getWidth();
        final int h = getHeight();
        final Color color1 = Color.CYAN;
        final Color color2 = Color.YELLOW;
        final LinearGradientPaint gp = new LinearGradientPaint(0, 0, w, h,
                new float[]{0.0f, 0.5f, 1.0f}, new Color[]{color1, color2, color1});
        theGraphics.setPaint(gp);
        theGraphics.fillRect(0, 0, w, h);
    }

    /**
     * Creates the shape of the tetromino.
     *
     * @param theG2d Graphics2d object for drawing.
     */
    @SuppressWarnings("OverlyLongMethod")
    /*
     * method is overly long but necessary.
     */
    private void createShape(final Graphics2D theG2d) {
        if (myCurrentTetrisPiece != null) {
            switch (myCurrentTetrisPiece) {
                case I:
                    createIShape(theG2d, BLOCK_HEIGHT, getHeight() - (
                            myY + HEIGHT_ABOVE_BOARD) * BLOCK_HEIGHT,
                            myX * BLOCK_HEIGHT, myRotation);
                    break;
                case L:
                    createLShape(theG2d, BLOCK_HEIGHT, getHeight() - (
                            myY + HEIGHT_ABOVE_BOARD) * BLOCK_HEIGHT,
                            myX * BLOCK_HEIGHT, myRotation);
                    break;
                case J:
                    createJShape(theG2d, BLOCK_HEIGHT, getHeight() - (
                            myY + HEIGHT_ABOVE_BOARD) * BLOCK_HEIGHT,
                            myX * BLOCK_HEIGHT, myRotation);
                    break;
                case O:
                    createOShape(theG2d, BLOCK_HEIGHT, getHeight() - (
                            myY + HEIGHT_ABOVE_BOARD) * BLOCK_HEIGHT,
                            (myX + 1) * BLOCK_HEIGHT, myRotation);
                    break;
                case S:
                    createSShape(theG2d, BLOCK_HEIGHT, getHeight() - (
                            myY + HEIGHT_ABOVE_BOARD) * BLOCK_HEIGHT,
                            myX * BLOCK_HEIGHT, myRotation);
                    break;
                case T:
                    createTShape(theG2d, BLOCK_HEIGHT, getHeight() - (
                            myY + HEIGHT_ABOVE_BOARD) * BLOCK_HEIGHT,
                            myX * BLOCK_HEIGHT, myRotation);
                    break;
                case Z:
                    createZShape(theG2d, BLOCK_HEIGHT, getHeight() - (
                            myY + HEIGHT_ABOVE_BOARD) * BLOCK_HEIGHT,
                            myX * BLOCK_HEIGHT, myRotation);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: "
                            + myCurrentTetrisPiece);
            }
        }
    }

    /**
     * Returns the color associated with the specified type of block.
     *
     * @param theBlock The type for which the color is required.
     * @return The color associated with the specified block type.
     */
    private Color getColorForBlock(final Block theBlock) {
        return switch (theBlock) {
            case I -> Color.CYAN;
            case J -> Color.BLUE;
            case L -> Color.ORANGE;
            case O -> Color.YELLOW;
            case S -> Color.GREEN;
            case T -> Color.MAGENTA;
            case Z -> Color.PINK;
            default -> Color.GRAY;
        };
    }
}
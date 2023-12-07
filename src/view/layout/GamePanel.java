package view.layout;

import static model.Board.*;
import static model.paint.PaintI.createIShape;
import static model.paint.PaintJ.createJShape;
import static model.paint.PaintL.createLShape;
import static model.paint.PaintO.createOShape;
import static model.paint.PaintS.createSShape;
import static model.paint.PaintT.createTShape;
import static model.paint.PaintZ.createZShape;


import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

import java.util.*;

import model.*;
import model.Point;

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
        this.myBoard.addPropertyChangeListener(this);

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

        final int w = getWidth();
        final int h = getHeight();
        final Color color1 = Color.PINK;
        final Color color2 = Color.MAGENTA;
        final LinearGradientPaint gp = new LinearGradientPaint(0, 0, w, h,
                new float[]{0.0f, 0.5f, 1.0f}, new Color[]{color1, color2, color1});
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

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

        // Draw the frozen blocks on the game panel.
        List<Block[]> frozenBlocks = myBoard.getFrozenBlocks();
        if (frozenBlocks != null) {
            for (int row = 0; row < frozenBlocks.size(); row++) {
                for (int col = 0; col < frozenBlocks.get(row).length; col++) {
                    Block block = frozenBlocks.get(row)[col];
                    if (block != null) {
                        g2d.setColor(getColorForBlock(block)); // Set color based on block type
                        int x = col * BLOCK_WIDTH;
                        int y = (frozenBlocks.size() - row - 1) * BLOCK_HEIGHT; // Invert y-axis
                        g2d.fillRect(x, y, BLOCK_WIDTH, BLOCK_HEIGHT);
                        g2d.setColor(Color.BLACK); // Set color for block border
                        g2d.drawRect(x, y, BLOCK_WIDTH, BLOCK_HEIGHT); // Draw block border
                    }
                }
            }
        }
    }

    /**
     * Returns the color associated with the specified type of block.
     *
     * @param block The type for which the color is required.
     * @return The color associated with the specified block type.
     */
    private Color getColorForBlock(Block block) {
        return switch (block) {
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
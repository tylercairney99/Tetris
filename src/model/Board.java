/*
 * TCSS 305
 *
 * An implementation of the classic game "Tetris".
 */

package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import model.wallkicks.WallKick;

/**
 * Represents a Tetris board. Board objects communicate with clients via Observer pattern. 
 * <p>Clients can expect Board objects to call norifyObservers with four different 
 * data types:</p>
 * <dl>
 * <dt>{@code List<Block[]>}</dt>
 * <dd>Represents the non-moving pieces on the Board. i.e. Frozen Blocks</dd>
 * <dt>{@link MovableTetrisPiece MovableTerisPiece}</dt>
 * <dd>Represents current moving Piece.</dd>
 * <dt>{@link TetrisPiece TertisPiece}</dt>
 * <dd>Represents next Piece.</dd>
 * <dt>{@code Integer[]}</dt>
 * <dd>The size of the array represents the number of rows of Frozen Blocks removed.</dd>
 * <dt>{@code Boolean}</dt>
 * <dd>When true, the game is over. </dd>
 * </dl>
 *
 * @author Charles Bryan
 * @author Alan Fowler
 * @version 1.3
 */
@SuppressWarnings({"ClassWithTooManyFields", "ClassWithTooManyMethods", "OverlyComplexClass"})
/*
 * This fields and methods in this class are required for tetris.
 */
public class Board implements MyBoard {

    /**
     * Ensures only one panel is instantiated.
     */
    private static int count;

    /**
     * Manager for Property Change Listeners.
     */
    private final PropertyChangeSupport myPcs;

    /**
     * Width of the game board.
     */
    private final int myWidth;

    /**
     * Height of the game board.
     */
    private final int myHeight;

    /**
     * The frozen blocks on the board.
     */
    private final List<Block[]> myFrozenBlocks;

    /**
     * The game over state.
     */
    private boolean myGameOver = true;

    /**
     * Contains a non random sequence of TetrisPieces to loop through.
     */
    private List<TetrisPiece> myNonRandomPieces;

    /**
     * The current index in the non random piece sequence.
     */
    private int mySequenceIndex;

    /**
     * Piece that is next to play.
     */
    private TetrisPiece myNextPiece;

    /**
     * Piece that is currently movable.
     */
    private MovableTetrisPiece myCurrentPiece;

    /**
     * A flag to indicate when moving a piece down is part of a drop operation.
     * This is used to prevent the Board from notifying observers for each incremental
     * down movement in the drop.
     */
    private boolean myDrop;

    /**
     * Default Tetris board constructor.
     * Creates a standard size tetris game board.
     */
    public Board() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);

    }

    /**
     * Tetris board constructor for non-default sized boards.
     *
     * @param theWidth Width of the Tetris game board.
     * @param theHeight Height of the Tetris game board.
     */
    @SuppressWarnings("AssignmentToStaticFieldFromInstanceMethod")
    /*
     * warning is suppressed because count is used to ensure only one
     * Board is instantiated.
     */
    public Board(final int theWidth, final int theHeight) {
        super();
        if (count > 0) {
            throw new IllegalArgumentException("Only one board allowed");
        }
        count++;
        myWidth = theWidth;
        myHeight = theHeight;
        myFrozenBlocks = new LinkedList<>();
        myNonRandomPieces = new ArrayList<>();
        mySequenceIndex = 0;
        myPcs = new PropertyChangeSupport(this);
    }

    /**
     * Get the width of the board.
     *
     * @return Width of the board.
     */
    @Override
    public int getWidth() {
        return myWidth;
    }

    /**
     * Get the height of the board.
     *
     * @return Height of the board.
     */
    @Override
    public int getHeight() {
        return myHeight;
    }

    /**
     * Resets the board for a new game.
     * This method must be called before the first game
     * and before each new game.
     */
    @Override
    public void newGame() {
        if (myGameOver) {
            mySequenceIndex = 0;
            myFrozenBlocks.clear();
            for (int h = 0; h < myHeight; h++) {
                myFrozenBlocks.add(new Block[myWidth]);
            }

            myGameOver = false;
            myCurrentPiece = nextMovablePiece(true);
            myPcs.firePropertyChange(PROPERTY_CURRENT_PIECE_CHANGES, null, myCurrentPiece);
            myPcs.firePropertyChange(PROPERTY_NEW_GAME, null, null);
            myDrop = false;
        }
    }

    /**
     * Sets a non random sequence of pieces to loop through.
     *
     * @param thePieces the List of non random TetrisPieces.
     */
    @Override
    public void setPieceSequence(final List<TetrisPiece> thePieces) {
        myNonRandomPieces = new ArrayList<>(thePieces);
        mySequenceIndex = 0;
        myCurrentPiece = nextMovablePiece(true);
    }

    /**
     * Advances the board by one 'step'.
     * <p>
     * This could include
     * - moving the current piece down 1 line
     * - freezing the current piece if appropriate
     * - clearing full lines as needed
     */
    @Override
    public void step() {
        down();
    }

    /**
     * Try to move the movable piece down.
     * Freeze the Piece in position if down tries to move into an illegal state.
     * Clear full lines.
     */
    @Override
    public void down() {
        if (!move(myCurrentPiece.down())) {
            addPieceToBoardData(myFrozenBlocks, myCurrentPiece);
            checkRows();
            if (!myGameOver) {
                myCurrentPiece = nextMovablePiece(false);
            }
            myPcs.firePropertyChange(PROPERTY_CURRENT_PIECE_CHANGES, null, myCurrentPiece);
        }
    }

    /**
     * Try to move the movable piece left.
     */
    @Override
    public void left() {
        if (myCurrentPiece != null) {
            move(myCurrentPiece.left());
            myPcs.firePropertyChange(PROPERTY_CURRENT_PIECE_CHANGES, null, myCurrentPiece);
        }
    }

    /**
     * Try to move the movable piece right.
     */
    @Override
    public void right() {
        if (myCurrentPiece != null) {
            move(myCurrentPiece.right());
            myPcs.firePropertyChange(PROPERTY_CURRENT_PIECE_CHANGES, null, myCurrentPiece);
        }
    }

    /**
     * Try to rotate the movable piece in the clockwise direction.
     */
    @Override
    public void rotateCW() {
        if (myCurrentPiece != null) {
            if (myCurrentPiece.getTetrisPiece() == TetrisPiece.O) {
                move(myCurrentPiece.rotateCW());
                myPcs.firePropertyChange(PROPERTY_PIECE_ROTATES, null,
                        myCurrentPiece.getRotation());
            } else {
                final MovableTetrisPiece cwPiece = myCurrentPiece.rotateCW();
                final Point[] offsets = WallKick.getWallKicks(cwPiece.getTetrisPiece(),
                        myCurrentPiece.getRotation(),
                        cwPiece.getRotation());
                for (final Point p : offsets) {
                    final Point offsetLocation = cwPiece.getPosition().transform(p);
                    final MovableTetrisPiece temp = cwPiece.setPosition(offsetLocation);
                    if (move(temp)) {
                        break;
                    }
                }
            }
        }
    }

    /**
     * Try to rotate the movable piece in the counter-clockwise direction.
     */
    @Override
    public void rotateCCW() {
        if (myCurrentPiece != null) {
            if (myCurrentPiece.getTetrisPiece() == TetrisPiece.O) {
                move(myCurrentPiece.rotateCCW());
            } else {
                final MovableTetrisPiece ccwPiece = myCurrentPiece.rotateCCW();
                myPcs.firePropertyChange(PROPERTY_PIECE_ROTATES, null,
                        myCurrentPiece.getRotation());
                final Point[] offsets = WallKick.getWallKicks(ccwPiece.getTetrisPiece(),
                        myCurrentPiece.getRotation(),
                        ccwPiece.getRotation());
                for (final Point p : offsets) {
                    final Point offsetLocation = ccwPiece.getPosition().transform(p);
                    final MovableTetrisPiece temp = ccwPiece.setPosition(offsetLocation);
                    if (move(temp)) {
                        break;
                    }
                }
            }
        }
    }

    /**
     * Drop the piece until piece is set.
     */
    @Override
    public void drop() {
        if (!myGameOver) {
            myDrop = true;
            while (isPieceLegal(myCurrentPiece.down())) {
                down();
            }
            myDrop = false;
            down();
            myPcs.firePropertyChange(PROPERTY_CURRENT_PIECE_CHANGES, null, myCurrentPiece);
        }
    }

    @SuppressWarnings("OverlyLongMethod")
    @Override
    public String toString() {
        final List<Block[]> board = getBoard();
        board.add(new Block[myWidth]);
        board.add(new Block[myWidth]);
        board.add(new Block[myWidth]);
        board.add(new Block[myWidth]);
        final String line = "-".repeat(this.myWidth);
        if (myCurrentPiece != null) {
            addPieceToBoardData(board, myCurrentPiece);
        }
        final StringBuilder sb = new StringBuilder();
        for (int i = board.size() - 1; i >= 0; i--) {
            final Block[] row = board.get(i);
            sb.append('|');
            for (final Block c : row) {
                if (c == null) {
                    sb.append(' ');
                } else {
                    sb.append('*');
                }
            }
            sb.append("|\n");
            if (i == this.myHeight) {
                sb.append(' ');
                sb.append(line);
                sb.append('\n');
            }
        }
        sb.append('|');
        sb.append(line);
        sb.append('|');
        return sb.toString();
    }

    /**
     * Helper function to check if the current piece can be shifted to the
     * specified position.
     *
     * @param theMovedPiece the position to attempt to shift the current piece
     * @return True if the move succeeded
     */
    private boolean move(final MovableTetrisPiece theMovedPiece) {
        boolean result = false;
        if (isPieceLegal(theMovedPiece)) {
            myCurrentPiece = theMovedPiece;
            result = true;
            if (!myDrop) {
                myPcs.firePropertyChange(PROPERTY_CURRENT_PIECE_CHANGES, null, myCurrentPiece);
            }
        }
        return result;
    }

    /**
     * Helper function to test if the piece is in a legal state.
     * <p>
     * Illegal states:
     * - points of the piece exceed the bounds of the board
     * - points of the piece collide with frozen blocks on the board
     *
     * @param thePiece MovableTetrisPiece to test.
     * @return Returns true if the piece is in a legal state; false otherwise
     */
    private boolean isPieceLegal(final MovableTetrisPiece thePiece) {
        boolean result = true;
        for (final Point p : thePiece.getBoardPoints()) {
            if (p.x() < 0 || p.x() >= myWidth) {
                result = false;
            }
            if (p.y() < 0) {
                result = false;
            }
        }
        return result && !collision(thePiece);
    }

    /**
     * Adds a movable Tetris piece into a list of board data.
     * <p>
     * Allows a single data structure to represent the current piece
     * and the frozen blocks.
     *
     * @param theFrozenBlocks Board to set the piece on.
     * @param thePiece Piece to set on the board.
     */
    private void addPieceToBoardData(final List<Block[]> theFrozenBlocks,
                                     final MovableTetrisPiece thePiece) {
        for (final Point p : thePiece.getBoardPoints()) {
            //noinspection LawOfDemeter
            setPoint(theFrozenBlocks, p, thePiece.getTetrisPiece().getBlock());
        }
    }

    /**
     * Checks the board for complete rows.
     */
    private void checkRows() {
        final List<Integer> completeRows = new ArrayList<>();
        for (final Block[] row : myFrozenBlocks) {
            boolean complete = true;
            for (final Block b : row) {
                if (b == null) {
                    complete = false;
                    myPcs.firePropertyChange(PROPERTY_FROZEN_PIECE, null, myFrozenBlocks);
                    break;
                }
            }
            if (complete) {
                completeRows.add(myFrozenBlocks.indexOf(row));
            }
        }
        myPcs.firePropertyChange(PROPERTY_ROW_CLEARED, null, completeRows.size());
        if (!completeRows.isEmpty()) {
            for (int i = completeRows.size() - 1; i >= 0; i--) {
                final Block[] row = myFrozenBlocks.get(completeRows.get(i));
                myFrozenBlocks.remove(row);
                myFrozenBlocks.add(new Block[myWidth]);
            }
        }
    }

    /**
     * Helper function to copy the board.
     *
     * @return A new copy of the board.
     */
    private List<Block[]> getBoard() {
        final List<Block[]> board = new ArrayList<>();
        for (final Block[] row : myFrozenBlocks) {
            board.add(row.clone());
        }
        return board;
    }

    /**
     * Determines if a point is on the game board.
     *
     * @param theBoard Board to test.
     * @param thePoint Point to test.
     * @return True if the point is on the board otherwise false.
     */
    private boolean isPointOnBoard(final List<Block[]> theBoard, final Point thePoint) {
        return thePoint.x() >= 0 && thePoint.x() < myWidth && thePoint.y() >= 0
                && thePoint.y() < theBoard.size();
    }

    /**
     * Sets a block at a board point.
     *
     * @param theBoard Board to set the point on.
     * @param thePoint Board point.
     * @param theBlock Block to set at board point.
     */
    private void setPoint(final List<Block[]> theBoard,
                          final Point thePoint,
                          final Block theBlock) {
        if (isPointOnBoard(theBoard, thePoint)) {
            final Block[] row = theBoard.get(thePoint.y());
            row[thePoint.x()] = theBlock;
        } else if (!myGameOver) {
            myGameOver = true;
            myPcs.firePropertyChange(PROPERTY_GAME_OVER, false, true);
        }
    }

    /**
     * Returns the block at a specific board point.
     *
     * @param thePoint the specific Point to check
     * @return the Block type at point or null if no block exists.
     */
    private Block getPoint(final Point thePoint) {
        Block b = null;
        if (isPointOnBoard(myFrozenBlocks, thePoint)) {
            b = myFrozenBlocks.get(thePoint.y())[thePoint.x()];
        }
        return b;
    }

    /**
     * Helper function to determine of a movable block has collided with set
     * blocks.
     *
     * @param theTest movable TetrisPiece to test for collision.
     * @return Returns true if any of the blocks has collided with a set board
     *         block.
     */
    private boolean collision(final MovableTetrisPiece theTest) {
        boolean res = false;
        for (final Point p : theTest.getBoardPoints()) {
            if (getPoint(p) != null) {
                res = true;
            }
        }
        return res;
    }

    /**
     * Gets the next MovableTetrisPiece.
     *
     * @param theRestart Restart the non random cycle.
     * @return A new MovableTetrisPiece.
     */
    private MovableTetrisPiece nextMovablePiece(final boolean theRestart) {

        if (myNextPiece == null || theRestart) {
            prepareNextMovablePiece();
        }

        final TetrisPiece next = myNextPiece;

        int startY = myHeight - 1;
        if (myNextPiece == TetrisPiece.I) {
            startY--;
        }

        prepareNextMovablePiece();
        return new MovableTetrisPiece(
                next,
                new Point((myWidth - myNextPiece.getWidth()) / 2, startY));
    }

    /**
     * Prepares the Next movable piece for preview.
     */
    private void prepareNextMovablePiece() {
        final boolean share = myNextPiece != null;
        if (myNonRandomPieces == null || myNonRandomPieces.isEmpty()) {
            myNextPiece = TetrisPiece.getRandomPiece();
        } else {
            mySequenceIndex %= myNonRandomPieces.size();
            myNextPiece = myNonRandomPieces.get(mySequenceIndex++);
        }
        if (share && !myGameOver) {
            updateNextPiece();
        }
    }

    @Override
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);
    }

    @Override
    public void addPropertyChangeListener(final String thePropertyName,
                                          final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(thePropertyName, theListener);
    }

    @Override
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(theListener);
    }

    @Override
    public void removePropertyChangeListener(final String thePropertyName,
                                             final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(thePropertyName, theListener);
    }

    /**
     * Updates the next piece for the game, either from a predefined sequence or randomly.
     * This method determines and updates the next Tetris piece. Observers are notified about
     * the change in the next piece, allowing GUI components to display the upcoming piece.
     * It supports both random piece generation and predefined piece sequences.
     * Usage:
     *   Call this method after a piece has landed to determine the next piece in the game.
     *   It should be part of the game loop or piece settling logic.
     */
    private void updateNextPiece() {
        if (myNonRandomPieces == null || myNonRandomPieces.isEmpty()) {
            myNextPiece = TetrisPiece.getRandomPiece();
        } else {
            mySequenceIndex %= myNonRandomPieces.size();
            myNextPiece = myNonRandomPieces.get(mySequenceIndex++);
        }
        myPcs.firePropertyChange(PROPERTY_NEXT_PIECE_CHANGES, null, myNextPiece);
    }
}

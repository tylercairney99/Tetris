package model;

import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * Interface for the Tetris Board. This interface defines the essential actions and
 * functionalities of a Tetris game board.
 * Implementing classes are expected to manage game state, handle piece movements,
 * and update game status accordingly.
 *
 * @author Tyler Cairney
 * @version 1.0
 */
public interface MyBoard {

    /**
     * Gets the width of the board.
     *
     * @return The width of the board.
     */
    int getWidth();

    /**
     * Gets the height of the board.
     *
     * @return The height of the board.
     */
    int getHeight();

    /**
     * Starts a new game. This should reset the board and prepare it for a new game session.
     */
    void newGame();

    /**
     * Sets a non-random sequence of Tetris pieces to be used by the board.
     *
     * @param thePieces The list of Tetris pieces to be used in sequence.
     */
    void setPieceSequence(List<TetrisPiece> thePieces);

    /**
     * Advances the board by one step. This typically involves moving the current piece,
     * handling any line completions, and updating the game state.
     */
    void step();

    /**
     * Moves the current piece down by one row, if possible.
     */
    void down();

    /**
     * Moves the current piece one column to the left, if possible.
     */
    void left();

    /**
     * Moves the current piece one column to the right, if possible.
     */
    void right();

    /**
     * Rotates the current piece in a clockwise direction, if possible.
     */
    void rotateCW();

    /**
     * Rotates the current piece in a counter-clockwise direction, if possible.
     */
    void rotateCCW();

    /**
     * Instantly drops the current piece to the bottom of the board.
     */
    void drop();

    /**
     * Provides a string representation of the current state of the board.
     * This typically includes the layout of the Tetris pieces on the board.
     *
     * @return A string representing the current state of the board.
     */
    @Override
    String toString();

    // Implementation of Observer Design Pattern

    /**
     * Add a PropertyChangeListener to the listener list. The listener is registered for
     * all properties. The same listener object may be added more than once, and will be
     * called as many times as it is added. If listener is null, no exception is thrown and
     * no action is taken.
     *
     * @param theListener The PropertyChangeListener to be added
     */
    void addPropertyChangeListener(PropertyChangeListener theListener);

    /**
     * Add a PropertyChangeListener for a specific property. The listener will be invoked only
     * when a call on firePropertyChange names that specific property. The same listener object
     * may be added more than once. For each property, the listener will be invoked the number
     * of times it was added for that property. If propertyName or listener is null, no
     * exception is thrown and no action is taken.
     *
     * @param thePropertyName The name of the property to listen on.
     * @param theListener The PropertyChangeListener to be added
     */
    void addPropertyChangeListener(String thePropertyName, PropertyChangeListener theListener);

    /**
     * Remove a PropertyChangeListener from the listener list. This removes a
     * PropertyChangeListener that was registered for all properties. If listener was added
     * more than once to the same event source, it will be notified one less time after being
     * removed. If listener is null, or was never added, no exception is thrown and no action
     * is taken.
     *
     * @param theListener The PropertyChangeListener to be removed
     */
    void removePropertyChangeListener(PropertyChangeListener theListener);

    /**
     * Remove a PropertyChangeListener for a specific property. If listener was added more than
     * once to the same event source for the specified property, it will be notified one less
     * time after being removed. If propertyName is null, no exception is thrown and no action
     * is taken. If listener is null, or was never added for the specified property, no
     * exception is thrown and no action is taken.
     *
     * @param thePropertyName The name of the property that was listened on.
     * @param theListener The PropertyChangeListener to be removed
     */
    void removePropertyChangeListener(String thePropertyName,
                                      PropertyChangeListener theListener);

}


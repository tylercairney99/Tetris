package model;

/**
 * Interface defining methods for changing and retrieving the difficulty level of a game.
 *
 * @author Group 7
 * @version 1.0
 */
public interface MyDifficultyChanger {

    /**
     * This method sets the new difficulty for the game,
     * adjusts the game timer's delay accordingly, restarts
     * the game board to apply the new difficulty, and restarts the game timer.
     *
     * @param theNewDifficulty The new difficulty level to set.
     *                      This should be one of the predefined constants: EASY_DIFFICULTY,
     *                      MEDIUM_DIFFICULTY, or HARD_DIFFICULTY.
     */
    void changeDifficulty(int theNewDifficulty);

    /**
     * This method returns a string representation of the current difficulty level.
     *
     * @return Easy / Medium / Hard / if none are chosen defaults to easy.
     */
    String getCurrentDifficulty();

    /**
     * gets current difficulty.
     *
     * @return myCurrentDifficulty (Constant for times step is called per second).
     */
    int getCurrentDifficultyValue();
}

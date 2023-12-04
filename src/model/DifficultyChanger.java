package model;

/**
 * Interface defining methods for changing and retrieving the difficulty level of a game.
 *
 * @author Group 7
 * @version 1.0
 */
public interface DifficultyChanger  {

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
     * It maps the internal numeric difficulty value to a human-readable format.
     *
     * @return A string representing the current difficulty level. Possible values are "Easy",
     *         "Medium", "Hard". If the current difficulty does not match any of the predefined
     *         levels, it defaults to "Easy".
     */
    String getCurrentDifficulty();
}

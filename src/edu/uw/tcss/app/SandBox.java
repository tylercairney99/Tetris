package edu.uw.tcss.app;

import java.util.logging.Logger;
import model.Board;

/**
 * This class serves as a sandbox for testing the functionalities of the Board class.
 * It demonstrates various actions that can be performed on a Board object such as
 * starting a new game, taking steps, rotating, and dropping.
 * This is a utility class containing only static methods,
 * and thus is not meant to be instantiated.
 *
 * @author Tyler Cairney
 * @version 1.0
 **/
public final class SandBox {

    /**
     * Logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(SandBox.class.getName());

    /**
     * Private constructor to prevent instantiation of this utility class.
     *
     * @throws AssertionError (if instantiation is attempted)
     */
    private SandBox() {
        super();
        throw new AssertionError("Utility classes cannot be instantiated");
    }

    /**
     * The main method that serves as the entry point for the sandbox test.
     * It creates a new Board instance and demonstrates its functionalities
     * through various actions and logging the results.
     *
     * @param theArgs Command line arguments (not used in this application).
     */
    public static void main(final String[] theArgs) {
        final Board b = new Board();
        b.newGame();

        LOGGER.info(b.toString());

        b.step();
        LOGGER.info(b.toString());
        b.rotateCW();
        LOGGER.info(b.toString());
        b.rotateCW();
        LOGGER.info(b.toString());
        b.rotateCW();
        LOGGER.info(b.toString());
        b.rotateCW();
        LOGGER.info(b.toString());
        b.drop();
        LOGGER.info(b.toString());
    }
}

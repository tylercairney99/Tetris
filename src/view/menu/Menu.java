package view.menu;

import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import model.Board;


/**
 * A class representing the menu for a Tetris game.
 *
 * @author Group 7
 * @version 1.2
 */
public class Menu extends JMenuBar {

    /**
     * The game board associated with this menu.
     * It represents the current state of the Tetris game, including the arrangement
     * of Tetris blocks and handling game logic.
     */
    private final Board myBoard;

    /**
     * The timer that controls the game's timing and updates.
     * This timer is used to advance the state of the game at regular intervals,
     * such as moving Tetris blocks down the game board.
     */
    private final Timer myGameTimer;

    /**
     * Constructs a Menu object.
     *
     * @param theBoard The game board.
     * @param theGameTimer The game timer.
     */
    public Menu(final Board theBoard, final Timer theGameTimer) {
        super();
        myBoard = theBoard;
        myGameTimer = theGameTimer;
    }

    private void initializeMenu() {
        final JMenuBar menuBar = createMenu();
        this.add(menuBar);
    }

    /**
     * Creates the menu bar for the game.
     *
     * @return The menu bar.
     */
    private JMenuBar createMenu() {
        final JMenuBar menuBar = new JMenuBar();

        menuBar.add(buildGameMenu());
        menuBar.add(buildAboutMenu());

        return menuBar;
    }

    /**
     * Builds the game menu.
     *
     * @return The game menu.
     */
    private JMenu buildGameMenu() {
        final JMenu gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);

        final JMenuItem newGameItem = new JMenuItem("New Game");
        final JMenuItem exitItem = new JMenuItem("Exit");

        gameMenu.add(newGameItem);
        gameMenu.addSeparator();
        gameMenu.add(exitItem);

        newGameItem.addActionListener(theEvent -> {
            myBoard.newGame();
            myGameTimer.start();
            JOptionPane.showMessageDialog(null, "Starting a New Game!");
        });

        exitItem.addActionListener(theEvent -> System.exit(0));

        return gameMenu;
    }

    /**
     * Builds the about menu.
     *
     * @return The about menu.
     */
    private JMenu buildAboutMenu() {
        final JMenu aboutMenu = new JMenu("About");
        aboutMenu.setMnemonic(KeyEvent.VK_A);

        final JMenuItem aboutUsItem = new JMenuItem("About Us");

        aboutMenu.add(aboutUsItem);

        aboutUsItem.addActionListener(theEvent ->
                JOptionPane.showMessageDialog(null, """
                    Group 7 Tetris Project - Sprint 2
                    Group Members: Tyler, James, Josh, Cam
                    This is version 1.1 of our Tetris project
                    """));

        return aboutMenu;
    }
}

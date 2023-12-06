package view.menu;

import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import model.Board;
import view.controller.TetrisGUI;


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
     * A reference to the main Tetris GUI instance.
     * This field holds a reference to the TetrisGUI object, which is the primary controller
     * for the Tetris game interface. It is used to interact with the overall game window,
     * including updating game settings like difficulty, and managing the game's state.
     */
    private final TetrisGUI myTetrisGUI;

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
    public Menu(final Board theBoard, final Timer theGameTimer, final TetrisGUI theTetrisGUI) {
        super();
        initializeMenu();
        myBoard = theBoard;
        myGameTimer = theGameTimer;
        myTetrisGUI = theTetrisGUI;
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
        menuBar.add(buildDifficultyMenu());
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
            JOptionPane.showMessageDialog(null, "Starting a New Game on "
                    + myTetrisGUI.getCurrentDifficulty() + " Difficulty!");
        });

        exitItem.addActionListener(theEvent -> System.exit(0));

        return gameMenu;
    }

    /**
     * Builds the difficulty menu.
     *
     * @return The difficulty menu.
     */
    private JMenu buildDifficultyMenu() {
        final JMenu difficultyMenu = new JMenu("Difficulty");
        difficultyMenu.setMnemonic(KeyEvent.VK_D);

        final JMenuItem difficultyEasyItem = new JMenuItem("Easy");
        final JMenuItem difficultyMediumItem = new JMenuItem("Medium");
        final JMenuItem difficultyHardItem = new JMenuItem("Hard");

        difficultyEasyItem.addActionListener(theEvent -> {
            myTetrisGUI.changeDifficulty(TetrisGUI.EASY_DIFFICULTY);
        });
        difficultyMediumItem.addActionListener(theEvent ->  {
            myTetrisGUI.changeDifficulty(TetrisGUI.MEDIUM_DIFFICULTY);
        });
        difficultyHardItem.addActionListener(theEvent ->  {
            myTetrisGUI.changeDifficulty(TetrisGUI.HARD_DIFFICULTY);
        });

        difficultyMenu.add(difficultyEasyItem);
        difficultyMenu.addSeparator();
        difficultyMenu.add(difficultyMediumItem);
        difficultyMenu.addSeparator();
        difficultyMenu.add(difficultyHardItem);

        return difficultyMenu;

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
        final JMenuItem scoreItem = new JMenuItem("Scoring Info");

        aboutMenu.add(aboutUsItem);
        aboutMenu.addSeparator();
        aboutMenu.add(scoreItem);

        aboutUsItem.addActionListener(theEvent ->
                JOptionPane.showMessageDialog(null, """
                Group 7 Tetris Project - Sprint 3
                Group Members: Tyler, James, Josh, Cam
                This is version 1.2 of our Tetris project
                """));

        scoreItem.addActionListener(theEvent ->
                JOptionPane.showMessageDialog(null, """
                How the scoring works:
                Points are awarded based on the amount of lines cleared,
                and the level that you are currently on.
                Every 5 lines cleared, you progress 1 level.
                For example, given that you are on level n,
                1 line cleared = 40 * n points awarded.
                2 lines cleared = 100 * n points awarded.
                3 lines cleared = 300 * n points awarded.
                4 lines cleared = 1200 * n points awarded.
                """));

        return aboutMenu;
    }
}

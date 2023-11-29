package view.Menu;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Menu extends JMenuBar {

    public Menu() {
        super();
        add(createMenu());
    }

    private JMenuBar createMenu() {
        final JMenuBar menuBar = new JMenuBar();

        menuBar.add(buildGameMenu());
        menuBar.add(buildAboutMenu());

        return menuBar;
    }

    private JMenu buildGameMenu() {
        final JMenu gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);

        final JMenuItem newGameItem = new JMenuItem("New Game");
        final JMenuItem exitItem = new JMenuItem("Exit");

        gameMenu.add(newGameItem);
        gameMenu.addSeparator();
        gameMenu.add(exitItem);

        newGameItem.addActionListener(theEvent -> {
            JOptionPane.showMessageDialog(null, "Starting a New Game!");
        });

        /**
         *  REPLACE WITH THIS BLOCK OF CODE
         *
         *
         * exitItem.addActionListener(theEvent ->
         *                 theFrame.dispatchEvent(new WindowEvent(theFrame, WindowEvent.WINDOW_CLOSING)));
         *
         */
        exitItem.addActionListener(theEvent -> {
            System.exit(0);
        });

        return gameMenu;
    }

    private JMenu buildAboutMenu() {
        final JMenu aboutMenu = new JMenu("About");
        aboutMenu.setMnemonic(KeyEvent.VK_A);

        final JMenuItem aboutUsItem = new JMenuItem("About Us");

        aboutMenu.add(aboutUsItem);

        aboutUsItem.addActionListener(theEvent -> {
            JOptionPane.showMessageDialog(null, " Group 7 Tetris Project - Sprint 2 \n" +
                    "\n Group Members:  Tyler, James, Josh, Cam " +
                    "\n This is version 1.1 of our Tetris project");
        });

        return aboutMenu;
    }
}


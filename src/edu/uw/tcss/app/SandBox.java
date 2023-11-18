package edu.uw.tcss.app;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
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
public final class SandBox extends JPanel {

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
        super(new BorderLayout());
        throw new AssertionError("Utility classes cannot be instantiated");

//        setUpComponents();
    }

//    private void setUpComponents() {
//        add();
//    }




    private JMenuBar createMenu(final JFrame theFrame) {
        final JMenuBar menuBar = new JMenuBar();

        menuBar.add(buildFileMenu(theFrame));
        //menuBar.add()
        return menuBar;
    }

    private JMenu buildFileMenu(final JFrame theFrame) {
        final JMenu menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        menu.add(buildSubMenu());
        menu.addSeparator();
        return menu;
    }



    private JMenu buildSubMenu() {
        final JMenu subMenu = new JMenu("What do we want here?");

        subMenu.add(buildSimpleMenuItem("Hello"));
        return subMenu;
    }

    private JMenuItem buildSimpleMenuItem(final String theText) {
        final JMenuItem item = new JMenuItem(theText);
        item.addActionListener(theEvent ->
                JOptionPane.showMessageDialog(SandBox.this, theText));
        return item;
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

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (final UnsupportedLookAndFeelException
                    | ClassNotFoundException
                    | InstantiationException
                    | IllegalAccessException ex) {
//            System.out.println(ex);


            javax.swing.SwingUtilities.invokeLater(SandBox::createAndShowGUI);

        }
    }

    private static void createAndShowGUI() {
        final JFrame frame = new JFrame("Group 7 Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final SandBox newContentPane = new SandBox();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setJMenuBar(newContentPane.createMenu(frame));

        frame.pack();
        frame.setVisible(true);
    }
}

package view;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * 
 */
public final class TetrisGUI extends JFrame {

    /**
     *
     */
    public TetrisGUI() {
        super();
        setJMenuBar(new Menu());

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);
    }


    public static void main(String[] args) {
        // Ensure GUI is created in the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new view.TetrisGUI());
    }

}

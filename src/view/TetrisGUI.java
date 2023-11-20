package view;

import javax.swing.*;

public class TetrisGUI extends JFrame {

    public TetrisGUI() {
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

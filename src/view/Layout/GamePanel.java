package view.Layout;

import static model.Board.PROPERTY_BOARD_CHANGES;

import model.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class GamePanel extends JPanel implements PropertyChangeListener {

    private Board myBoard;

    private final int STROKE_WIDTH = 1;
    private static int count = 0;

    public GamePanel(final Board theBoard) {
        super();

        if (count > 0) {
            throw new IllegalArgumentException("Only one GamePanel allowed");
        }
        count++;

        this.myBoard = theBoard;
        this.myBoard.addPropertyChangeListener(this);

        setBackground(Color.RED);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (PROPERTY_BOARD_CHANGES.equals(theEvent.getPropertyName())) {
            repaint();
        }
    }

}

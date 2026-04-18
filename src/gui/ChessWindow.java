package gui;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;
/**
 * The main GUI window for the chess game
 * Displays the 8x8 chess board
 */
public class ChessWindow {
    //constructor
    public ChessWindow() {
        //sets the size of the window to 8x8 squares
        this.setLayout(new GridLayout(8, 8));

        for (int i = 0; i < 64; i++){
            //make a square
        }
    }

}

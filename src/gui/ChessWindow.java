package gui;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Color;
/**
 * The main GUI window for the chess game
 * Displays the 8x8 chess board
 */
public class ChessWindow extends JFrame {
    //constructor
    public ChessWindow() {
        //sets the size of the window to 8x8 squares
        this.setLayout(new GridLayout(8, 8));

        for (int i = 0; i < 64; i++){
            JPanel square = new JPanel();
            int row = i / 8;
            int col = i % 8;

            if((row + col) % 2 == 0){ // if even then light green else dark green
                square.setBackground(new java.awt.Color(238, 238, 210));
            }
            else{
                square.setBackground(new java.awt.Color(118, 150, 86));
            }
            //adds the square to the window
            this.add(square);

            /**
             * An array of the starting pieces
             * Empty strings are empty squares
             */
            String[] startingPieces = {
                "\u265C", "\u265E ", "\u265D ", "\u265B ", "\u265A ", "\u265D ", "\u265E ", "\u265C ",
                "\u265F ", "\u265F ", "\u265F ", "\u265F ", "\u265F ", "\u265F ", "\u265F ", "\u265F ",
                " ", " ", " ", " ", " ", " ", " ", " ",
                " ", " ", " ", " ", " ", " ", " ", " ",
                " ", " ", " ", " ", " ", " ", " ", " ",
                " ", " ", " ", " ", " ", " ", " ", " ",
                "\u2659 ", "\u2659 ", "\u2659 ", "\u2659 ", "\u2659 ", "\u2659 ", "\u2659 ", "\u2659 ",
                "\u2656 ", "\u2658 ", "\u2657 ", "\u2655 ", "\u2654 ", "\u2657 ", "\u2658 ", "\u2656 "
            };


            //adds the piece sticker to the square
            JLabel pieceSticker = new JLabel(startingPieces[i]); 
            pieceSticker.setFont(new java.awt.Font("Serif", java.awt.Font.PLAIN, 40));
            square.add(pieceSticker);
        }
    }
    public static void main(String[] args){
        ChessWindow window = new ChessWindow();
        window.setSize(600, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes the window
        window.setVisible(true); //makes window visible
    }
}

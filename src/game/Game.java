package game;

import board.Board;

/**
 * The main class for the game
 */
public class Game {
    /**
     * the main entry point for the game
     * @param args the command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("Welcome to Chess!");
        System.out.println("Starting game...");

        // creates a new chess board
        Board gameBoard = new Board();

        // Displays the board
        gameBoard.printBoard();
    }
}

package game;

import board.Board;
import java.util.Scanner;

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

        // creates a new scanner to read user input
        Scanner scanner = new Scanner(System.in);
        boolean isWhiteTurn = true; // white always starts the game

        // Game loop
        while (true) {
        // Displays the board
        gameBoard.printBoard();

        //determines whos turn it is
        String currentPlayer = isWhiteTurn ? "White" : "Black";
        System.out.println(currentPlayer + "'s turn. to move (format: E2 E4 or quit): ");

        //reads user input
        String input = scanner.nextLine();

        //checks if the user wants to quit
        if (input.equals("quit")) {
            System.out.println("Goodbye!");
            break;
        }

        //checks if the user input is valid
        if(input.length() != 5 || input.charAt(2) != ' '){
            System.out.println("Invalid input. Try again.");
            continue; //skips to the next iteration of the loop
        }
 
        //Parse out the "From" and "To" coordinates
        String from = input.substring(0, 2).toUpperCase();
        String to = input.substring(3, 5).toUpperCase();

        System.out.println("\n=> " + currentPlayer + " played: " + from + " to " + to + "\n");

        //switches turns
        isWhiteTurn = !isWhiteTurn;
        }

        scanner.close();

        
    }
}

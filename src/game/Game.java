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

        //convert standard chess notation to 0-7 array indexes
        // 'A' is ASCII 65, so subtract 65 to get the array index
        int fromCol = from.charAt(0) - 'A';
        int fromRow = Character.getNumericValue(from.charAt(1) - 1);
        int toCol = to.charAt(0) - 'A';
        int toRow = Character.getNumericValue(to.charAt(1) - 1);

        //rules 1. Bounds Check: ensure the coordinates are between 0 - 7
        if(fromRow < 0 || fromRow > 7 || fromCol < 0 || fromCol > 7 || toRow < 0 || toRow > 7 || toCol < 0 || toCol > 7){
            System.out.println("Invalid input. Try again.");
            continue; //skips to the next iteration of the loop
        }

        utils.Position fromPos = new utils.Position(fromRow, fromCol);
        utils.Position toPos = new utils.Position(toRow, toCol);

        pieces.Piece pieceToMove = gameBoard.getPiece(fromPos);
        pieces.Piece pieceToCapture = gameBoard.getPiece(toPos);
        utils.Color currentTurnColor = isWhiteTurn ? utils.Color.WHITE : utils.Color.BLACK;

        //rule 2 empty square check
        if(pieceToMove == null){
            System.out.println("Invalid input. Try again.");
            continue; //skips to the next iteration of the loop
        }

        // rule 3. Ensure the player is moving their own piece
        if(pieceToMove.getColor() != currentTurnColor){
            System.out.println("You can't move someone else's piece. Try again.");
            continue; //skips to the next iteration of the loop
        }

        // rule 4. Friendly fire check
        if(pieceToCapture != null && pieceToCapture.getColor() == currentTurnColor){
            System.out.println("You can't capture your own piece. Try again.");
            continue; //skips to the next iteration of the loop
        }

        // rule 5. Valid move check
        if(!pieceToMove.isValidMove(gameBoard, toPos)){
            System.out.println("Illegal move for that piece! Try again.");
            continue; //skips to the next iteration of the loop
        }

        //move the piece
        gameBoard.movePiece(fromPos, toPos);

        System.out.println("\n=> " + currentPlayer + " played: " + from + " to " + to + "\n");

        /**
         * Rule 6. Anounce check
         * Check if the move puts the ENEMY king in check
         */
        utils.Color enemyColor = isWhiteTurn ? utils.Color.BLACK : utils.Color.WHITE;
        if(gameBoard.isInCheck(enemyColor)){
            String enemyName = isWhiteTurn ? "Black" : "White";
            System.out.println("************************");
            System.out.println("CHECK! " + enemyName + "'s king is under attack!");
            System.out.println("************************");
        }

        //switches turns
        isWhiteTurn = !isWhiteTurn;
        }

        scanner.close();

        
    }
}

package pieces;

import utils.*;
import java.util.List;

public abstract class Piece {
    protected Color color;
    protected Position position;

    /**
     * makes a generic chess piece
     * @param color the color of the piece (white or black)
     * @param position the starting position of the piece
     */
    public Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    /**
     * calculuates the list of possible moves for the piece in its current position
     * @return the list of possible moves for the piece
     */
    public abstract List<Position> possibleMoves();

    /**
     * Checks if a move is valid for this specific piece
     * *@param  board the current state of the board
     * @param end the target position the player want to move this piece to
     * @return true if the move follows the piece's rules, false otherwise
     */

    public abstract boolean isValidMove(board.Board board, Position end);

    /**
     * Get the text representation of the piece
     * @return a 2 character string representing the piece
     */
    public abstract String getSymbol();

    /**
     * Udpates the position of the piece
     * @param newPosition the new position of the piece
     */
    public void updatePosition(Position newPosition) {
        this.position = newPosition;
    }

    // Getters
    public Color getColor() {
        return this.color;
    }

    public Position getPosition() {
        return position;
    }


}

package pieces;

import utils.*;
import java.util.ArrayList;
import java.util.List;

/**
 * resents a chess queen
 */
public class Queen extends Piece {
    public Queen(Color color, Position position) {
        super(color, position);
    }

    @Override
    public String getSymbol() {
        // returns  wQ for white  or bQ for black queen
        return (this.color == Color.WHITE) ? "wQ" : "bQ";
    }

    @Override
    public List<Position> possibleMoves() {
        // not implemented yet
        return new ArrayList<>();
    }

    /**
     * Validates the queen's moves
     * @param board The current state of the board
     * @param end The target position
     * @return true if the move is valid and unblocked false otherwise
     */
    @Override
    public boolean isValidMove(board.Board board, Position end) {
        int startRow = this.position.getRow();
        int startCol = this.position.getCol();
        int endRow = end.getRow();
        int endCol = end.getCol();

        boolean isStraight = (startRow = endRow) || (startCol == endCol);
        boolean isDiagonal = Math.abs(startRow - endRow) == Math.abs(startCol - endCol);

        // queen must move straight or diagonal
        if(!isStraight && !isDiagonal) return false;

        int rowStep = Integer.compare(endRow, startRow);
        int colStep = Integer.compare(endCol, startCol);

        // check if there is no piece along the path
        int currentRow = startRow + rowStep;
        int currentCol = startCol + colStep;
        while(currentRow != endRow || currentCol != endCol) {
            if(board.getPiece(new utils.Position(currentRow, currentCol)) != null){
                return false;
            }
            currentRow += rowStep;
            currentCol += colStep;
        }

        // make sure we don't land on our own piece
        pieces.Piece targetPiece = board.getPiece(end);
        if(targetPiece != null && targetPiece.getColor() == this.color){
            return false;
        }
        return true;
    }
}

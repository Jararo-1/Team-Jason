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
}

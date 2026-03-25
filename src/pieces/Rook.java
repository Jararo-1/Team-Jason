package pieces;

import utils.*;
import java.util.ArrayList;
import java.util.List;

/**
 * resents a chess rook
 */
public class Rook extends Piece {
    public Rook(Color color, Position position) {
        super(color, position);
    }
    @Override
    public String getSymbol() {
        // returns  wR for white  or bR for black rook
        return (this.color == Color.WHITE) ? "wR" : "bR";
    }

    @Override
    public List<Position> possibleMoves() {
        // not implemented yet
        return new ArrayList<>();
    }
}

package pieces;

import utils.*;
import java.util.ArrayList;
import java.util.List;
/**
 * resents a chess knight
 */
public class Knight extends Piece {
    public Knight(Color color, Position position) {
        super(color, position);
    }

    @Override
    public String getSymbol() {
        // returns  wN for white  or bN for black knight
        return (this.color == Color.WHITE) ? "wN" : "bN";
    }

    @Override
    public List<Position> possibleMoves() {
        // not implemented yet
        return new ArrayList<>();
    }
}

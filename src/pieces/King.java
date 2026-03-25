package pieces;

import utils.*;
import java.util.ArrayList;
import java.util.List;

/**
 * resents a chess king
 */

public class King extends Piece {
    public King(Color color, Position position) {
        super(color, position);
    }

    @Override
    public String getSymbol() {
        // returns  wK for white  or bK for black king
        return (this.color == Color.WHITE) ? "wK" : "bK";
    }

    @Override
    public List<Position> possibleMoves() {
        // not implemented yet
        return new ArrayList<>();
    }

}

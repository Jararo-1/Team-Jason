package pieces;

import utils.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Resents a chess pawn
 */
public class Pawn extends Piece{
    public Pawn(Color color, Position position) {
        super(color, position);
    }

    @Override
    public String getSymbol() {
        // returns  wp for white  or bd for black pawn
        return (this.color == Color.WHITE) ? "wp" : "bp";
    }

    @Override
    public List<Position> possibleMoves() {
        // not implemented yet
        return new ArrayList<>();
    }
}

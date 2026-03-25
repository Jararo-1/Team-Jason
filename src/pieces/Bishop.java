package pieces;

import utils.Color;
import utils.Position;
import java.util.ArrayList;
import java.util.List;
/**
 * resents a chess bishop
 */
public class Bishop extends Piece {
    public Bishop(Color color, Position position) {
        super(color, position);
    }

    @Override
    public String getSymbol() {
        // returns  wB for white  or bB for black bishop
        return (this.color == Color.WHITE) ? "wB" : "bB";
    }

    @Override
    public List<Position> possibleMoves() {
        // not implemented yet
        return new ArrayList<>();
    }
}

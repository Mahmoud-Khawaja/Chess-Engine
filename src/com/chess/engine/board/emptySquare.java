package com.chess.engine.board;
import com.chess.engine.pieces.Piece;

public final class emptySquare extends Square {

    emptySquare(final int x, final int y) {
        super(x,y);
    }

    @Override
    public boolean isOccupied() {
        return false;
    }

    @Override
    public Piece getPiece() {
        return null;
    }

    @Override
    public String toString() {
        return ".";
    }

}

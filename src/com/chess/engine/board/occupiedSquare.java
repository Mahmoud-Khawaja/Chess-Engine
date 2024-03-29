package com.chess.engine.board;
import com.chess.engine.pieces.Piece;

public final class OccupiedSquare extends Square {

    private final Piece piece;

    public OccupiedSquare(final int x, final int y, Piece piece) {
        super(x, y);
        this.piece = piece;
    }

    @Override
    public boolean isOccupied() {
        return true;
    }

    @Override
    public Piece getPiece() {
        return this.piece;
    }

    @Override
    public String toString() {
        // Returns the piece's string representation in lowercase if the piece is black,
        // otherwise returns the piece's string representation as is.
        return getPiece().getPieceUtility().isBlack() ? getPiece().toString().toLowerCase() : getPiece().toString();
    }
}

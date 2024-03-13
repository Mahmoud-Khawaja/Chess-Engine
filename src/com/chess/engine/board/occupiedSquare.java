package com.chess.engine.board;
import  com.chess.engine.pieces.Piece;

public final class occupiedSquare extends Square {

    private final Piece piece;

    occupiedSquare(final int x, final int y, Piece piece) {
        super(x,y);
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
        return getPiece().getPieceUtility().isBlack() ? getPiece().toString().toLowerCase() : getPiece().toString();
    }
    
}

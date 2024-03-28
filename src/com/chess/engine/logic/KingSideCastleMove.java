package com.chess.engine.logic;

import com.chess.engine.board.Board;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;

public final class KingSideCastleMove extends CastleMove {

    public KingSideCastleMove(Board board, Piece movedPiece, int xPosition, int yPosition, Rook castleRook, int castleRookStartX, int castleRookStartY, int castleRookDestinationX, int castleRookDestinationY) {
        super(board, movedPiece, xPosition, yPosition, castleRook, castleRookStartX, castleRookStartY, castleRookDestinationX, castleRookDestinationY);
    }
}

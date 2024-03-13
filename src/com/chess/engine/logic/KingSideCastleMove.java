package com.chess.engine.logic;

import com.chess.engine.board.Board;
import com.chess.engine.pieces.Piece;

public final class KingSideCastleMove extends CastleMove {

    public KingSideCastleMove(Board board, Piece movedPiece, int xPosition, int yPosition) {
        super(board, movedPiece, xPosition, yPosition);
    }


}

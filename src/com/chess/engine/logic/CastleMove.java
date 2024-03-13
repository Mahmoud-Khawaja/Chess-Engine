package com.chess.engine.logic;

import com.chess.engine.board.Board;
import com.chess.engine.pieces.Piece;

abstract class CastleMove extends Moves {

    public CastleMove(Board board, Piece movedPiece, int xPosition, int yPosition) {
        super(board, movedPiece, xPosition, yPosition);
    }

}

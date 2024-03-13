package com.chess.engine.logic;

import com.chess.engine.board.Board;
import com.chess.engine.pieces.Piece;

public class QueenSideCastleMove extends CastleMove {

    public QueenSideCastleMove(Board board, Piece movedPiece, int xPosition, int yPosition) {
        super(board, movedPiece, xPosition, yPosition);
    }

}

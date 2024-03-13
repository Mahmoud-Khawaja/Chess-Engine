package com.chess.engine.logic;

import com.chess.engine.board.Board;
import com.chess.engine.pieces.Piece;

public class PawnMove extends Moves {

    public PawnMove(Board board, Piece movedPiece, int xPosition, int yPosition) {
        super(board, movedPiece, xPosition, yPosition);
    }

}

package com.chess.engine.logic;

import com.chess.engine.board.Board;
import  com.chess.engine.pieces.Piece;

public final class NormalMove extends Moves{

    public NormalMove(Board board, Piece movedPiece, int x, int y) {
        super(board, movedPiece, x, y);
    }

}

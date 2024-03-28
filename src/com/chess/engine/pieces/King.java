package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.chess.engine.board.Board;
import com.chess.engine.board.Square;
import com.chess.engine.logic.Moves;
import com.chess.engine.logic.NormalMove;

public class King extends Piece {

    public King(int x, int y, Utilities pieceUtilities) {
        super(x, y, pieceUtilities,PieceType.KING);
    }
    private static final int[] MOVES_COORDINATES_X_AXIS = {1,-1,0,0,1,1,-1,-1};
    private static final int[] MOVES_COORDINATES_Y_AXIS = {0,0,1,-1,1,-1,1,-1};

    @Override
    public List<Moves> calculateMoves(Board board) {
        final List<Moves> legalMoves = new ArrayList<>();
        for(int i = 0; i < 8; i++) {
            int x = this.x + MOVES_COORDINATES_X_AXIS[i];
            int y = this.y - MOVES_COORDINATES_Y_AXIS[i];
            if(board.isValidCoordiante(x, y)){
                Square square = board.getSqaure(x, y);
                if(!square.isOccupied()) {
                    legalMoves.add(new NormalMove(board, this, x, y));
                }
            }
        }
        return legalMoves;
    }
    @Override
    public King movePiece(Moves move) {
        return new King(move.getXPosition(), move.getYPosition(), move.getMovedPiece().getPieceUtility());
    }
    @Override
    public String toString() {
        return PieceType.KING.toString();
    }
    
}

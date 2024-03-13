package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.Square;
import com.chess.engine.logic.AttackingMove;
import com.chess.engine.logic.Moves;
import com.chess.engine.logic.NormalMove;

public class Knight extends Piece {

    private static final int[] MOVES_COORDINATES_X_AXIS = {2,1,-1,-2,-2,-1,1,2};
    private static final int[] MOVES_COORDINATES_Y_AXIS = {1,2,2,-1,-1,-2,-2,-1};

    public Knight(int x, int y, Utilities pieceUtilities) {
        super(x,y, pieceUtilities,PieceType.KNIGHT);
    }
 
    @Override
    public List<Moves> calculateMoves(final Board board) {

        final List<Moves> legalMove = new ArrayList<>(); 

        for(int i = 0; i < 8; i++) {
            int x = this.x + MOVES_COORDINATES_X_AXIS[i]; 
            int y = this.y + MOVES_COORDINATES_Y_AXIS[i];
            if(board.isValidCoordiante(x, y)){
                 final Square ourDistination = board.getSqaure(x,y);
                 if(!ourDistination.isOccupied()){
                    legalMove.add(new NormalMove(board,this, x,y));
                 } else {
                    final Piece pieceAtlocation = ourDistination.getPiece();
                    final Utilities pieceutil = pieceAtlocation.getPieceUtility();
                        if(this.pieceUtilities != pieceutil){
                        legalMove.add(new AttackingMove(board,this,x,y,pieceAtlocation));
                    }
                 }
            }
        }

        return legalMove;
    }
    
    @Override
    public Knight movePiece(Moves move) {
        return new Knight(move.getXPosition(), move.getYPosition(), move.getMovedPiece().getPieceUtility());
    }
    @Override
    public String toString() {
        return PieceType.KNIGHT.toString();
    }


    
    
}

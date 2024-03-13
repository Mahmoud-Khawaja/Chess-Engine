package com.chess.engine.pieces;


import java.util.ArrayList;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.Square;
import com.chess.engine.logic.AttackingMove;
import com.chess.engine.logic.Moves;
import com.chess.engine.logic.NormalMove;

public class Pawn extends Piece {

    public Pawn(int x, int y, Utilities pieceUtilities) {
        super(x, y, pieceUtilities,PieceType.PAWN);
    }

    private void calcuateAllDircestionsNormal(final Board board,final List<Moves> legalMove, int x, int y,int addX, int addY) {
        if(board.isValidCoordiante(x+addX, y+addY)){
            Square square = board.getSqaure(x+addX, y+addY);
            if(!square.isOccupied()){
            legalMove.add(new NormalMove(board,this,x+addX,y+addY));
            }
        }
        return;
    }

    
    private void calcuateAllDircestionsAttack(final Board board,final List<Moves> legalMove, int x, int y,int addX, int addY) {
        if(board.isValidCoordiante(x+addX, y+addY)){
            Square square = board.getSqaure(x+addX, y+addY);
            Piece pieceAtLocation = square.getPiece();
            if(square.isOccupied()&& pieceAtLocation.pieceUtilities != this.pieceUtilities){
            legalMove.add(new AttackingMove(board,this, x+addX, y+addY, pieceAtLocation));
        }
    }
    }

    @Override
    public List<Moves> calculateMoves(final Board board) {
        final List<Moves> legalMove = new ArrayList<>(); 
        int x = this.x;
        int y = this.y;
        if(this.pieceUtilities.isWhite()){
            if (x == 1) {
                Square square = board.getSqaure(x+2, y);
                if(!square.isOccupied()) legalMove.add(new NormalMove(board,this, x+2, y));
            }
            calcuateAllDircestionsAttack(board, legalMove, x, y, 1, 1);
            calcuateAllDircestionsAttack(board, legalMove, x, y, 1, -1);
            calcuateAllDircestionsNormal(board, legalMove, x, y, 1, 0);
        }else {
            if (x == 6) {
                Square square = board.getSqaure(x-2, y);
                if(!square.isOccupied()) legalMove.add(new NormalMove(board,this, x-2, y));
            }
            calcuateAllDircestionsAttack(board, legalMove, x, y, -1, -1);
            calcuateAllDircestionsAttack(board, legalMove, x, y, -1, 1);
            calcuateAllDircestionsNormal(board, legalMove, x, y, -1, 0);
        }
        return legalMove;
    }

    
    @Override
    public Pawn movePiece(Moves move) {
        return new Pawn(move.getXPosition(), move.getYPosition(), move.getMovedPiece().getPieceUtility());
    }
    
    @Override
    public String toString() {
        return PieceType.PAWN.toString();
    }

    
}

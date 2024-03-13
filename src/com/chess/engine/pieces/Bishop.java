package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.Square;
import com.chess.engine.logic.AttackingMove;
import com.chess.engine.logic.Moves;
import com.chess.engine.logic.NormalMove;

public class Bishop extends Piece {

    public Bishop(int x, int y, Utilities pieceUtilities) {
        super(x,y, pieceUtilities,PieceType.BISHOP);
    }
    private final void calcuateAllDircestions(Board board,List<Moves> legalMove, int x, int y,int addX, int addY) {
        while(true){
            if(board.isValidCoordiante(x+addX, y+addY)){
                Square square = board.getSqaure(x+addX, y+addY);
                if(!square.isOccupied()){ 
                    legalMove.add(new NormalMove(board, this, x+addX, y+addY));
                }
                else {
                    Piece pieceAtlocation = square.getPiece();
                    if(this.pieceUtilities!=pieceAtlocation.pieceUtilities){
                        legalMove.add(new AttackingMove(board, pieceAtlocation, x+addX, y+addY, pieceAtlocation));
                    }
                    break;
                }
            }else {
                break;
            }
            x = x+addX;
            y = y+addY;
        }
    }
    @Override
    public List<Moves> calculateMoves(Board board) {
        final List<Moves> legalMove = new ArrayList<>(); 
        int x = this.x;
        int y = this.y;

        calcuateAllDircestions(board, legalMove, x, y, 1, 1);
        calcuateAllDircestions(board, legalMove, x, y, 1, -1);
        calcuateAllDircestions(board, legalMove, x, y, -1, 1);
        calcuateAllDircestions(board, legalMove, x, y, -1, -1);

        return legalMove;
    }
    @Override
    public String toString() {
        return PieceType.BISHOP.toString();
    }
    @Override
    public Bishop movePiece(Moves move) {
        return new Bishop(move.getXPosition(), move.getYPosition(), move.getMovedPiece().getPieceUtility());
    }
    
}

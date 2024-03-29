package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.Square;
import com.chess.engine.logic.AttackingMove;
import com.chess.engine.logic.Moves;
import com.chess.engine.logic.NormalMove;
public class Knight extends Piece {

    private static final int[] KNIGHT_MOVES_X = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] KNIGHT_MOVES_Y = {1, 2, 2, -1, -1, -2, -2, -1};

    public Knight(int x, int y, Utilities pieceUtilities) {
        super(x, y, pieceUtilities, PieceType.KNIGHT);
    }

    @Override
    public List<Moves> calculateMoves(final Board board) {
        final List<Moves> legalMoves = new ArrayList<>();

        for(int i = 0; i < KNIGHT_MOVES_X.length; i++) {
            int candidateX = this.x + KNIGHT_MOVES_X[i];
            int candidateY = this.y + KNIGHT_MOVES_Y[i];
            if(board.isValidCoordinate(candidateX, candidateY)){
                final Square candidateDestinationSquare = board.getSquare(candidateX, candidateY);
                if(!candidateDestinationSquare.isOccupied()) {
                    legalMoves.add(new NormalMove(board, this, candidateX, candidateY));
                } else {
                    final Piece pieceAtLocation = candidateDestinationSquare.getPiece();
                    final Utilities pieceUtility = pieceAtLocation.getPieceUtility();
                    if(this.pieceUtilities != pieceUtility){
                        legalMoves.add(new AttackingMove(board, this, candidateX, candidateY, pieceAtLocation));
                    }
                }
            }
        }

        return legalMoves;
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
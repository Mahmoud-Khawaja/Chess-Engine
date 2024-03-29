package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.Square;
import com.chess.engine.logic.AttackingMove;
import com.chess.engine.logic.Moves;
import com.chess.engine.logic.NormalMove;
public class King extends Piece {

    private static final int[] KING_MOVES_X = {1, -1, 0, 0, 1, 1, -1, -1};
    private static final int[] KING_MOVES_Y = {0, 0, 1, -1, 1, -1, 1, -1};

    public King(int x, int y, Utilities pieceUtilities) {
        super(x, y, pieceUtilities, PieceType.KING);
    }

    @Override
    public List<Moves> calculateMoves(Board board) {
        final List<Moves> legalMoves = new ArrayList<>();
        for(int i = 0; i < KING_MOVES_X.length; i++) {
            int candidateX = this.x + KING_MOVES_X[i];
            int candidateY = this.y + KING_MOVES_Y[i];
            if(board.isValidCoordinate(candidateX, candidateY)){
                Square candidateDestinationSquare = board.getSquare(candidateX, candidateY);
                if(!candidateDestinationSquare.isOccupied()) {
                    legalMoves.add(new NormalMove(board, this, candidateX, candidateY));
                } else {
                    // Add attacking move if the square is occupied by an opponent's piece
                    Piece pieceAtDestination = candidateDestinationSquare.getPiece();
                    if(this.pieceUtilities != pieceAtDestination.getPieceUtility()) {
                        legalMoves.add(new AttackingMove(board, this, candidateX, candidateY, pieceAtDestination));
                    }
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

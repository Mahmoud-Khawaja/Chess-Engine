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
        super(x, y, pieceUtilities, PieceType.BISHOP);
    }

    private void calculateAllDirections(Board board, List<Moves> legalMove, int x, int y, int xDirectionIncrement, int yDirectionIncrement) {
        while(board.isValidCoordinate(x + xDirectionIncrement, y + yDirectionIncrement)) {
            int newX = x + xDirectionIncrement;
            int newY = y + yDirectionIncrement;
            Square square = board.getSquare(newX, newY);
            if(!square.isOccupied()) {
                legalMove.add(new NormalMove(board, this, newX, newY));
            } else {
                Piece pieceAtLocation = square.getPiece();
                if(!this.pieceUtilities.equals(pieceAtLocation.getPieceUtility())) {
                    legalMove.add(new AttackingMove(board, this, newX, newY, pieceAtLocation));
                }
                break;
            }
            x = newX;
            y = newY;
        }
    }

    @Override
    public List<Moves> calculateMoves(Board board) {
        final List<Moves> legalMove = new ArrayList<>();
        calculateAllDirections(board, legalMove, this.x, this.y, 1, 1);
        calculateAllDirections(board, legalMove, this.x, this.y, 1, -1);
        calculateAllDirections(board, legalMove, this.x, this.y, -1, 1);
        calculateAllDirections(board, legalMove, this.x, this.y, -1, -1);

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
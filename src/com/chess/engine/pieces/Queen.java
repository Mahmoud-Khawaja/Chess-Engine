package com.chess.engine.pieces;


import java.util.ArrayList;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.Square;
import com.chess.engine.logic.AttackingMove;
import com.chess.engine.logic.Moves;
import com.chess.engine.logic.NormalMove;

public class Queen extends Piece {

    public Queen(int x, int y, Utilities pieceUtilities) {
        super(x, y, pieceUtilities, PieceType.QUEEN);
    }

    private void calculateAllDirections(final Board board, final List<Moves> legalMoves, int x, int y, int xDirectionIncrement, int yDirectionIncrement) {
        while(board.isValidCoordinate(x + xDirectionIncrement, y + yDirectionIncrement)) {
            int newX = x + xDirectionIncrement;
            int newY = y + yDirectionIncrement;
            Square square = board.getSquare(newX, newY);
            if(!square.isOccupied()) {
                legalMoves.add(new NormalMove(board, this, newX, newY));
            } else {
                Piece pieceAtLocation = square.getPiece();
                if(!this.pieceUtilities.equals(pieceAtLocation.getPieceUtility())) {
                    legalMoves.add(new AttackingMove(board, this, newX, newY, pieceAtLocation));
                }
                break;
            }
            x = newX;
            y = newY;
        }
    }

    @Override
    public List<Moves> calculateMoves(Board board) {
        List<Moves> legalMoves = new ArrayList<>();
        // Horizontal and vertical moves
        calculateAllDirections(board, legalMoves, this.x, this.y, 1, 0);
        calculateAllDirections(board, legalMoves, this.x, this.y, -1, 0);
        calculateAllDirections(board, legalMoves, this.x, this.y, 0, 1);
        calculateAllDirections(board, legalMoves, this.x, this.y, 0, -1);
        // Diagonal moves
        calculateAllDirections(board, legalMoves, this.x, this.y, 1, 1);
        calculateAllDirections(board, legalMoves, this.x, this.y, 1, -1);
        calculateAllDirections(board, legalMoves, this.x, this.y, -1, 1);
        calculateAllDirections(board, legalMoves, this.x, this.y, -1, -1);

        return legalMoves;
    }

    @Override
    public Queen movePiece(Moves move) {
        return new Queen(move.getXPosition(), move.getYPosition(), move.getMovedPiece().getPieceUtility());
    }

    @Override
    public String toString() {
        return PieceType.QUEEN.toString();
    }
}

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
        super(x, y, pieceUtilities, PieceType.PAWN);
    }

    private void calculateAllDirectionsNormal(final Board board, final List<Moves> legalMove, int x, int y, int addX) {
        int newX = x + addX;
        if(board.isValidCoordinate(newX, y)){
            Square square = board.getSquare(newX, y);
            if(!square.isOccupied()){
                legalMove.add(new NormalMove(board, this, newX, y));
            }
        }
    }

    private void calculateAllDirectionsAttack(final Board board, final List<Moves> legalMove, int x, int y, int addX, int addY) {
        int newX = x + addX;
        int newY = y + addY;
        if(board.isValidCoordinate(newX, newY)){
            Square square = board.getSquare(newX, newY);
            Piece pieceAtLocation = square.getPiece();
            if(square.isOccupied() && pieceAtLocation.getPieceUtility() != this.getPieceUtility()){
                legalMove.add(new AttackingMove(board, this, newX, newY, pieceAtLocation));
            }
        }
    }

    @Override
    public List<Moves> calculateMoves(final Board board) {
        final List<Moves> legalMove = new ArrayList<>();
        int x = this.x;
        int y = this.y;
        int pawnMoveDirection = this.getPieceUtility().isWhite() ? 1 : -1;
        int pawnStartRow = this.getPieceUtility().isWhite() ? 1 : 6;

        // Handle the initial double move
        if (y == pawnStartRow) {
            int twoStepForward = y + (2 * pawnMoveDirection);
            Square squareTwoStep = board.getSquare(x, twoStepForward);
            if(!squareTwoStep.isOccupied()) {
                legalMove.add(new NormalMove(board, this, x, twoStepForward));
            }
        }

        // Handle normal and attack moves
        calculateAllDirectionsNormal(board, legalMove, x, y, pawnMoveDirection);
        calculateAllDirectionsAttack(board, legalMove, x, y, pawnMoveDirection, 1);
        calculateAllDirectionsAttack(board, legalMove, x, y, pawnMoveDirection, -1);

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
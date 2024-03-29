package com.chess.engine.logic;

import com.chess.engine.board.Board;
import com.chess.engine.board.Builder;
import com.chess.engine.pieces.Pawn;
import com.chess.engine.pieces.Piece;

/**
 * Represents a pawn jump move in chess.
 */
public class PawnJump extends Moves {

    /**
     * Constructor for PawnJump.
     *
     * @param board The current board state.
     * @param movedPiece The pawn being moved.
     * @param xPosition The x-coordinate of the destination square.
     * @param yPosition The y-coordinate of the destination square.
     */
    public PawnJump(Board board, Piece movedPiece, int xPosition, int yPosition) {
        super(board, movedPiece, xPosition, yPosition);
    }

    /**
     * Initiates the pawn jump move.
     *
     * @return The new board state after the pawn jump.
     */
    @Override
    public Board initiate(){
        final Builder builder = new Builder();
        
        // Set all pieces except the moved pawn
        for(final Piece piece : this.board.currentPlayer().getCurrentActivePieces()){
            if(!this.movedPiece.equals(piece)){
                builder.setPiece(piece);
            }
        }
        // Set all opponent pieces
        for(final Piece piece : this.board.currentPlayer().getOpponent().getCurrentActivePieces()){
             builder.setPiece(piece);
        }

        // Move the pawn and set it as the en passant pawn
        final Pawn movedPawn = (Pawn)this.movedPiece.movePiece(this);
        builder.setPiece(movedPawn);
        builder.setEnPassantPawn(movedPawn);
        // Update the current player
        builder.setNextMove(this.board.currentPlayer().getOpponent().getUtilities());
        
        return builder.build();
    }
}

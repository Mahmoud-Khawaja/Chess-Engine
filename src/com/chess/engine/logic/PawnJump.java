package com.chess.engine.logic;

import com.chess.engine.board.Board;
import com.chess.engine.board.Builder;
import com.chess.engine.pieces.Pawn;
import com.chess.engine.pieces.Piece;

public class PawnJump extends Moves {

    public PawnJump(Board board, Piece movedPiece, int xPosition, int yPosition) {
        super(board, movedPiece, xPosition, yPosition);
    }

    @Override
    public Board initiate(){
        final Builder builder = new Builder();
        
        for(final Piece piece : this.board.currentPlayer().getCurrentActivePieces()){
            if(!this.movedPiece.equals(piece)){
                builder.setPiece(piece);
            }
        }
        for(final Piece piece : this.board.currentPlayer().getOpponent().getCurrentActivePieces()){
             builder.setPiece(piece);
        }

        final Pawn movedPawn = (Pawn)this.movedPiece.movePiece(this);
        builder.setPiece(movedPawn);
        builder.SetEnPassantPawn(movedPawn);
        builder.setNextMove(this.board.currentPlayer().getOpponent().getUtilities());
        return builder.build();
    }

}

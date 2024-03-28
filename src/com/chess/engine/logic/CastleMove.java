package com.chess.engine.logic;

import com.chess.engine.board.Board;
import com.chess.engine.board.Builder;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;

abstract class CastleMove extends Moves {
    protected final Rook castleRook;
    protected final int castleRookStartX;
    protected final int castleRookStartY;
    
    protected final int castleRookDestinationX;
    protected final int castleRookDestinationY;

    public CastleMove(Board board, Piece movedPiece, int xPosition, int yPosition, Rook castleRook, int castleRookDestinationX, int castleRookDestinationY, int castleRookStartX, int castleRookStartY) {
        super(board, movedPiece, xPosition, yPosition);
        this.castleRook = castleRook;
        this.castleRookStartX = castleRookStartX;
        this.castleRookStartY = castleRookStartY;
        this.castleRookDestinationX = castleRookDestinationX;
        this.castleRookDestinationY = castleRookDestinationY;
    }

    public Rook getCastleRook(){
        return this.castleRook;
    }

    @Override
    public boolean isCastlingMove(){
        return true;
    }
    
    @Override
    public Board initiate(){
        final Builder builder = new Builder();
        for (Piece piece : this.board.currentPlayer().getCurrentActivePieces()){
            if(!this.movedPiece.equals(piece)&&!this.castleRook.equals(piece)){
                builder.setPiece(piece);
            }
        }
        
        for (Piece piece : this.board.currentPlayer().getOpponent().getCurrentActivePieces()){
                builder.setPiece(piece);
        } 
        builder.setPiece(this.movedPiece.movePiece(this));
        // check the first move later 
        builder.setPiece(new Rook(this.castleRookDestinationX,this.castleRookDestinationY,this.castleRook.getPieceUtility()));
        builder.setNextMove(this.board.currentPlayer().getOpponent().getUtilities());
        return builder.build();
    }

}

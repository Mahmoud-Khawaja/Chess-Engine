package com.chess.engine.logic;

import com.chess.engine.board.Board;
import com.chess.engine.board.Builder;
import  com.chess.engine.pieces.Piece;

public abstract class Moves {
    final Board board;
    final Piece movedPiece;
    final int xPosition;
    final int yPosition;


    // let's create singleton
    public static final Moves NULL_MOVE = new NullMove();
    

    public Moves(Board board, Piece movedPiece, int xPosition, int yPosition) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
    
    public int getCurrentCoordinateX() {
        return this.movedPiece.getXPosition();
    }   
    
    public int getCurrentCoordinateY() {
        return this.movedPiece.getYPosition();
    }   

    public int getYPosition() {
        return this.yPosition;
    }

    public int getXPosition() {
        return this.xPosition;
    }
    
    public Piece getMovedPiece(){
        return this.movedPiece;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((movedPiece == null) ? 0 : movedPiece.hashCode());
        result = prime * result + xPosition;
        result = prime * result + yPosition;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Moves other = (Moves) obj;
        if (board == null) {
            if (other.board != null)
                return false;
        } else if (!board.equals(other.board))
            return false;
        if (movedPiece == null) {
            if (other.movedPiece != null)
                return false;
        } else if (!movedPiece.equals(other.movedPiece))
            return false;
        if (xPosition != other.xPosition)
            return false;
        if (yPosition != other.yPosition)
            return false;
        return true;
    }

    public boolean isAttack(){
        return false;
    }
    
    public boolean isCastlingMove(){
        return false;
    }

    public Piece getAttackedPiece(){
        return null;
    }

    public Board initiate() {
        // let's make an imaginary board here to check if we can do a normal move 
        // we don't want to mutate the original board cause we don't wanna lose advantage of immutability  
        final Builder builder = new Builder();
        for (Piece piece : this.board.currentPlayer().getCurrentActivePieces()){
            if(!this.movedPiece.equals(piece)){
                builder.setPiece(piece);
            }
        }

        
        for (Piece piece : this.board.currentPlayer().getOpponent().getCurrentActivePieces()){
                builder.setPiece(piece);
        }
        
        builder.setPiece(this.movedPiece.movePiece(this));// moving the piece 
        builder.setNextMove(this.board.currentPlayer().getOpponent().getUtilities());

        return builder.build();
    }
    
    public static class FactoryClass{
        private FactoryClass(){
            throw new RuntimeException("a7a not working yet"); 
        }

        public static Moves createMoves(final Board board, final int currentCooridnateX
        ,final int currentCooridnateY, final int destinationCoordinateX,final int destinationCoordinateY){

            for(final Moves move: board.getAllLegalMoves()){
                if(move.getCurrentCoordinateX() == currentCooridnateX && move.getCurrentCoordinateY() == currentCooridnateY 
                && move.getXPosition() == destinationCoordinateX && move.getYPosition() == destinationCoordinateY){
                    return move;
                }
            }

            return NULL_MOVE;
        }
}

}




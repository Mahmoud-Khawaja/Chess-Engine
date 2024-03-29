package com.chess.engine.board;

import com.chess.engine.pieces.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class Builder {
    Map<Entry<Integer,Integer>,Piece>boardConfig; 
    Utilities nextMove;
    Pawn enPassantPawn;
    public Builder(){
        this.boardConfig = new HashMap<>();
    }

    public Builder setPiece(final Piece piece){
        this.boardConfig.put(Map.entry(piece.getXPosition(),piece.getYPosition()), piece);
        return this;
    }
    public Builder setNextMove(final Utilities nextMove){
        this.nextMove = nextMove;
        return this;
    }
    public Board build(){
        return new Board(this);
    }
    public void setEnPassantPawn(final Pawn enPassantPawn) {
        this.enPassantPawn = enPassantPawn;
    }
    
}

package com.chess.engine.board;

import com.chess.engine.pieces.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class Builder {
    Map<Entry<Integer,Integer>,Piece>boardcofig; 
    Utilities nextMove;
    Pawn enPassantPawn;
    public Builder(){
        this.boardcofig = new HashMap<>();
    }

    public Builder setPiece(final Piece piece){
        this.boardcofig.put(Map.entry(piece.getXPosition(),piece.getYPosition()), piece);
        return this;
    }
    public Builder setNextMove(final Utilities nextMove){
        this.nextMove = nextMove;
        return this;
    }
    public Board build(){
        return new Board(this);
    }

    public void SetEnPassantPawn(Pawn enPassantPawn) {
       this.enPassantPawn = enPassantPawn;
    }
}

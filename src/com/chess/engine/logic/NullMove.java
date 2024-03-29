package com.chess.engine.logic;

import com.chess.engine.board.Board;

public class NullMove extends Moves{

    public NullMove() {
        super(null,null,-1,-1);
    }
    
    @Override
    public Board initiate(){
        throw new RuntimeException("a7a neek no move");
    }
}

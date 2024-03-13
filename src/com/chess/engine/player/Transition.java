package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.logic.Moves;


public class Transition {
    private final Board transitionBoard; // imaginary board to see if it's okay to make move to defend checks 
    private final Moves move;
    private final MoveStatus moveStatus;
    public Transition(Board transitionBoard, Moves move, MoveStatus moveStatus) {
        this.transitionBoard = transitionBoard;
        this.move = move;
        this.moveStatus = moveStatus;
    }
    public MoveStatus getMoveStatus() {
        return this.moveStatus;
    }
}

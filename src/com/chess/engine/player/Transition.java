package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.logic.Moves;

/**
 * Represents the result of a move attempt on the chess board.
 */
public class Transition {
    /**
     * The board state after the move has been made.
     */
    private final Board transitionBoard;

    /**
     * The move that was attempted.
     */
    private final Moves move;

    /**
     * The status of the move attempt.
     */
    private final MoveStatus moveStatus;

    /**
     * Constructs a new Transition object.
     *
     * @param transitionBoard The board state after the move.
     * @param move            The move that was attempted.
     * @param moveStatus      The status of the move attempt.
     */

    public Transition(Board transitionBoard, Moves move, MoveStatus moveStatus) {
        this.transitionBoard = transitionBoard;
        this.move = move;
        this.moveStatus = moveStatus;
    }

    /**
     * Gets the status of the move attempt.
     *
     * @return The move status.
     */
    public MoveStatus getMoveStatus() {
        return this.moveStatus;
    }

    public Board getTransitionBoard(){
        return this.transitionBoard;
    }
    // Consider adding getters for transitionBoard and move if needed
}

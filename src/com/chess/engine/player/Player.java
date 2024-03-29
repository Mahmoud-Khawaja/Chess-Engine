package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.logic.Moves;
import com.chess.engine.pieces.King;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Player {
    protected final King kingTracker;
    protected final Board board;
    protected final Collection<Moves> legalMoves;
    private final boolean isIncheck;

    public Player(final Collection<Moves> opponentMoves, final Board board, final Collection<Moves> legalMoves) {
        this.board = board;
        this.kingTracker = trackKing();
        this.isIncheck = !Player.calculateAttacks(this.kingTracker.getXPosition(), this.kingTracker.getYPosition(), opponentMoves).isEmpty();
        this.legalMoves = legalMoves;
        this.legalMoves.addAll(calculateAttacks(this.kingTracker.getXPosition(), this.kingTracker.getYPosition(), opponentMoves));
    }

    public King getKing() {
        return this.kingTracker;
    }

    public Collection<Moves> getLegalMoves() {
        return this.legalMoves;
    }

    public boolean isIncheck() {
        return this.isIncheck;
    }

    public boolean isInStaleMate() {
        return !this.isIncheck && !hasEscapeMove();
    }

    public boolean isIncheckMate() {
        return this.isIncheck && !hasEscapeMove();
    }

    public boolean isLegalMove(Moves move) {
        return this.legalMoves.contains(move);
    }

    public Transition makeMove(final Moves move) {
        if (!isLegalMove(move)) {
            return new Transition(this.board, move, MoveStatus.ILLEGAL_MOVE);
        }

        final Board transitionBoard = move.initiate();

        final Collection<Moves> kingAttacks = Player.calculateAttacks(
            transitionBoard.currentPlayer().getOpponent().getKing().getXPosition(),
            transitionBoard.currentPlayer().getOpponent().getKing().getYPosition(),
            transitionBoard.currentPlayer().getLegalMoves()
        );

        if (!kingAttacks.isEmpty()) {
            return new Transition(this.board, move, MoveStatus.LEAVE_PLAYER_ON_CHECK);
        }

        return new Transition(transitionBoard, move, MoveStatus.DONE);
    }

    protected boolean hasEscapeMove() {
        for (final Moves move : this.legalMoves) {
            final Transition transition = makeMove(move);
            if (transition.getMoveStatus().isDone()) {
                return true;
            }
        }
        return false;
    }

    protected static Collection<Moves> calculateAttacks(int xPosition, int yPosition, Collection<Moves> moves) {
        final List<Moves> attackMoves = new ArrayList<>();
        for (final Moves move : moves) {
            if (xPosition == move.getXPosition() && yPosition == move.getYPosition()) {
                attackMoves.add(move);
            }
        }
        return attackMoves;
    }

    private King trackKing() {
        for (final Piece piece : getCurrentActivePieces()) {
            if (piece.getPieceType().isKing()) {
                return (King) piece;
            }
        }
        throw new RuntimeException("The King could not be found on the board");
    }

    public abstract Collection<Piece> getCurrentActivePieces();

    public abstract Utilities getUtilities();

    public abstract Player getOpponent();

    protected abstract Collection<Moves> calculateKingCastle(Collection<Moves> playerLegals, Collection<Moves> opponentLegals);
}

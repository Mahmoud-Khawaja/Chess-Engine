package com.chess.engine.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Utilities;
import com.chess.engine.logic.Moves;
import com.chess.engine.board.Board;
import com.chess.engine.pieces.King;

public abstract class Player {
    protected final King kingTracker;
    protected final Board board;
    protected final Collection<Moves> legalMoves;
    private final boolean isIncheck;
    public Player(final Collection<Moves> opponentMoves,final Board board,final Collection<Moves> legalMoves) {
        this.kingTracker = trackKing();
        this.board = board;
        this.isIncheck = !Player.calculateAttacks(this.kingTracker.getXPosition(),this.kingTracker.getYPosition(),opponentMoves).isEmpty();
        legalMoves.addAll(calculateAttacks(this.kingTracker.getXPosition(),this.kingTracker.getYPosition(), opponentMoves));
        this.legalMoves = legalMoves; 
    }

    public abstract Collection<Piece>getCurrentActivePieces();
    public abstract Utilities getUtilities();
    public abstract Player getOpponent();
    protected abstract Collection<Moves> calculateKingCastle(Collection<Moves>playerLegals,Collection<Moves>opponentLegals);

    public King getKing(){
        return this.kingTracker;
    }
    public Collection<Moves>getLegalMoves(){
        return this.legalMoves;
    }
    private King trackKing() {
        for(final Piece piece:getCurrentActivePieces()){
            if(piece.getPieceType().isKing()) return (King) piece;
        }
        return kingTracker;
    }

    public boolean isIncheck(){
        return this.isIncheck;
    }
    
    public boolean isInStaleMate(){
        return !this.isIncheck && !hasEscapeMove();
    }

    public boolean isIncheckMate(){
        return this.isIncheck && !hasEscapeMove();
    }

    protected boolean hasEscapeMove() {
        for(final Moves move: this.legalMoves){
            final Transition transition = makeMove(move);
            if(transition.getMoveStatus().isDone())return true;
        }
        return false;
    }

    public boolean isLegalMove(Moves move){
        return this.legalMoves.contains(move);
    }

    protected static Collection<Moves> calculateAttacks(int xPosition,int yPosition, Collection<Moves> moves){
        final List<Moves> attackMoves = new ArrayList<>();
        for(final Moves move:moves){
            if(xPosition == move.getXPosition() && yPosition == move.getYPosition()) {
                attackMoves.add(move);
            }
        }
        return attackMoves;
    }
    public Transition makeMove(final Moves move){
        if (!isLegalMove(move)){
            return new Transition(this.board, move,MoveStatus.ILLEGAL_MOVE);
        }

        // we will make the move on an imaginary board
        final Board transitionBoard = move.initiate();

        // after we make the move we're no longer the current player
        // so we will act as a the opposite color palyer

        // then let's check if there is any king attacks on the current player after we make this move
        int xPosition = transitionBoard.currentPlayer().getOpponent().getKing().getXPosition();
        int yPosition = transitionBoard.currentPlayer().getOpponent().getKing().getYPosition();
        final Collection<Moves> kingAttacks = Player.calculateAttacks(xPosition,yPosition,transitionBoard.currentPlayer().getLegalMoves());
        if(!kingAttacks.isEmpty()){// if this move is gonna expose our king on check then we won't be able to make this move
            return new Transition(this.board, move, MoveStatus.LEAVE_PLAYER_ON_CHECK);
        }
        return new Transition(transitionBoard, move, MoveStatus.DONE);
    }
    }

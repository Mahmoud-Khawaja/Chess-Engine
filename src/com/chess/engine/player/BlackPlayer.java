package com.chess.engine.player;

import java.util.Collection;

import com.chess.engine.board.Board;
import com.chess.engine.logic.Moves;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Utilities;

public class BlackPlayer extends Player {

    public BlackPlayer(Board board, Collection<Moves> whiteLegalMoves, Collection<Moves> blackLegalMoves) {
        super(whiteLegalMoves,board,blackLegalMoves);
    }

    @Override
    public Collection<Piece> getCurrentActivePieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Utilities getUtilities() {
        return Utilities.BLACK;
    }

    @Override
    public Player getOpponent() {
        return this.board.whitePlayer();
    }
    
}

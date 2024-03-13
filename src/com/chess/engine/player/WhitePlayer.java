package com.chess.engine.player;

import java.util.Collection;

import com.chess.engine.board.Board;
import com.chess.engine.logic.Moves;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Utilities;

public class WhitePlayer extends Player {

    public WhitePlayer(Board board, Collection<Moves> whiteLegalMoves, Collection<Moves> blackLegalMoves) {
    super(blackLegalMoves,board,whiteLegalMoves);
    }

    @Override
    public Collection<Piece> getCurrentActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Utilities getUtilities() {
        return Utilities.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.board.blackPlayer();
    }
    
}

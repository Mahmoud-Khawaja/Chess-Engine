package com.chess.engine.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.Square;
import com.chess.engine.logic.KingSideCastleMove;
import com.chess.engine.logic.Moves;
import com.chess.engine.logic.QueenSideCastleMove;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;
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
    @Override
    protected Collection<Moves> calculateKingCastle(Collection<Moves> playerLegals, Collection<Moves> opponentLegals) {
        final List<Moves>kingCastles = new ArrayList<>();
        if(this.kingTracker.isFirstMove() && !this.isIncheck()){

            if(!this.board.getSqaure(7, 5).isOccupied() && !this.board.getSqaure(0, 6).isOccupied()){
                final Square rookSquare = this.board.getSqaure(0, 7);

                if(rookSquare.isOccupied() && rookSquare.getPiece().isFirstMove()){
                    if(Player.calculateAttacks(7, 5, opponentLegals).isEmpty()
                    && Player.calculateAttacks(7, 6, opponentLegals).isEmpty()
                    && rookSquare.getPiece().getPieceType().isRook()) {
                    kingCastles.add(new KingSideCastleMove(this.board,this.kingTracker,7,6,
                    (Rook)rookSquare.getPiece(),rookSquare.getSqaureCoordinatesX(),rookSquare.getSqaureCoordinatesY(),
                    7,5));
                    }
                }
            }
            
            if(!this.board.getSqaure(7, 1).isOccupied()
             && !this.board.getSqaure(7, 2).isOccupied()
             && !this.board.getSqaure(7, 3).isOccupied()){
                final Square rookSquare = this.board.getSqaure(7, 0);
                if(rookSquare.isOccupied() && rookSquare.getPiece().isFirstMove()){
                    kingCastles.add(new QueenSideCastleMove(this.board, this.kingTracker, 7,2, (Rook)rookSquare.getPiece(),rookSquare.getSqaureCoordinatesX(),rookSquare.getSqaureCoordinatesY(),
                    7,1));
                }
            }
        }

        return kingCastles;
    }
    
}

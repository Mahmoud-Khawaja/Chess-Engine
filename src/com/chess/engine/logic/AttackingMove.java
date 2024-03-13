package com.chess.engine.logic;

import com.chess.engine.board.Board;
import  com.chess.engine.pieces.Piece;

public final class AttackingMove extends Moves {
    final Piece attakedPiece;
    public AttackingMove(Board board, Piece movedPiece, int x, int y, final Piece attakedPiece) {
        super(board, movedPiece, x, y);
        this.attakedPiece = attakedPiece;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((attakedPiece == null) ? 0 : attakedPiece.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        AttackingMove other = (AttackingMove) obj;
        if (attakedPiece == null) {
            if (other.attakedPiece != null)
                return false;
        } else if (!attakedPiece.equals(other.attakedPiece))
            return false;
        return true;
    }
    @Override
    public Board initiate() {
        return null;
    }
    @Override
    public boolean isAttack(){
        return true;
    }
    @Override
    public Piece getAttackedPiece(){
        return this.attakedPiece;
    }
    
}

package com.chess.engine.pieces;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.logic.Moves;

public abstract class Piece {
    public final Utilities pieceUtilities; 
    protected final int x;
    protected final int y;
    protected final boolean isFirstMove;
    protected final PieceType pieceType;
    private final int cachedHashCode;


    Piece(final int x, final int y, final Utilities pieceUtilities,final PieceType pieceType){
        this.pieceUtilities = pieceUtilities;
        this.x = x;
        this.y = y;
        this.isFirstMove = false;
        this.pieceType = pieceType;
        this.cachedHashCode = computeHashCode();
    }
    private int computeHashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pieceUtilities == null) ? 0 : pieceUtilities.hashCode());
        result = prime * result + x;
        result = prime * result + y;
        result = prime * result + (isFirstMove ? 1231 : 1237);
        result = prime * result + ((pieceType == null) ? 0 : pieceType.hashCode());
        return result;
    }
    public Utilities getPieceUtility(){
        return this.pieceUtilities;
    }
    public boolean isFirstMove(){
        return this.isFirstMove;
    }
    public int getXPosition(){
        return this.x;
    }
    public int getYPosition(){
        return this.y;
    }
    public abstract Piece movePiece(Moves move);
    public abstract List<Moves> calculateMoves(final Board board);
    



    @Override
    public int hashCode() {
        return this.cachedHashCode;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Piece other = (Piece) obj;
        if (pieceUtilities != other.pieceUtilities)
            return false;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        if (isFirstMove != other.isFirstMove)
            return false;
        if (pieceType != other.pieceType)
            return false;
        return true;
    }


    public enum PieceType {
        BISHOP("B") {
            @Override
            public boolean isKing() {
                return false;   
            }
            @Override
            public boolean isRook() {
                return false;
            }
        },
        KNIGHT("N") {
            @Override
            public boolean isKing() {
                return false;
            }
            @Override
            public boolean isRook() {
                return false;
            }
        },
        ROOK("R") {
            @Override
            public boolean isKing() {
                return false;
            }
            @Override
            public boolean isRook() {
                return true;
            }
        },
        PAWN("P") {
            @Override
            public boolean isKing() {
                return false;
            }
            @Override
            public boolean isRook() {
                return false;
            }
        },
        QUEEN("Q") {
            @Override
            public boolean isKing() {
                return false;
            }
            @Override
            public boolean isRook() {
                return false;
            }
        },
        KING("K") {
            @Override
            public boolean isKing() {
                return true;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        };
        
        private String pieceName;
        PieceType(final String pieceName){
            this.pieceName = pieceName;
        }
        @Override
        public String toString() {
            return this.pieceName;
        }
    public abstract boolean isKing();
    public abstract boolean isRook();
    }
        
    public PieceType getPieceType(){
        return this.pieceType;
    }

}

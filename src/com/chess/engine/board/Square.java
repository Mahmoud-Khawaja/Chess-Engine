package com.chess.engine.board;
import  com.chess.engine.pieces.Piece;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;
public abstract class Square {
    protected final int x;
    protected final int y;

    private static final Map<Map.Entry<Integer,Integer>,emptySquare> EMPTY_CHASH = createAllTiles();

    private static Map<Map.Entry<Integer,Integer>, emptySquare> createAllTiles() {
        final Map<Map.Entry<Integer,Integer>, emptySquare> empty = new HashMap<>();
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++){
                empty.put(Map.entry(i,j), new emptySquare(i, j));
            }
        }
        return empty;
    }
    

    public static Square creat(final int x, final int y, final Piece piece){
        return piece != null ? new occupiedSquare(x,y,piece) : EMPTY_CHASH.get(Map.entry(x,y));
    }

    
    Square(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    public abstract boolean isOccupied();
    public abstract Piece getPiece();
    @Override
    public String toString() {
        return ".";
    }


    public int getSqaureCoordinatesX() {
        return this.x;
    }
    public int getSqaureCoordinatesY() {
        return this.y;
    }
}

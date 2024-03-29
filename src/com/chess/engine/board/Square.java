package com.chess.engine.board;
import com.chess.engine.pieces.Piece;
import java.util.HashMap;
import java.util.Map;

public abstract class Square {
    protected final int x;
    protected final int y;

    private static final Map<Map.Entry<Integer,Integer>, EmptySquare> EMPTY_CACHE = createAllTiles();

    private static Map<Map.Entry<Integer,Integer>, EmptySquare> createAllTiles() {
        final Map<Map.Entry<Integer,Integer>, EmptySquare> empty = new HashMap<>();
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++){
                empty.put(Map.entry(i,j), new EmptySquare(i, j));
            }
        }
        return empty;
    }

    public static Square create(final int x, final int y, final Piece piece){
        return piece != null ? new OccupiedSquare(x, y, piece) : EMPTY_CACHE.get(Map.entry(x, y));
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

    public int getSquareCoordinatesX() {
        return this.x;
    }
    public int getSquareCoordinatesY() {
        return this.y;
    }
}

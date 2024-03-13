package com.chess.engine.board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import  com.chess.engine.logic.Moves;

import com.chess.engine.pieces.*;
import com.chess.engine.player.*;

public class Board {
    private final List<List<Square>> gameBoard;
    private final Collection<Piece>whitePieces;
    private final Collection<Piece>blackPieces;

    private final WhitePlayer whitePlayer;
    private final BlackPlayer blackPlayer;
    private final Player currenPlayer;

    protected Board(Builder builder){
        this.gameBoard = createGameBoard(builder);
        this.whitePieces = calculateActivePieces(this.gameBoard,Utilities.WHITE);
        this.blackPieces = calculateActivePieces(this.gameBoard,Utilities.BLACK);

        final Collection<Moves> whiteLegalMoves = caluclateLegalMoves(this.whitePieces);
        final Collection<Moves> blackLegalMoves = caluclateLegalMoves(this.blackPieces);

        this.whitePlayer = new WhitePlayer(this,whiteLegalMoves,blackLegalMoves);
        this.blackPlayer = new BlackPlayer(this,whiteLegalMoves,blackLegalMoves);
        this.currenPlayer = builder.nextMove.choosePlayer(this.whitePlayer,this.blackPlayer);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 8; i++){
            String row = "";
            for(int j = 0; j < 8; j++){
                row = this.gameBoard.get(i).get(j).toString() + " ";
                builder.append(String.format("%2s",row));
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    private Collection<Moves> caluclateLegalMoves(final Collection<Piece>Pieces) {
        final List <Moves> legalMoves = new ArrayList<>();
        for(final Piece piece:Pieces){
            legalMoves.addAll(piece.calculateMoves(this));
        }
        return legalMoves;
    }

    private static Collection<Piece> calculateActivePieces(final List<List<Square>> gameBoard,final Utilities utilities) {
        final List<Piece> currentPieces = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(gameBoard.get(i).get(j).isOccupied()){
                    final Piece piece = gameBoard.get(i).get(j).getPiece();
                    if(piece.getPieceUtility() == utilities) currentPieces.add(piece);
                }
            }
        }
        return currentPieces;
    }
    public Player currentPlayer(){
        return this.currenPlayer;
    }

    private static List<List<Square>> createGameBoard(final Builder builder){
        List<List<Square>>squares = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            List<Square>tempSquare = new ArrayList<>();
            for(int j = 0; j < 8; j++){
                tempSquare.add(Square.creat(i, j,builder.boardcofig.get(Map.entry(i,j))));                
            }
            squares.add(tempSquare);
        }
        return squares;
    }
    public static Board createBoard(){

        final Builder builder = new Builder();

        builder.setPiece(new Rook(0,0,Utilities.WHITE));
        builder.setPiece(new Knight(0,1,Utilities.WHITE));
        builder.setPiece(new Bishop(0,2,Utilities.WHITE));
        builder.setPiece(new Queen(0,3,Utilities.WHITE));
        builder.setPiece(new King(0,4,Utilities.WHITE));
        builder.setPiece(new Bishop(0,5,Utilities.WHITE));
        builder.setPiece(new Knight(0,6,Utilities.WHITE));
        builder.setPiece(new Rook(0,7,Utilities.WHITE));
        
        for(int i = 0; i < 8; i++) {
            builder.setPiece(new Pawn(1, i, Utilities.WHITE));
        }
        
        builder.setPiece(new Rook(7,0,Utilities.BLACK));
        builder.setPiece(new Knight(7,1,Utilities.BLACK));
        builder.setPiece(new Bishop(7,2,Utilities.BLACK));
        builder.setPiece(new Queen(7,3,Utilities.BLACK));
        builder.setPiece(new King(7,4,Utilities.BLACK));
        builder.setPiece(new Bishop(7,5,Utilities.BLACK));
        builder.setPiece(new Knight(7,6,Utilities.BLACK));
        builder.setPiece(new Rook(7,7,Utilities.BLACK));
        
        for(int i = 0; i < 8; i++) {
            builder.setPiece(new Pawn(6, i, Utilities.BLACK));
        }
        builder.setNextMove(Utilities.WHITE);
        return builder.build();
    }

    public Square getSqaure(final int x, final int y) {
        return gameBoard.get(x).get(y);
    }
    public boolean isValidCoordiante(int x, int y) {
        return x >= 0 && y >= 0 && x < 8 && y < 8;
    }

    public Collection<Piece> getBlackPieces() {
        return this.blackPieces;
    }

    public Collection<Piece> getWhitePieces() {
        return this.whitePieces;
    }

    public Player whitePlayer() {
        return this.whitePlayer;
    }

    public Player blackPlayer() {
        return this.whitePlayer;
    }

    public List<Moves> getAllLegalMoves() {
        List<Moves>allLegalMoves = new ArrayList<>();
        allLegalMoves.addAll(this.whitePlayer.getLegalMoves());
        allLegalMoves.addAll(this.blackPlayer.getLegalMoves());
        return allLegalMoves;
    }
}

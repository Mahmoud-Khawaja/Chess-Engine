package com.chess.gui;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.chess.engine.board.Board;
import com.chess.engine.board.Square;
import com.chess.engine.logic.Moves;
import com.chess.engine.pieces.Piece;
import com.chess.engine.player.Transition;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Table {
    private final JFrame gameFrame; 
    private final BoardPanel boardPanel;
    private Board chessBoard;


    private Square source;
    private Square destination;
    private Piece HumanMovedPiece;


    private static final Dimension OUTER_FRAME_DIMENSION = new Dimension(800,800);
    private static final Dimension BOARD_PANEL_DIMENSIONS = new Dimension(400,35);
    private static final Dimension TILE_PANEL_DIMENSIONS = new Dimension(60,60);

    private static String defaultImagePath = "src/com/chess/pics/";

    private final Color lightColor = Color.decode("#FFFACD");
    private final Color darkColor = Color.decode("#593E1A");
    
    public Table(){
        this.gameFrame = new JFrame("Jchess");
        this.gameFrame.setLayout(new BorderLayout());
        final JMenuBar tableMenuBar = createMenuBar();
        this.gameFrame.setJMenuBar(tableMenuBar);
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        this.chessBoard = Board.createStandardBoard();
        this.boardPanel = new BoardPanel();
        this.gameFrame.add(this.boardPanel,BorderLayout.CENTER);
        this.gameFrame.setVisible(true);
    }

    private JMenuBar createMenuBar() {
        final JMenuBar tablJMenuBar = new JMenuBar();
        tablJMenuBar.add(createFileMenu());
        return tablJMenuBar;
    }

    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu("File");
        final JMenuItem openPGN = new JMenuItem("Load PGN File");
        openPGN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("open up PGN");
            }
        });
        fileMenu.add(openPGN);
        final JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);
        return fileMenu;
    }

    private class BoardPanel extends JPanel {
        final List<SquarePanel> boardSquares;
        BoardPanel(){
            super(new GridLayout(8,8));
            this.boardSquares = new ArrayList<>();
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                final SquarePanel squarePanel = new SquarePanel(this,i,j);
                this.boardSquares.add(squarePanel);
                add(squarePanel);
                }
            }
            setPreferredSize(BOARD_PANEL_DIMENSIONS);
            validate();
        }

        public void drawBoard(Board chessBoard) {
            removeAll();
            for(final SquarePanel squarePanel : boardSquares){
                squarePanel.drawSquare(chessBoard);
                add(squarePanel);
            }
            validate();
            repaint();
        }
        

    }

    private class SquarePanel extends JPanel {
        private final int sqaureX;
        private final int sqaureY;
        SquarePanel(final BoardPanel boardPanel,final int sqaureX, final int sqaureY){
            super(new GridLayout());
            this.sqaureX = sqaureX;
            this.sqaureY = sqaureY;
            setPreferredSize(TILE_PANEL_DIMENSIONS);
            assingSquareColor();
            assingSquares(chessBoard);
            addMouseListener(new MouseListener() {@Override
                public void mouseClicked(final MouseEvent e) {
                    if (SwingUtilities.isRightMouseButton(e)) {
                        // Reset everything if the right mouse button is clicked
                        source = null;
                        destination = null;
                        HumanMovedPiece = null;
                    } else if (SwingUtilities.isLeftMouseButton(e)) {
                        // If the left mouse button is clicked, proceed with the move
                        if (source == null) {
                            // First click: select the source square
                            source = chessBoard.getSquare(sqaureX, sqaureY);
                            HumanMovedPiece = source.getPiece();
                            if (HumanMovedPiece == null) {
                                source = null;
                            }
                        } else {
                            // Second click: select the destination square and make the move
                            destination = chessBoard.getSquare(sqaureX, sqaureY);
                            final Moves move = Moves.FactoryClass.createMoves(chessBoard,
                                    source.getSquareCoordinatesX(), source.getSquareCoordinatesY(),
                                    destination.getSquareCoordinatesX(), destination.getSquareCoordinatesY());
                            final Transition transition = chessBoard.currentPlayer().makeMove(move);
                            if (transition.getMoveStatus().isDone()) {
                                chessBoard = transition.getTransitionBoard();
                                // Add this move to the move log if needed
                            }
                            // Reset the source, destination, and human moved piece for the next move
                            source = null;
                            destination = null;
                            HumanMovedPiece = null;
                        }
                        // Update the GUI after the move is made
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                boardPanel.drawBoard(chessBoard);
                            }
                        });
                    }
                }
                @Override
                public void mousePressed(final MouseEvent e) {
                }

                @Override
                public void mouseReleased(final MouseEvent e) {
                }

                @Override
                public void mouseEntered(final MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
                
        });
            validate();
        }
        public void drawSquare(final Board board){
            assingSquareColor();
            assingSquares(board);
            validate();
            repaint();
        }
        private void assingSquareColor() {
                setBackground((sqaureX + sqaureY) % 2 == 0?lightColor:darkColor);
        }
        private void assingSquares(final Board board){
            this.removeAll();
            
            if(board.getSquare(sqaureX, sqaureY).isOccupied()){
                try {
                    final BufferedImage image = ImageIO.read(
                        new File(defaultImagePath + board.getSquare(sqaureX, sqaureY).getPiece().getPieceUtility().toString().substring(0,1) 
                        + board.getSquare(sqaureX, sqaureY).getPiece().toString()+".png"));
                    
                    // Scale the image to a larger size
                    int newWidth = TILE_PANEL_DIMENSIONS.width;
                    int newHeight = TILE_PANEL_DIMENSIONS.height;
                    Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                    
                    add(new JLabel(new ImageIcon(scaledImage)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
}

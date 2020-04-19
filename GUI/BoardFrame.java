package GUI;

import Game.Board;
import Game.ComputerPlayer;
import Game.Manipulator;
import jdk.nashorn.internal.scripts.JO;
import pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardFrame extends JFrame {

    public BoardFrame(){
        super("White to Move");
        // This part adds all of the features of the board including menu bar stuff
        BoardPanel m_boardPanel = new BoardPanel();
        JMenuBar menuBar = new JMenuBar();
        JMenu onePlayer = new JMenu("New One Player");

        // This part constructs the menu bar objects
        JMenuItem newWhiteOP = new JMenuItem("WHITE");
        JMenuItem newBlackOP = new JMenuItem("BLACK");
        JMenuItem newTwoPlayer = new JMenuItem("New Two Player");



        // Adds action listener so that the board is set up correctly
        newTwoPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                m_boardPanel.setupBoard();
                m_boardPanel.mode = 0;
                System.out.println("mode 0");
            }
        });
        newWhiteOP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                m_boardPanel.setupBoard();
                m_boardPanel.mode = 1;
                m_boardPanel.m_opp = new ComputerPlayer(m_boardPanel.m_board, Color.BLACK);
            }
        });
        newBlackOP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                m_boardPanel.setupBoard();
                m_boardPanel.mode = 2;
                m_boardPanel.setupBlack();
            }
        });

        // Adds all of the pieces to the frame
        onePlayer.add(newWhiteOP);
        onePlayer.add(newBlackOP);
        menuBar.add(newTwoPlayer);
        menuBar.add(onePlayer);

        // final setup for the GUI
        this.setJMenuBar(menuBar);
        this.setLayout(new BorderLayout());
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(m_boardPanel);
        this.setVisible(true);
    }

    public class BoardPanel extends JPanel {
        Board m_board;
        Square[][] m_squares = new Square[8][8];
        Piece movePiece;
        Color m_toMove;
        ComputerPlayer m_opp;
        int mode; // mode 0 then 2 player. 1 white single, 2 black single

        public BoardPanel(){
            setLayout(new GridLayout(8,8));
            m_board = new Board();

            Square addSquare;
            for(int j=7; j>=0; j--){
                for(int i=0; i<8; i++){
                    addSquare = new Square(i,j);
                    m_squares[i][j] = addSquare;
                    add(addSquare);
                }
            }
            setupBoard();

            movePiece = null;
            m_toMove = Color.WHITE;
        }


        public void setupBoard(){
            System.out.println("HERE");
            BoardFrame.super.setTitle("White to move");
            m_toMove = Color.WHITE;
            movePiece = null;
            m_board.setupBoard();
            Piece testPiece;
            for(int i=0; i<8; i++){
                for(int j=0; j<8; j++){
                    testPiece = m_board.getPieceOnPosition(i, j);
                    if(testPiece == null){
                        m_squares[i][j].setIcon(null);
                    } else{
                        m_squares[i][j].setIcon(testPiece.getIcon());
                    }
                }
            }
        }

        public void setupBlack(){
            BoardFrame.super.setTitle("Computer is thinking");
            m_toMove = Color.WHITE;
            movePiece = null;
            m_board.setupBoard();
            m_opp = new ComputerPlayer(m_board, Color.WHITE);
            m_opp.movePiece();
            Piece testPiece;
            for(int i=0; i<8; i++){
                for(int j=0; j<8; j++){
                    testPiece = m_board.getPieceOnPosition(i, j);
                    if(testPiece == null){
                        m_squares[i][j].setIcon(null);
                    } else{
                        m_squares[i][j].setIcon(testPiece.getIcon());
                    }
                }
            }
            m_toMove = Color.BLACK;
            BoardFrame.super.setTitle("Black to move");
        }


        private class Square extends JButton {
            int m_x;
            int m_y;
            Color m_color;


            public Square(int x, int y){
                super();
                m_x = x;
                m_y = y;

                m_color = (x+y) % 2 == 0 ? Color.WHITE : Color.GRAY;
                setBackground(m_color);

                addActionListener( new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        Piece testPiece = m_board.getPieceOnPosition(m_x, m_y);
                        int curX = 0;
                        int curY = 0;

                        if(mode == 0 || (mode == 1 && m_toMove == Color.WHITE) || (mode == 2 && m_toMove == Color.BLACK)){ // only process a click if the mode is correct
                            if(testPiece != null && testPiece.getColor() == m_toMove) {
                                movePiece = testPiece;
                                curX = movePiece.getX();
                                curY = movePiece.getY();
                            } else if(movePiece != null){
                                curX = movePiece.getX();
                                curY = movePiece.getY();
                                if(m_board.movePiece(movePiece, m_x, m_y) == 0){
                                    if(m_toMove == Color.WHITE){
                                        m_toMove = Color.BLACK;
                                    } else{
                                        m_toMove = Color.WHITE;
                                    }

                                    m_squares[curX][curY].setIcon(null);
                                    m_squares[m_x][m_y].setIcon(m_board.getPieceOnPosition(m_x,m_y).getIcon());

                                    if(mode == 1 || mode == 2) {
                                        if (m_board.checkmated(m_toMove)) {
                                            JOptionPane.showMessageDialog(null, "You WIN!");
                                            m_toMove = null;
                                            return;
                                        }

                                        BoardFrame.super.setTitle("Computer is moving");
                                        m_opp.movePiece();

                                        Piece redrawPiece;
                                        Icon img;
                                        for (int i = 0; i < 8; i++) {
                                            for (int j = 0; j < 8; j++) {
                                                redrawPiece = m_board.getPieceOnPosition(i, j);
                                                img = redrawPiece == null ? null : redrawPiece.getIcon();
                                                m_squares[i][j].setIcon(img);
                                            }
                                        }

                                        m_toMove = movePiece.getColor();
                                        if(m_board.checkmated(m_toMove)){
                                            JOptionPane.showMessageDialog(null, "You Lose!");
                                            m_toMove = null;
                                            return;
                                        }

                                        m_toMove = movePiece.getColor();
                                    }
                                    movePiece = null;
                                    String title = m_toMove == Color.WHITE ? "White to move" : "Black to move";
                                    BoardFrame.super.setTitle(title);
                                } else{
                                    System.out.println("Invalid move");
                                }
                            }
                        }
                    }
                });


            }

            public Square(String text){
                super(text);
            }

            public Square(){
                super();
            }

            public int get_mX(){
                return m_x;
            }

            public int get_mY(){
                return m_y;
            }

            public Color get_mColor(){
                return m_color;
            }

            public void set_mX(int x){
                m_x = x;
            }

            public void set_mY(int y){
                m_y = y;
            }
        }
    }

    public static void main(String[] Args){
        BoardFrame frame = new BoardFrame();
    }
}
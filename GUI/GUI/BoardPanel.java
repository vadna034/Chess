package GUI;

import Game.Board;
import pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardPanel extends JPanel {
    Board m_board;
    Square[][] m_squares = new Square[8][8];
    Piece movePiece;

    public BoardPanel(){
        setLayout(new GridLayout(8,8));
        m_board = new Board();
        setupBoard(m_board);
        movePiece = null;
    }


    public void setupBoard(Board testBoard){
        Square b1;
        Board gameBoard = testBoard;
        Piece testPiece;
        ImageIcon icon;

        for(int j=7; j>=0; j--){
            for(int i=0; i<8; i++){
                b1 = new Square(i, j);
                testPiece = gameBoard.getPieceOnPosition(i, j);

                if(testPiece != null) {
                    icon = testPiece.getIcon();
                    b1.setIcon(icon);
                }
                m_squares[i][j] = b1;

                add(b1);

            }
        }
    }



    private class Square extends JButton {
        int m_x;
        int m_y;
        Color m_color;
        ImageIcon m_icon;

        public Square(int x, int y){
            super();
            m_x = x;
            m_y = y;

            m_color = (x+y) % 2 == 0 ? Color.WHITE : Color.GRAY;
            setBackground(m_color);

            addActionListener( new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    if(movePiece == null){
                        movePiece = m_board.getPieceOnPosition(m_x, m_y);
                        System.out.println(movePiece);
                    }
                    else {
                        int curX = movePiece.getX();
                        int curY = movePiece.getY();
                        ImageIcon curIcon = movePiece.getIcon();

                        if(m_board.movePiece(movePiece, m_x, m_y) == 0){
                            System.out.println("move successful");

                            m_squares[m_x][m_y].setIcon(curIcon);
                            m_squares[curX][curY].setIcon(null);

                        }
                        else{
                            System.out.println("move unseccessful");
                        }

                        movePiece = null;

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

        public ImageIcon get_mIcon(){
            return m_icon;
        }

        public void set_mIcon(ImageIcon icon){
            m_icon = icon;
            setIcon(icon);
        }
    }


}

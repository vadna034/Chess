package GUI;

import Game.Board;
import Game.PieceCollection;
import pieces.Piece;

import javax.swing.*;
import java.awt.*;


public class Tutorial extends JPanel{
    JButton b1;

    JButton selected1;
    JButton selected2;

    public Tutorial(){
        setLayout(new GridLayout(8,8));

        Board gameBoard = new Board();
        Piece testPiece;
        ImageIcon icon;

        for(int j=7; j>=0; j--){
            for(int i=0; i<8; i++){

                testPiece = gameBoard.getPieceOnPosition(i, j);

                if(testPiece != null){
                    icon = testPiece.getIcon();
                    b1 = new JButton(icon);
                } else{
                    b1 = new JButton();
                }

                if((i+j) % 2 == 0){
                    b1.setBackground(Color.WHITE);
                } else{
                    b1.setBackground(Color.GRAY);
                }

                add(b1);

            }
        }
    }


    public static void main(String[] Args){
        BoardFrame frame = new BoardFrame();


    }
}

package Game;

import Game.*;
import pieces.*;

import java.awt.*;
import java.util.Scanner;


public class Player extends Manipulator{
    public Player(Board board, Color teamColor){
        m_board = board;
        m_color = teamColor;
        m_pieces = (m_color.equals(Color.WHITE)) ? m_board.getWhiteTeam() : m_board.getBlackTeam();
    }

    public void movePiece(){
        int input1, input2, input3, input4;
        boolean moveMade = false;

        Scanner myScanner = new Scanner(System.in);
        while(! moveMade) {


            m_board.printBoard();
            System.out.println(this.toString() + " to move");
            System.out.print("Please enter the position of a piece you would like to move: ");
            input1 = myScanner.nextInt();
            input2 = myScanner.nextInt();

            System.out.print("Please enter the position you would like to move this piece to: ");
            input3 = myScanner.nextInt();
            input4 = myScanner.nextInt();

            System.out.println();

            if (movePiece(input1, input2, input3, input4) == 1) {
                System.out.println("INVALID MOVE SELECTED");
                continue;
            } else{
                moveMade = true;
            }

        }
    }

    // Returns 0 upon a successful move. Returns 1 upon a failed move.
    public int movePiece(int sx, int sy, int nx, int ny) {
        Piece movePiece = m_pieces.getPieceOnPosition(sx, sy);
        if(movePiece == null){
            return 1;
        }
        return m_board.movePiece(movePiece, nx, ny);
    }
}

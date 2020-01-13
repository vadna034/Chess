package pieces;

import java.awt.*;

public class Queen extends Piece {
    public Queen(int x, int y, Color color){
        m_x = x;
        m_y = y;
        m_color = color;
        m_score = 9;
        m_type = Type.QUEEN;
    }

    public Queen copyPiece(){
        return new Queen(m_x, m_y, m_color);
    }

    public String toString(){
        if(m_color == Color.WHITE){
            return "wQ";
        }
        else{
            return "bQ";
        }
    }

    public boolean isValidMove(int x, int y){
        return (m_x == x ^ m_y == y) //Valid move for a rook
                || ((Math.abs(m_x - x) == Math.abs(m_y - y)) && (x != m_x || y != m_y));
    }
}

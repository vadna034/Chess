package pieces;

import java.awt.*;

public class King extends Piece{
    public King(int x, int y, Color color){
        m_x = x;
        m_y = y;
        m_color = color;
        m_score = 100000;
        m_type = Type.KING;
    }

    public King copyPiece(){
        return new King(m_x, m_y, m_color);
    }

    public String toString(){
        if(m_color == Color.WHITE){
            return "wK";
        }
        else{
            return "bK";
        }
    }

    public boolean isValidMove(int x, int y){
        return Math.abs(m_x - x) <= 1 && Math.abs(m_y - y) <= 1 && (x != m_x || y != m_y);
    }
}

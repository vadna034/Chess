package pieces;

import java.awt.*;

public class Bishop extends Piece{
    public Bishop(int x, int y, Color color){
        m_x = x;
        m_y = y;
        m_color = color;
        m_score = 3;
        m_type = Type.BISHOP;
    }

    public Bishop copyPiece(){
        return new Bishop(m_x, m_y, m_color);
    }

    public String toString(){
        if(m_color == Color.WHITE){
            return "wB";
        }
        else{
            return "bB";
        }
    }

    public boolean isValidMove(int x, int y){
        return Math.abs(m_x - x) == Math.abs(m_y - y) && (x != m_x || y != m_y);
    }
}

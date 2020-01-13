package pieces;

import java.awt.*;

public class Horse extends Piece{
    public Horse(int x, int y, Color color){
        m_x = x;
        m_y = y;
        m_color = color;
        m_score = 3;
        m_type = Type.HORSE;
    }

    public Horse copyPiece(){
        return new Horse(m_x, m_y, m_color);
    }

    public String toString(){
        if(m_color == Color.WHITE){
            return "wH";
        }
        else{
            return "bH";
        }
    }

    public boolean isValidMove(int x, int y){
        return (Math.abs(m_x - x) == 2 && Math.abs(m_y - y) == 1)
                || (Math.abs(m_y - y) == 2 && Math.abs(m_x - x) == 1);
    }
}
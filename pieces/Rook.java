package pieces;

import java.awt.*;

public class Rook extends Piece{

    public Rook(int x, int y, Color color){
        m_x = x;
        m_y = y;
        m_color = color;
        m_score = 5;
        m_type = Type.ROOK;
    }

    public Rook copyPiece(){
        return new Rook(m_x, m_y, m_color);
    }

    public String toString(){
        if(m_color == Color.WHITE){
            return "wR";
        }
        else{
            return "bR";
        }
    }

    public boolean isValidMove(int x, int y){
        return m_x == x ^ m_y == y;
    }
}

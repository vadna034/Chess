package pieces;

import java.awt.*;

public class Pawn extends Piece{
    public Pawn(int x, int y, Color color){
        m_x = x;
        m_y = y;
        m_color = color;
        m_score = 1;
        m_type = Type.PAWN;
        hasMoved = false;
    }

    public Pawn(int x, int y, Color color, boolean hmoved){
        m_x = x;
        m_y = y;
        m_color = color;
        m_score = 1;
        m_type = Type.PAWN;
        hasMoved = hmoved;
    }

    //Returns a new piece with type
    public Pawn copyPiece(){
        Pawn newPawn = new Pawn(m_x, m_y, m_color, hasMoved);
        return newPawn;
    }

    public String toString(){
        if(m_color == Color.WHITE){
            return "wP";
        }
        else{
            return "bP";
        }
    }

    public boolean isValidMove(int x, int y){
        int yDiff = m_y - y;
        if(m_color == Color.WHITE){
            yDiff *= -1;
        }

        return x == m_x && (yDiff == 1 || (yDiff == 2 && !hasMoved));
    }

    public boolean getHasMoved(){
        return hasMoved;
    }
}

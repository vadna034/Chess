package pieces;

import javax.swing.*;
import java.awt.*;
/**
 * Class representing a pawn. Implements the abstract piece class
 */
public class Pawn extends Piece{
    /**
     * Paramaetrized constructor for the pawn class
     * @param x xPos of the new pawn
     * @param y yPos of the new pawn
     * @param color color of the new pawn
     * Constructs a new pawn object
     */
    public Pawn(int x, int y, Color color){
        m_x = x;
        m_y = y;
        m_color = color;
        m_score = 1;
        m_type = Type.PAWN;
        m_hasMoved = false;

        if(Color.WHITE.equals(m_color)){
            m_icon = new ImageIcon("src/images/wPawn.png");
        } else{
            m_icon = new ImageIcon("src/images/bPawn.png");
        }
    }

    /**
     * Creates a deep copy of a pawn
     * @return returns a new pawn object with the same params as the copied pawn
     */
    public Pawn copyPiece(){
        Pawn newPawn = new Pawn(m_x, m_y, m_color);
        if(m_hasMoved){
            newPawn.setHasMoved();
        }
        return newPawn;
    }

    /**
     * @Override Returns a string for printing of the board
     * @return  "wP" or "bP" representing string of a pawn
     */
    public String toString(){
        if(m_color == Color.WHITE){
            return "wP";
        }
        else{
            return "bP";
        }
    }

    /**
     * Tests if a move is valid
     * @param newX: xPos that player wants to move the bishop to
     * @param newY: yPos that player wants to move the bishop to
     * @return True if a move is valid false otherwise
     */
    public boolean isValidMove(int newX, int newY){
        int yDiff = m_y - newY;
        if(m_color == Color.WHITE){
            yDiff *= -1;
        }

        return newX == m_x && (yDiff == 1 || (yDiff == 2 && ! m_hasMoved));
    }
}

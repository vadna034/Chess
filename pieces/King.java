package pieces;

import javax.swing.*;
import java.awt.*;
/**
 * Class representing a king. Implements the abstract piece class
 */
public class King extends Piece{
    /**
     * Paramaetrized constructor for the king class
     * @param x xPos of the new king
     * @param y yPos of the new king
     * @param color color of the new king
     * Constructs a new king object
     */
    public King(int x, int y, Color color){
        m_x = x;
        m_y = y;
        m_color = color;
        m_score = 100000;
        m_type = Type.KING;

        if(Color.WHITE.equals(m_color)){
            m_icon = new ImageIcon("src/images/wKing.png");
        } else{
            m_icon = new ImageIcon("src/images/bKing.png");
        }
    }

    /**
     * Creates a deep copy of a king
     * @return returns a new king object with the same params as the copied king
     */
    public King copyPiece(){
        return new King(m_x, m_y, m_color);
    }

    /**
     * @Override Returns a string for printing of the board
     * @return  "wK" or "bK" representing string of a king
     */
    public String toString(){
        if(m_color == Color.WHITE){
            return "wK";
        }
        else{
            return "bK";
        }
    }

    /**
     * Tests if a move is valid
     * @param newX: xPos that player wants to move the bishop to
     * @param newY: yPos that player wants to move the bishop to
     * @return True if a move is valid false otherwise
     */
    public boolean isValidMove(int newX, int newY) {
        return Math.abs(m_x - newX) <= 1 && Math.abs(m_y - newY) <= 1 && (newX != m_x || newY != m_y);
    }
}

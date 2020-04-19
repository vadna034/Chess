package pieces;

import javax.swing.*;
import java.awt.*;
/**
 * Class representing a queen. Implements the abstract piece class
 */
public class Queen extends Piece {
    /**
     * Paramaetrized constructor for the queen class
     * @param x xPos of the new queen
     * @param y yPos of the new queen
     * @param color color of the new queen
     * Constructs a new queen object
     */
    public Queen(int x, int y, Color color){
        m_x = x;
        m_y = y;
        m_color = color;
        m_score = 9;
        m_type = Type.QUEEN;

        if(Color.WHITE.equals(m_color)){
            m_icon = new ImageIcon("src/images/wQueen.png");
        } else{
            m_icon = new ImageIcon("src/images/bQueen.png");
        }
    }

    /**
     * Creates a deep copy of a queen
     * @return returns a new queen object with the same params as the copied queen
     */
    public Queen copyPiece(){
        return new Queen(m_x, m_y, m_color);
    }

    /**
     * @Override Returns a string for printing of the board
     * @return  "wQ" or "bQ" representing string of a queen
     */
    public String toString(){
        if(m_color == Color.WHITE){
            return "wQ";
        }
        else{
            return "bQ";
        }
    }

    /**
     * Tests if a move is valid
     * @param newX: xPos that player wants to move the bishop to
     * @param newY: yPos that player wants to move the bishop to
     * @return True if a move is valid false otherwise
     */
    public boolean isValidMove(int newX, int newY){
        return (m_x == newX ^ m_y == newY) //Valid move for a rook
                || ((Math.abs(m_x - newX) == Math.abs(m_y - newY)) && (newX != m_x || newY != m_y));
    }
}

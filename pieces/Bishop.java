package pieces;

import javax.swing.*;
import java.awt.*;

/**
 * Class representing a bishop. Implements the abstract piece class
 */
public class Bishop extends Piece{
    /**
     * Paramaetrized constructor for the bishop class
     * @param x xPos of the new bishop
     * @param y yPos of the new bishop
     * @param color color of the new bishop
     * Constructs a new bishop object
     */
    public Bishop(int x, int y, Color color){
        m_x = x;
        m_y = y;
        m_color = color;
        m_score = 3;
        m_type = Type.BISHOP;

        if(Color.WHITE.equals(m_color)){
            m_icon = new ImageIcon("src/images/wBishop.png");
        } else{
            m_icon = new ImageIcon("src/images/bBishop.png");
        }

    }

    /**
     * Creates a deep copy of a bishop
     * @return returns a new bishop object with the same params as the copied bishop
     */
    public Bishop copyPiece(){
        return new Bishop(m_x, m_y, m_color);
    }

    /**
     * @Override Returns a string for printing of the board
     * @return  "wB" or "bB" representing string of a bishop
     */
    public String toString(){
        if(m_color == Color.WHITE){
            return "wB";
        }
        else{
            return "bB";
        }
    }

    /**
     * Tests if a move is valid
     * @param newX: xPos that player wants to move the bishop to
     * @param newY: yPos that player wants to move the bishop to
     * @return True if a move is valid false otherwise
     */
    public boolean isValidMove(int newX, int newY){
        return Math.abs(m_x - newX) == Math.abs(m_y - newY) && (newX != m_x || newY != m_y);
    }
}

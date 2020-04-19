package pieces;

import javax.swing.*;
import java.awt.*;
/**
 * Class representing a horse. Implements the abstract piece class
 */
public class Horse extends Piece{
    /**
     * Paramaetrized constructor for the horse class
     * @param x xPos of the new horse
     * @param y yPos of the new horse
     * @param color color of the new horse
     * Constructs a new horse object
     */
    public Horse(int x, int y, Color color){
        m_x = x;
        m_y = y;
        m_color = color;
        m_score = 3;
        m_type = Type.HORSE;

        if(Color.WHITE.equals(m_color)){
            m_icon = new ImageIcon("src/images/wHorse.png");
        } else{
            m_icon = new ImageIcon("src/images/bHorse.png");
        }

    }

    /**
     * Creates a deep copy of a horse
     * @return returns a new horse object with the same params as the copied horse
     */
    public Horse copyPiece(){
        return new Horse(m_x, m_y, m_color);
    }

    /**
     * @Override Returns a string for printing of the board
     * @return  "wH" or "bH" representing string of a horse
     */
    public String toString(){
        if(m_color == Color.WHITE){
            return "wH";
        }
        else{
            return "bH";
        }
    }

    /**
     * Tests if a move is valid
     * @param newX: xPos that player wants to move the horse to
     * @param newY: yPos that player wants to move the horse to
     * @return True if a move is valid false otherwise
     */
    public boolean isValidMove(int newX, int newY){
        return (Math.abs(m_x - newX) == 2 && Math.abs(m_y - newY) == 1)
                || (Math.abs(m_y - newY) == 2 && Math.abs(m_x - newX) == 1);
    }
}
package pieces;

import javax.swing.*;
import java.awt.*;
/**
 * Class representing a rook. Implements the abstract piece class
 */
public class Rook extends Piece {
    /**
     * Paramaetrized constructor for the rook class
     * @param x xPos of the new rook
     * @param y yPos of the new rook
     * @param color color of the new rook
     * Constructs a new rook object
     */
    public Rook(int x, int y, Color color) {
        m_x = x;
        m_y = y;
        m_color = color;
        m_score = 5;
        m_type = Type.ROOK;

        if(Color.WHITE.equals(m_color)){
            m_icon = new ImageIcon("src/images/wRook.png");
        } else{
            m_icon = new ImageIcon( "src/images/bRook.png");
        }
    }

    /**
     * Creates a deep copy of a rook
     * @return returns a new rook object with the same params as the copied rook
     */
    public Rook copyPiece() {
        return new Rook(m_x, m_y, m_color);
    }

    /**
     * @Override Returns a string for printing of the board
     * @return  "wR" or "bR" representing string of a rook
     */
    public String toString() {
        if (m_color == Color.WHITE) {
            return "wR";
        } else {
            return "bR";
        }
    }

    /**
     * Tests if a move is valid
     * @param newX: xPos that player wants to move the bishop to
     * @param newY: yPos that player wants to move the bishop to
     * @return True if a move is valid false otherwise
     */
    public boolean isValidMove(int newX, int newY) {
        return m_x == newX ^ m_y == newY;
    }

}

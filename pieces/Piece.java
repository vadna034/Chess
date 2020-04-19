package pieces;

import javax.swing.*;
import java.awt.*;

public abstract class Piece {

    /**
     * Member variable representing x Position of the piece.
     */
    protected int m_x;
    /**
     * Member variable representing y Position of the piece.
     */
    protected int m_y;
    /**
     * Member variable representing the type of the piece
     */
    protected Type m_type;

    /**
     * Member variable used in calculation of the score of the piece
     */
    protected int m_score;

    /**
     * Member variable representing color of a piece making it easy to
     * identify easily which team a piece is on
     */
    protected Color m_color;

    /**
     * Member variable used mainly for pawns to test if they can move two
     * spaces or not
     */
    protected boolean m_hasMoved;

    /**
     * Member variable used in the GUI so that we may print the icon of the
     * piece on a button
     */
    protected ImageIcon m_icon;

    /**
     * Abstract function to copy a piece
     * @return a copy of the piece
     */
    public abstract Piece copyPiece();
    /**
     * Abstract function to convert a piece to a string
     * @return a string representation of the piece
     */
    public abstract String toString();

    /**
     * Abstract function to test if a certain piece can move to a position
     */
    public abstract boolean isValidMove(int x, int y);

    /****************** Getters and Setters ****************/

    /**
     * Sets x Position of the piece
     * @param x
     */
    public void setX(int x){
        m_x = x;
    }

    /**
     * Sets y Position of the piece
     * @param y
     */
    public void setY(int y){
        m_y = y;
    }

    /**
     * returns current y position of the piece
     * @return m_y
     */
    public int getX(){
        return m_x;
    }

    /**
     * returns current x position of the piece
     * @return m_y
     */
    public int getY(){
        return m_y;
    }

    /**
     * gets the type of a piece
     * @return m_type
     */
    public Type getType(){
        return m_type;
    }

    /**
     * gets the color of the piece
     * @return m_color
     */
    public Color getColor() {
        return m_color;
    }

    /**
     * gets the score of the piece
     * @return the score of the piece (1 for pawn, 3 for horse/bishop, 5 for rook, 9 for queen, 1000000 for king)
     */
    public int getScore(){
        return m_score;
    }

    /**
     * Tests if a piece is a pawn moving in an attacking way
     * @param newX xPos of the piece
     * @param newY yPos of the piece
     * @return True if the piece is a pawn and it is moving in an attacking way
     */
    public boolean isValidAttack(int newX, int newY){
        if(m_type != Type.PAWN || Math.abs(m_x - newX) != 1){
            return false;
        }
        if(m_color == Color.WHITE){
            return m_y - newY == -1;
        } else {
            return m_y - newY == 1;
        }
    }

    /**
     * Tests if a piece should be allowed to promote
     * @return True if a pawn on the last rank
     */
    public boolean shouldPromote(){
        return m_type.equals(Type.PAWN) && ((Color.WHITE.equals(m_color) && m_y == 7)
                || ( Color.BLACK.equals(m_color) &&  m_y == 0));
    }

    /**
     * Sets that a piece has moved
     */
    public void setHasMoved(){
        m_hasMoved = true;
    }

    /**
     * getter for whether a piece has moved
     * @return true if a piece hasn't moved yet
     */
    public boolean getHasMoved(){
        return m_hasMoved;
    }

    /**
     * Getter for the icon of the piece
     * @return Returns the icon of the piece
     */
    public ImageIcon getIcon(){
        return m_icon;
    }
}

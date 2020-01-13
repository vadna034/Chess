package pieces;

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

    protected int m_score;

    protected Color m_color;

    protected boolean hasMoved;

    //Returns a new piece with type
    public abstract Piece copyPiece();

    public abstract String toString();

    public abstract boolean isValidMove(int x, int y);

    /****************** Getters and Setters ****************/

    //Sets the xPos
    public void setX(int x){
        m_x = x;
    }

    //Sets the yPos
    public void setY(int y){
        m_y = y;
    }

    //Returns the x Position
    public int getX(){
        return m_x;
    }

    //Returns the y Position
    public int getY(){
        return m_y;
    }

    //Returns the type of a piece
    public Type getType(){
        return m_type;
    }

    public Color getColor() {
        return m_color;
    }

    public int getScore(){
        return m_score;
    }

    public boolean isValidAttack(int x, int y){
        if(m_type != Type.PAWN || Math.abs(m_x - x) != 1){
            return false;
        }
        if(m_color == Color.WHITE){
            return m_y - y == -1;
        } else {
            return m_y - y == 1;
        }
    }

    public boolean shouldPromote(){
        return m_type.equals(Type.PAWN) && ((Color.WHITE.equals(m_color) && m_y == 7)
                || ( Color.BLACK.equals(m_color) &&  m_y == 0));
    }

    public void setHasMoved(){
        hasMoved = true;
    }
}

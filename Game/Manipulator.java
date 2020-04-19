package Game;

import java.awt.*;
import java.util.ArrayList;

import pieces.*;

public abstract class Manipulator {
    protected PieceCollection m_pieces;
    protected Board m_board;
    protected Color m_color;


    public PieceCollection getPieces(){
        return m_pieces;
    }

    public String toString(){
        return (m_pieces.getColor().equals(Color.WHITE)) ? "WHITE" : "BLACK";
    }

    public boolean isCheckMated(){
        return m_board.checkmated(m_pieces);
    }

    public boolean isStaleMated(){
        return !m_board.teamCanMove(m_pieces);
    }

    public boolean isChecked(){
        return m_board.check(m_pieces);
    }

    public abstract void movePiece();
}

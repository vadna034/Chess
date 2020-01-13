package Game;

import pieces.*;

import java.awt.*;
import java.util.ArrayList;

import static java.lang.System.exit;
import static java.lang.System.in;


public class PieceCollection {
    public ArrayList<Piece> m_pieces;
    private Color m_color;


    public PieceCollection(Color inColor){
        m_color = inColor;
        m_pieces = new ArrayList<Piece>();

        if(inColor == Color.WHITE){
            setUpAsWhite();
        }
        else if(inColor == Color.BLACK){
            setUpAsBlack();    
        }
        else {
            System.out.println("Invalid input Color");
            exit(1);
        }
    }

    // Constructor for the case in which we want to copy a collection of pieces
    public PieceCollection(Color inColor, ArrayList<Piece> inPieces){
        m_color = inColor;
        m_pieces = inPieces;
    }


    private void setUpAsWhite(){
        m_pieces.clear();
        m_pieces.add(new Rook(0,0, m_color));
        m_pieces.add(new Horse(1,0, m_color));
        m_pieces.add(new Bishop(2,0, m_color));

        m_pieces.add(new King(3,0, m_color));

        m_pieces.add(new Queen(4,0, m_color));
        m_pieces.add(new Bishop(5,0, m_color));
        m_pieces.add(new Horse(6,0, m_color));
        m_pieces.add(new Rook(7,0, m_color));
        
        for(int i=0; i<8; i++){
            m_pieces.add(new Pawn(i, 1, m_color));
        }
    }
    
    private void setUpAsBlack(){
        m_pieces.clear();
        m_pieces.add(new Rook(0,7, m_color));
        m_pieces.add(new Horse(1,7, m_color));
        m_pieces.add(new Bishop(2,7, m_color));

        m_pieces.add(new King(3,7, m_color));

        m_pieces.add(new Queen(4,7, m_color));
        m_pieces.add(new Bishop(5,7, m_color));
        m_pieces.add(new Horse(6,7, m_color));
        m_pieces.add(new Rook(7,7, m_color));

        for(int i=0; i<8; i++){
            m_pieces.add(new Pawn(i, 6, m_color));
        }
        
    }
    
    public Color getColor(){
        return m_color;
    }

    public Piece getPieceOnPosition(int xPos, int yPos){
        for(Piece testPiece : m_pieces){
            if(testPiece.getX() == xPos && testPiece.getY() == yPos){
                return testPiece;
            }
        }
        return null;
    }

    public Piece getKing(){
        for(Piece testPiece : m_pieces){
            if(testPiece.getType() == Type.KING) return testPiece;
        }
        return null;
    }

    // Performs a deep copy of the team
    public PieceCollection deepCopy(){
        Color teamColor = m_color;
        ArrayList<Piece> teamPieces = new ArrayList<Piece>();

        for(Piece inPiece : m_pieces){
            teamPieces.add(inPiece.copyPiece());
        }

        return new PieceCollection(teamColor, teamPieces);
    }

    public void removePiece(Piece rPiece){
        m_pieces.remove(rPiece);
    }

    public ArrayList<Piece> getPieces(){
        return m_pieces;
    }

    public int getScore(){
        int score = 0;

        for(Piece testPiece : m_pieces){
            score += testPiece.getScore();
        }

        return score;
    }
}

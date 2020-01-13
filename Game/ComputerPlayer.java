package Game;

import pieces.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.System.exit;

public class ComputerPlayer extends Manipulator{
    PieceCollection m_opp;

    public ComputerPlayer(Board board, Color teamColor){
        m_board = board;
        m_color = teamColor;
        m_pieces = (m_color.equals(Color.WHITE)) ? m_board.getWhiteTeam() : m_board.getBlackTeam();
        m_opp = (m_color.equals(Color.WHITE)) ? m_board.getBlackTeam() : m_board.getWhiteTeam();
    }

    public void movePiece() {
        //m_board.printBoard();
        System.out.println(this.toString() + " to move");

        int newX = -1;
        int newY = -1;
        int curX;
        int curY;
        Piece mPiece = null;
        ArrayList<Piece> pieces = m_pieces.getPieces();
        Piece movePiece = null;

        Board copyBoard;
        int score = -2110000000;
        int newScore;
        Random random = new Random();

        for(Piece testPiece : pieces){
            curX = testPiece.getX();
            curY = testPiece.getY();
            for(int i=0; i<8; i++){
                for(int j=0; j<8; j++){
                    if(testPiece.isValidMove(i, j) || testPiece.isValidAttack(i, j)){
                        copyBoard = m_board.deepCopy();
                        mPiece = copyBoard.getPieceOnPosition(curX, curY);
                        if(copyBoard.computerMovePiece(mPiece, i, j) == 0) {

                            newScore = getOppMove(copyBoard, 1);

                            if (newScore > score) {
                                movePiece = testPiece;
                                score = newScore;
                                newX = i;
                                newY = j;
                            } else if (newScore == score && random.nextInt(7) == 0) {
                                movePiece = testPiece;
                                score = newScore;
                                newX = i;
                                newY = j;
                            }
                        }
                    }
                }
            }
        }

        if(movePiece == null){
            System.out.println("No move found in this position");
            m_board.printBoard();
            exit(1);
        }
        System.out.printf("Last move: (%d,%d) to (%d,%d)\n", movePiece.getX(), movePiece.getY(), newX, newY);
        m_board.computerMovePiece(movePiece, newX, newY);
    }

    public int getOppMove(Board moveBoard, int iteration){
        PieceCollection oppTeam = (Color.WHITE.equals(m_color)) ? moveBoard.getBlackTeam() : moveBoard.getWhiteTeam();
        PieceCollection curTeam = (Color.WHITE.equals(m_color)) ? moveBoard.getWhiteTeam() : moveBoard.getBlackTeam();
        int score = 200000000;
        int curX;
        int curY;
        Board copyBoard = null;

        ArrayList<Piece> oppPieces = oppTeam.getPieces();
        Piece mPiece;

        if(moveBoard.check(curTeam)){
            return -200000000;
        }

        if(moveBoard.checkmated(oppTeam)){
            return 200000000;
        }

        if(!moveBoard.teamCanMove(oppTeam)){
            return 0;
        }

        if(iteration >= 4){
            return moveBoard.getScore(curTeam);
        }

        for(Piece testPiece : oppPieces){
            curX = testPiece.getX();
            curY = testPiece.getY();
            for(int i=0; i<8; i++){
                for(int j=0; j<8; j++){
                    if(testPiece.isValidMove(i, j) || testPiece.isValidAttack(i, j)){
                        copyBoard = moveBoard.deepCopy();
                        mPiece = copyBoard.getPieceOnPosition(curX, curY);
                        if(copyBoard.computerMovePiece(mPiece, i, j) == 1) continue;
                        int scoreWhite = copyBoard.getWhiteTeam().getScore();
                        int scoreBlack = copyBoard.getBlackTeam().getScore();

                        score = Math.min(score, getUserMove(copyBoard, iteration + 1));
                    }
                }
            }
        }
        return score;
    }

    public int getUserMove(Board moveBoard, int iteration){
        PieceCollection curTeam = (Color.WHITE.equals(m_color)) ? moveBoard.getWhiteTeam() : moveBoard.getBlackTeam();
        PieceCollection oppTeam = (Color.WHITE.equals(m_color)) ? moveBoard.getBlackTeam() : moveBoard.getWhiteTeam();
        int score = -200000000;
        int curX;
        int curY;
        Board copyBoard = null;

        if(moveBoard.check(oppTeam)){
            return 200000000;
        }

        if(moveBoard.checkmated(curTeam)){
            return -200000000;
        }

        if(!moveBoard.teamCanMove(curTeam)){
            return 0;
        }

        if(iteration >= 4){
            return moveBoard.check(oppTeam) ? moveBoard.getScore(curTeam) + 1 : moveBoard.getScore(curTeam);
        }

        ArrayList<Piece> curPieces = curTeam.getPieces();
        Piece mPiece;

        for(Piece testPiece : curPieces){
            curX = testPiece.getX();
            curY = testPiece.getY();
            for(int i=0; i<8; i++){
                for(int j=0; j<8; j++){
                    if(testPiece.isValidMove(i, j) || testPiece.isValidAttack(i, j)){
                        copyBoard = moveBoard.deepCopy();
                        mPiece = copyBoard.getPieceOnPosition(curX, curY);
                        if(copyBoard.computerMovePiece(mPiece, i, j) == 1) continue;

                        score = Math.max(score, getOppMove(copyBoard, iteration + 1));
                    }
                }
            }
        }
        return score;
    }
}

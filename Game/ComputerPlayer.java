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
        m_pieces = (teamColor.equals(Color.WHITE)) ? m_board.getWhiteTeam() : m_board.getBlackTeam();
        m_opp = (teamColor.equals(Color.WHITE)) ? m_board.getBlackTeam() : m_board.getWhiteTeam();
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
        Piece killPiece = null;
        Piece testPiece;


        int score = -2110000000;
        int newScore;
        Random random = new Random();

        for(int xPos = 0; xPos<8; xPos++) {
            for (int yPos = 0; yPos < 8; yPos++) {
                testPiece = m_board.getPieceOnPosition(xPos, yPos);
                if(testPiece == null || testPiece.getColor() != m_pieces.getColor()) continue;
                curX = testPiece.getX();
                curY = testPiece.getY();
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (testPiece.isValidMove(i, j) || testPiece.isValidAttack(i, j)) {
                            mPiece = m_board.getPieceOnPosition(curX, curY);
                            killPiece = m_board.getPieceOnPosition(i, j);

                            if (m_board.computerMovePiece(mPiece, i, j) == 0) {

                                newScore = getOppMove(m_board, 1);

                                mPiece.setX(curX);
                                mPiece.setY(curY);


                                m_opp.addPiece(killPiece);


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
        }

        if(movePiece == null){
            System.out.println("No move found in this position");
            m_board.printBoard();
            exit(1);
        }
        System.out.printf("Last move: (%d,%d) to (%d,%d)\n", movePiece.getY(), movePiece.getX(), newY, newX);
        m_board.computerMovePiece(movePiece, newX, newY);
    }

    public int getOppMove(Board moveBoard, int iteration){
        PieceCollection oppTeam = m_opp;
        PieceCollection curTeam = m_pieces;
        int score = 200000000;
        int curX;
        int curY;

        Piece killPiece;
        Piece testPiece;


        if(moveBoard.check(curTeam)){
            return -200000000;
        }

        if(moveBoard.checkmated(oppTeam)){
            return 200000000;
        }

        if(!moveBoard.teamCanMove(oppTeam)){
            return 0;
        }

        if(iteration >= 3){
            return moveBoard.getScore(curTeam);
        }

        for(int xPos = 0; xPos < 8; xPos++) {
            for (int yPos = 0; yPos < 8; yPos++) {
                testPiece = m_board.getPieceOnPosition(xPos, yPos);
                if (testPiece == null || oppTeam.getColor() != testPiece.getColor()) continue;
                curX = testPiece.getX();
                curY = testPiece.getY();
                for(int i=0; i<8; i++){
                    for(int j=0; j<8; j++){
                        if(testPiece.isValidMove(i, j) || testPiece.isValidAttack(i, j)){


                            killPiece = m_board.getPieceOnPosition(i, j);
                            if(m_board.computerMovePiece(testPiece, i, j) == 1) continue;

                            score = Math.min(score, getUserMove(m_board, iteration + 1));

                            testPiece.setX(curX);
                            testPiece.setY(curY);
                            curTeam.addPiece(killPiece);
                        }
                    }
                }
            }
        }


        return score;
    }

    public int getUserMove(Board moveBoard, int iteration){
        PieceCollection curTeam = m_pieces;
        PieceCollection oppTeam = m_opp;
        int score = -200000000;
        int curX;
        int curY;
        Piece killPiece;
        Piece testPiece;

        if(moveBoard.check(oppTeam)){
            return 200000000;
        }

        if(moveBoard.checkmated(curTeam)){
            return -200000000;
        }

        if(!moveBoard.teamCanMove(curTeam)){
            return 0;
        }

        if(iteration >= 3){
            return moveBoard.check(oppTeam) ? moveBoard.getScore(curTeam) + 1 : moveBoard.getScore(curTeam);
        }

        Piece mPiece;

        for(int xPos = 0; xPos<8; xPos++) {
            for (int yPos = 0; yPos < 8; yPos++) {
                testPiece = m_board.getPieceOnPosition(xPos, yPos);
                if (testPiece == null || curTeam.getColor() != testPiece.getColor()) continue;
                curX = testPiece.getX();
                curY = testPiece.getY();
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (testPiece.isValidMove(i, j) || testPiece.isValidAttack(i, j)) {

                            mPiece = testPiece;
                            killPiece = m_board.getPieceOnPosition(i, j);

                            if (m_board.computerMovePiece(mPiece, i, j) == 1) continue;

                            score = Math.max(score, getOppMove(m_board, iteration + 1));

                            mPiece.setX(curX);
                            mPiece.setY(curY);
                            oppTeam.addPiece(killPiece);
                        }
                    }
                }
            }
        }
        return score;
    }

    public ArrayList<Integer> movePieceHelper() {
        //m_board.printBoard();
        System.out.println(this.toString() + " to move");

        int newX = -1;
        int newY = -1;
        int curX;
        int curY;
        Piece mPiece = null;
        ArrayList<Piece> pieces = m_pieces.getPieces();
        Piece movePiece = null;
        Piece killPiece = null;
        Piece testPiece;


        int score = -2110000000;
        int newScore;
        Random random = new Random();

        for(int xPos = 0; xPos<8; xPos++) {
            for (int yPos = 0; yPos < 8; yPos++) {
                testPiece = m_board.getPieceOnPosition(xPos, yPos);
                if(testPiece == null || testPiece.getColor() != m_pieces.getColor()) continue;
                curX = testPiece.getX();
                curY = testPiece.getY();
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (testPiece.isValidMove(i, j) || testPiece.isValidAttack(i, j)) {
                            mPiece = m_board.getPieceOnPosition(curX, curY);
                            killPiece = m_board.getPieceOnPosition(i, j);

                            if (m_board.computerMovePiece(mPiece, i, j) == 0) {

                                newScore = getOppMove(m_board, 1);

                                mPiece.setX(curX);
                                mPiece.setY(curY);


                                m_opp.addPiece(killPiece);


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
        }

        if(movePiece == null){
            System.out.println("No move found in this position");
            m_board.printBoard();
            exit(1);
        }
        System.out.printf("Last move: (%d,%d) to (%d,%d)\n", movePiece.getY(), movePiece.getX(), newY, newX);
        ArrayList<Integer> vals = new ArrayList<>();
        vals.add(movePiece.getX());
        vals.add(movePiece.getY());
        vals.add(newX);
        vals.add(newY);
        m_board.computerMovePiece(movePiece, newX, newY);
        return vals;
    }
}

package Game;

import pieces.King;
import pieces.*;

import java.awt.*;
import java.util.Scanner;

public class Board {
    private PieceCollection whiteTeam;
    private PieceCollection blackTeam;

    // Sets up the board with a new game
    public Board(){
        setupBoard();
    }

    // Sets up a board with pieces set to be the piece collections input into the constructor
    public Board(PieceCollection white, PieceCollection black){
        whiteTeam = white;
        blackTeam = black;
    }

    // Sets up a new set of pieces
    public void setupBoard(){
        whiteTeam = new PieceCollection(Color.WHITE);
        blackTeam = new PieceCollection(Color.BLACK);
    }

    // Function just works to print the board well!
    public void printBoard(){
        String boardString = "";
        Piece testPiece;

        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                testPiece = whiteTeam.getPieceOnPosition(j, i);

                if(testPiece != null) {
                    boardString += String.format("%8s", testPiece.toString());
                } else{
                    testPiece = blackTeam.getPieceOnPosition(j, i);
                    if(testPiece != null) {
                        boardString += String.format("%8s", testPiece.toString());
                    } else{
                        boardString += String.format("%8s", i + "," + j);
                    }
                }
            }
            boardString += "\n\n";
        }
        System.out.println(boardString);
    }

    //Checks that every position up until
    private boolean pathOpen(Piece testPiece, int xPos, int yPos){
        if(testPiece.getType() == Type.HORSE){
            return true;
        }
        /**
        // Sets lower X to be the lower of testPieces x and xPos
        int lowerX = (testPiece.getX() < xPos) ? testPiece.getX() : xPos;
        int lowerY = (testPiece.getY() < yPos) ? testPiece.getY() : yPos;

        // Sets these values to be the larger of testPieces y and yPos
        int higherX = (testPiece.getX() >= xPos) ? testPiece.getX() : xPos;
        int higherY = (testPiece.getY() >= yPos) ? testPiece.getY() : yPos;

        if(lowerX != higherX) lowerX++;
        if(lowerY != higherY) lowerY++;


        while(lowerX != higherX && lowerY != higherY){
            // Returns false if there is a piece on the given path
            if(whiteTeam.getPieceOnPosition(lowerX, lowerY) != null || blackTeam.getPieceOnPosition(lowerX, lowerY) != null){
                return false;
            }

            if(lowerX != higherX) lowerX++;
            if(lowerY != higherY) lowerY++;

        }
        return true;
         */
        int curX = testPiece.getX();
        int curY = testPiece.getY();

        int x_increment = 0;
        int y_increment = 0;

        if(xPos > curX){
            x_increment = 1;
        } else if(xPos < curX){
            x_increment = -1;
        }

        if(yPos > curY){
            y_increment = 1;
        } else if(yPos < curY){
            y_increment = -1;
        }

        curX += x_increment;
        curY += y_increment;

        while(curX != xPos || curY != yPos){
            if(getPieceOnPosition(curX, curY) != null){
                return false;
            }

            curX += x_increment;
            curY += y_increment;
        }

        return true;
    }

    // Just checks if a move is valid. Does not make the move. Does not modify the board, and does not check
    // if the king is in check
    private boolean moveValid(Piece testPiece, int xPos, int yPos){
        PieceCollection userTeam;
        PieceCollection oppTeam;
        int sx = testPiece.getX();
        int sy = testPiece.getY();

        if(testPiece.getColor() == Color.WHITE){
            userTeam = whiteTeam;
            oppTeam = blackTeam;
        }
        else {
            userTeam = blackTeam;
            oppTeam = whiteTeam;
        }
        if(testPiece.isValidAttack(xPos, yPos) && oppTeam.getPieceOnPosition(xPos, yPos) != null){
            return true;
        }
        else if(!testPiece.isValidMove(xPos, yPos)){
            return false;
        }
        else if(testPiece.getType() == Type.PAWN && (oppTeam.getPieceOnPosition(xPos, yPos) != null )){
            return false;
        }
        //False if there is a piece of the same color on that position
        else if(userTeam.getPieceOnPosition(xPos, yPos) != null){
            return false;
        }
        else if(xPos >= 8 || xPos <= -1 || yPos >= 8 || yPos <= -1){
            return false; // True if it is a pawn making an attack and there is a piece on that position
        }
        // Lastly we have true in the case that
        return pathOpen(testPiece, xPos, yPos);
    }

    public Board deepCopy(){
        return new Board(whiteTeam.deepCopy(), blackTeam.deepCopy());
    }

    // Checks if a team is in check
    public boolean check(PieceCollection userTeam){
        PieceCollection oppTeam = userTeam.equals(whiteTeam) ? blackTeam : whiteTeam;

        Piece userKing = userTeam.getKing();
        if(userKing == null){
            return true;
        }
        int xPos = userKing.getX();
        int yPos = userKing.getY();

        for(Piece testPiece : oppTeam.m_pieces){
            if(moveValid(testPiece, xPos, yPos)) return true;
        }
        return false;
    }

    public Piece getPieceOnPosition(int xPos, int yPos){
        Piece rPiece;
        rPiece = whiteTeam.getPieceOnPosition(xPos, yPos);
        return (rPiece != null) ? rPiece : blackTeam.getPieceOnPosition(xPos, yPos);
    }

    public boolean checkmated(PieceCollection testPieces){
        return check(testPieces) && !teamCanMove(testPieces);
    }

    public PieceCollection getWhiteTeam(){
        return whiteTeam;
    }

    public PieceCollection getBlackTeam(){
        return blackTeam;
    }

    // Hard coded movement of a piece. Tests if a move is valid, and if so performs that move.
    public int movePiece(Piece mPiece, int xPos, int yPos) {
        if (!moveValid(mPiece, xPos, yPos)) {
            return 1;
        }

        // Now need to check if this move leads our current team to be in check
        Board copyBoard = this.deepCopy();
        Piece copyPiece = copyBoard.getPieceOnPosition(mPiece.getX(), mPiece.getY());
        copyBoard.manMovePiece(copyPiece, xPos, yPos);

        PieceCollection copyTeam = (mPiece.getColor().equals(Color.WHITE)) ? copyBoard.getWhiteTeam() : copyBoard.getBlackTeam();

        if(copyBoard.check(copyTeam)){
            return 1;
        }

        manMovePiece(mPiece, xPos, yPos);

        if(mPiece.shouldPromote()){
            promotePiece(mPiece);
        }
        return 0;
    }

    public int computerMovePiece(Piece mPiece, int xPos, int yPos){
        if (!moveValid(mPiece, xPos, yPos)) {
            return 1;
        }

        // Now need to check if this move leads our current team to be in check
        Board copyBoard = this.deepCopy();
        Piece copyPiece = copyBoard.getPieceOnPosition(mPiece.getX(), mPiece.getY());
        copyBoard.manMovePiece(copyPiece, xPos, yPos);

        PieceCollection copyTeam = (mPiece.getColor().equals(Color.WHITE)) ? copyBoard.getWhiteTeam() : copyBoard.getBlackTeam();
        PieceCollection curTeam = (mPiece.getColor().equals(Color.WHITE)) ? whiteTeam : blackTeam;

        if(copyBoard.check(copyTeam)){
            return 1;
        }

        manMovePiece(mPiece, xPos, yPos);

        if(mPiece.shouldPromote()){
            Piece addQueen = new Queen(xPos, yPos, mPiece.getColor());
            curTeam.removePiece(mPiece);
            curTeam.m_pieces.add(addQueen);
        }
        return 0;
    }

    // Private Function allowing us to perform a move manually. Only used in the move valid function
    // So that we can check for check after a move has been made
    public void manMovePiece(Piece mPiece, int xPos, int yPos){
        PieceCollection oppTeam = (Color.WHITE.equals(mPiece.getColor())) ? blackTeam : whiteTeam;
        Piece killPiece = getPieceOnPosition(xPos, yPos);

        // Kills an opponents piece if it was on that position
        if(killPiece != null) oppTeam.removePiece(killPiece);

        mPiece.setX(xPos);
        mPiece.setY(yPos);
        mPiece.setHasMoved();
    }

    public boolean teamCanMove(PieceCollection pieces){
        Board copyBoard = this.deepCopy();

        PieceCollection userTeam = (pieces.getColor().equals(Color.WHITE)) ? copyBoard.getWhiteTeam() : copyBoard.getBlackTeam();

        for(Piece testPiece : userTeam.m_pieces){
            for(int i=0; i<8; i++){
                for(int j=0; j<8; j++){
                    if(copyBoard.movePiece(testPiece, i, j) == 0){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private void promotePiece(Piece piece){
        int xPos = piece.getX();
        int yPos = piece.getY();
        Color teamColor = piece.getColor();

        System.out.println("Promotion! what would you like to promote to? (h = horse, b = bishop, q = queen, r = rook");
        Scanner myScanner = new Scanner(System.in);
        char c;

        while(true){
            c = myScanner.next().charAt(0);
            if(c == 'q' || c == 'r' || c == 'h' || c == 'b'){
                break;
            }
            System.out.println("INVALID INPUT");
        }

        Piece addPiece;

        switch(c) {
            case 'q':
                addPiece = new Queen(xPos, yPos, teamColor);
                break;
            case 'r':
                addPiece = new Rook(xPos, yPos, teamColor);
                break;
            case 'h':
                addPiece = new Horse(xPos, yPos, teamColor);
                break;
            case 'b':
                addPiece = new Bishop(xPos, yPos, teamColor);
                break;
            default:
                addPiece = new Queen(xPos, yPos, teamColor);
        }

        PieceCollection curTeam;

        curTeam = Color.WHITE.equals(teamColor) ? whiteTeam : blackTeam;

        curTeam.removePiece(piece);

        curTeam.m_pieces.add(addPiece);
    }

    public int getScore(PieceCollection pieces){
        if(Color.WHITE.equals(pieces.getColor())){
            return whiteTeam.getScore() - blackTeam.getScore();
        }
        return blackTeam.getScore() - whiteTeam.getScore();
    }
}

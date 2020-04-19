package tests;

import Game.*;
import pieces.*;

import java.awt.*;
import java.util.Scanner;

public class ComputerTester {
    public static void main(String[] args) {
        Board gameBoard = new Board();
        ComputerPlayer whitePlayer = new ComputerPlayer(gameBoard, Color.WHITE);
        ComputerPlayer blackPlayer = new ComputerPlayer(gameBoard, Color.BLACK);

        ComputerPlayer curMover = whitePlayer;



        while(true){
            if(curMover.isCheckMated()){
                gameBoard.printBoard();
                curMover.isCheckMated();

                System.out.println(curMover.toString() + " is in checkmate!");
                break;
            }
            else if(curMover.isChecked()){
                System.out.println(curMover.toString() + " is in check!");

            }
            else if(curMover.isStaleMated()){
                curMover.isStaleMated();
                gameBoard.printBoard();
                System.out.println("STALEMATE");
                break;
            }

            curMover.movePiece();
            System.out.println("Black Score: " + blackPlayer.getPieces().getScore());
            System.out.println("White Score: " + whitePlayer.getPieces().getScore());
            curMover = (curMover.equals(whitePlayer)) ? blackPlayer : whitePlayer;
        }

    }
}
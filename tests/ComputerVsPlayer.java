package tests;

import Game.Board;
import Game.ComputerPlayer;
import Game.Manipulator;
import Game.Player;

import java.awt.*;

public class ComputerVsPlayer {
    public static void main(String[] args) {
        Board gameBoard = new Board();
        Player whitePlayer = new Player(gameBoard, Color.WHITE);
        ComputerPlayer blackPlayer = new ComputerPlayer(gameBoard, Color.BLACK);

        Manipulator curMover = whitePlayer;



        while(true){
            if(curMover.isCheckMated()){
                System.out.println(curMover.toString() + " is in checkmate!");
                break;
            }
            else if(curMover.isChecked()){
                System.out.println(curMover.toString() + " is in check!");

            }
            else if(curMover.isStaleMated()){
                System.out.println("STALEMATE");
                break;
            }

            curMover.movePiece();

            curMover = (curMover.equals(whitePlayer)) ? blackPlayer : whitePlayer;
        }

    }
}

package tests;

import Game.*;
import pieces.*;

import java.awt.*;
import java.util.Scanner;

public class PlayerTester {
    public static void main(String[] args) {
        Board gameBoard = new Board();
        Player whitePlayer = new Player(gameBoard, Color.WHITE);
        Player blackPlayer = new Player(gameBoard, Color.BLACK);
        int input1, input2, input3, input4;

        Player curMover = whitePlayer;
        Scanner myScanner = new Scanner(System.in);



        while(true){
            System.out.println(curMover + " to move");
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

            curMover = (curMover.equals(whitePlayer)) ? blackPlayer : whitePlayer;
        }

    }
}

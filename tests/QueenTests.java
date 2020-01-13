package tests;
import pieces.*;

import java.awt.*;

public class QueenTests {
    public static void main(String[] args) {
        Queen newQueen = new Queen(5, 6, Color.WHITE);

        System.out.println(newQueen);
        System.out.println(newQueen.isValidMove(6, 6));
        System.out.println(newQueen.isValidMove(5,5));
        System.out.println(newQueen.isValidMove(5,6));
        System.out.println(newQueen.isValidMove(5,7));
        System.out.println(newQueen.isValidMove(4,4));
        System.out.println(newQueen.isValidMove(7,8));
        System.out.println();

    }
}

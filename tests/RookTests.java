package tests;
import pieces.*;

import java.awt.*;

/**
 * Class I was using to test the if the rook isValidMove function was working correctly
 */
public class RookTests {
    public static void main(String[] args) {
        Rook newRook = new Rook(5,4, Color.WHITE);

        System.out.println(newRook);
        System.out.println(newRook.isValidMove(6, 6));
        System.out.println(newRook.isValidMove(5,5));
        System.out.println(newRook.isValidMove(5,6));
        System.out.println(newRook.isValidMove(5,7));
        System.out.println(newRook.isValidMove(4,4));
        System.out.println();
    }
}

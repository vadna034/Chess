package tests;
import pieces.*;

import java.awt.*;

public class KingTests {
    public static void main(String[] args) {
        King newKing = new King(5,5, Color.WHITE);

        System.out.println(newKing);
        System.out.println(newKing.isValidMove(6,6));
        System.out.println(newKing.isValidMove(5,5));
        System.out.println(newKing.isValidMove(5,6));
        System.out.println(newKing.isValidMove(5,7));
        System.out.println();
    }
}

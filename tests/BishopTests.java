package tests;
import pieces.*;

import java.awt.*;

public class BishopTests {
    public static void main(String[] args) {
        Bishop newBishop = new Bishop(4, 4, Color.WHITE); //Instantiate a new bishop at position 4, 4

        System.out.println(newBishop);
        System.out.println(newBishop.isValidMove(6, 6)); //Should print true
        System.out.println(newBishop.isValidMove(5,5));  //Should print true
        System.out.println(newBishop.isValidMove(5,6));  //Should print false
        System.out.println(newBishop.isValidMove(5,7));  //Should print false
        System.out.println(newBishop.isValidMove(4,4));  //Should print false
        System.out.println();

    }

    public static void changePos(Bishop testBishop){
        testBishop.setX(0);
        testBishop.setY(5);
        return;
    }
}

package tests;
import pieces.*;

import java.awt.*;

public class PawnTests {
    public static void main(String[] args) {
        Pawn newPawn = new Pawn(5, 6, Color.BLACK);

        System.out.println(newPawn);
        System.out.println(newPawn.isValidMove(6, 6));
        System.out.println(newPawn.isValidMove(5,5));
        System.out.println(newPawn.isValidMove(5,6));
        System.out.println(newPawn.isValidMove(5,7));
        System.out.println(newPawn.isValidMove(5,4));
        System.out.println(newPawn.isValidMove(7,8));
        System.out.println();
        System.out.println(newPawn.isValidAttack(5,4));
        System.out.println(newPawn.isValidAttack(4,5));
        System.out.println(newPawn.isValidAttack(6,5));
    }
}

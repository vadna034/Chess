package tests;
import pieces.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class I was using to test the if the horse isValidMove function was working correctly
 */
public class HorseTests {
    public static void main(String[] args) {
        Horse newHorse = new Horse(5,6, Color.WHITE); //Instantiates a new horse object at position (5, 6)

        System.out.println(newHorse);
        System.out.println(newHorse.isValidMove(6, 6)); //Should print false
        System.out.println(newHorse.isValidMove(5,5)); //Should print false
        System.out.println(newHorse.isValidMove(5,6)); //Should print false
        System.out.println(newHorse.isValidMove(5,7)); //Should print false
        System.out.println(newHorse.isValidMove(4,4)); //Should print true
        System.out.println(newHorse.isValidMove(7,7)); //Should print true
        System.out.println(); //Should print (5, 6)


    }
}

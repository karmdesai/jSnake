/**
 * This is the 'GameApple' class for the jSnake game.
 * This version of snake was made for the Graphical User Interface Assignment.
 * This class is used to move the apple that the snake wants to eat.
 * 
 * Made by Karm Desai (@karmdesai)
 * Version 1.0
 */

// Created a new class for the apple (object) for better organization
public class GameApple
{
    // The apple will always start off at (10, 10)
    int currentX = 10;
    int currentY = 10;

    // Use this method to set a new position for the apple
    public void newRandomLocation ()
    {
        // The grid will be 25 x 25, so set random co-ordinates from 1 to 23 (so that it's well inside the grid)
        currentX = (int)(Math.random() * 23);
        currentY = (int)(Math.random() * 23);
    }
}
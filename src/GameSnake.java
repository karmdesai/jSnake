/**
 * This is the 'GameSnake' class for the jSnake game.
 * This version of snake was made for the Graphical User Interface Assignment.
 * This class is used to control the snake's movement.
 * 
 * Made by Karm Desai (@karmdesai)
 * Version 1.0
 */

// Created a new class for the snake for better organization
public class GameSnake
{
    // Create a boolean that represents the status of the game
    boolean gameOver = false;

    // The direction that the snake is moving
    // Will use a KeyListener to change the direction
    String currentDirection = "movingRight";

    // The snake starts off with a length of 3
    int snakeLength = 3;

    // Create two arrays to store the co-ordinates of the snake
    // The number 625 is used since that represents the maximum area (we are using a 25 x 25 grid)
    int gameSnakeX [] = new int [625];
    int gameSnakeY [] = new int [625];

    public GameSnake ()
    {
        // Set the initial position of the snake (the top-left corner of the grid)
        // The Y Co-ordinate is always 1

        // The head is at X Co-ordinate 4
        gameSnakeX [0] = 4;
        gameSnakeY [0] = 1;

        // The second joint is at X Co-ordinate 3
        gameSnakeX [1] = 3;
        gameSnakeY [1] = 1;  

        // The third joint is at X Co-ordinate 2
        gameSnakeX [2] = 2;
        gameSnakeY [2] = 1;
    }

    public void moveSnake ()
    {
        // Start off with snakeLength and decrease 'i' by 1 after each iteration
        for (int i = snakeLength; i > 0; i -= 1)
        {
            /* The element at index 'i' becomes the element before it.
            The second joint moves where the first one was, the third joint
            moves where the second one was, and it goes on */
            gameSnakeX [i] = gameSnakeX [i - 1]; 
            gameSnakeY [i] = gameSnakeY [i - 1];
        }

        // If the user is moving right
        if (currentDirection == "movingRight")
        {
            // Add one to the element at index 0 of gameSnakeX (X co-ordinate += 1)
            gameSnakeX [0] += 1;
        }

        // If the user is moving left
        if (currentDirection == "movingLeft")
        {
            // Subtract one from the element at index 0 of gameSnakeX (X co-ordinate -= 1)
            gameSnakeX [0] -= 1;
        }

        // If the user is moving down
        if (currentDirection == "movingDown")
        {
            // Add one to the element at index 0 of gameSnakeY (Y co-ordinate += 1)
            gameSnakeY [0] += 1;
        }

        // If the user is moving up
        if (currentDirection == "movingUp")
        {
            // Subtract one from the element at index 0 of gameSnakeY (Y co-ordinate -= 1)
            gameSnakeY [0] -= 1;
        }

        // Use a for loop to check for collisions
        // Start off with snakeLength and decrease 'i' by 1 after each iteration 
        // This ensures that every part of the snake is tested
        for (int i = snakeLength; i > 0; i -= 1)
        {
            /* If the snake's 'head' (element at index 0) has the same co-ordinates 
            as a certain part of the body (the element at index 'i'), then a collision has occured. */
            if ((gameSnakeX [0] == gameSnakeX [i]) && (gameSnakeY [0] == gameSnakeY [i]))
            {
                // Set gameOver to true - this will represent the end of the game
                gameOver = true;
            }
        }

        // If the snake's head goes off the grid (east-side)
        if (gameSnakeX [0] > 23)
        {
            // Set gameOver to true - this will represent the end of the game
            gameOver = true;
        }

        // If the snake's head goes off the grid (west-side)
        if (gameSnakeX [0] < 0)
        {
            // Set gameOver to true - this will represent the end of the game
            gameOver = true;
        }

        // If the snake's head goes off the grid (south-side)
        if (gameSnakeY [0] > 22)
        {
            // Set gameOver to true - this will represent the end of the game
            gameOver = true;
        }

        // If the snake's head goes off the grid (north-side)
        if (gameSnakeY [0] < 0)
        {
            // Set gameOver to true - this will represent the end of the game
            gameOver = true;
        }
    } 
}
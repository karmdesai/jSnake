/**
 * This is the 'GameBoard' class for the jSnake game.
 * This version of snake was made for the Graphical User Interface Assignment.
 * It will be used to run an instance of the snake game.
 * 
 * Made by Karm Desai (@karmdesai)
 * Version 1.0
 */

// Import the required libraries/dependencies
import java.io.File;
import java.lang.System;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import javax.swing.*;
import javax.swing.Timer;
import javax.imageio.ImageIO;

public class GameBoard extends JPanel implements KeyListener, ActionListener
{
    // Create an instance of each class for the game
    GameSnake mySnake = new GameSnake ();
    GameApple myApple = new GameApple ();
    Timer myTimer;

    // Initialising the object images
    Image appleIcon = createCustomImage("assets/images/appleIcon.png");
    Image orangeIcon = createCustomImage("assets/images/orangeIcon.png");
    Image coinIcon = createCustomImage("assets/images/coinIcon.png");

    // Initialising the background images
    Image yellowSand = createCustomImage("assets/images/yellowSand.jpg");
    Image blueOcean = createCustomImage("assets/images/blueOcean.png");
    Image whiteClouds = createCustomImage("assets/images/whiteClouds.jpg");

    // 'Current' variables -> has to do with what backgrounds and objects the user prefers
    Image currentBackground;
    Image currentObject;
    String currentTheme;

    /* 'main' method -> there is one argument (userTheme) and 
    this is used since the method will later be called within itself */
    public static void main(String userTheme)
    {
        GameBoard content = new GameBoard(userTheme);

        JFrame window = new JFrame ("Java Snake - Play");
        window.setContentPane(content);

        // Set the window size to a 625 x 625 square
        window.setSize (625, 625);
        // Retrieve the user's screen size in width-height format
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the window
        window.setLocation((screenSize.width - window.getWidth()) / 2, 
            (screenSize.height - window.getHeight()) / 2);

        // Don't allow the user to resize the window (mantains aspect ratio)
        window.setResizable (false);
        window.setVisible (true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // class constructor -> also takes an argument called userTheme
    public GameBoard (String userTheme)
    {
        /* Assign userTheme to the global theme variable (currentTheme) 
        so that it can be used outside of the constructor */
        currentTheme = userTheme;

        // If userTheme is blueOcean
        if (userTheme == "blueOcean")
        {
            // Set currentBackground to the blueOcean image and the currentObject to the orangeIcon image
            currentBackground = blueOcean;
            currentObject = orangeIcon;
        }

        // Else if userTheme is yellowSand
        else if (userTheme == "yellowSand")
        {
            // Set currentBackground to the yellowSand image and the currentObject to the appleIcon image
            currentBackground = yellowSand;
            currentObject = appleIcon;
        }

        // Else if userTheme is whiteClouds
        else if (userTheme == "whiteClouds")
        {
            // Set currentBackground to the whiteClouds image and the currentObject to the coinIcon image
            currentBackground = whiteClouds;
            currentObject = coinIcon;
        }

        // Create a new timer
        myTimer = new Timer(150, this);

        // Start myTimer
        myTimer.start();

        // Add a keyListener so that the game can be playing using the arrow keys
        addKeyListener(this);

        // setFocusable to true -> this component will receive keyboard input
        setFocusable(true);
        requestFocus();
    }

    // Define the paint method (all painting code is placed here)
    public void paintComponent(Graphics g)
    {
        // Drawing the background image
        g.drawImage(currentBackground, 0, 0, null);

        /* Painting the snake */
        // For every joint/section of the snake
        for (int i = 0; i < mySnake.snakeLength; i += 1)
        {
            // If i is 0 (i.e. the head of the snake)
            if (i == 0)
            {
                // Set the color to purple (to differentiate the head from the rest)
                g.setColor(Color.MAGENTA);
            }

            // If i is any other number (i.e. any other joint)
            else
            {
                // Set the color to green
                g.setColor(Color.GREEN);
            }

            // Fill a 24 x 24 rectangle at the correct co-ordinate (this represents a joint)
            g.fillRect(mySnake.gameSnakeX[i] * 26, mySnake.gameSnakeY[i] * 26, 24, 24);
        }

        /* Painting the object */
        // Draw the object image at the correct co-ordinate (this represents the apple)
        g.drawImage(currentObject, myApple.currentX * 26, myApple.currentY * 26, null);  
    }

    // actionPerformed method (timer)
    public void actionPerformed (ActionEvent e)
    {
        // Move the snake using method 'moveSnake'
        mySnake.moveSnake();

        // After the snake moves, check if gameOver is true
        if (mySnake.gameOver == true)
        {
            // Stop myTimer
            myTimer.stop();

            // Create a new JOptionPane
            JOptionPane gameMessage = new JOptionPane();

            // Show the required dialog message and ask for user input -> 'Yes' or 'No'
            int userReply = gameMessage.showConfirmDialog(null, "Game Over!\nYour Score: " + (mySnake.snakeLength - 3) + 
                    "\nWould you like to play again?", " Game Over!", JOptionPane.YES_NO_OPTION);

            // If the user chooses 'Yes'
            if (userReply == JOptionPane.YES_OPTION)
            {
                // Call 'main' to open a new window and start a new instance of the game (takes currentTheme as an argument)
                main(currentTheme);
            }

            // If the user chooses 'No'
            else if (userReply == JOptionPane.NO_OPTION)
            {
                // Exit the program
                System.exit(0);
            }              
        }

        // If the snake and apple are at the same co-ordinates
        if ((mySnake.gameSnakeX[0] == myApple.currentX) && (mySnake.gameSnakeY[0] == myApple.currentY))
        {
            // Move the apple to a new (random) location
            myApple.newRandomLocation();

            // Add one to the snake's length
            mySnake.snakeLength += 1;
        }
        // 'repaint' method -> used to get the component to repaint itself
        repaint ();
    }

    // createCustomImage method (used for the game's background)
    public Image createCustomImage (String filePath)
    {
        // Initialise 'myImage' (as null for now)
        Image myImage = null;

        // Try-catch is required otherwise there will be an error
        try
        {
            // Try to read the image
            myImage = ImageIO.read(new File(filePath));
        }

        catch (Exception e)
        {
            // Helps track down the error
            e.printStackTrace();
        }

        // Return myImage -> if all goes well, the required image will be returned
        return myImage;
    }

    // keyPressed method
    @Override
    public void keyPressed (KeyEvent e)
    {
        /* If the left key is pressed and the snake is not currently 
        moving right (to avoid the snake from going into itself) */
        if ((e.getKeyCode() == KeyEvent.VK_LEFT) && (mySnake.currentDirection != "movingRight"))
        {
            // Change the currentDirection to left
            mySnake.currentDirection = "movingLeft";
        }

        /* If the right key is pressed and the snake is not currently 
        moving left (to avoid the snake from going into itself) */
        if ((e.getKeyCode() == KeyEvent.VK_RIGHT) && (mySnake.currentDirection != "movingLeft"))
        {
            // Change the currentDirection to right
            mySnake.currentDirection = "movingRight";
        }

        /* If the up key is pressed and the snake is not currently 
        moving down (to avoid the snake from going into itself) */
        if ((e.getKeyCode() == KeyEvent.VK_DOWN) && (mySnake.currentDirection != "movingUp"))
        {
            // Change the currentDirection to down
            mySnake.currentDirection = "movingDown";
        }

        /* If the down key is pressed and the snake is not currently 
        moving up (to avoid the snake from going into itself) */
        if ((e.getKeyCode() == KeyEvent.VK_UP) && (mySnake.currentDirection != "movingDown"))
        {
            // Change the currentDirection to up
            mySnake.currentDirection = "movingUp";
        }
    }

    // keyReleased method
    @Override
    public void keyReleased (KeyEvent e)
    {
        // Don't do anything
    }

    // keyTyped method
    @Override
    public void keyTyped (KeyEvent e)
    {
        // Don't do anything
    }
}
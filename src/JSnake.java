/**
 * This is the 'main' class for the jSnake game.
 * This version of checkers was made for the Graphical User Interface Assignment.
 * 
 * Made by Karm Desai (@karmdesai)
 * Version 1.0
 */

// Import the required libraries/dependencies
import java.io.File;
import java.lang.System;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class JSnake extends JPanel implements ActionListener
{
    // Declare global variables
    BorderLayout myBorderLayout = new BorderLayout();
    CardLayout myCardLayout = new CardLayout();

    // Create a separate JFrame for the actual game
    JFrame gameWindow = new JFrame ("Java Snake - Play");

    // Declare a variable called userTheme -> this will be used to set themes in the game
    // The default theme is "yellowSand"
    String userTheme = "yellowSand";

    /* Global JButtons (only required for the image 
    buttons so that they can be styled in actionPerformed) */
    JButton blueOceanButton;
    JButton yellowSandButton;
    JButton whiteCloudsButton;

    public static void main (String[] args)
    {
        JSnake content = new JSnake ();

        // Play music when the user starts the game
        musicPlayer.playAudio(new File("assets/theDeliSong.wav"));

        JFrame window = new JFrame ("Java Snake - Main Menu");
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

    public JSnake ()
    {
        // Use CardLayout to open new windows (content won't be display without it)
        setLayout(myCardLayout);

        // Call the mainMenu method
        mainMenu();
        // Call the optionScreen method
        optionScreen();
    }

    public void mainMenu ()
    {
        // Create the JPanel and set the background to a cherry red
        JPanel mainScreen = new JPanel ();
        mainScreen.setBackground (new Color (255, 99, 71));
        // I will do the layout manually
        mainScreen.setLayout(null);

        // Create the main title and style it
        JLabel gameTitle = new JLabel ("Snake");
        gameTitle.setFont(new Font("Poppins", Font.BOLD, 120));
        gameTitle.setForeground(Color.white);
        gameTitle.setBounds(112, 50, 400, 110);

        // Create a description of the game and style it
        JLabel descriptionTitle = new JLabel ("STRATEGY VIDEO GAME");
        descriptionTitle.setFont(new Font("Poppins", Font.BOLD, 30));
        descriptionTitle.setForeground(Color.white);
        descriptionTitle.setBounds(125, 150, 375, 50);

        // Create the 'Play' button and style it
        JButton playButton = new JButton ("New Game");
        // Set the background to transparent
        playButton.setBackground(new Color(0, 0, 0, 0));
        playButton.setForeground(Color.white);
        playButton.setFont(new Font("Poppins", Font.PLAIN, 30));
        playButton.setBorder(new LineBorder(Color.white, 4));

        // Set the button's size & bounds
        playButton.setSize(200, 50);
        playButton.setBounds(87, 250, 200, 50);

        // Remove that unnecessary border around text
        playButton.setFocusPainted(false);

        // Prevent the GIF from filling the transparent button
        playButton.setRolloverEnabled(false);
        playButton.setFocusable(false);

        // Set action command and add action listener
        playButton.setActionCommand("mainScreenButton");
        playButton.addActionListener(this);

        // Create the 'Options' button and style it
        JButton optionsButton = new JButton ("Options");
        // Set the background to transparent
        optionsButton.setBackground(new Color(0, 0, 0, 0));
        optionsButton.setForeground(Color.white);
        optionsButton.setFont(new Font("Poppins", Font.PLAIN, 30));
        optionsButton.setBorder(new LineBorder(Color.white, 4));

        // Set the button's size & bounds
        optionsButton.setSize(200, 50);
        optionsButton.setBounds(337, 250, 200, 50);

        // Remove that unnecessary border around text
        optionsButton.setFocusPainted(false);

        // Prevent the GIF from filling the transparent button
        optionsButton.setRolloverEnabled(false);
        optionsButton.setFocusable(false);

        // Set action command and add action listener
        optionsButton.setActionCommand("optionsButton");
        optionsButton.addActionListener(this);

        // Display a GIF of a snake from Scooby Doo
        JLabel snakeImage = new JLabel (createImageIcon("assets/imageIcons/snakeScoobyDoo.gif"));
        snakeImage.setBorder(new LineBorder(Color.white, 4));
        snakeImage.setBounds (132, 325, 360, 240);

        // Add all components to mainScreen
        mainScreen.add(gameTitle);
        mainScreen.add(descriptionTitle);
        mainScreen.add(playButton);
        mainScreen.add(optionsButton);
        mainScreen.add(snakeImage);

        // Add mainScreen
        add("panelOne", mainScreen);    
    }

    public void optionScreen ()
    {
        // Create the JPanel and set the background to a cherry red
        JPanel optionScreen = new JPanel ();
        optionScreen.setBackground (new Color (255, 99, 71));
        // I will do the layout manually
        optionScreen.setLayout(null);

        // Create the main title and style it
        JLabel screenTitle = new JLabel ("Options");
        screenTitle.setFont(new Font("Poppins", Font.BOLD, 110));
        screenTitle.setForeground(Color.white);
        screenTitle.setBounds(87, 50, 450, 125);

        // Create the 'Theme' title and style it
        JLabel themeTitle = new JLabel ("THEMES");
        themeTitle.setFont(new Font("Poppins", Font.BOLD, 30));
        themeTitle.setForeground(Color.white);
        themeTitle.setBounds(250, 200, 125, 50);

        // Create the 'Back' button and style it
        JButton backButton = new JButton ("Back");
        backButton.setBackground(new Color(0, 0, 0, 0));
        backButton.setForeground(Color.white);
        backButton.setFont(new Font("Poppins", Font.PLAIN, 30));
        backButton.setBorder(new LineBorder(Color.white, 4));

        // Set the button's size & bounds
        backButton.setSize(200, 50);
        backButton.setBounds(252, 500, 120, 50);

        // Remove that unnecessary border around text
        backButton.setFocusPainted(false);

        // Prevent the images from filling the transparent button
        backButton.setRolloverEnabled(false);
        backButton.setFocusable(false);

        // Set action command and add action listener
        backButton.setActionCommand("backButton");
        backButton.addActionListener(this);

        // Make the blueOcean JButton using createImageIcon
        blueOceanButton = new JButton (createImageIcon("assets/imageIcons/blueOceanIcon.png"));

        // Set the button's bounds
        blueOceanButton.setBounds(25, 275, 175, 175);

        // Set action command and add action listener
        blueOceanButton.setActionCommand("blueOceanButton");
        blueOceanButton.addActionListener(this);

        // Make the yellowSand JButton using createImageIcon
        yellowSandButton = new JButton (createImageIcon("assets/imageIcons/yellowSandIcon.jpg"));

        // Set the button's bounds
        yellowSandButton.setBounds(225, 275, 175, 175);

        // Set a border for this buttton only (since it is the default theme and is selected at the start)
        yellowSandButton.setBorder(new LineBorder(Color.white, 4));

        // Set action command and add action listener
        yellowSandButton.setActionCommand("yellowSandButton");
        yellowSandButton.addActionListener(this);

        // Make the whiteClouds JButton using createImageIcon
        whiteCloudsButton = new JButton (createImageIcon("assets/imageIcons/whiteCloudsIcon.jpg"));

        // Set the button's bounds
        whiteCloudsButton.setBounds(425, 275, 175, 175);

        // Set action command and add action listener
        whiteCloudsButton.setActionCommand("whiteCloudsButton");
        whiteCloudsButton.addActionListener(this);

        // Add all components to optionScreen
        optionScreen.add(screenTitle);
        optionScreen.add(themeTitle);
        optionScreen.add(backButton);
        optionScreen.add(blueOceanButton);
        optionScreen.add(yellowSandButton);
        optionScreen.add(whiteCloudsButton);

        // Add optionScreen
        add("panelTwo", optionScreen);
    }

    // gameScreen method (used for the actual game) -> will be an entirely new JFrame
    public void gameScreen (String userTheme)
    {
        GameBoard myGameBoard = new GameBoard(userTheme);

        gameWindow.setContentPane(myGameBoard);

        // Set the JFrame's size to a 625 x 625 square
        gameWindow.setSize (625, 625);
        // Retrieve the user's screen size in width-height format
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the window
        gameWindow.setLocation((screenSize.width - gameWindow.getWidth()) / 2, 
            (screenSize.height - gameWindow.getHeight()) / 2);

        // Don't allow the user to resize the JFrame (mantains aspect ratio)
        gameWindow.setResizable (false);
        gameWindow.setVisible (true);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
    }

    // createImageIcon method
    protected static ImageIcon createImageIcon (String path)
    {
        java.net.URL imgURL = JSnake.class.getResource(path);
        if (imgURL != null)
        {
            return new ImageIcon (imgURL);
        } 

        else 
        {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    // actionPerformed method
    public void actionPerformed (ActionEvent e)
    {
        // If the user clicks the 'Options' button
        if (e.getActionCommand().equals("optionsButton"))
        {
            // Show the options screen
            myCardLayout.show(this, "panelTwo");
        }

        // If the user clicks the blue ocean (image) button
        if (e.getActionCommand().equals("blueOceanButton"))
        {
            // Set all other image button's borders to null (since those themes aren't selected)
            whiteCloudsButton.setBorder(null);
            yellowSandButton.setBorder(null);

            // Set the blueOcean image button's border (since the theme is being used)
            blueOceanButton.setBorder(new LineBorder(Color.white, 4));

            // Set userTheme to blueOcean
            userTheme = "blueOcean";
        }

        // If the user clicks the yellow sand (image) button
        if (e.getActionCommand().equals("yellowSandButton"))
        {
            // Set all other image button's borders to null (since those themes aren't selected)
            blueOceanButton.setBorder(null);
            whiteCloudsButton.setBorder(null);

            // Set the yellowSand image button's border (since the theme is being used)
            yellowSandButton.setBorder(new LineBorder(Color.white, 4));

            // Set userTheme to yellowSand
            userTheme = "yellowSand";
        }

        // If the user clicks the white clouds (image) button
        if (e.getActionCommand().equals("whiteCloudsButton"))
        {
            // Set all other image button's borders to null (since those themes aren't selected)
            blueOceanButton.setBorder(null);
            yellowSandButton.setBorder(null);

            // Set the whiteClouds image button's border (since the theme is being used)
            whiteCloudsButton.setBorder(new LineBorder(Color.white, 4));

            // Set userTheme to whiteClouds
            userTheme = "whiteClouds";
        }

        // If the user clicks the 'Back' button
        if (e.getActionCommand().equals("backButton"))
        {
            // Show the main menu
            myCardLayout.show(this, "panelOne");
        }

        // If the user clicks the 'New Game' button, call gameScreen
        if (e.getActionCommand().equals("mainScreenButton"))
        {
            // This will open a a new window for the actual game
            gameScreen(userTheme);
        }
    }
}
/**
 * This is the 'musicPlayer' class for the jCheckers game.
 * This version of snake was made for the Graphical User Interface Assignment.
 * This class will play the audio required to play the game.
 * 
 * Made by Karm Desai (@karmdesai)
 * Version 1.0
 */

// Import the required libraries/dependencies
import java.io.File;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

public class musicPlayer 
{
    // playAudio method (this method plays audio given a file as a parameter)
    static void playAudio (File audioFile)
    {
        // Everything must be inside a 'try-catch' statement to prevent an Exception
        try
        {
            // Create the audio clip
            Clip myAudio = AudioSystem.getClip();

            // Open the audio clip
            myAudio.open(AudioSystem.getAudioInputStream(audioFile));

            // Start playing the audio clip
            myAudio.start();

            // Loop the audio clip
            myAudio.loop(Clip.LOOP_CONTINUOUSLY);
        }

        // Catch is required to prevent an Exception
        catch (Exception certainException)
        {
            // Helps trace the exception
            certainException.printStackTrace();
        }
    }
}
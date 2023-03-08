package com.pokemonsound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class ClipGenerator {
    public static Clip generateSoundClip(String soundFile, int volume)
    {
        Clip soundClip = null;
        AudioInputStream inputStream = null;
        try
        {
            URL url = Paths.get(soundFile).toUri().toURL();
            inputStream = AudioSystem.getAudioInputStream(url);
        }
        catch (UnsupportedAudioFileException | IOException e)
        {
            //log.warn("Unable to create audio input stream: ", e);
        }

        if (inputStream == null)
        {
            return null;
        }

        try
        {
            soundClip = AudioSystem.getClip();
            soundClip.open(inputStream);
          //  log.info("clip should be loaded");
        }
        catch (LineUnavailableException | IOException e)
        {
            //log.warn("Could not load sound file: ", e);
        }

        if (soundClip == null)
        {
            return null;
        }

        FloatControl control = (FloatControl) soundClip.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue((float) (volume / 2 - 45));

        return soundClip;
    }
}

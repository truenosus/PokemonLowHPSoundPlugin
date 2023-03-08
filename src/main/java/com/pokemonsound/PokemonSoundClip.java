package com.pokemonsound;

import javax.sound.sampled.Clip;

public class PokemonSoundClip{
    private String soundFile = "src/main/java/com/pokemonsound/lowHPSound.wav";
    private Clip pokemonSoundClip;
    //private boolean playSound;

    public void generateClip(){
        pokemonSoundClip = ClipGenerator.generateSoundClip(soundFile,100);
    }


    public void play() {
        pokemonSoundClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        pokemonSoundClip.stop();
    }

    }


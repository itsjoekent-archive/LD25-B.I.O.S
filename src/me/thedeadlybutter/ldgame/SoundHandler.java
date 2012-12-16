/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.thedeadlybutter.ldgame;

import java.io.File;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


/**
 *
 * @author Butters
 */
public class SoundHandler {
    
    public void playClick(){
        try{
            URL defaultSound = this.getClass().getResource("res/click.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(defaultSound);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void playCoin(){
        try{
            URL defaultSound = this.getClass().getResource("res/coin.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(defaultSound);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void playResearch(){
        try{
            URL defaultSound = this.getClass().getResource("res/research.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(defaultSound);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void playInfect(){
        try{
            URL defaultSound = this.getClass().getResource("res/infect.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(defaultSound);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }    
    
}

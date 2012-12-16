/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.thedeadlybutter.ldgame;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author Butters
 */
public class SystemVirusScan implements AntiSystem {
    
    private boolean found = false;
    private BufferedImage sprite = null;    
    
    @Override
    public String getName() {
        return "VirusScan";
    }

    @Override
    public boolean doAction() {
        Random random = new Random();
        int number = random.nextInt(2);
        if(number == 0){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean isFound() {
        return found;
    }

    @Override
    public void found() {
        found = true;
    }

    @Override
    public BufferedImage getSprite() {
        return sprite;
    }    
    
}

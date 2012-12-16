/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.thedeadlybutter.ldgame;

import java.awt.image.BufferedImage;

/**
 *
 * @author Butters
 */
public class SystemBackup implements AntiSystem {
    
    private boolean found = false;
    private BufferedImage sprite = null;    
    
    @Override
    public String getName() {
        return "Backup";
    }

    @Override
    public boolean doAction() {
        return true;
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

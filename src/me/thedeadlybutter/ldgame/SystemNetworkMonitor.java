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
public class SystemNetworkMonitor implements AntiSystem {

    private boolean found = false;
    private BufferedImage sprite = null;
    
    @Override
    public String getName() {
        return "NetworkMonitor";
    }

    @Override
    public boolean doAction() {
        return false; //Only packetflood should interact with this
    }

    @Override
    public void found() {
        found = true;
    }
    
    @Override
    public boolean isFound(){
        return found;
    }

    @Override
    public BufferedImage getSprite() {
        return sprite;
    }
    
}

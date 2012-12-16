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
public abstract class Virus {
    
    public final String name;
    public final int memory;
    private BufferedImage sprite;
    
    public Virus(String name, int memory){
        this.name = name;
        this.memory = memory;
    }
    
    public abstract void handleSystem(Main game, AntiSystem system, Building building, VirusGun gun);

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }
    
    
    
}

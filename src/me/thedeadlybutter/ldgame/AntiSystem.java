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
public interface AntiSystem {
    
    String getName();
    boolean doAction(); //If this system uses random 
    boolean isFound();
    void found(); //If a virus has learned this system is here
    BufferedImage getSprite();
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.thedeadlybutter.ldgame;

import java.awt.Graphics2D;

/**
 *
 * @author Butters
 */
public interface GUI {
    
    void init(Main game);
    void render(Main game, Graphics2D g);
    void handleClick(Main game, int x, int y);
    void update(Main game);
    
}

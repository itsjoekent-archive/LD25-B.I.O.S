/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.thedeadlybutter.ldgame;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Butters
 */
public class GUILoading implements GUI{

    @Override
    public void init(Main game) {
        
    }

    @Override
    public void render(Main game, Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, game.getWidth(), game.getHeight());
        g.setColor(Color.GREEN);   
        g.drawString("Loading...", 0, 0);
        game.loadCity(1);
        game.switchGUI(new GUIGame());
    }

    @Override
    public void handleClick(Main game, int x, int y) {
        
    }

    @Override
    public void update(Main game) {
        
    }
    
}

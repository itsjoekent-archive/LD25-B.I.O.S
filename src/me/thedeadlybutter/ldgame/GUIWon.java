/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.thedeadlybutter.ldgame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author Butters
 */
public class GUIWon implements GUI{
    
    @Override
    public void init(Main game) {
        
    }

    @Override
    public void render(Main game, Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, game.getWidth(), game.getHeight());
        g.setColor(Color.green);
        g.drawString("You destoyed the entire city Congrats, you won the game!!", 100, 100);
        g.drawString("Press the enter key to return to the main menu...", 100, 120);
    }

    @Override
    public void handleClick(Main game, int x, int y) {
        
    }

    @Override
    public void update(Main game) {
        if(game.getInput().isKeyDown(KeyEvent.VK_ENTER)){
            game.switchGUI(new GUIMainMenu());
            game.loadCity(1);
        }
    }    
    
}

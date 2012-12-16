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
public class GUIHelp implements GUI{


    @Override
    public void render(Main game, Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, game.getWidth(), game.getHeight());
        g.setColor(Color.green);
        g.drawString("Sorry, didn't have time to make this in-game, but there is a document"  , 100, 100);
        g.drawString("inside the zip file which has everything you need to know. Have fun!", 100, 120);
        g.drawString("Press enter to go back to the main menu.", 100, 140);
    }

    @Override
    public void handleClick(Main game, int x, int y) {
        
    }

    @Override
    public void update(Main game) {
        if(game.getInput().isKeyDown(KeyEvent.VK_ENTER)){
            game.switchGUI(new GUIMainMenu());
        }
    }

    @Override
    public void init(Main game) {
        
    }
    
}

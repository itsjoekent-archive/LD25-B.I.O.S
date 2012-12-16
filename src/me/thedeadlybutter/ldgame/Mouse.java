/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.thedeadlybutter.ldgame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Butters
 */
public class Mouse implements MouseListener {

    private Main game;
    
    public Mouse(Main game){
        this.game = game;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("X/Y: " + e.getX() + " " + e.getY());
        game.handleClick(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
    
}

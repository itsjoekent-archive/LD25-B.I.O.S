/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.thedeadlybutter.ldgame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Butters
 */
public class Input implements KeyListener{

    private Main game;
    
    /**
     * Array of ascii values
     */
    private int[] keys = new int[256];
    /**
     * Keys being released
     */
    private boolean[] key_state_up = new boolean[256];
    /**
     * keys being pressed
     */
    private boolean[] key_state_down = new boolean[256];
    /**
     * Boolean value representing if any key was pressed
     */
    private boolean keyPressed = false;
    /**
     * Boolean value representing if any key was released
     */
    private boolean keyReleased = false;
    
    public Input(Main game){
        this.game = game;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if( e.getKeyCode() >= 0 && e.getKeyCode() < 256 ) {
            key_state_down[e.getKeyCode()] = true;
            key_state_up[e.getKeyCode()] = false;
            keyPressed = true;
            keyReleased = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if( e.getKeyCode() >= 0 && e.getKeyCode() < 256 ) {
            keys[e.getKeyCode()] = 0;
            key_state_up[e.getKeyCode()] = true;
            key_state_down[e.getKeyCode()] = false;
            keyPressed = false;
            keyReleased = true;
        }
    }
    
    /**
     * Resets all up key states because we don't need key_states showing up forever
     */
    public void update() {
        //clear out the key up states
        key_state_up = new boolean[256];
        keyReleased = false;
    }
    
    /**
     * Check if a key is being pressed
     * @param ascii value of the keyboard key being checked
     * @return true is that key is currently being pressed.
     */
    public boolean isKeyDown( int key ) {
        return key_state_down[key];
    }

    /**
     * Check if a key is not being pressed
     * @param ascii value of the key being checked
     * @return true if it's not being pressed
     */
    public boolean isKeyUp( int key ) {
        return key_state_up[key];
    }

    /**
     * Check if any key is being pressed
     * @return true if one or more keys are being pressed.
     */
    public boolean isAnyKeyDown() {
        return keyPressed;
    }

    /**
     * Check if a key was released
     * @return true if one or more keys have been released 
     */
    public boolean isAnyKeyUp() {
        return keyReleased;
    }  
    
}

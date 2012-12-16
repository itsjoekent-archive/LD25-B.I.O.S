/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.thedeadlybutter.ldgame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Butters
 */
public class GUIMainMenu implements GUI{

    private BufferedImage mainMenu = null;
    private BufferedImage playButton = null; 
    private BufferedImage helpButton = null;
    private BufferedImage quitButton = null; 
    
    private int playX;
    private int playY;
    private int helpX;
    private int helpY;
    private int quitX;
    private int quitY;
    
    private Rectangle2D playBox;
    private Rectangle2D helpBox;
    private Rectangle2D quitBox;
    
    @Override
    public void init(Main game) {     
        
        try {
            mainMenu = ImageIO.read(this.getClass().getResource("res/MainMenu.png"));
            playButton = ImageIO.read(this.getClass().getResource("res/PlayButton.png"));
            helpButton = ImageIO.read(this.getClass().getResource("res/HelpButton.png"));
            quitButton = ImageIO.read(this.getClass().getResource("res/QuitButton.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }  

        int halfofScreen = game.getWidth() / 2;
        int yStart = game.getHeight() / 2;
        int ySeperator = game.getHeight() / 20;
        
        int halfOfButton = playButton.getWidth() / 2; //All menu buttons are same length/width
        playX = halfofScreen - halfOfButton;
        helpX = halfofScreen - halfOfButton;
        quitX = halfofScreen - halfOfButton;
        playY = yStart;
        helpY = yStart + playButton.getHeight() + ySeperator;
        quitY = helpY + helpButton.getHeight() + ySeperator;        
        
        playBox = new Rectangle2D.Double(playX, playY, playButton.getWidth(), playButton.getHeight());
        helpBox = new Rectangle2D.Double(helpX, helpY, helpButton.getWidth(), helpButton.getHeight());
        quitBox = new Rectangle2D.Double(quitX, quitY, quitButton.getWidth(), quitButton.getHeight());
        
    }

    @Override
    public void render(Main game, Graphics2D g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, game.getWidth(), game.getHeight());
        g.drawImage(mainMenu, 0, 0, null);

        g.drawImage(playButton, playX, playY, null);
        g.drawImage(helpButton, helpX, helpY, null);
        g.drawImage(quitButton, quitX, quitY, null);
    }

    @Override
    public void handleClick(Main game, int x, int y) {
        if(playBox.contains(x, y)){
            if(!game.getBuildings().isEmpty()){
                game.switchGUI(new GUIGame());
            }
            else{
                game.switchGUI(new GUILoading());
            }
            game.getSound().playClick();
        }
        else if(helpBox.contains(x, y)){
            game.switchGUI(new GUIHelp());
            game.getSound().playClick();
        }
        else if(quitBox.contains(x, y)){
            game.getSound().playClick();
            game.stop();
        }
    }

    @Override
    public void update(Main game) {
        
    }
    
    
    
}

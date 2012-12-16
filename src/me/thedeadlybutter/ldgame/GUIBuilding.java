/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.thedeadlybutter.ldgame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Butters
 */
public class GUIBuilding implements GUI {

    public final Building b;
    private int guiWidth;
    private int guiHeight;
    private int guiX;
    private int guiY;
    
    private BufferedImage researchButton = null; 
    private BufferedImage infectButton = null; 
    private BufferedImage background = null;
    
    private Rectangle2D resBox;
    private Rectangle2D infectBox;
    
    public GUIBuilding(Building b){
        this.b = b;
    }
    
    @Override
    public void init(Main game) {
        
        try{
            background = ImageIO.read(this.getClass().getResource("res/BuildingGUI.png"));
            researchButton = ImageIO.read(this.getClass().getResource("res/ResearchButton.png"));
            infectButton = ImageIO.read(this.getClass().getResource("res/InfectButton.png"));
        }catch(IOException e){
            e.printStackTrace();
        
        }
        
        resBox = new Rectangle2D.Double(200, 145, researchButton.getWidth(), researchButton.getHeight());
        infectBox = new Rectangle2D.Double(200, 245, infectButton.getWidth(), infectButton.getHeight());
    }

    @Override
    public void render(Main game, Graphics2D g) {
        g.drawImage(background, (320/2), 50, null);
        
        if(b.isDefeated()){
            g.setColor(Color.RED);
            g.drawString("Infected", 200, 145);  
        }
        else if(b.isResearched()){
            g.setColor(Color.GREEN);
            g.drawString("Researched", 200, 145);
        }
        else{
            g.drawImage(researchButton, 200, 145, null);
            g.drawImage(infectButton, 200, 245, null); 
            g.drawString("Virus Defense Systems Total: " + b.originalSystems.size() , 200, 300);
            g.drawString("Virus Defnse Systems Found: " + b.getFoundSystems().size(), 200, 315);
            int yStart = 100;
            int xStart = 300;
            int index = 1;
            int offset = 12;
            
            for(AntiSystem sys : b.originalSystems){
                if(b.getFoundSystems().contains(sys)){
                    if(b.getSystems().contains(sys)){
                        g.setColor(Color.ORANGE);
                        g.drawString(sys.getName(), xStart, yStart + (index*offset));
                    }
                    else{
                        g.setColor(Color.green);
                        g.drawString(sys.getName(), xStart, yStart + (index*offset));
                    }
                }
                else{
                    g.setColor(Color.red);
                    g.drawString("System not broken", xStart, yStart + (index*offset));
                }
                index++;
            }

        }
    } // 300 100

    @Override
    public void handleClick(Main game, int x, int y) {
        if(resBox.contains(x, y) && (!b.isResearched() || !b.isDefeated())){
            boolean r = game.getVirusGun().research(b);
            if(r){
                game.getSound().playResearch();
            }
        }
        else if(infectBox.contains(x, y) && (!b.isDefeated() || !b.isDefeated())){
            boolean i = game.getVirusGun().execute(game, b);
            if(i){
                game.getSound().playInfect();
            }
        }
    }

    @Override
    public void update(Main game) {
        if(game.getInput().isKeyDown(KeyEvent.VK_C)){
            game.switchGUI(new GUIGame());
        }
        
    }
    
    
    
}

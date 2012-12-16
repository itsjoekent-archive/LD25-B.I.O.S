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
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author Butters
 */
public class GUIVirusGun implements GUI{

    private BufferedImage addButton = null;
    private BufferedImage minusButton = null;
    private BufferedImage background = null;
    
    private Rectangle2D playBox;
    private Rectangle2D addButton1 = null; 
    private Rectangle2D addButton2 = null; 
    private Rectangle2D addButton3 = null; 
    private Rectangle2D addButton4 = null; 
    private Rectangle2D addButton5 = null; 
    private Rectangle2D addButton6 = null; 
    
    private Rectangle2D slot1 = null;     
    private Rectangle2D slot2 = null;     
    private Rectangle2D slot3 = null;     
    private Rectangle2D slot4 = null;     
    
    @Override
    public void init(Main game) {
        try {
            
            addButton = ImageIO.read(this.getClass().getResource("res/PlusButton.png"));
            minusButton = ImageIO.read(this.getClass().getResource("res/MinusButton.png"));
            background = ImageIO.read(this.getClass().getResource("res/BuildingGUI.png"));
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }  
        
    }

    @Override
    public void render(Main game, Graphics2D g) {
        g.drawImage(background, (320/2), 50, null);
        
        g.drawString("Total Ram / Used Ram = " + game.getVirusGun().MEMORY_SIZE + " / " + game.getVirusGun().getCurrent_memory(), 195, 64);
        g.drawString("Total Balance / Cost to infect = " + game.getVirusGun().getMoney() + " / " + game.getVirusGun().getEstimatedCost(), 195, 78);
        
        List<Virus> viruses = game.getVirusGun().getViruses();
        int xToDraw = 200;
        int yToDraw = 100;
        for(Virus v : viruses){
            g.drawImage(v.getSprite(), xToDraw, yToDraw, null);
            yToDraw += 64;
        }
        
        yToDraw = 100;
        if(viruses.size() >= 1){
            slot1 = new Rectangle2D.Double(xToDraw, yToDraw, 64, 64);
        }
        else{
            slot1 = null;
        }
        if(viruses.size() >= 2){
            slot2 = new Rectangle2D.Double(xToDraw, yToDraw + 64, 64, 64);
        }        
        else{
            slot2 = null;
        }
        if(viruses.size() >= 3){
            slot3 = new Rectangle2D.Double(xToDraw, yToDraw + 128, 64, 64);
        }        
        else{
            slot3 = null;
        }
        if(viruses.size() >= 4){
            slot1 = new Rectangle2D.Double(xToDraw, yToDraw + 192, 64, 64);
        }
        else{
            slot4 = null;
        }
        
        g.setColor(Color.black);
        g.drawLine(270, 80, 270, 400);
        
        //Draw shop
        xToDraw = 275;
        yToDraw = 100;
        List<Virus> available = game.getVirusGun().getAvailableTypes();
        for(int index = 0; index < 2; index++){
            g.drawImage(available.get(index).getSprite(), xToDraw, yToDraw, null);
            g.drawImage(addButton, xToDraw, yToDraw + 70, null);
            xToDraw += 70;
        }
        
        addButton1 = new Rectangle2D.Double(275, 170, 40, 40);
        addButton2 = new Rectangle2D.Double(345, 170, 40, 40);
        
        xToDraw = 275;
        yToDraw = 217;        
        if(available.size() >= 3){
            g.drawImage(available.get(2).getSprite(), xToDraw, yToDraw, null);
            g.drawImage(addButton, xToDraw, yToDraw + 70, null);
            xToDraw += 70;
            addButton3 = new Rectangle2D.Double(275, 290, 40, 40);
        }        
                
        if(available.size() >= 4){
            g.drawImage(available.get(3).getSprite(), xToDraw, yToDraw, null);
            g.drawImage(addButton, xToDraw, yToDraw + 70, null);
            xToDraw += 70;
            addButton4 = new Rectangle2D.Double(340, 280, 40, 40);
        }    
        
        xToDraw = 275;
        yToDraw = 331;
        if(available.size() >= 5){
            g.drawImage(available.get(4).getSprite(), xToDraw, yToDraw, null);
            g.drawImage(addButton, xToDraw, yToDraw + 70, null);
            xToDraw += 70;
            addButton5 = new Rectangle2D.Double(275, 400, 40, 40);
        }        
                
        if(available.size() >= 6){
            g.drawImage(available.get(5).getSprite(), xToDraw, yToDraw, null);
            g.drawImage(addButton, xToDraw, yToDraw + 70, null);
            xToDraw += 70;
            addButton6 = new Rectangle2D.Double(275+70, 400, 40, 40);
        }        
                  
        
    }

    @Override
    public void handleClick(Main game, int x, int y) {
        if(addButton1.contains(x, y)){
            game.getVirusGun().addVirus(game.getVirusGun().getAvailableTypes().get(0));
        }
        if(addButton2.contains(x, y)){
            game.getVirusGun().addVirus(game.getVirusGun().getAvailableTypes().get(1));
        }
        if(addButton3 != null){
            if(addButton3.contains(x, y)){
                game.getVirusGun().addVirus(game.getVirusGun().getAvailableTypes().get(2));
            }
        }        
        if(addButton4 != null){
            if(addButton4.contains(x, y)){
                boolean added = game.getVirusGun().addVirus(game.getVirusGun().getAvailableTypes().get(3));
                System.out.println(added);
            }
        } 
        if(addButton5 != null){
            if(addButton5.contains(x, y)){
                game.getVirusGun().addVirus(game.getVirusGun().getAvailableTypes().get(4));
            }
        }         
        if(addButton6 != null){
            System.out.println("1");
            if(addButton6.contains(x, y)){
                System.out.println("2");
                game.getVirusGun().addVirus(game.getVirusGun().getAvailableTypes().get(5));
            }
        }         
        
        if(slot1 != null){
            if(slot1.contains(x, y))
            game.getVirusGun().removeVirus(0);
        }
        if(slot2 != null){
            if(slot2.contains(x, y))
            game.getVirusGun().removeVirus(1);
        }        
        if(slot3 != null){
            if(slot3.contains(x, y))
            game.getVirusGun().removeVirus(2);
        }        
        if(slot4 != null){
            if(slot4.contains(x, y))
            game.getVirusGun().removeVirus(3);
        }        
        
        
    }

    @Override
    public void update(Main game) {
        if(game.getInput().isKeyDown(KeyEvent.VK_C)){
            game.switchGUI(new GUIGame());
        }
        
    }
    
}

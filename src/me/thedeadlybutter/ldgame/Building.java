/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.thedeadlybutter.ldgame;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author Butters
 */
public class Building {
    
    private List<AntiSystem> systems = new ArrayList<AntiSystem>();
    private List<AntiSystem> foundSystems = new ArrayList<AntiSystem>();
    public final List<AntiSystem> originalSystems;
    private boolean researched = false;
    private boolean defeated = false;
    private BufferedImage sprite;
    public final int security_level;
    public final int x;
    public final int y;
    public final Rectangle2D tile;
    public final String name;
    
    public Building(int securityLevel, int x, int y){
        this.security_level = securityLevel;
        this.x = x;
        this.y = y;
        this.tile = new Rectangle2D.Double(x, y, 32, 32);
        this.name = NameGenerator.getName();
        loadSprite();
        createSecurity();
        originalSystems = new ArrayList<AntiSystem>(systems);
    }
    
    public void render(Graphics2D g){
        g.drawImage(sprite, x, y, null);
    }
    
    private void loadSprite(){
        Random random = new Random();
        if(security_level == 1 || security_level == 0){
            int toLoad = random.nextInt(7);
            
            //Can't load a image with a 0 because I don't have one :P
            
            try {
                if(toLoad == 0){
                    sprite = ImageIO.read(this.getClass().getResource("res/Building1.png"));
                }
                else{
                    sprite = ImageIO.read(this.getClass().getResource("res/Building" + toLoad + ".png"));
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }            
        }
        else if(security_level == 2){
            int toLoad = random.nextInt(4);
            
            try{
             
                if(toLoad == 0){
                    sprite = ImageIO.read(this.getClass().getResource("res/Building7.png"));
                }                
                else if(toLoad == 1){
                    sprite = ImageIO.read(this.getClass().getResource("res/Building8.png"));
                }
                else if(toLoad == 2){
                    sprite = ImageIO.read(this.getClass().getResource("res/Building9.png"));
                }
                else if(toLoad == 3){
                    sprite = ImageIO.read(this.getClass().getResource("res/Building10.png"));
                }
                
            } catch(IOException e){
                e.printStackTrace();
            }
        }
        else{
            int toLoad = random.nextInt(2);
            try{
               
                if(toLoad == 0){
                    sprite = ImageIO.read(this.getClass().getResource("res/Building11.png"));
                }
                else{
                    sprite = ImageIO.read(this.getClass().getResource("res/Building12.png"));
                }
                
            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    
    private void createSecurity() {
        Random r = new Random();
        int totalSystemsToMake = security_level + r.nextInt(4);
        for(int index = 0; index < totalSystemsToMake; index++){
            addSystem(getRandomSystem());
        }
    }    
    
    public void destoryCurrentSystem(){
        if(!foundSystems.contains(systems.get(0))){
            foundSystems.add(systems.get(0));
            systems.get(0).found();
        }
        systems.remove(0);
    }
    
    public void destroyCurrentSystemAndReplace(){
        if(!foundSystems.contains(systems.get(0))){
            foundSystems.add(systems.get(0));
            systems.get(0).found();
        }
        systems.set(0, getRandomSystem());
    }
    
    public void destroyCurrentSystemAndReplace(AntiSystem system){
        if(!foundSystems.contains(systems.get(0))){
            foundSystems.add(systems.get(0));
            system.found();
        }
        systems.set(0, system);
    }    
    
    public void destorySystem(AntiSystem system){
        systems.remove(system);
        if(!foundSystems.contains(system)){
            foundSystems.add(system);
            system.found();
        }
    }
    
    public void foundSystemButDidNotDestory(AntiSystem system){
        if(!foundSystems.contains(system)){
           foundSystems.add(system); 
           system.found();
        }
        
    }
    
    public void addSystem(AntiSystem system){
        systems.add(system);
    }
    
    public AntiSystem getCurrentSystem(){
        if(systems.isEmpty()){
            return null;
        }
        else{
            return systems.get(0);
        }
    }
    
    public AntiSystem getSystemAt(int index){
        return systems.get(index);
    }
    
    public int getSystemsSize(){
        return systems.size();
    }

    public void defeat(){
        defeated = true;
    }

    public boolean isDefeated() {
        return defeated;
    }
    
    public void research(){
        researched = true;
    }
    
    public boolean isResearched() {
        return researched;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public List<AntiSystem> getFoundSystems() {
        return foundSystems;
    }
 
    public List<AntiSystem> getSystems() {
        return systems;
    }    
    
    public List<AntiSystem> getAvailableSystems(){
        List<AntiSystem> availableSystems = new ArrayList<AntiSystem>();
        availableSystems.add(new SystemFirewall());
        availableSystems.add(new SystemBackup());
        availableSystems.add(new SystemPacketRoute());
        availableSystems.add(new SystemVirusScan());
        
        if(security_level == 2){
            availableSystems.add(new SystemNetworkMonitor());
            availableSystems.add(new SystemSearchDestroy());
        }
        
        if(security_level == 3){
            availableSystems.add(new SystemEncryption());
            availableSystems.add(new SystemQuarantine());
        }
        
        return availableSystems;
    }
    
    private AntiSystem getRandomSystem(){
        List<AntiSystem> availableSystems = getAvailableSystems();
        Random r = new Random();
        int toReturn = r.nextInt(availableSystems.size());
        return availableSystems.get(toReturn);
    }
    
    
}

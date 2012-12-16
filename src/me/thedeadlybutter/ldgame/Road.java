/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.thedeadlybutter.ldgame;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Butters
 */
public class Road {
    
    public enum Road_Direction {
        VERTICAL, HORIZONTAL, INTERSECTION
    }
    
    public final Road_Direction direction;
    public final int x;
    public final int y;
    private BufferedImage sprite;
    
    public Road(int x, int y, Road_Direction direction){
        this.direction = direction;
        this.sprite = null;
        this.x = x;
        this.y = y;
        try{
            if(direction == Road_Direction.HORIZONTAL){
                sprite = ImageIO.read(this.getClass().getResource("res/RoadHorizantal.png"));
            }
            else if(direction == Road_Direction.VERTICAL){
                sprite = ImageIO.read(this.getClass().getResource("res/RoadVertical.png"));
            }
            else if(direction == Road_Direction.INTERSECTION){
                sprite = ImageIO.read(this.getClass().getResource("res/Intersection.png"));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void render(Graphics2D g){
        g.drawImage(sprite, x, y, null);
    }
    
    public BufferedImage getSprite() {
        return sprite;
    }
    
    
    
}

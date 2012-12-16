/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.thedeadlybutter.ldgame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author Butters
 */
public class Main extends JFrame implements Runnable {

    private final VirusGun virusGun = new VirusGun();
    
    private List<Building> buildings;
    private List<Road> roads;
    
    private GUI currentGUI;
    
    private boolean running;
    private int FPS = 0;
    private Thread thread;    
    
    private Input input;
    private Mouse mouse;
    
    private SoundHandler sound;
    
    public Main(){
        input = new Input(this);
        mouse = new Mouse(this);
        sound = new SoundHandler();
        
        buildings = new ArrayList<Building>();
        roads = new ArrayList<Road>();
        
        currentGUI = new GUIMainMenu();
        
        addKeyListener(input);
        addMouseListener(mouse);
        
        setSize(640, 480);
        setTitle("B.I.O.S");
        setUndecorated(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        requestFocus();
        createBufferStrategy(2);
        
        start();        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Main();
    }

    private void start() {
        currentGUI.init(this);
        thread = new Thread(this, "GameThread");
        running = true;
        thread.start();
    }
    
    private void update(){
        if(input.isKeyDown(KeyEvent.VK_ESCAPE)){
            switchGUI(new GUIMainMenu());
        }
        
        currentGUI.update(this);
    }
    
    public void stop(){
        System.exit(0);
    }
    
    public void switchGUI(GUI newGUI){
        currentGUI = newGUI;
        currentGUI.init(this);
    }
    
    public void handleClick(int x, int y){
        currentGUI.handleClick(this, x, y);
    }
    
    private void render(){
        Graphics2D g = null;
        BufferStrategy bs = getBufferStrategy();
        
        try{
            g = (Graphics2D) bs.getDrawGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            currentGUI.render(this, g);
        }
        finally{
            g.dispose();
        }
        
        bs.show();
        Toolkit.getDefaultToolkit().sync();
    }
    
    private void resetPolling(){
        input.update();
    }
    
    public void renderCity(Graphics2D g){
        g.setColor(new Color(184, 200, 175));
        g.fillRect(0, 0, getWidth(), getHeight());
        
        for(Building b : buildings){
            b.render(g);
        }
        
        for(Road r : roads){
            r.render(g);
        }
    }
    
    public void loadCity(int level){
        virusGun.reset();
        buildings.clear();
        roads.clear();
        
        BufferedReader br = null;
        try {
            InputStream is = this.getClass().getResourceAsStream("res/level1.txt");
            br = new BufferedReader(new InputStreamReader(is));
            
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            String everything = sb.toString();
            translateIntoCity(everything);
            br.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    //1 = level1 building --> 4= Vert road 5= Hor. road 6=Intersection  + new line
    public void translateIntoCity(String world){
        int xPos = 0;
        int yPos = 0;
        
        for(int index = 0; index < world.length(); index++){
            if(world.charAt(index) == '1'){
                buildings.add(new Building(1, xPos, yPos));
                xPos += 32;
            }
            else if(world.charAt(index) == '2'){
                buildings.add(new Building(2, xPos, yPos));
                xPos += 32;
            }
            else if(world.charAt(index) == '3'){
                buildings.add(new Building(3, xPos, yPos));
                xPos += 32;
            }
            else if(world.charAt(index) == '4'){
                roads.add(new Road(xPos, yPos, Road.Road_Direction.VERTICAL));
                xPos += 32;
            }
            else if(world.charAt(index) == '5'){
                roads.add(new Road(xPos, yPos, Road.Road_Direction.HORIZONTAL));
                xPos += 32;
            }
            else if(world.charAt(index) == '6'){
                roads.add(new Road(xPos, yPos, Road.Road_Direction.INTERSECTION));
                xPos += 32;
            }
            else if(world.charAt(index) == '+'){
                yPos += 32;
                xPos = 0;
            }     
        }
        
    }
    
    public void generateCity(){
        int width = getContentPane().getWidth();
        int height = getContentPane().getHeight();
        
        int currentX = 0;
        int currentY = 0;
        
        Random r = new Random();
        
        for(int yIndex = 0; yIndex < height/32; yIndex++){
            for(int xIndex = 0; xIndex < width/32; xIndex++){
                buildings.add(new Building(1, currentX, currentY));
                currentX += 32;
            }
            currentX = 0;
            currentY += 32;
            
            //Change security level

        } //End of generation loop        
        
    }
    
    @Override
    public void run() {
        //Current Delta value
        Double delta;
        //Last FPS - Used for calculations
        long lastFps = 0;

        //Target FPS
        final int TARGET = 60;
        //Optimal time per frame
        final long OPTIMAL_TIME = 1000000000 / TARGET;   

        long lastLoop = System.currentTimeMillis();
        
        while (running){
            
            //Figure out how long its been since last update - used for entity calculation
            long current = System.currentTimeMillis();
            long updateTime = current - lastLoop;
            lastLoop = current;
            
            delta = updateTime / ((double)OPTIMAL_TIME);
            
            lastFps += updateTime;
            FPS++;
            
            if(lastFps >= 1000000000){
                System.out.println("(FPS: "+ FPS +")");
                lastFps = 0;
                FPS = 0;
            }
            
            //Updates state + calls render
            update();
            render();
            resetPolling();
            
            /*
             * We need each frame to take 10mill-sec
             * Take recorded time + 10 milli
             * then factor current time
             * this ^ is final value to wait
             */
            try{
                Thread.sleep( (lastLoop - System.currentTimeMillis() + OPTIMAL_TIME) /1000000 );
            }
            catch(InterruptedException exception){
                exception.printStackTrace();
            }
        }
    }
    
    public Building getBuildingAt(int x, int y){
        for(Building b : buildings){
            if(b.tile.contains(new Point.Double(x, y))){
                return b;
            }
        }
        return null;
    }

    public int getFPS() {
        return FPS;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public GUI getCurrentGUI() {
        return currentGUI;
    }

    public Input getInput() {
        return input;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public List<Road> getRoads() {
        return roads;
    }

    public VirusGun getVirusGun() {
        return virusGun;
    }

    public SoundHandler getSound() {
        return sound;
    }
    
    
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.thedeadlybutter.ldgame;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Butters
 */
public class VirusGun {
    
    public final int MEMORY_SIZE = 1000;
    private int current_memory = 0;
    private int researchLevel = 0; //2 Allows False bot, 5 allows a packet flood //10 allows exe //15 allows bat
    private int money = 500;
    
    private List<Virus> viruses = new ArrayList<Virus>();
    
    public boolean addVirus(Virus virus){
        System.out.println(virus.name);
        if(current_memory + virus.memory > MEMORY_SIZE || viruses.size() >= 4){
            return false;
        }
        else{
            current_memory += virus.memory;
            viruses.add(virus);
            return true;
        }
    }
    
    public void removeVirus(Virus virus){
        viruses.remove(virus);
        current_memory -= virus.memory;
    }
    
    public void removeVirus(int index){
        current_memory -= viruses.get(index).memory;
        viruses.remove(index);
    }
    
    public void reset(){
        current_memory = 0;
        viruses.clear();
        researchLevel = 0;
        money = 500;
    }
    
    //true if it defeats the building
    public boolean execute(Main game, Building building){
        if(building.isDefeated() || building.isResearched()){
            return false;
        }
        for(Virus virus : viruses){
            if(building.getAvailableSystems().isEmpty()){
                building.defeat();
                giveMoney(building);
                return true;
            }
            else {
                if(building.getCurrentSystem() == null){
                    building.defeat();
                    giveMoney(building);
                    return true;
                }
                virus.handleSystem(game, building.getCurrentSystem(), building, this);
                if(building.getCurrentSystem() == null){
                    building.defeat();
                    giveMoney(building);
                    return true;
                }
            }
            
            spendMoney(virus.memory / 2);
        } 
        return false;
    }

    public int getEstimatedCost(){
        int total = 0;
        for(Virus v : viruses){
            total += v.memory / 2;
        }
        return total;
    }
    
    public boolean research(Building b){
        if(b.isDefeated() || b.isResearched() || researchLevel == 15){
            return false;
        }
        else{
            if(b.security_level == 1){
                b.research();
                researchLevel++;
                spendMoney(b.security_level * 200);
                return true;
            }            
            if(b.security_level == 2 && researchLevel >= 5){
                b.research();
                researchLevel += 2;
                spendMoney(b.security_level * 200);
                return true;
            }
            if(b.security_level == 3 && researchLevel >= 10){
                b.research();
                researchLevel += 3;
                spendMoney(b.security_level * 200);
                return true;
            }

        }
        return false;
    }
    
    public void giveMoney(Building building){
        if(building.security_level == 1){
            money += 200 * viruses.size() / 2;
        }
        else if(building.security_level == 2){
            money += 400 * viruses.size() / 2;
        }
        else if(building.security_level == 3){
            money += 600 * viruses.size() / 2;
        }
    }
    
    public void setMoney(int amount){
        money = amount;
    }
    
    public void spendMoney(int amount){
        money -= amount;
    }
    
    public int getCurrent_memory() {
        return current_memory;
    }
    
    public int getResearchLevel(){
        return researchLevel;
    }
    
    public List<Virus> getAvailableTypes(){
        List<Virus> virusTypes = new ArrayList<Virus>();
        
        virusTypes.add(new VirusTypeBot());
        virusTypes.add(new VirusTypeBotWave());
        
        if(researchLevel >= 2){
            virusTypes.add(new VirusTypeFalseBot());
        }
        
        if(researchLevel >= 5){
            virusTypes.add(new VirusTypeFlood());
        }
        
        if(researchLevel >= 10){
            virusTypes.add(new VirusTypeExe());
        }
        
        if(researchLevel >= 15){
            virusTypes.add(new VirusTypeBat());
        }
        
        return virusTypes;
    }

    public List<Virus> getViruses() {
        return viruses;
    }

    public int getMoney() {
        return money;
    }

    
    
    
}

package com.company;

import java.util.HashMap;

public class Player extends BaseMech {

    //------------------- ATTRIBUTES --------------------
    private String name;
    private final String race;
    private int maxHp;
    private int hp;
    private int maxAc;
    private int ac;
    private int level;
    private int exp;
    //private int gold;

    //store character stat values
    private HashMap<String, Integer> stats = new HashMap<>();
    public Inventory inventory;
    //public Weapon weapon = new Weapon();

    //exp need to level to next level
    int[] lvUp = {0, 300, 900, 2700, 6500, 14000};


    //------------------- CONSTRUCTOR --------------------
    //TODO let players change name?
    public Player(String newName, String newRace, int newLevel){
        name = newName;
        race = newRace;
        level = newLevel;
        exp = 0;

        createStats();
        setMaxHp(level);
        maxAc = 10 + getStatMod("dex");
        ac = maxAc;
        inventory = new Inventory();
    }

    //randomly roll stats based on 3d6
    private void createStats(){
        stats.put("str", rollDice(3, 6));
        stats.put("con", rollDice(3, 6));
        stats.put("dex", rollDice(3, 6));
        stats.put("int", rollDice(3, 6));
        stats.put("wis", rollDice(3, 6));
        stats.put("cha", rollDice(3, 6));
    }

    //------------------- ACCESSORS --------------------
    public int getLevel(){ return level; }

    //get current ac
    public int getAc() { return ac; }

    //get current hp
    public int getHp() { return hp; }

    public int getMaxHp(){ return maxHp; }

    public int getExp(){ return exp; }

//    public int getGold(){ return gold; }

    //get stat modifier based on stat (only 1-20)
    public int getStatMod(String stat){
        int score = stats.get(stat);

        switch (score){
            case 1: return -5;
            case 2:
            case 3:
                return -4;
            case 4:
            case 5:
                return -3;
            case 6:
            case 7:
                return -2;
            case 8:
            case 9:
                return -1;
            case 10:
            case 11:
                return 0;
            case 12:
            case 13:
                return 1;
            case 14:
            case 15:
                return 2;
            case 16:
            case 17:
                return 3;
            case 18:
            case 19:
                return 4;
            case 20: return 5;
            //the chart goes to 30, I think this is enough

            default: return 0;
        }
    }

    //------------------- MODIFIERS --------------------
    //create hp: current only calculate wizards
    //TODO add hit dice variable to make more generic?
    public void setMaxHp(int level){
        if(level == 1){
            maxHp = 6 + getStatMod("con");
        } else {
            maxHp += rollDice(1, 6) + getStatMod("con");
        }
        hp = maxHp;
    }

//    public void setGold(boolean buy, int cost){
//        if(buy){
//            gold -= cost;
//        } else {
//            gold += cost;
//        }
//    }

    public void setExp(int newExp){ exp += newExp; }

    public void setAC(boolean reset){
        if(reset){
            ac = maxAc;
        } else {
            ac += 2;
        }
    }

    //------------------- WEAPON METHODS --------------------
    //subtract gold when upgrading weapon, return true if complete
//    public boolean upgradeWeapon() {
//        int cost = weapon.upgrade(gold);
//        if(cost > 0){
//            setGold(true, cost);
//            return true;
//        }
//        return false;
//    }
//
//    public boolean buyWeapon(String newWeapon) {
//        if(weapon.buyWpn(gold, newWeapon)) {
//            setGold(true, weapon.getValue());
//            return true;
//        }
//        return false;
//    }
//
//    public void sellWeapon() { setGold(false, weapon.sellWeapon()); }

    //------------------- GENERAL METHODS --------------------
    //TODO print out level up message if return true.
    //gain gp when level up?
    public boolean levelUp(){
        if(exp >= lvUp[level]){
            exp = 0;
            level++;
            setMaxHp(level);
            return true;
        }
        return false;
    }

    public void takeDmg(int dmg){
        hp -= dmg;
    }

    public boolean heal(){
        if(hp == maxHp || inventory.getHealthPotions() == 0){
            return false;   //cannot heal anymore
        } else {
            hp += inventory.getHealPoints();
            if(hp > maxHp){
                hp = maxHp;
            }
            inventory.setHealthPotions(false);
            return true;
        }
    }

    //return true if dead
    public boolean isDead(){
        return hp <= 0;
    }

    //print player info
    public void printPlayerInfo(){
        System.out.println("---------------------------------");
        System.out.println("Name: " + name + " (" + race + ", lv. " + level + ")");
        System.out.println("HP: " + hp);
        System.out.println("AC: " + ac);
        System.out.println();

        for (String s : stats.keySet()) {
            System.out.println( s.toUpperCase() + ": " + stats.get(s) + "\t (" + getStatMod(s) + ")");
        }
        System.out.println("---------------------------------");
    }

}
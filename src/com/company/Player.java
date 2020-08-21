package com.company;

import java.util.HashMap;

public class Player extends BaseMech {

    //------------------- ATTRIBUTES --------------------
    private String name;
    private final String race;
    private int hp;
    private int ac;
    private int level;
    private int exp;
    private int gold;

    public Weapon weapon = new Weapon();

    //store character stat values
    private HashMap<String, Integer> stats = new HashMap<>();

    //------------------- CONSTRUCTOR --------------------
    //TODO let players change name?
    public Player(String newName, String newRace, int newLevel){
        name = newName;
        race = newRace;
        level = newLevel;
        gold = 15;  //TODO default value should be 2gp for game
        exp = 0;

        createStats();
        setHp(level);
        ac = 10 + getStatMod("dex");
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

    public int getAc() { return ac; }

    public int getHp() { return hp; }

    public int getGold(){ return gold; }

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
    public void setHp(int level){
        if(level == 1){
            hp = 6 + getStatMod("con");

        } else {
            hp += rollDice(1, 6) + getStatMod("con");
        }
    }

    public void setGold(boolean buy, int cost){
        if(buy){
            gold -= cost;
        } else {
            gold += cost;
        }
    }

    //------------------- WEAPON METHODS --------------------
    //subtract gold when upgrading weapon
    public void upgradeWeapon() { setGold(true, weapon.upgrade(gold)); }

    public void buyWeapon(String newWeapon) {
        setGold(true, weapon.buyWeapon(gold, newWeapon));
    }

    public void sellWeapon() { setGold(false, weapon.sellWeapon()); }


    //------------------- GENERAL METHODS --------------------
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
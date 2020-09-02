package com.company;

import java.util.ArrayList;

public class Spell extends BaseMech {
    private String name;
    private int level;
    private int dmgDice;
    private int dmgTimes;
    private int bonus;
    private boolean heal;

    //Could put spellInfo into Hashmap<Strings, int[]> but need to create
    private String[] spells = {"falseLife", "fireBolt", "magicMissile", "scorchingRay"};

    //array shows {level, dmgTimes, dmgDice, bonus, heal}
    private int[][] spellInfo = {
            {1, 1, 4, 4, 1, 2},    //falseLife
            {1, 1, 10, 0, 0, 2},   //fireBolt
            {1, 2, 4, 1, 0, 2},    //magicMissile
            {2, 2, 6, 0, 0, 1}     //scorchingRay
    };

    //--------------------- CONSTRUCTOR ------------------------
    public Spell(String newName){
        int spellIndex = findIndex(spells, newName);

        name = newName;
        level = spellInfo[spellIndex][0];
        dmgTimes = spellInfo[spellIndex][1];
        dmgDice = spellInfo[spellIndex][2];
        bonus = spellInfo[spellIndex][3];
        heal = spellInfo[spellIndex][4] == 1;
    }

    //--------------------- ACCESSOR ------------------------
    public String getName(){ return name; }

    public int getLevel() { return level; }

    public boolean getHeal(){ return heal; }

    public int getDamageDone(){
        return rollDice(dmgTimes, dmgDice) + bonus;
    }

    //show spells available to buy in Guild
    public ArrayList<String> getAvailableSpells(int level){
        ArrayList<String> availableSpells = new ArrayList<>();
        for(int i = 0; i < spells.length && spellInfo[i][0] <= level; i++){
            availableSpells.add(spells[i]);
        }
        return availableSpells;
    }

    public int[] getSpellInfo(String name){
        int spellIndex = findIndex(spells, name);
        return spellInfo[spellIndex];
    }

    //--------------------- MODIFIERS ------------------------

    public void setDmgTimes(){ dmgTimes++; }

    public void upgradeSpell(){
        dmgTimes++;
    }

}

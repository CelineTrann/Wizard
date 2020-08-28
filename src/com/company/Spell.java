package com.company;

public class Spell extends BaseMech {
    private String name;
    private int level;
    private int dmgDice;
    private int dmgTimes;
    private int bonus;
    private boolean heal;

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
    public int getLevel() { return level; }

    public boolean getHeal(){ return heal; }

    public int getDamageDone(){
        return rollDice(dmgTimes, dmgDice) + bonus;
    }

    //--------------------- MODIFIERS ------------------------

    public void setDmgTimes(){ dmgTimes++; }

    public void upgradeSpell(){
        dmgTimes++;
    }

}

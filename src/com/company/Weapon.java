package com.company;

import java.util.HashMap;

public class Weapon extends BaseMech {
    private String currentWeapon;
    private int dmgDice;
    private int dmgTimes;
    private int upCost;
    private int value;
    private boolean canShield;

    String[] weaponNames = {"none", "dagger", "shortSword", "crossBow", "hammer", "axe"};

    //shows {value, dmgTimes, dmgDice, canShield}
    int[][] weaponInfo = {
            {0, 0, 0, 1},   //none
            {2, 1, 4, 1},   //dagger
            {5, 1, 6, 1},   //shortSword
            {25, 2, 8, 0},  //crossBow
            {10, 1, 8, 0},  //hammer
            {5, 1, 6, 0}    //axe
    };

    //--------------------- CONSTRUCTOR ------------------------
    public Weapon(){
        currentWeapon = "none";
        dmgDice = 0;
        dmgTimes = 1;
        canShield = true;
        value = 0;
        upCost = calcUpCost();
    }

    //--------------------- ACCESSORS ------------------------
    public String getCurrentWeapon(){
        return currentWeapon;
    }

    public int getDmgDice() { return dmgDice; }

    public int getDmgTimes(){ return dmgTimes; }

    public int getValue(){ return value; }

    public int getUpCost(){ return upCost; }

    public boolean getCanShield(){ return canShield; }


    //--------------------- BUY ------------------------
    public boolean buyWpn(int gold, String newWeapon){
        int weaponIndex = findIndex(weaponNames, newWeapon);

        if(!currentWeapon.equals("none")){
            return false;
        } else if(gold < arrPrice(newWeapon)){
            return false;
        } else {
            currentWeapon = weaponNames[weaponIndex];
            value = weaponInfo[weaponIndex][0];
            dmgTimes = weaponInfo[weaponIndex][1];
            dmgDice = weaponInfo[weaponIndex][2];
            canShield = weaponInfo[weaponIndex][3] == 0;
            upCost = calcUpCost();
            return true;
        }
    }

    public int arrPrice(String weapon){
        int index = findIndex(weaponNames, weapon);
        return weaponInfo[index][0];
    }

    public int calcUpCost(){
        return value * dmgTimes;
    }


    //--------------------- SELL ------------------------
    public int sellWeapon(){
        currentWeapon = "none";
        dmgDice = 0;
        dmgTimes = 0;
        canShield = true;

        return value;
    }

    public boolean showSellWeapon() {
        return !currentWeapon.equals("none");
    }

    //--------------------- UPGRADE ------------------------
    public int upgrade(int gold) {
        if(currentWeapon.equals("none")){
            return 0;

        } else if (gold < upCost){
            return 0;

        } else {
            value += upCost;
            int tempCost = upCost;
            dmgTimes++;
            upCost = calcUpCost();
            return tempCost;
        }
    }

    //--------------------- UPGRADE ------------------------
    public boolean attackRoll(Enemy enemy){
        return enemy.getAc() < rollDice(1, 20);
    }

    public int dmgRoll(){
        return rollDice(dmgTimes, dmgDice);
    }
}

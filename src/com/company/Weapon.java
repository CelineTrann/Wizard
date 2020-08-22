package com.company;

import java.util.HashMap;

public class Weapon extends BaseMech {
    private String currentWeapon;
    private HashMap<String, Integer> price = new HashMap<>();
    private int dmgDice;
    private int dmgTimes;
    private int upCost;
    private int value;
    private boolean canShield;

    //--------------------- CONSTRUCTOR ------------------------
    public Weapon(){
        currentWeapon = "none";
        dmgDice = 0;
        dmgTimes = 1;
        canShield = true;
        value = 0;

        //put all cost in
        price.put("dagger", 2);
        price.put("shortSword", 5);
        price.put("crossBow", 25);
        price.put("hammer", 10);
        price.put("axe", 5);
        price.put("none", 0);

        upCost = price.get(currentWeapon) * dmgTimes;
    }

    //--------------------- ACCESSORS ------------------------
    public String getCurrentWeapon(){
        return currentWeapon;
    }

    public int getDmgDice() { return dmgDice; }

    public int getDmgTimes(){ return dmgTimes; }

    public int getValue(){ return value; }

    public int getUpCost(){ return upCost; }


    //--------------------- BUY ------------------------
    //return true if transaction is complete
    public boolean buyWeapon(int gold, String newWeapon) {
        switch (newWeapon) {
            case "dagger":
                return buyDagger(gold);
            case "shortSword":
                return buyShortSword(gold);
            case "crossBow":
                return buyCrossBow(gold);
            case "hammer":
                return buyHammer(gold);
            case "axe":
                return buyAxe(gold);
            default:
                return false;
        }
    }

    //always buy at base value then have to upgrade
    private boolean buyDagger(int gold){
        if(!currentWeapon.equals("none")){
            System.out.println("You can only carry one weapon at a time. Please sell your weapon first.");
            return false;

        } else if(gold < price.get("dagger")) {
            System.out.println("Not enough gold.");
            return false;

        } else {
            currentWeapon = "dagger";
            dmgDice = 4;
            dmgTimes = 1;
            canShield = true;
            value = price.get("dagger");
            upCost = price.get("dagger") * dmgTimes;
            System.out.println("Transaction complete.");
            return true;
        }
    }

    private boolean buyShortSword(int gold){
        if(!currentWeapon.equals("none")){
            System.out.println("You can only carry one weapon at a time. Please sell your weapon first.");

        } else if(gold < price.get("shortSword")) {
            System.out.println("Not enough gold.");

        } else {
            currentWeapon = "shortSword";
            dmgDice = 6;
            dmgTimes = 1;
            canShield = true;
            value = price.get("shortSword");
            upCost = price.get("shortSword") * dmgTimes;
            System.out.println("Transaction complete.");
            return true;
        }
        return false;
    }

    private boolean buyCrossBow(int gold){
        if(!currentWeapon.equals("none")){
            System.out.println("You can only carry one weapon at a time. Please sell your weapon first.");

        } else if(gold < price.get("crossBow")) {
            System.out.println("Not enough gold.");

        } else {
            currentWeapon = "crossBow";
            dmgDice = 8;
            dmgTimes = 2;
            canShield = false;
            value = price.get("crossBow");
            upCost = price.get("crossBow") * dmgTimes;
            System.out.println("Transaction complete.");
            return true;
        }
        return false;
    }

    private boolean buyHammer(int gold){
        if(!currentWeapon.equals("none")){
            System.out.println("You can only carry one weapon at a time. Please sell your weapon first.");

        } else if(gold < price.get("hammer")) {
            System.out.println("Not enough gold.");

        } else {
            currentWeapon = "hammer";
            dmgDice = 8;
            dmgTimes = 1;
            canShield = false;
            value = price.get("hammer");
            upCost = price.get("hammer") * dmgTimes;
            System.out.println("Transaction complete.");
            return true;
        }
        return false;
    }

    private boolean buyAxe(int gold){
        if(!currentWeapon.equals("none")){
            System.out.println("You can only carry one weapon at a time. Please sell your weapon first.");

        } else if(gold < price.get("axe")) {
            System.out.println("Not enough gold.");

        } else {
            currentWeapon = "axe";
            dmgDice = 6;
            dmgTimes = 2;
            canShield = false;
            value = price.get("axe");
            upCost = price.get("axe") * dmgTimes;
            System.out.println("Transaction complete.");
            return true;
        }
        return false;
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
            upCost = price.get(currentWeapon) * dmgTimes;
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

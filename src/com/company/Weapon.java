package com.company;

import java.util.HashMap;

public class Weapon extends BaseMech {
    String currentWeapon;
    HashMap<String, Integer> price = new HashMap<>();
    int dmgDice;
    int dmgTimes;
    int upCost;
    int value;
    boolean canShield;

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
    public int getDamage(){
        return rollDice(dmgTimes, dmgDice);
    }

    public String getCurrentWeapon(){
        return currentWeapon;
    }

    //--------------------- BUY ------------------------
    public void showWeaponInfo(){
        System.out.print("1. dagger \n" +
                "\t * Cost: 2gp \n" +
                "\t * dmg Dice: 1d4 \n" +
                "\t * Use Shield: yes \n");

        System.out.print("2. shortSword \n" +
                "\t * Cost: 5gp \n" +
                "\t * dmg Dice: 1d6 \n" +
                "\t * Use Shield: yes \n");

        System.out.print("3. crossBow \n" +
                "\t * Cost: 25gp \n" +
                "\t * dmg Dice: 2d8 \n" +
                "\t * Use Shield: no \n");

        System.out.print("4. hammer \n" +
                "\t * Cost: 10gp \n" +
                "\t * dmg Dice: 1d8 \n" +
                "\t * Use Shield: no \n");

        System.out.print("5. axe \n" +
                "\t * Cost: 5gp \n" +
                "\t * dmg Dice: 1d6 \n" +
                "\t * Use Shield: no \n");
        System.out.println("exit");
    }

    public int buyWeapon(int gold, String newWeapon) {
        boolean completeTrans = false;

        switch (newWeapon) {
            case "dagger":
                completeTrans = buyDagger(gold);
                break;
            case "shortSword":
                completeTrans = buyShortSword(gold);
                break;
            case "crossBow":
                completeTrans = buyCrossBow(gold);
                break;
            case "hammer":
                completeTrans = buyHammer(gold);
                break;
            case "axe":
                completeTrans = buyAxe(gold);
                break;
            case "exit":
                break;
            default:
                System.out.println("That weapon is unavailable.");
        }

        if(completeTrans){
            return price.get(newWeapon);
        }
        return 0;
    }

    //always buy at base value then have to upgrade
    private boolean buyDagger(int gold){
        if(!currentWeapon.equals("none")){
            System.out.println("You can only carry one weapon at a time. Please sell your weapon first.");

        } else if(gold < price.get("dagger")) {
            System.out.println("Not enough gold.");

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
        return false;
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
        if(currentWeapon.equals("none")){
            return false;
        } else {
            System.out.println("Weapon: " + currentWeapon);
            System.out.println("Price: " + value + "gp");
            return true;
        }
    }

    //--------------------- UPGRADE ------------------------
    public int upgrade(int gold) {
        if(currentWeapon.equals("none")){
            System.out.println("You have no weapon.");
            return 0;

        } else if (gold < upCost){
            System.out.println("Not enough gold to upgrade");
            return 0;

        } else {
            dmgTimes++;
            upCost = price.get(currentWeapon) * dmgTimes;
            value += upCost;
            return upCost;
        }
    }

    public void showUpgradeWeapon() {
        if(currentWeapon.equals("none")){
            System.out.println("You have no weapon.");
        } else {
            System.out.println("Weapon: " + currentWeapon);
            System.out.println("\t Upgrade cost: " + upCost + "gp");
            System.out.println("\t New Dmg Dice: " + (dmgTimes + 1) + "d" + dmgDice);
        }
    }

}

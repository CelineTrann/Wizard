package com.company;

import java.util.HashMap;

public class Weapon extends BaseMech {
    String currentWeapon;
    HashMap<String, Integer> price = new HashMap<>();
    int dmgDice;
    int dmgTimes;
    int upCost;
    boolean canShield;

    //--------------------- CONSTRUCTOR ------------------------
    public Weapon(){
        currentWeapon = "none";
        dmgDice = 0;
        dmgTimes = 1;
        canShield = true;

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
    }

    public int buyWeapon(int gold, String newWeapon) {
        switch (newWeapon) {
            case "dagger":
                buyDagger(gold);
                break;
            case "shortSword":
                buyShortSword(gold);
                break;
            case "crossBow":
                buyCrossBow(gold);
                break;
            case "hammer":
                buyHammer(gold);
                break;
            case "axe":
                buyAxe(gold);
                break;
            default: System.out.println("That weapon is unavailable.");
        }

        return price.get(newWeapon);
    }

    //always buy at base value then have to upgrade
    private void buyDagger(int gold){
        if(!currentWeapon.equals("none")){
            System.out.println("You can only carry one weapon at a time. Please sell your weapon first.");
        } else if(gold < price.get("dagger")) {
            System.out.println("Not enough gold.");
        } else {
            currentWeapon = "dagger";
            dmgDice = 4;
            dmgTimes = 1;
            canShield = true;
        }
    }

    private void buyShortSword(int gold){
        if(!currentWeapon.equals("none")){
            System.out.println("You can only carry one weapon at a time. Please sell your weapon first.");
        } else if(gold < price.get("shortSword")) {
            System.out.println("Not enough gold.");
        } else {
            currentWeapon = "shortSword";
            dmgDice = 6;
            dmgTimes = 1;
            canShield = true;
        }
    }

    private void buyCrossBow(int gold){
        if(!currentWeapon.equals("none")){
            System.out.println("You can only carry one weapon at a time. Please sell your weapon first.");
        } else if(gold < price.get("crossBow")) {
            System.out.println("Not enough gold.");
        } else {
            currentWeapon = "crossBow";
            dmgDice = 8;
            dmgTimes = 2;
            canShield = false;
        }
    }

    private void buyHammer(int gold){
        if(!currentWeapon.equals("none")){
            System.out.println("You can only carry one weapon at a time. Please sell your weapon first.");
        } else if(gold < price.get("hammer")) {
            System.out.println("Not enough gold.");
        } else {
            currentWeapon = "hammer";
            dmgDice = 8;
            dmgTimes = 1;
            canShield = false;
        }
    }

    private void buyAxe(int gold){
        if(!currentWeapon.equals("none")){
            System.out.println("You can only carry one weapon at a time. Please sell your weapon first.");
        } else if(gold < price.get("axe")) {
            System.out.println("Not enough gold.");
        } else {
            currentWeapon = "axe";
            dmgDice = 6;
            dmgTimes = 2;
            canShield = false;
        }
    }

    //--------------------- SELL ------------------------
    public int sellWeapon(){
        String soldWeapon = currentWeapon;
        currentWeapon = "none";
        dmgDice = 0;
        dmgTimes = 0;
        canShield = true;

        System.out.println(currentWeapon + " was sold for " + price.get(soldWeapon));
        return price.get(soldWeapon);
    }

    //--------------------- UPGRADE ------------------------
    public int upgrade(int gold) {
        if(currentWeapon.equals("none")){
            System.out.println("You have no weapon.");
            return 0;

        } else if (gold < price.get(currentWeapon)){
            System.out.println("Not enough gold to upgrade");
            return 0;

        } else {
            dmgTimes++;
            upCost = price.get(currentWeapon) * dmgTimes;
            return upCost;
        }
    }

    public void showUpgradeWeapon() {
        System.out.println("Weapon: " + currentWeapon);
        System.out.println("\t Upgrade cost: " + upCost + "gp");
        System.out.println("\t New Dmg Dice: " + (dmgTimes + 1) + "d" + dmgDice);
    }

}

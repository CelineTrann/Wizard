package com.company;

public class Inventory {
    Weapon weapon;
    private int gold;
    private int healthPotions;
    private int healPoints = 5;

    //------------------- CONSTRUCTORS --------------------
    public Inventory(){
        weapon = new Weapon();
        gold = 15;
        healthPotions = 1;
    }

    //------------------- ACCESSORS --------------------
    public int getGold(){
        return gold;
    }

    public int getHealthPotions(){
        return healthPotions;
    }

    public int getHealPoints(){
        return healPoints;
    }

    //------------------- MODIFIERS --------------------
    public void setGold(boolean buy, int cost){
        if(buy){
            gold -= cost;
        } else {
            gold += cost;
        }
    }

    public void setHealthPotions(boolean buy){
        if(buy) {
            healthPotions++;
        } else {
            healthPotions--;
        }
    }

    //------------------- WEAPONS METHOD --------------------
    public boolean buyWeapon(String newWeapon) {
        if(weapon.buyWpn(gold, newWeapon)) {
            setGold(true, weapon.getValue());
            return true;
        }
        return false;
    }

    public void sellWeapon() {
        setGold(false, weapon.sellWeapon());
    }

    public boolean upgradeWeapon() {
        int cost = weapon.upgrade(gold);
        if(cost > 0){
            setGold(true, cost);
            return true;
        }
        return false;
    }

}

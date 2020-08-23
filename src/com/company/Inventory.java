package com.company;

public class Inventory {
    Weapon weapon;
    int gold;
    int healthPotions;

    //------------------- CONSTRUCTORS --------------------
    public Inventory(){
        weapon = new Weapon();
        gold = 15;
        healthPotions = 0;
    }

    //------------------- ACCESSORS --------------------
    public int getGold(){
        return gold;
    }

    public int getHealthPotions(){
        return healthPotions;
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

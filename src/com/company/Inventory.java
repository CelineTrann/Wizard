package com.company;

public class Inventory {
    Weapon weapon;
    int gold;
    int healthPotions;

    public Inventory(){
        gold = 15;
        healthPotions = 0;
    }

    //------------------- MODIFIERS --------------------
    public void setGold(boolean buy, int cost){
        if(buy){
            gold -= cost;
        } else {
            gold += cost;
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

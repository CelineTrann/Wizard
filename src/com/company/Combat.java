package com.company;

import java.util.Random;

public class Combat extends BaseMech {
    //------------------- ATTRIBUTES --------------------
    String[] creatures = {"none", "giantWeasel", "giantFrog", "hobgoblin", "brownBear"};
    Enemy enemy;

    //------------------- CONSTRUCTORS --------------------
    public Combat(Player player1){
        int enemyIndex = getEnemyIndex(player1.getLevel());
        enemy = new Enemy(creatures[enemyIndex]);
    }

    //return which enemy can be access in enemy array
    private int getEnemyIndex(int level){
        Random randomEnemy = new Random();
        return randomEnemy.nextInt(level * 2) + 1;
    }

    //------------------- PLAYER ACTION --------------------
    //return true if enemy is hit
    public int attack(Player player1){
        if(player1.inventory.weapon.attackRoll(enemy)) {
            int dmg = player1.inventory.weapon.dmgRoll();
            enemy.setHp(dmg);
            return dmg;
        }
        return 0;
    }

    public boolean defend(Player player1){
        if(player1.inventory.weapon.getCanShield()){
            player1.setAC(false);
            return true;
        }
        return false;
    }

    //return true if successfully escape
    public boolean run(Player player1){
        return rollDice(1, 20) + player1.getStatMod("dex") > 10;
    }

    //------------------- ENEMY ACTION --------------------
    public int attackPlayer(Player player1){
        if(enemy.attackRoll(player1)){
            int dmg = enemy.dmgRoll();
            player1.takeDmg(dmg);
            return dmg;

        } else {
            return 0;
        }
    }

    //shows boolean {enemyDied, levelUp}
    public boolean[] died(Player player1){
        if(enemy.isDead()){
            player1.setExp(enemy.getExp());
            return new boolean[] {true, player1.levelUp()};
        }
        return new boolean[] {false, false};
    }
}

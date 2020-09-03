package com.company;

import java.util.Random;

public class Combat extends BaseMech {
    //------------------- ATTRIBUTES --------------------
    String[] creatures = {"none", "giantWeasel", "giantFrog", "hobgoblin", "brownBear"};
    Enemy enemy;
    Player player1;

    //------------------- CONSTRUCTORS --------------------
    public Combat(Player newPlayer){
        player1 = newPlayer;
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
    public int attack(){
        if (player1.inventory.getWeaponName().equals("none")){
            return -1;

        } else if(player1.inventory.weapon.attackRoll(enemy)) {
            int dmg = player1.inventory.weapon.dmgRoll();
            enemy.setHp(dmg);
            return dmg;

        } else {
            return 0;
        }
    }

    public boolean defend(){
        if(player1.inventory.weapon.getCanShield()){
            player1.setAC(false);
            return true;
        }
        return false;
    }

    //return true if successfully escape
    public boolean run(){
        return rollDice(1, 20) + player1.getStatMod("dex") > 10;
    }

    public int spellAttack(String spell){
        int spellIndex = player1.getSpellIndex(spell);
        Spell curSpell = player1.spells[spellIndex];

        if(spellIndex == -1) {
            return -1;

        } else if (!player1.setSpellSlots(curSpell.getLevel(), false)){
            return -2;

        } else if(player1.getSpellAtk(enemy)){
            int dmg = curSpell.getDamageDone();
            enemy.setHp(dmg);
            return dmg;

        } else {
            return 0;
        }
    }

    //------------------- ENEMY ACTION --------------------
    public int attackPlayer(){
        if(enemy.attackRoll(player1)){
            int dmg = enemy.dmgRoll();
            player1.takeDmg(dmg);
            return dmg;

        } else {
            return 0;
        }
    }

    //shows boolean {enemyDied, levelUp}
    public boolean[] died(){
        if(enemy.isDead()){
            player1.setExp(enemy.getExp());
            return new boolean[] {true, player1.levelUp()};
        }
        return new boolean[] {false, false};
    }
}

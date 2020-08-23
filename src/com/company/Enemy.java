package com.company;

public class Enemy extends BaseMech {
    private String name;
    private final int dmgDice;
    private int dmgTimes;
    private int hp, ac;
    private int level, exp;

    String[] enemyName = {"none", "giantWeasel", "giantFrog", "hobgoblin", "brownBear"};

    int[][] info = {
            //{lv, dmgDice, dmgTimes, AC, HP, exp}
            {0, 0, 0, 0, 0, 0},     //none
            {1, 4, 1, 13, 9, 25},   //giantWeasel
            {1, 6, 1, 11, 18, 50},  //giantFrog
            {2, 8, 1, 18, 11, 100}, //hobgoblin
            {2, 6, 2, 11, 34, 200}  //brownBear
    };

    //------------------- CONSTRUCTOR --------------------
    public Enemy(String enemy){
        int enemyIndex = findIndex(enemyName, enemy);

        name = enemy;
        level = info[enemyIndex][0];
        dmgDice = info[enemyIndex][1];
        dmgTimes = info[enemyIndex][2];
        ac = info[enemyIndex][3];
        hp = info[enemyIndex][4];
        exp = info[enemyIndex][5];
    }

    //------------------- ACCESSORS --------------------
    public int getHp(){ return hp; }

    public int getAc(){ return ac; }

    public int getExp(){ return exp; }

    public String getName(){ return name; }

    //------------------- MODIFIERS --------------------
    public void setHp(int dmg){
        hp -= dmg;
    }

    //------------------- METHODS --------------------
    //says enemy is dead if hp is less than or equal to zero (0)
    public boolean isDead(){
        return hp <= 0;
    }

    public boolean attackRoll(Player player1){
        return player1.getAc() < rollDice(1, 20);
    }

    public int dmgRoll(){
        return rollDice(dmgTimes, dmgDice);
    }
}

package com.company;

import java.util.HashMap;

public class Enemy {
    String name;
    int dmgDice, dmgTimes;
    int hp, ac;
    int level, exp;

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
        name = enemy;

        int row = 0;
        switch (enemy){
            case "giantWeasel":
                row = 1;
                break;
            case "giantFrog":
                row = 2;
                break;
            case "hobgoblin":
                row = 3;
                break;
            case "brownBear":
                row = 4;
                break;
            default:
                name = "none";
                break;
        }

        level = info[row][0];
        dmgDice = info[row][1];
        dmgTimes = info[row][2];
        ac = info[row][3];
        hp = info[row][4];
        exp = info[row][5];

    }

    //------------------- ACCESSORS --------------------

}

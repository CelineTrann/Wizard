package com.company;

import java.util.Random;
import java.util.Scanner;

public class Combat extends BaseMech {
    String[] creatures = {"none", "giantWeasel", "giantFrog", "hobgoblin", "brownBear"};
    Enemy enemy;
    boolean exit;

    //return true when exit or enemy died
    public boolean encounter(Scanner user, Player player1){
        int enemyIndex = getEnemyIndex(player1.getLevel());
        enemy = new Enemy(creatures[enemyIndex]);

        while(!died(player1) && !exit){
            System.out.println("A " + enemy.getName()+ " appeared!");
            System.out.println("What will you do?");

            while(playerOption(user, player1)){
                System.out.print("\t1. Attack \n" +
                        "\t2. Defend \n" +
                        "\t3. Run");
            }

            if(!died(player1) && !exit){
                attackPlayer(player1);
            }
            player1.setAC(true);
        }
        //enemy is dead or user wants to run
        return true;
    }

    private int getEnemyIndex(int level){
        Random randomEnemy = new Random();
        return randomEnemy.nextInt(level * 2) + 1;
    }

    //------------------- PLAYER ACTION --------------------
    public boolean playerOption(Scanner user, Player player1){
        switch (user.nextLine()){
            case "attack":
                attack(player1);
                break;
            case "defend":
                defend(player1);
                break;
            case "run":
                exit = run(player1);
                break;
            default:
                System.out.println("Invalid option");
                return true;
        }
        return false;
    }

    //return true if enemy is hit
    public void attack(Player player1){
        if(player1.weapon.attackRoll(enemy)) {
            int dmg = player1.weapon.dmgRoll();
            enemy.setHp(dmg);
            System.out.println("You did "+ dmg + " damage.");
        }
        System.out.println("You missed");
    }

    public void defend(Player player1){
        player1.setAC(false);
        System.out.println("AC increased by 2 for this round.");
    }

    //return true if successfully escape
    public boolean run(Player player1){
        if(rollDice(1, 20) + player1.getStatMod("dex") > 15){
            return true;
        }
        return false;
    }

    //------------------- ENEMY ACTION --------------------
    public void attackPlayer(Player player1){
        if(enemy.attackRoll(player1)){
            int dmg = enemy.dmgRoll();
            player1.takeDmg(dmg);
            System.out.println("You did "+ dmg + " damage.");
        } else {
            System.out.println("You missed");
        }
    }

    public boolean died(Player player1){
        if(enemy.getIsDead()){
            player1.setExp(enemy.getExp());
            System.out.println("You defeated the " + enemy.getName());
            System.out.println("You gained " + enemy.getExp() + "exp");
            return true;
        }
        return false;
    }
}

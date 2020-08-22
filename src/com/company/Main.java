package com.company;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner user = new Scanner(System.in);

        //--------------------- CREATE CHARACTER ------------------------
        System.out.println("Hello! Welcome to your new adventure. What is your name?");
        String name = user.nextLine();

        System.out.println("Welcome " + name + "! What race are your?");
        String race = user.nextLine();

        Player player1 = new Player(name, race, 1);

        viewStats(user, player1);

        //--------------------- SHOPPING TUT. ------------------------
        System.out.println("Let me teach you about the store. It can be found at the Adventure guild. \n" +
                "There you can buy, sell and upgrade weapons. Remember you can only carry one (1) weapon at a time. \n" +
                "So be sure to sell your weapon before you buy another one. \n");

        System.out.println("Type 'guild' to access the guild!");
        while(!user.nextLine().equalsIgnoreCase("guild")){
            System.out.println("try again!");
        }

        useGuild(user, player1);


        //------------------- COMBAT TUT. --------------------
        System.out.println("Now let's talk combat");
        System.out.println("Type 'forest' to fight monsters!");
        while(!user.nextLine().equalsIgnoreCase("forest")){
            System.out.println("try again!");
        }

        forest(user, player1);

    }

    //------------------- GUILD METHODS --------------------
    public static void useGuild(Scanner user, Player player1){
        do{
            showGuild(player1);
        } while (guildChoice(user, player1));

        System.out.println("Exiting Guild");
    }

    // Currently only supporting weapons
    public static void showGuild(Player player1){
        System.out.println("\nWeapons (" + player1.getGold() + "gp)");
        System.out.println("\t 1. buy \n" +
                "\t 2. sell \n" +
                "\t 3. upgrade \n" +
                "\t 4. exit");
    }

    public static boolean guildChoice(Scanner user, Player player1){
        switch (user.nextLine()){
            case "buy":
                buyWeapon(user, player1);
                break;
            case "sell":
                sellWeapon(user, player1);
                break;
            case "upgrade":
                upgradeWeapon(user, player1);
                break;
            case "exit":
                return false;
            default:
                System.out.println("Invalid input");
        }
        return true;
    }

    public static void showWeaponInfo(){
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
        System.out.println("5. exit");
    }

    public static void buyWeapon(Scanner user, Player player1){
        if(!player1.weapon.getCurrentWeapon().equals("none")) {
            System.out.println("You can only carry one weapon at a time. Please sell your weapon first.");
        } else {
            System.out.println("Opening shop... (Hit ENTER to continue)");
            user.nextLine();
            showWeaponInfo();

            System.out.println("\nWhat would you like to buy? (Case sensitive)");
            System.out.println("Current gold: " + player1.getGold());
            if(player1.buyWeapon(user.nextLine())){
                System.out.println("Transaction complete.");
            } else {
                System.out.println("Not enough gold");
            }
        }

    }

    public static void sellWeapon(Scanner user, Player player1){
        if(player1.weapon.showSellWeapon()){
            System.out.println("Weapon: " + player1.weapon.getCurrentWeapon());
            System.out.println("Price: " + player1.weapon.getValue() + "gp");
            System.out.println("Would you like to sell your weapon? (y/n)");

            if(user.nextLine().equalsIgnoreCase("y")){
                player1.sellWeapon();
                System.out.println("Transaction complete");
            }
        } else {
            System.out.println("You have no weapon to sell.");
        }
    }

    //UPGRADE METHODS
    public static boolean showUpgradeWeapon(Player player1) {
        if(player1.weapon.getCurrentWeapon().equals("none")){
            System.out.println("You have no weapon.");
            return false;
        } else {
            System.out.println("Weapon: " + player1.weapon.getCurrentWeapon());
            System.out.println("\t Upgrade cost: " + player1.weapon.getUpCost() + "gp");
            System.out.println("\t New Dmg Dice: " + (player1.weapon.getDmgTimes() + 1)
                    + "d" + player1.weapon.getDmgDice());
            return true;
        }
    }

    public static void upgradeWeapon(Scanner user, Player player1){
        if(showUpgradeWeapon(player1)){
            System.out.println("Would you like to upgrade your weapon? (y/n)");

            if(user.nextLine().equalsIgnoreCase("y")){
                if(player1.upgradeWeapon()){
                    System.out.println("Upgrade Complete \n");
                } else {
                    System.out.println("You don't have enough gold.");
                }
            }
        }
    }

    //------------------- PLAYER METHODS --------------------
    public static void viewStats(Scanner user, Player player1) {
        System.out.println("Would you like to see your overall stats? (y/n)");
        if(user.nextLine().equalsIgnoreCase("y")){
            player1.printPlayerInfo();
        }
    }

    //------------------- COMBAT METHODS --------------------
    public static void forest(Scanner user, Player player1){
        Combat combat1;
        do{
            combat1 = new Combat(player1);
            showMonster(combat1);
        } while (combatRounds(user, player1, combat1, false));
    }

    public static void showMonster(Combat combat1){
        System.out.println("Oh no! A " + combat1.enemy.getName() + " appeared!");
    }

    //return true if user wants to continue;
    //Fixed: dead variable prevents enemy death message twice due to recurssion
    public static boolean combatRounds(Scanner user, Player player1, Combat combat, boolean dead) {
        //if died get out of recurssion loop
        if (died(player1)){
            return false;
        }

        //store boolean {valid response, run}
        boolean[] good;
        do {
            good = showCombatOption(user, player1, combat);
        } while (!good[0]);

        if (good[1]) {
            return wilContinue(user);
        }

        //Enemy Attacks
        if (!enemyDied(player1, combat) && !died(player1)) {
            attackPlayer(player1, combat);
            endDefend(player1);
            return combatRounds(user, player1, combat, dead); //recursion will loop through round until quit, run, or player dies

        } else if(dead){
            dead = enemyDied(player1, combat);
            return wilContinue(user);

        }

        System.out.println("How did you get here?");
        return wilContinue(user);
    }

    public static boolean wilContinue(Scanner user){
        System.out.println("Would you like to continue? (y/n)");
        return user.nextLine().equals("y");
    }

    //returning boolean {valid response, run}
    public static boolean[] showCombatOption(Scanner user, Player player1, Combat combat){
        System.out.println("What will you do?");
        System.out.println("\t1. Attack \n" +
                "\t2. Defend \n" +
                "\t3. Run");

        switch (user.nextLine()){
            case "attack":
                attack(player1, combat);
                return new boolean[] {true, false};
            case "defend":
                defend(player1, combat);
                return new boolean[] {true, false};
            case "run":
                return new boolean[] {true, run(player1, combat)};
            default:
                System.out.println("Invalid option");
                return new boolean[] {false, false};
        }
    }

    //Player Function
    public static void attack(Player player1, Combat combat){
        int dmg = combat.attack(player1);
        if(dmg > 0){
            System.out.println("You did " + dmg + "dmg");
            if (combat.enemy.getHp() <= 0){
                System.out.println("Enemy health: 0");
            } else {
                System.out.println("Enemy health: " + combat.enemy.getHp());
            }
        } else {
            System.out.println("You missed.");
        }
    }

    public static void defend(Player player1, Combat combat1){
        combat1.defend(player1);
        System.out.println("Your AC increased to " + player1.getAc() + " for this round.");
    }

    public static boolean run(Player player1, Combat combat){
        if(combat.run(player1)){
            System.out.println("You escaped!");
            return true;
        }
        System.out.println("You failed to escape");
        return false;

    }

    public static void endDefend(Player player1){
        player1.setAC(true);
        System.out.println("You have lowered you shield. You AC returns to " + player1.getAc());
    }

    public static boolean died(Player player1){
        if(player1.isDead()){
            System.out.println("You died.");
            return true;
        }
        return false;
    }

    //Enemy functions
    public static void attackPlayer(Player player1, Combat combat){
        int dmg = combat.attackPlayer(player1);
        if(dmg > 0){
            System.out.println("You took " + dmg + "dmg.");
            System.out.println("Current hp: " + player1.getHp() + "/" + player1.getMaxHp());
        } else {
            System.out.println("The enemy missed");
        }
    }

    public static boolean enemyDied(Player player, Combat combat){
        boolean[] deathGain = combat.died(player);
        if(deathGain[0]){
            System.out.println("You defeated " + combat.enemy.getName());
            System.out.println("You gained "+ combat.enemy.getExp() + "exp");
            System.out.println("Total exp: " + player.getExp());

            if(deathGain[1]){
                System.out.println("You leveled up to level" + player.getLevel());
            }
            return true;
        }
        return false;
    }

}
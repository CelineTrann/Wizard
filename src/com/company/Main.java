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

        useGuild(user, player1, "guild");


        //------------------- COMBAT TUT. --------------------
        System.out.println("Now let's talk combat");
        System.out.println("Type 'forest' to fight monsters!");
        while(!user.nextLine().equalsIgnoreCase("forest")){
            System.out.println("try again!");
        }

        forest(user, player1);

        //------------------- NORMAL GAMEPLAY --------------------
        System.out.println("Now that you know everything.");
        do{
            System.out.println("What do you want to do?");
            System.out.println("1. guild \n" +
                    "2. Forest \n" +
                    "3. exit");
        }while (choices(user, player1) && !player1.isDead());

        System.out.println("Thank you for playing.");
    }

    public static boolean choices(Scanner user, Player player1) {
        switch (user.nextLine()){
            case "guild":
                useGuild(user, player1, "guild");
                break;
            case "forest":
                forest(user, player1);
                break;
            case "exit":
                return false;
            default:
                System.out.println("Invalid option");
        }
        return true;
    }

    //-----------------------------------------------------------------------
    //------------------- GUILD METHODS --------------------
    public static void showGuild(Player player1){
        System.out.println("\nGuild (" + player1.inventory.getGold() + "gp)");
        System.out.println("\t 1. weapons \n" +
                "\t 2. healthPotions  \n" +
                "\t 3. spells \n" +
                "\t 4. exit");
    }

    public static boolean guildChoice(Scanner user, Player player1){
        switch (user.nextLine()){
            case "weapons":
                useGuild(user, player1, "weapons");
                break;
            case "healthPotions":
                useGuild(user, player1, "healthPotions");
                break;
            case "spells":
                useGuild(user, player1, "spells");
                break;
            case "exit":
                return false;
            default:
                System.out.println("Invalid input");
        }
        return true;
    }

    public static void useGuild(Scanner user, Player player1, String section){
        switch (section) {
            case "guild":
                do {
                    showGuild(player1);
                } while (guildChoice(user, player1));

                System.out.println("Exiting Guild");
                break;

            case "weapons":
                do {
                    showWGuild(player1);
                } while (wGuildChoice(user, player1));

                System.out.println("Exiting Weapons Shop");
                break;

            case "healthPotions":
                do {
                    showHGuild(player1);
                } while (hGuildChoice(user, player1));

                System.out.println("Exiting Health Potion Shop");
                break;

            case "spells":
                do {
                    showSGuild();
                }while (sGuildChoice(user, player1));

                System.out.println("Exiting Spell Shop");
                break;

            default:
                System.out.println("Invalid option");
                break;
        }
    }

    //------------------- GUILD: WEAPONS --------------------
    public static void showWGuild(Player player1){
        System.out.println("\nWeapons (" + player1.inventory.getGold() + "gp)");
        System.out.println("\t 1. buy \n" +
                "\t 2. sell \n" +
                "\t 3. upgrade \n" +
                "\t 4. exit");
    }

    public static boolean wGuildChoice(Scanner user, Player player1){
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
        if(!player1.inventory.weapon.getCurrentWeapon().equals("none")) {
            System.out.println("You can only carry one weapon at a time. Please sell your weapon first.");
        } else {
            System.out.println("Opening shop... (Hit ENTER to continue)");
            user.nextLine();
            showWeaponInfo();

            System.out.println("\nWhat would you like to buy? (Case sensitive)");
            System.out.println("Current gold: " + player1.inventory.getGold());
            if(player1.inventory.buyWeapon(user.nextLine())){
                System.out.println("Transaction complete.");
            } else {
                System.out.println("Not enough gold");
            }
        }

    }

    public static void sellWeapon(Scanner user, Player player1){
        if(player1.inventory.weapon.showSellWeapon()){
            System.out.println("Weapon: " + player1.inventory.weapon.getCurrentWeapon());
            System.out.println("Price: " + player1.inventory.weapon.getValue() + "gp");
            System.out.println("Would you like to sell your weapon? (y/n)");

            if(user.nextLine().equalsIgnoreCase("y")){
                player1.inventory.sellWeapon();
                System.out.println("Transaction complete");
            }
        } else {
            System.out.println("You have no weapon to sell.");
        }
    }

    //UPGRADE METHODS
    public static boolean showUpgradeWeapon(Player player1) {
        if(player1.inventory.weapon.getCurrentWeapon().equals("none")){
            System.out.println("You have no weapon.");
            return false;
        } else {
            System.out.println("Weapon: " + player1.inventory.weapon.getCurrentWeapon());
            System.out.println("\t Upgrade cost: " + player1.inventory.weapon.getUpCost() + "gp");
            System.out.println("\t New Dmg Dice: " + (player1.inventory.weapon.getDmgTimes() + 1)
                    + "d" + player1.inventory.weapon.getDmgDice());
            return true;
        }
    }

    public static void upgradeWeapon(Scanner user, Player player1){
        if(showUpgradeWeapon(player1)){
            System.out.println("Would you like to upgrade your weapon? (y/n)");

            if(user.nextLine().equalsIgnoreCase("y")){
                if(player1.inventory.upgradeWeapon()){
                    System.out.println("Upgrade Complete \n");
                } else {
                    System.out.println("You don't have enough gold.");
                }
            }
        }
    }

    //------------------- GUILD: HEALTH POTIONS --------------------
    public static void showHGuild(Player player1){
        System.out.println("Health Potions (" + player1.inventory.getGold() + "gp, "
                + player1.getHp() + "/" + player1.getMaxHp() + "hp), "
                + "Health Potions: " + player1.inventory.getHealthPotions());
        System.out.println("\t1. buy \n" +
                "\t2. heal \n" +
                "\t3. exit");
    }

    public static boolean hGuildChoice(Scanner user, Player player1){
        switch (user.nextLine()){
            case "buy":
                buyHealthPotion(user, player1);
                break;
            case "heal":
                heal(player1);
                break;
            case "exit":
                return false;
            default:
                System.out.println("Invalid option");
        }
        return true;
    }

    public static void buyHealthPotion(Scanner user, Player player1){
        System.out.println("Cost: 5gp, Heals: 5hp");
        System.out.println("How many would you like to buy?");
        if(player1.inventory.buyHealthPotions(user.nextInt())){
            System.out.println("Thank you for your purchase.");
        } else {
            System.out.println("You don't have enough money");
        }
        user.nextLine();
    }

    //------------------- GUILD: SPELLS --------------------
    public static void showSGuild(){
        System.out.println("\nSpells");
        System.out.println("\t 1. acquire \n" +
                "\t 2. upgrade \n" +
                "\t 3. exit");
    }

    public static boolean sGuildChoice(Scanner user, Player player1){
        switch (user.nextLine()){
            case "acquire":
                showSpells(player1);
                buySpells(user, player1);
                break;
            case "exit":
                return false;
            default:
                System.out.println("Invalid input");
        }
        return true;
    }

    public static void showSpells(Player player1){
        String[] availableSpells = player1.spells[0].getAvailableSpells(player1.getLevel()).toArray(new String[0]);

        for(String spells: availableSpells){
            System.out.println(spells);
            int[] spellInfo = player1.spells[0].getSpellInfo(spells);
            System.out.println("\tLevel: " + spellInfo[0]
                + "\n\tDice Roll: " + spellInfo[1] + "d" + spellInfo[2]
                + "\n\tBonus: " + spellInfo[3]);

            if(spellInfo[4] == 1){
                System.out.println("\tType: Heals \n");
            } else{
                System.out.println("\tType: Attack \n");
            }
        }
    }

    public static void buySpells(Scanner user, Player player1){
        System.out.println("What spell would you like to acquire");
        String spellName = user.nextLine();

        System.out.println("What spell would you like to replace?");
        String[] curSpells = player1.showCurSpells();
        for(String name : curSpells){
            System.out.println("* " + name);
        }
        System.out.println("exit");

        int index = player1.findIndex(curSpells, user.nextLine());
        if(index == -1){
            System.out.println("Invalid spell.");
        } else {
            player1.setSpells(spellName, index);
            System.out.println("Spell acquired.");
        }
    }

    //-----------------------------------------------------------------------
    //------------------- PLAYER METHODS --------------------
    public static void viewStats(Scanner user, Player player1) {
        System.out.println("Would you like to see your overall stats? (y/n)");
        if(user.nextLine().equalsIgnoreCase("y")){
            player1.printPlayerInfo();
        }
    }


    //-----------------------------------------------------------------------
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
    public static boolean combatRounds(Scanner user, Player player1, Combat combat, boolean dead) {
        //if died get out of recursion loop
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
            endDefend(player1, good[2]);
            return combatRounds(user, player1, combat, dead); //recursion will loop through round until quit, run, or player dies

        } else if(dead){
            dead = enemyDied(player1, combat);
            return wilContinue(user);

        }

        return wilContinue(user);
    }

    //return true to stay in forest
    public static boolean wilContinue(Scanner user){
        System.out.println("Would you like to continue fighting? (y/n)");
        return user.nextLine().equals("y");
    }

    //returning boolean {valid response, run, defended}
    public static boolean[] showCombatOption(Scanner user, Player player1, Combat combat){
        System.out.println("What will you do?");
        System.out.println("\t1. attack \n" +
                "\t2. defend \n" +
                "\t3. heal \n" +
                "\t4. run");

        switch (user.nextLine()){
            case "attack":
                attack(player1, combat);
                return new boolean[] {true, false, false};
            case "defend":
                return new boolean[] {true, false, defend(player1, combat)};
            case "heal":
                heal(player1);
                return new boolean[] {true, false, false};
            case "run":
                return new boolean[] {true, run(player1, combat), false};
            default:
                System.out.println("Invalid option");
                return new boolean[] {false, false, false};
        }
    }

    //------------------- COMBAT: PLAYERS --------------------
    public static void attack(Player player1, Combat combat){
        int dmg = combat.attack(player1);
        if(dmg > 0){
            System.out.println("You did " + dmg + "dmg");
            if (combat.enemy.getHp() <= 0){
                System.out.println("Enemy health: 0");
            } else {
                System.out.println("Enemy health: " + combat.enemy.getHp());
            }
        } else if (dmg == 0){
            System.out.println("You missed.");

        } else {
            System.out.println("You don't have a weapon to attack with.");
        }
    }

    public static boolean defend(Player player1, Combat combat1){
        if(combat1.defend(player1)){
            System.out.println("Your AC increased to " + player1.getAc() + " for this round.");
            return true;
        } else {
            System.out.println("You cannot defend with this weapon.");
            return false;
        }

    }

    public static void endDefend(Player player1, boolean defended){
        if(defended && !player1.isDead()){
            player1.setAC(true);
            System.out.println("You have lowered you shield. You AC returns to " + player1.getAc());
        }
    }

    public static void heal(Player player1){
        int preHeal = player1.getHp();
        if(player1.heal()){
            if(player1.getHp() == player1.getMaxHp()){
                System.out.println("You healed: " + (player1.getMaxHp() - preHeal) + "points");
            }

            System.out.println("Current health: " + player1.getHp() + "/" + player1.getMaxHp());
            System.out.println("Health Potions left: " + player1.inventory.getHealthPotions());
        } else if(player1.inventory.getHealthPotions() == 0) {
            System.out.println("You are out of health potions.");
        } else {
            System.out.println("You are at full health.");
        }
    }

    public static boolean run(Player player1, Combat combat){
        if(combat.run(player1)){
            System.out.println("You escaped!");
            return true;
        }
        System.out.println("You failed to escape");
        return false;

    }

    public static boolean died(Player player1){
        if(player1.isDead()){
            System.out.println("You died.");
            return true;
        }
        return false;
    }

    //------------------- COMBAT: ENEMY --------------------
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
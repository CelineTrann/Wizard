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

        do{
            showGuild(player1);
        } while (guildChoice(user, player1));

        System.out.println("Thanks for playing!");

    }

    //------------------- GUILD METHODS --------------------
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

    public static void buyWeapon(Scanner user, Player player1){
        System.out.println("Opening shop... (Hit ENTER to continue)");
        user.nextLine();
        player1.weapon.showWeaponInfo();

        System.out.println("\nWhat would you like to buy? (Case sensitive)");
        System.out.println("Current gold: " + player1.getGold());
        String purchase = user.nextLine();
        player1.buyWeapon(purchase);
    }

    public static void sellWeapon(Scanner user, Player player1){
        if(player1.weapon.showSellWeapon()){
            System.out.println("Would you like to sell your weapon? (y/n)");

            if(user.nextLine().equalsIgnoreCase("y")){
                player1.sellWeapon();
            }
        } else {
            System.out.println("You have no weapon to sell.");
        }
        System.out.println("Exiting upgrade screen.");

    }

    public static void upgradeWeapon(Scanner user, Player player1){
        player1.weapon.showUpgradeWeapon();
        System.out.println("Would you like to upgrade your weapon? (y/n)");

        if(user.nextLine().equalsIgnoreCase("y")){
            player1.upgradeWeapon();
        } else {
            System.out.println("Exiting upgrade screen.");
        }
    }

    //------------------- PLAYER METHODS --------------------
    public static void viewStats(Scanner user, Player player1) {
        System.out.println("Would you like to see your overall stats? (y/n)");
        if(user.nextLine().equalsIgnoreCase("y")){
            player1.printPlayerInfo();
        }
    }
}
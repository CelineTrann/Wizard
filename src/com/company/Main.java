package com.company;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner user = new Scanner(System.in);

        System.out.println("Hello! Welcome to your new adventure. What is your name?");
        String name = user.nextLine();

        System.out.println("Welcome " + name + "! What race are your?");
        String race = user.nextLine();

        System.out.println("Well that's great! Now, what class do you want to play?");
        user.nextLine();

        System.out.println("Whoops. There seems to be a problem. The only class available is 'Wizard' :(");
        Player player1 = new Player(name, race, 1);

        System.out.println("Would you like to see your overall stats? (y/n)");
        if(user.nextLine().equalsIgnoreCase("y")){
            player1.printPlayerInfo();
        }

        if(player1.weapon.currentWeapon.equalsIgnoreCase("none")) {
            System.out.println("Oh! It looks like you have no weapons.");
        } else {
            System.out.println("Current weapon" + player1.weapon.currentWeapon);
        }
        System.out.println("Opening shop... (Hit ENTER to continue)");
        user.nextLine();
        player1.weapon.showWeaponInfo();

        System.out.println("What would you like to buy? (Case sensitive)");
        System.out.println("Current gold: " + player1.getGold());
        String purchase = user.nextLine();
        player1.buyWeapon(purchase);

        System.out.println("Would you like to upgrade your weapon? (y/n)");
        if(user.nextLine().equalsIgnoreCase("y")){
            player1.weapon.showUpgradeWeapon();
        }

    }
}
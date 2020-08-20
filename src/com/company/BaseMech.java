package com.company;

import java.util.Random;

public class BaseMech {
    //roll and dice (times d sides)
    public int rollDice(int times, int sides){
        Random dice = new Random();
        int sum = 0;

        for(int i = 0; i < times; i++){
            sum += dice.nextInt(sides) + 1;
        }

        return sum;
    }
}

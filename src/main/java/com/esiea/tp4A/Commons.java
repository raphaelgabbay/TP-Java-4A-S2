package com.esiea.tp4A;

import java.util.Random;

public class Commons {

    public static int getRandomInt(int max,int min){
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }
}

package com.offerista.main.producer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumberGenerator {

    protected static List<Integer> generateRandomNumbers(int size) {
        List<Integer>   result = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int number = random.nextInt(); // Generate random number
            result.add(number);
        }
        return result;
    }
}

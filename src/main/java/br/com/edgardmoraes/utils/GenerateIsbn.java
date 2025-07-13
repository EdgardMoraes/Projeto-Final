package br.com.edgardmoraes.utils;

import java.util.Random;

public class GenerateIsbn  {
    public static String generateIsbn(){
        Random rand = new Random();
        StringBuilder isbn = new StringBuilder("123");

        for (int i = 0; i < 9; i++) {
            isbn.append(rand.nextInt(10));
        }
        return isbn.toString();
    }
}

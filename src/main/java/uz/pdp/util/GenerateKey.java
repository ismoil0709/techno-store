package uz.pdp.util;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class GenerateKey {
    public String generate(){
        int rand = new Random(1).nextInt() * -1;
        System.out.println(rand);
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < rand; i++) {
            key.append((char) i * 5);
        }
        return key.toString();
    }
}

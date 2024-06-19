package br.metrics.helpers;

import java.util.Random;

public class RandomHelper {
    /*fonte https://www.baeldung.com/java-random-string*/
    public static String getRandomString(int size){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(size)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }
}

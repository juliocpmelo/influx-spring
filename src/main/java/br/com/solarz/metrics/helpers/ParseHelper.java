package br.com.solarz.metrics.helpers;

public class ParseHelper {
    public static Double parseInGib(Double value) {
        return value / Math.pow(2, 30);
    }

    public static Double parseInGibToGB(Double value) {
        return value * Math.pow(2, 30) / Math.pow(10, 9);
    }

    public static Double parse(Double value) {
        return value / 1024;
    }

    public static Double parseStrInDouble(String str) {
        return Double.parseDouble(str.replaceAll("[^0-9]+(?:[^.,][^0-9]+)?", ""));
    }
}

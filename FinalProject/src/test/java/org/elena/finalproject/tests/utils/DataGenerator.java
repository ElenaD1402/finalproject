package org.elena.finalproject.tests.utils;

import java.util.List;
import java.util.Random;

public class DataGenerator {

    private static final Random random = new Random();
    private static final String alphabet = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
    public static List<String> categoriesList = List.of("Books", "Music", "Series", "Games", "Sport");
    public static List<String> tagsList = List.of("tag1", "tag2", "tag3", "tag4", "tag5");

    public static String getRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return sb.toString();
    }

    public static String getRandomCategory() {
        return categoriesList.get(random.nextInt(categoriesList.size()));
    }

    public static String getRandomTag() {
        return tagsList.get(random.nextInt(tagsList.size()));
    }
}

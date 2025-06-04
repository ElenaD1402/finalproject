package org.elena.finalproject.utils;

import io.qameta.allure.Step;

import java.util.List;
import java.util.Random;

public class DataGenerator {

    private static final Random random = new Random();
    private static final String alphabet = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
    public static List<String> categoriesList = List.of("Books", "Music", "Series", "Games", "Sport", "Cartoons", "Language", "Transport", "Hardware", "Software");
    public static List<String> tagsList = List.of("Tag1", "Tag2", "Tag3", "Tag4", "Tag5", "Tag6", "Tag7", "Tag8", "Tag9", "Tag10");

    @Step("Generating a random string with length = {length}")
    public static String getRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return sb.toString();
    }

    @Step("Getting a random category from the categories list")
    public static String getRandomCategory() {
        return categoriesList.get(random.nextInt(categoriesList.size()));
    }

    @Step("Getting a random tag from the tags list")
    public static String getRandomTag() {
        return tagsList.get(random.nextInt(tagsList.size()));
    }
}

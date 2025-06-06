package org.elena.finalproject.utils;

import io.qameta.allure.Step;

import java.util.List;
import java.util.Random;

public class DataGenerator {

    private static final Random random = new Random();
    private static final String alphabet = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
    public static List<String> categoriesList = List.of("Books", "Music", "Series", "Games", "Sport", "Cartoons", "Language", "Transport", "Hardware", "Software");

    @Step("Generating a random string with length = {length}")
    public static String getRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return sb.toString();
    }

    @Step("Generating a category")
    public static String getRandomCategory() {
        return categoriesList.get(random.nextInt(categoriesList.size())) + System.currentTimeMillis();
    }

    @Step("Generating a tag")
    public static String getRandomTag() {
        return "Tag" + System.currentTimeMillis();
    }
}

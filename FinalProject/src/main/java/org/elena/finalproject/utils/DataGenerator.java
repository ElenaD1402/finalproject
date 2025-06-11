package org.elena.finalproject.utils;

import io.qameta.allure.Step;

import java.util.Random;

public class DataGenerator {

    private static final Random random = new Random();
    private static final String alphabet = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";

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
        return getRandomString(5) + System.currentTimeMillis();
    }

    @Step("Generating a tag")
    public static String getRandomTag() {
        return getRandomString(5) + System.currentTimeMillis();
    }
}

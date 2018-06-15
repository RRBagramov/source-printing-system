package ru.kpfu.printer.utils;

import java.util.Random;

/**
 * 02.06.2018
 *
 * @author Robert Bagramov.
 */
public class PasswordGenerator {
    private static final String SYMBOLS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SYMBOLS_COUNT = 62;

    public static String generatePassword(int length) {
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            password = password.append(SYMBOLS.charAt(random.nextInt(SYMBOLS_COUNT)));
        }

        return password.toString();
    }
}

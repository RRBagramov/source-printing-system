package ru.kpfu.printer.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 02.06.2018
 *
 * @author Robert Bagramov.
 */
public class LoginGenerator {
    private static final String SUFFIX_LESS_TEN = "0";

    public static List<String> generateLogins(String loginBasePart, int count) {
        int index = 0;
        StringBuilder login = new StringBuilder();
        List<String> logins = new ArrayList<>();

        for (; index < 10; index++) {
            login.append(loginBasePart).append(SUFFIX_LESS_TEN).append(index);
            logins.add(login.toString());
            login.setLength(0);
        }
        for (; index < count; index++) {
            login.append(loginBasePart).append(index);
            logins.add((login.toString()));
            login.setLength(0);
        }

        return logins;
    }
}

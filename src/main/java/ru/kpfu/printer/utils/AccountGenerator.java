package ru.kpfu.printer.utils;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 02.06.2018
 *
 * @author Robert Bagramov.
 */
public class AccountGenerator {
    public static Map<String, String> generateAccounts(int count, int passwordLength, String baseLoginPart) {
        List<String> logins = LoginGenerator.generateLogins(baseLoginPart, count);
        Map<String, String> accounts = new TreeMap<>();

        for (int i = 0; i < count; i++) {
            accounts.put(logins.get(i), PasswordGenerator.generatePassword(passwordLength));
        }

        return accounts;
    }
}

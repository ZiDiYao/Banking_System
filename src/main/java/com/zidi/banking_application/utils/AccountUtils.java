package com.zidi.banking_application.utils;

import java.time.Year;
import java.util.concurrent.ThreadLocalRandom;

public class AccountUtils {

    /**
     * Form：2025 + 6 digits random number，such as  2025104923
     */
    public static String generateAccountNumber() {
        String year = String.valueOf(Year.now().getValue());
        int randomNumber = ThreadLocalRandom.current().nextInt(100000, 1000000);
        return year + randomNumber;
    }
}

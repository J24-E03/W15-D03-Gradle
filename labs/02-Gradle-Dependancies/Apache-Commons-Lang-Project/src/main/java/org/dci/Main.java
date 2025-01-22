package org.dci;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        useCommonLangFunctionalities();

        bonus();
    }

    private static void useCommonLangFunctionalities() {
        // Using StringUtils for string manipulation:
        System.out.println("Using StringUtils for string manipulation:\n");
        String text = "   Hello, My name is Maryam   ";
        System.out.println("Original String: \"" + text + "\"");
        String trimmedText = StringUtils.trim(text);
        System.out.println("Trimmed String: \"" + trimmedText + "\"");
        System.out.println("Reversed String: \"" + StringUtils.reverse(trimmedText) + "\"");
        System.out.println("Is the input String blank? " + StringUtils.isBlank(trimmedText));
        System.out.println("Capitalized String: \"" + StringUtils.capitalize(trimmedText.toLowerCase()) + "\"");
        System.out.println("Contains 'maryam' (case-insensitive)? " + StringUtils.containsIgnoreCase(trimmedText, "maryam"));
        System.out.println("Swapped Case String: " + StringUtils.swapCase(trimmedText));
        System.out.println("Repeated String: " + StringUtils.repeat("Maryam", 3));

        // Generating random strings using RandomStringUtils:
        System.out.println("\nGenerating random strings using RandomStringUtils:\n");
        System.out.println("Random Alphanumeric String: " + RandomStringUtils.randomAlphanumeric(10));
        System.out.println("Random Numeric String: " + RandomStringUtils.randomNumeric(6));
        System.out.println("Random Alphabetic String: " + RandomStringUtils.randomAlphabetic(8));

        // Using NumberUtils for Numeric Operations
        System.out.println("\nUsing NumberUtils for Numeric Operations:");
        String validNumString = "1234";
        String invalidNumString = "12A34";
        System.out.println("\"" + validNumString + "\" is a number? " + NumberUtils.isCreatable( validNumString));
        System.out.println("\"" + invalidNumString + "\" is a number? " + NumberUtils.isCreatable( invalidNumString));
        System.out.println("Converted Number: " + NumberUtils.toInt(validNumString));

        int[] numbers = {3, 5, 1, 9, 2, 8, 10};
        System.out.println("Array of Integers: " + Arrays.toString(numbers));
        int max = NumberUtils.max(numbers);
        int min = NumberUtils.min(numbers);
        System.out.println("Max: " + max + ", Min: " + min);
    }

    private static void bonus() {
        System.out.println("\nBonus Task:");
        System.out.println("Using DateUtils for Date Manipulation");
        Date now = new Date();
        System.out.println("Current Date: " + DateFormatUtils.format(now, "yyyy-MM-dd HH:mm:ss"));
        System.out.println("Tomorrow's Date: " + DateFormatUtils.format(DateUtils.addDays(now, 1), "yyyy-MM-dd HH:mm:ss"));
        System.out.println("Last Month's Date: " + DateFormatUtils.format(DateUtils.addMonths(now, -1), "yyyy-MM-dd HH:mm:ss"));

        System.out.println("\nUsing ObjectUtils for Object Operations:");
        String nullString = null;
        String str1 = "Maryam";

        System.out.println(ObjectUtils.defaultIfNull(null, "Default Value"));
        System.out.println(ObjectUtils.defaultIfNull(str1, "Default Value"));

        System.out.println("All object are null? " + ObjectUtils.allNotNull(null, str1, "hello"));

        System.out.println("Objects are equal: " + ObjectUtils.equals(str1, "Hello"));
    }
}
package org.example;

import org.apache.commons.lang3.*;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // StringUtils example
        String reversed = StringUtils.reverse("BankingProject");
        System.out.println("Reversed String: " + reversed);

        // RandomStringUtils example
        String random = RandomStringUtils.randomAlphanumeric(22);
        System.out.println("Random String: " + random);

        // NumberUtils example
        boolean isNumber = NumberUtils.isCreatable("1500100900");
        System.out.println("Is '12345' a number? " + isNumber);

        // DateUtils example
        Date now = new Date();
        Date tomorrow = DateUtils.addDays(now, 1);
        System.out.println("Tomorrow: " + tomorrow);

        // ObjectUtils example
        String str = null;
        String safeString = ObjectUtils.defaultIfNull(str, "Default Value");
        System.out.println("Safe String: " + safeString);

        // JoinString example
        String joined = StringUtils.join(new String[]{"Apple", "Banana", "Cherry"}, ", ");
        System.out.println("Joined: " + joined); // Output: Apple, Banana, Cherry

        // Check isBlank example
        boolean isBlank = StringUtils.isBlank("   "); // true
        System.out.println("Is blank: " + isBlank);

        // DurationFormatUtils example
        long durationMillis = 2 * 60 * 60 * 1000; // 2 hours in milliseconds
        // Format duration as HH:mm:ss
        String formattedDuration = DurationFormatUtils.formatDuration(durationMillis, "HH:mm:ss");
        System.out.println("Formatted Duration: " + formattedDuration); // Output: 02:00:00

        // ArrayUtils example
        int[] numbers = {};

        // Check if the array is empty
        boolean isEmpty = ArrayUtils.isEmpty(numbers);
        System.out.println("Is Empty: " + isEmpty); // Output: true
        }
    }

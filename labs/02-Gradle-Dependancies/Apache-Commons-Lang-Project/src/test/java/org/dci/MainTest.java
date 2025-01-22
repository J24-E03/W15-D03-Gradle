package org.dci;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    public void testStringUtils() {
        String text = "   Hello, My name is Maryam   ";

        String trimmedText = StringUtils.trim(text);
        assertEquals("Hello, My name is Maryam", trimmedText);

        String reversedText = StringUtils.reverse(trimmedText);
        assertEquals("mayraM si eman yM ,olleH", reversedText);

        assertTrue(StringUtils.isBlank("   "));
        assertFalse(StringUtils.isBlank(trimmedText));

        String capitalizedText = StringUtils.capitalize(trimmedText.toLowerCase());
        assertEquals("Hello, my name is maryam", capitalizedText);

        assertTrue(StringUtils.containsIgnoreCase(trimmedText, "maryam"));

        String swappedCaseText = StringUtils.swapCase(trimmedText);
        assertEquals("hELLO, mY NAME IS mARYAM", swappedCaseText);

        String repeatedText = StringUtils.repeat("Maryam", 3);
        assertEquals("MaryamMaryamMaryam", repeatedText);
    }

    @Test
    public void testRandomStringUtils() {
        String randomAlpha = RandomStringUtils.randomAlphabetic(8);
        assertEquals(8, randomAlpha.length());

        String randomNumeric = RandomStringUtils.randomNumeric(6);
        assertEquals(6, randomNumeric.length());

        String randomAlphanumeric = RandomStringUtils.randomAlphanumeric(10);
        assertEquals(10, randomAlphanumeric.length());
    }

    @Test
    public void testNumberUtils() {
        String validNumString = "1234";
        String invalidNumString = "12A34";

        assertTrue(NumberUtils.isCreatable(validNumString));
        assertFalse(NumberUtils.isCreatable(invalidNumString));

        assertEquals(1234, NumberUtils.toInt(validNumString));

        int[] numbers = {3, 5, 1, 9, 2, 8, 10};
        assertEquals(10, NumberUtils.max(numbers));
        assertEquals(1, NumberUtils.min(numbers));
    }

    @Test
    public void testDateUtils() {
        Date now = new Date();
        String currentDate = DateFormatUtils.format(now, "yyyy-MM-dd HH:mm:ss");


        assertNotNull(currentDate);
        assertTrue(currentDate.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"));


        Date tomorrow = DateUtils.addDays(now, 1);
        String tomorrowDate = DateFormatUtils.format(tomorrow, "yyyy-MM-dd HH:mm:ss");
        assertNotNull(tomorrowDate);

        Date lastMonth = DateUtils.addMonths(now, -1);
        String lastMonthDate = DateFormatUtils.format(lastMonth, "yyyy-MM-dd HH:mm:ss");
        assertNotNull(lastMonthDate);
    }

    @Test
    public void testObjectUtils() {
        String str1 = "Maryam";
        String nullString = null;


        assertEquals("Default Value", ObjectUtils.defaultIfNull(nullString, "Default Value"));
        assertEquals("Maryam", ObjectUtils.defaultIfNull(str1, "Default Value"));

        assertTrue(ObjectUtils.allNotNull(str1, "Test", 123));
        assertFalse(ObjectUtils.allNotNull(nullString, "Test", 123));


        assertTrue(ObjectUtils.equals(str1, "Maryam"));
        assertFalse(ObjectUtils.equals(str1, "Hello"));
    }

}
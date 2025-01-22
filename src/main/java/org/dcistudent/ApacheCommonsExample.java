package org.dcistudent;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class ApacheCommonsExample {
  public static void main(String[] args) {
    // StringUtils example
    String reversed = StringUtils.reverse("BankingProject");
    System.out.println("Reversed String: " + reversed);

    // RandomStringUtils example
    String random = RandomStringUtils.randomAlphanumeric(10);
    System.out.println("Random String: " + random);

    // NumberUtils example
    boolean isNumber = NumberUtils.isCreatable("12345");
    System.out.println("Is '12345' a number? " + isNumber);
  }
}

package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DemoService {
    public static final String MINUS_SIGN = "-";
    static final List<String> THREE_OR_LESS = List.of("0", "1", "2", "3");
    static final List<String> EIGHT_OR_NINE = List.of("8", "9");

    Integer processNumber(int number) {
        int shifted = shiftThreeOrLessToRight(number);
        int afterMultiplication = multiplyEightOrNine(shifted);
        int afterDigitOperations = removeSevens(afterMultiplication);
        return divideByEvenDigitCount(afterDigitOperations);
    }


    private int shiftThreeOrLessToRight(int input) {
        String[] digitArray = toDigitArray(input);
        int secondLastIndex = digitArray.length - 2;
        for (int i = secondLastIndex; i >= 0; i--) {
            String digit = digitArray[i];
            if (isThreeOrLess(digit)) {
                shiftDigitRight(digitArray, i);
            }
        }
        return Integer.parseInt(String.join("", digitArray));
    }

    private String[] toDigitArray(Integer input) {
        return input.toString().split("");
    }

    private boolean isThreeOrLess(String digit) {
        return THREE_OR_LESS.contains(digit);
    }

    private void shiftDigitRight(String[] digits, int index) {
        swapDigits(digits, index, index + 1);
    }

    private void swapDigits(String[] digits, int index1, int index2) {
        String temp = digits[index1];
        digits[index1] = digits[index2];
        digits[index2] = temp;
    }


    private int multiplyEightOrNine(int input) {
        return Integer.parseInt(Arrays.stream(toDigitArray(input))
                .map((it) -> EIGHT_OR_NINE.contains(it)
                        ? multiplyString(it, 2)
                        : it).collect(Collectors.joining()));
    }

    @SuppressWarnings("SameParameterValue")
    private String multiplyString(String s, Integer multiplier) {
        return Integer.toString(Integer.parseInt(s) * multiplier);
    }


    private int removeSevens(int afterMultiplication) {
        return Integer.parseInt(Arrays.stream(toDigitArray(afterMultiplication))
                .filter((it) -> !"7".equals(it))
                .collect(Collectors.joining()));
    }


    private int divideByEvenDigitCount(int afterDigitOperations) {
        return afterDigitOperations / countEvenDigits(afterDigitOperations);
    }

    private int countEvenDigits(Integer input) {
        return Math.toIntExact(
                Arrays.stream(input.toString().split(""))
                        .filter(this::isEvenDigit)
                        .count());
    }

    private boolean isEvenDigit(String it) {
        if (MINUS_SIGN.equals(it)) return false;
        return Integer.parseInt(it) % 2 == 0;
    }
}

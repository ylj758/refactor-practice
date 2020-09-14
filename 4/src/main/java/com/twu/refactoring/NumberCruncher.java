package com.twu.refactoring;

public class NumberCruncher {
    private final int[] numbers;

    public NumberCruncher(int... numbers) {
        this.numbers = numbers;
    }

    public int countEven() {
        int count = getCountEvenOrOdd(0);
        return count;
    }

    public int countOdd() {
        int count = getCountEvenOrOdd(1);
        return count;
    }

    private int getCountEvenOrOdd(int i) {
        int count = 0;
        for (int number : numbers) {
            if (number % 2 == i) count++;
        }
        return count;
    }


    public int countPositive() {
        int count = 0;
        for (int number : numbers) {
            if (number >= 0) count++;
        }
        return count;
    }

    public int countNegative() {
        int count = 0;
        for (int number : numbers) {
            if (number < 0) count++;
        }
        return count;
    }
}

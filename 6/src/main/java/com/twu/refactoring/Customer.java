package com.twu.refactoring;

import java.util.ArrayList;
import java.util.Iterator;

public class Customer {

    private String name;
    private ArrayList<Rental> rentalList = new ArrayList<Rental>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental arg) {
        rentalList.add(arg);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Iterator<Rental> rentals = rentalList.iterator();
        StringBuilder result = new StringBuilder(String.format("Rental Record for %s\n",getName()));
        while (rentals.hasNext()) {
            double thisAmount = 0;
            Rental eachRental = rentals.next();
            thisAmount = getAmountForEachLine(thisAmount, eachRental);
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if ((eachRental.getMovie().getPriceCode() == Movie.NEW_RELEASE)
                    && eachRental.getDaysRented() > 1)
                frequentRenterPoints++;
            // show figures for this rental
            result.append(String.format("\t%s\t%s\n",eachRental.getMovie().getTitle(),String.valueOf(thisAmount)));
            totalAmount += thisAmount;
        }
        // add footer lines
        result.append(String.format("Amount owed is %s\n",String.valueOf(totalAmount)));
        result.append(String.format("You earned %s frequent renter points",String.valueOf(frequentRenterPoints)));
        return result.toString();
    }

    private double getAmountForEachLine(double amount, Rental rental) {
        switch (rental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                amount += 2;
                if (rental.getDaysRented() > 2)
                    amount += (rental.getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                amount += rental.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                amount += 1.5;
                if (rental.getDaysRented() > 3)
                    amount += (rental.getDaysRented() - 3) * 1.5;
                break;

        }
        return amount;
    }

}

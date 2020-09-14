package com.twu.refactoring;

public class Receipt {

    private final Taxi taxi;

    public Receipt(Taxi taxi) {
        this.taxi = taxi;
    }

    public double getTotalCost() {
        double totalCost = 0;
        totalCost += StaticFinals.FIXED_CHARGE;

        // taxi charges
        int totalKms = taxi.getTotalKms();
        double peakTimeMultiple = taxi.isPeakTime() ? StaticFinals.PEAK_TIME_MULTIPLIER : StaticFinals.OFF_PEAK_MULTIPLIER;
        double mathMinCost= Math.min(StaticFinals.RATE_CHANGE_DISTANCE, totalKms) * peakTimeMultiple;
        double mathMaxCost= Math.max(0, totalKms - StaticFinals.RATE_CHANGE_DISTANCE) * peakTimeMultiple;
        if(taxi.isAirConditioned()) {
            totalCost += mathMinCost * StaticFinals.PRE_RATE_CHANGE_AC_RATE ;
            totalCost += mathMaxCost * StaticFinals.POST_RATE_CHANGE_AC_RATE ;
        } else {
            totalCost += mathMinCost * StaticFinals.PRE_RATE_CHANGE_NON_AC_RATE ;
            totalCost += mathMaxCost * StaticFinals.POST_RATE_CHANGE_NON_AC_RATE ;
        }

        return totalCost * (1 + StaticFinals.SALES_TAX_RATE);
    }
}

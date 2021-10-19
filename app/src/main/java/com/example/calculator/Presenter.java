package com.example.calculator;

public class Presenter {
    private IListener iListener;

    Presenter(IListener iListener) {
        this.iListener = iListener;
    }

    public void getPercent(String costObject, String monthlyRent) {
        double cObj = Double.parseDouble(costObject);
        double cMont = Double.parseDouble(monthlyRent);
        String formattedDouble = String.format("%.2f", solvePercent(cObj, cMont)) + "%";
        iListener.setPercent(formattedDouble);
    }

    private double solvePercent(double costObject, double monthlyRent) {
        double percentageOfProfitability = ((monthlyRent * 12) / costObject) * 100;

        return percentageOfProfitability;
    }

}

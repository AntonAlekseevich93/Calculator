package com.example.calculator.entity;

public class Calculation {

    private double costObject;
    private double monthlyRent;
    private double expenses;
    private String percentageOfIncome;
    private String netProfit;
    private String paybackPeriod;

    public String getPercentageOfIncome() {
        return percentageOfIncome;
    }

    public String getNetProfit() {
        return netProfit;
    }

    public String getPaybackPeriod() {
        return paybackPeriod;
    }


    public Calculation(double costObject, double monthlyRent, double expenses) {
        this.costObject = costObject;
        this.monthlyRent = monthlyRent;
        this.expenses = expenses;
        setPercentageOfIncome(costObject, monthlyRent, expenses);
        setNetProfit(monthlyRent, expenses);
        setPaybackPeriod(costObject, monthlyRent, expenses);
    }

    private void setPercentageOfIncome(double costObject, double monthlyRent, double expenses) {
        double d = ((monthlyRent * 12 - expenses) / costObject) * 100;
        percentageOfIncome = String.format("%.2f", d) + "%";
    }

    private void setNetProfit(double monthlyRent, double expenses) {
        int i = (int) (monthlyRent * 12 - expenses);
        netProfit = String.valueOf(i);
    }

    private void setPaybackPeriod(double costObject, double monthlyRent, double expenses) {
        double d = costObject / (monthlyRent * 12 - expenses);
        String[] s = String.valueOf(d).split("\\.");
        paybackPeriod = s[0] + " year " + s[1].substring(0, 1) + " month";
    }

    public void calculation(double costObject, double monthlyRent, double expenses) {
        setPercentageOfIncome(costObject, monthlyRent, expenses);
        setNetProfit(monthlyRent, expenses);
        setPaybackPeriod(costObject, monthlyRent, expenses);
    }
}
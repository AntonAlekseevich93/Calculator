package com.example.calculator.interactor;

import com.example.calculator.entity.Calculation;

public class UseCase{

    UseCase() {
    }

    public Calculation calculate(String costObject, String monthlyRent, String expenses) {
        double dCostObject = Double.parseDouble(costObject);
        double dMonthlyRent = Double.parseDouble(monthlyRent);
        double dExpenses = Double.parseDouble(expenses);
        Calculation calculation = new Calculation(dCostObject, dMonthlyRent, dExpenses);
        return calculation;
    }
    
}

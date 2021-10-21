public class Calculation{
    private double costObject; 
    private double monthlyRent; 
    private double expenses;
    private String percentageOfIncome;
    private String netProfit;
    private String paybackPeriod;

    
    Calculation(double costObject, double monthlyRent, double expenses) {
        this.costObject = costObject; 
        this.monthlyRent = monthlyRent; 
        this.expenses = expenses;
        percentageOfIncome = getPercentOfIncome(costObject, monthlyRent, expenses); 
        netProfit = getNetProfit(monthlyRent, expenses);
        paybackPeriod = getPaybackPeriod(costObject, monthlyRent, expenses);
    }
    
    private void getPercentageOfIncome(double costObject, double monthlyRent, double expenses) {
        double d = ((monthlyRent * 12 - expenses) / costObject) * 100;
        percentageOfIncome = String.format("%.2f", d) + "%";
    }
    
    private void getNetProfit(double monthlyRent, double expenses){
        int i = (Integer) monthlyRent*12-expenses;
        netProfit = String.valueOf(i);
    }
    
    private void getPaybackPeriod(double costObject, double monthlyRent, double expenses) {
        double d = costObject/(monthlyRent * 12 - expenses);
        String [] s = String.valueOf(d).split("\\.");
        paybackPeriod = s[0] + " year, " + s[1].substring(0,1) + " month";
    }
}
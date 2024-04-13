package MainProgram;

import java.util.HashMap;
import java.util.Map;

/*The SalesReport class handles storing order information to be ready for display
* at the end of the day*/

public class SalesReport {
    HashMap<String, Integer> FoodQuantity = new HashMap<String, Integer>() {{
        put("Burrito", 0);
        put("Fries", 0);
        put("Soda", 0);
    }};

    HashMap<String, String> FoodMap = new HashMap<String, String>() {{
        put("01", "Burrito");
        put("02", "Fries");
        put("03", "Soda");
    }};

    HashMap<String, Double> FoodPrice = new HashMap<String, Double>() {{
        put("Burrito", 0.0);
        put("Fries", 0.0);
        put("Soda", 0.0);
    }};
    private double totalSales;
    private int leftoverFries = 0;
    private int mealSold = 0;

    public SalesReport () { }

    /*The method to show the Sales Report*/
    public void showReport() { reportStruct(); }

    /*The method to organize the items to be ready for display*/
    private void reportStruct() {
        int totalFood = 0;

        System.out.printf("%s %n%S %n%s %n", "========================================", "sales report", "========================================");
        System.out.printf("%s: %d %n%s:%n", "Unsold Serves of Fries", leftoverFries, "Total Sales");

        for (Map.Entry<String, String> entry : FoodMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.printf("%-3s %-15s %d %-5s $%.2f %n", key, value, FoodQuantity.get(value), "qty", FoodPrice.get(value));
            totalFood += FoodQuantity.get(value);
        }

        System.out.printf("%-19s %d %-5s ($%.2f)%n", "Meal Set Discount", mealSold, "qty" ,(mealSold*3.0), "--------------------------------");
        System.out.printf("%-19s %d %-5s $%.2f%n", " ", totalFood, " ", totalSales);
    }

    /*Getter and Setter for Sales, Meals, and Remaining Fries*/
    public void setLeftoverFries(int friesQuantity) { this.leftoverFries = friesQuantity; }
    public void setTotalSales(double salesPrice) { totalSales += salesPrice; }
    public double getTotalSales() { return totalSales; }
    public void setTotalMeals(int mealQuantity) { this.mealSold += mealQuantity; }
    public int getFoodQuantity(String key) { return FoodQuantity.get(key); }
    public double getFoodPrice(String key) { return FoodPrice.get(key); }

    /*The method to set the number of food sold during Orders*/
    public void setFoodQuantity(String key, int quantity) {
        if (FoodQuantity.get(key) > 0){
            int newQty = FoodQuantity.get(key) + quantity;
            FoodQuantity.put(key, newQty);
        }
        else {
            FoodQuantity.put(key, quantity);
        }
    }

    /*The method to keep up the price sum of food sold*/
    public void setFoodPrice(String key, double price) {
        if (FoodPrice.get(key) > 0){
            double newPrice = FoodPrice.get(key) + price;
            FoodPrice.put(key, newPrice);
        }
        else {
            FoodPrice.put(key, price);
        }
    }

    /*The method to change the price sum of a certain item*/
    public void changeFoodPrice(String key, double price){
        FoodPrice.put(key, price);
    }

    /*The method to handle recalculating total sales when there's a price change*/
    public void recalculateTotalSales(String key, double price){
        totalSales = 0;
        int foodQuantity = FoodQuantity.get(key);
        double newPrice = foodQuantity*price;
        changeFoodPrice(key, newPrice);
        for (Map.Entry<String, String> entry : FoodMap.entrySet()) {
            String value = entry.getValue();
            totalSales += FoodPrice.get(value);
        }
        totalSales -= (mealSold*3);
    }
}

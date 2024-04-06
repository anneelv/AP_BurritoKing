import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
//    private ArrayList<Food> foodList = new ArrayList<Food>();
    private double totalSales;
    private int leftoverFries = 0;
    private int mealSold = 0;
    private int totalFood = 0;

    public SalesReport () { }

    public void showReport() { reportStruct(); }

    private void reportStruct() {
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

//        System.out.println(FoodMap);
//        System.out.println(FoodQuantity);
//        System.out.println(FoodPrice);
    }

    public void setLeftoverFries(int friesQuantity) { this.leftoverFries = friesQuantity; }

    public void setTotalSales(double salesPrice) { totalSales += salesPrice; }

    public void setTotalMeals(int mealQuantity) { this.mealSold += mealQuantity; }

    public void setFoodQuantity(String key, int quantity) {
        if (FoodQuantity.get(key) > 0){
            int newQty = FoodQuantity.get(key) + quantity;
            FoodQuantity.put(key, newQty);
        }
        else {
            FoodQuantity.put(key, quantity);
        }
    }

    public void setFoodPrice(String key, double price) {
        if (FoodPrice.get(key) > 0){
            double newPrice = FoodPrice.get(key) + price;
            FoodPrice.put(key, newPrice);
        }
        else {
            FoodPrice.put(key, price);
        }
    }
}
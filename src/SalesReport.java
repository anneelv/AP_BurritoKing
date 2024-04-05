import java.util.*;

public class SalesReport {
    private ArrayList<Food> foodList = new ArrayList<Food>();
    private double totalSales;
    private int leftoverFries = 0;
    private int mealSold = 0;

    public SalesReport () {

    }

    public void showReport() {
        System.out.printf("This is the sales Report");
    }

    public void setLeftoverFries(int friesQuantity) {
        this.leftoverFries = friesQuantity;
    }

    public void setTotalSales(double salesPrice) {
        totalSales += salesPrice;
    }

    public void setTotalMeals(int mealQuantity) {
        this.mealSold = mealQuantity;
    }
}

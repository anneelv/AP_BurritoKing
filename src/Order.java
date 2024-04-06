import java.util.*;

public class Order {
    private Scanner input = new Scanner(System.in);
    private double totalPrice = 0;
    private static int remainingFriesQuantity = 0;
    private int mealCount = 0;
    ArrayList<Food> foodOrder = new ArrayList<Food>();
    private SalesReport sales;

    public Order(SalesReport sales) {
        this.sales = sales;
    }

    public void runMenu() {
        mainOrder();
    }

    private void displayMenu() {
        System.out.printf("%n %s %n %s %n %s %n %s %n %s %n %s %n", "> Select the food item", "1. Burrito", "2. Fries", "3. Soda", "4. Meal (1 Burrito, 1 Fries, and 1 Soda)", "5. No more");
    }

    private void mainOrder() {
        boolean exit = false;
        String choice;
        do {
            displayMenu();
            System.out.print("Please enter your choice: ");
            choice = input.nextLine();

//			Validating empty input from the user
            if (choice.isEmpty()) {
                System.out.printf("Please select a valid menu option. %n");
                continue;
            }

            switch (choice) {
                case "1":
                    addMenu("Burrito");
                    break;
                case "2":
                    addMenu("Fries");
                    break;
                case "3":
                    addMenu("Soda");
                    break;
                case "4":
                    addMenu("Meal");
                    break;
                case "5":
                    getFinalOrder();
                    payOrder();
                    setTotalWaitTime();
                    clearCurrentOrder();
                    System.out.printf("%n %n");
//                    System.out.println(" ");
                    exit = true;
                    break;
                default:
                    System.out.printf("%s %n", "The option that you input is invalid!");
                    break;
            }
        } while (!exit);
    }

    private void addMenu(String food) {
        int quantity = 0;
        boolean numeric = false;
        String orderInput;

        do {
            System.out.printf("How many %s would you like to buy: ", food);
            orderInput = input.nextLine();

            try {
                quantity = Integer.parseInt(orderInput);
                numeric = true;
            } catch (NumberFormatException ignored) {

            }

            if (!orderInput.isEmpty() && numeric && quantity > 0) {
                switch (food) {
                    case "Burrito":
                        Burrito burrito = new Burrito("Burrito", quantity);
                        addMenuToArray(burrito, quantity);
                        break;
                    case "Fries":
                        Fries fries = new Fries("Fries", quantity);
                        addMenuToArray(fries, quantity);
                        break;
                    case "Soda":
                        Soda soda = new Soda("Soda", quantity);
                        addMenuToArray(soda, quantity);
                        break;
                    case "Meal":
                        orderMeal(quantity);
                        break;
                }
            } else {
                System.out.printf("%s! %n", "Please enter a valid number");
            }

        } while (!numeric);
    }

    private void addMenuToArray(Food food, int quantity) {
        foodOrder.add(food);
        totalPrice = totalPrice + food.getPrice() * quantity;
        System.out.printf("%d %s has been successfully added to the order!%n", quantity, food.getName());
    }

    private void orderMeal(int quantity) {
        Burrito burrito = new Burrito("Burrito(MS)", quantity);
        foodOrder.add(burrito);

        Fries fries = new Fries("Fries(MS)", quantity);
        foodOrder.add(fries);

        Soda soda = new Soda("Soda(MS)", quantity);
        foodOrder.add(soda);
        totalPrice = totalPrice + ((burrito.getPrice() * quantity + fries.getPrice() * quantity + soda.getPrice() * quantity) - (3 * quantity));
        mealCount += quantity;
        sales.setTotalMeals(quantity);
        System.out.printf("%d Meal Set has been successfully added to the order! %n", quantity);
    }

    private void getFinalOrder() {
        System.out.println("Currently you have:");
        for (Food food : foodOrder) {
            System.out.printf("+ %-15s", food.getName());
            System.out.printf("%s %-5s", food.getOrderQuantity(), "qty");
            System.out.printf("@%.2f %n", food.getPrice());
        }
        if (mealCount > 0)
            System.out.printf("Total price is $%.2f, you get discount for ordering %d Meal Set %n", totalPrice, mealCount);
        else
            System.out.printf("Total price is $%.2f %n", totalPrice);
    }

    private void payOrder() {
        String moneyInput;
        boolean numeric = false;
        boolean paid = false;
        double money = 0;

        do {
            System.out.print("Please enter money: ");
            moneyInput = input.nextLine();
            try {
                money = Double.parseDouble(moneyInput);
                numeric = true;
            } catch (NumberFormatException e) {
                numeric = false;
            }

            if (!moneyInput.isEmpty() && numeric) {
                paid = moneyChange(money);
            }

        } while (!numeric || !paid);
    }

    private boolean moneyChange(double money) {
        if (totalPrice > money) {
            System.out.println("Sorry that's not enough to pay for order");
            System.out.println(" ");
            return false;
        } else {
            System.out.println("Change returned $" + (money - totalPrice));
            System.out.println(" ");
            return true;
        }
    }

    private void setTotalWaitTime() {
        int waitTime = 0;

        int burritoTime = 0;
        int cookedBurritoPerBatch = 0;
        int burritoPrepTime = 0;
        int burritoQuantity = 0;

        int friesTime = 0;
        int cookedFriesPerBatch = 0;
        int friesPrepTime = 0;
        int friesQuantity = 0;

//		Counting total burrito and fries ordered
        for (Food food : foodOrder) {
            if (food.getName().contains("Burrito")) {
                burritoQuantity = burritoQuantity + food.getOrderQuantity();
                cookedBurritoPerBatch = food.getQuantityPerBatch();
                burritoPrepTime = food.getPrepTime();

            } else if (food.getName().contains("Fries")) {
                friesQuantity = friesQuantity + food.getOrderQuantity();
                cookedFriesPerBatch = food.getQuantityPerBatch();
                friesPrepTime = food.getPrepTime();
            }
        }

        burritoTime = burritoCookingTime(burritoQuantity, cookedBurritoPerBatch, burritoPrepTime);
        friesTime = friesCookingTime(friesQuantity, cookedFriesPerBatch, friesPrepTime);

        if (burritoTime > friesTime)
            waitTime = burritoTime;
        else
            waitTime = friesTime;

        System.out.println("There are " + remainingFriesQuantity + " serve(s) of Fries left for next order");
        System.out.println("Please wait for " + waitTime + " minutes");
    }

    private int burritoCookingTime(int burritoQuantity, int cookedBurritoPerBatch, int burritoPrepTime) {
        int cookingTime = 0;
        double tempTime = 0;

        tempTime = (double) burritoQuantity / (double) cookedBurritoPerBatch;
        cookingTime = (int) Math.ceil(tempTime) * burritoPrepTime;

        return cookingTime;
    }

    private int friesCookingTime(int friesQuantity, int cookedFriesPerBatch, int friesPrepTime) {
        int cookingTime = 0;
        double tempTime = 0;

        if (remainingFriesQuantity >= friesQuantity) {
            remainingFriesQuantity = remainingFriesQuantity - friesQuantity;
        } else {
            tempTime = (double) friesQuantity / (double) cookedFriesPerBatch;
            cookingTime = (int) Math.ceil(tempTime) * friesPrepTime;
            remainingFriesQuantity = remainingFriesQuantity + ((cookingTime / 8) * 5) - friesQuantity;
        }

        return cookingTime;
    }

    private void clearCurrentOrder() {
        sales.setLeftoverFries(remainingFriesQuantity);
        sales.setTotalSales(totalPrice);
        setupInfoForSales();
        foodOrder.clear();
        totalPrice = 0;
        mealCount = 0;
    }

    private void setupInfoForSales() {
        int burritoQuantity = 0;
        int friesQuantity = 0;
        int sodaQuantity = 0;

        double burritoPrice = 0;
        double friesPrice = 0;
        double sodaPrice = 0;

        for (Food food : foodOrder) {
            if (food.getName().contains("Burrito")) {
                burritoQuantity += food.getOrderQuantity();
                burritoPrice = food.getPrice();
            }
            else if (food.getName().contains("Fries")) {
                friesQuantity += food.getOrderQuantity();
                friesPrice = food.getPrice();
            }
            else if (food.getName().contains("Soda")) {
                sodaQuantity += food.getOrderQuantity();
                sodaPrice = food.getPrice();
            }
            else {
                System.out.println("One of the entry has error!");
            }
        }
        sendFoodQuantityAndPriceToSales("Burrito", burritoQuantity, (burritoPrice*burritoQuantity));
        sendFoodQuantityAndPriceToSales("Fries", friesQuantity, (friesPrice*friesQuantity));
        sendFoodQuantityAndPriceToSales("Soda", sodaQuantity, (sodaPrice*sodaQuantity));
    }

    private void sendFoodQuantityAndPriceToSales(String foodName, int quantity, double price) {
        sales.setFoodQuantity(foodName, quantity);
        sales.setFoodPrice(foodName, price);
    }

}
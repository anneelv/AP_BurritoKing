import java.util.*;

public class Order {
    static ArrayList<Food> foodOrder = new ArrayList<Food>();
    static double totalPrice = 0;
    static int waitTime = 0;
    static int remainingFriesQuantity = 0;
    static int mealCount = 0;

    static Scanner input = new Scanner(System.in);

    public Order() {

    }

    //	Running the display menu for ordering food
    public static void OrderMenu() {
        boolean exit = false;
        String choice = "";

        do {
            displayMenu();
            System.out.print("Please enter your choice: ");
            choice = input.nextLine();

//			Validating empty input from the user
            if (choice.isEmpty()) {
                System.out.println("Please select a valid menu option.");
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
                    System.out.println(" ");
                    System.out.println(" ");
                    exit = true;
                    break;
                default:
                    System.out.println("The option that you input is invalid!");
                    break;
            }
        } while (!exit);
    }

    public static void displayMenu() {
        System.out.println("> Select the food item");
        System.out.println("1. Burrito");
        System.out.println("2. Fries");
        System.out.println("3. Soda");
        System.out.println("4. Meal (1 Burrito, 1 Fries, and 1 Soda)");
        System.out.println("5. No more");
    }

    public static void addMenu(String food) {
        int quantity = 0;
        boolean numeric = false;
        String orderInput = "";

        do {
            System.out.printf("How many %s would you like to buy: ", food);
            orderInput = input.nextLine();
            try {
                quantity = Integer.parseInt(orderInput);
                numeric = true;
            } catch (NumberFormatException e) {
                numeric = false;
            }

            if (!orderInput.isEmpty() && numeric == true && quantity > 0) {
                switch (food) {
                    case "Burrito":
                        Food Burrito = new Food(food, quantity);
                        foodOrder.add(Burrito);
                        totalPrice = totalPrice + Burrito.price*quantity;
                        System.out.printf("%s Burrito has been successfully added to the order!%n", orderInput);
                        break;
                    case "Fries":
                        Food Fries = new Food(food, quantity);
                        foodOrder.add(Fries);
                        totalPrice = totalPrice + Fries.price*quantity;
                        System.out.printf("%s Fries has been successfully added to the order!%n", orderInput);
                        break;
                    case "Soda":
                        Food Soda = new Food(food, quantity);
                        foodOrder.add(Soda);
                        totalPrice = totalPrice + Soda.price*quantity;
                        System.out.printf("%s Soda has been successfully added to the order!%n", orderInput);
                        break;
                    case "Meal":
                        orderMeal("Burrito", "Fries", "Soda", quantity);
                        break;
                }
            }
            else {
                System.out.println("Please enter a valid number");
                System.out.println(" ");
            }
        } while (numeric == false);
    }

    public static void orderMeal(String food1, String food2, String food3, int quantity) {
        for (int x = 1 ; x <= quantity; x++) {
            Food Burrito = new Food(food1, 1);
            foodOrder.add(Burrito);

            Food Fries = new Food(food2, 1);
            foodOrder.add(Fries);

            Food Soda = new Food(food3, 1);
            foodOrder.add(Soda);

            totalPrice = totalPrice + (Burrito.price - 1) + (Fries.price - 1) + (Soda.price - 1) ;
        }
        mealCount = quantity;
        System.out.println(quantity + " Meal Set has been successfully added to the order!");

    }

    public static void getFinalOrder() {
        System.out.println("Currently you have:");
        for (int i = 0; i < foodOrder.size(); i++) {
            System.out.printf("+ %-8s", foodOrder.get(i).name);
            System.out.print(foodOrder.get(i).orderQuantity + " qty");
            System.out.println("   @" + foodOrder.get(i).price);
        }
        if (mealCount > 0)
            System.out.printf("Total price is $%.2f, you get discount for ordering " + mealCount + " Meal Set %n", totalPrice);
        else
            System.out.printf("Total price is $%.2f %n", totalPrice);
    }

    public static void payOrder() {
        String moneyInput = "";
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

            if (!moneyInput.isEmpty() && numeric == true) {
                paid = moneyChange(money);
            }
            else
                paid = false;

        } while (numeric == false || paid == false);

    }

    public static boolean moneyChange(double money) {
        if(totalPrice > money) {
            System.out.println("Sorry that's not enough to pay for order");
            System.out.println(" ");
            return false;
        }
        else {
            System.out.println("Change returned $" + (money - totalPrice));
            System.out.println(" ");
            return true;
        }
    }

    public static void setTotalWaitTime() {
        int burritoTime = 0;
        int cookedBurritoPerBatch = 0;
        int burritoPrepTime = 0;
        int burritoQuantity = 0;

        int friesTime = 0;
        int cookedFriesPerBatch = 0;
        int friesPrepTime = 0;
        int friesQuantity = 0;

//		Counting total burrito and fries ordered
        for (int i = 0; i < foodOrder.size(); i++) {
            if (foodOrder.get(i).name.equalsIgnoreCase("Burrito")) {
                burritoQuantity = burritoQuantity + foodOrder.get(i).orderQuantity;
                cookedBurritoPerBatch = foodOrder.get(i).quantityPerBatch;
                burritoPrepTime = foodOrder.get(i).prepTime;

            }
            else if (foodOrder.get(i).name.equalsIgnoreCase("Fries")) {
                friesQuantity = friesQuantity + foodOrder.get(i).orderQuantity;
                cookedFriesPerBatch = foodOrder.get(i).quantityPerBatch;
                friesPrepTime = foodOrder.get(i).prepTime;
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

    public static int burritoCookingTime(int burritoQuantity, int cookedBurritoPerBatch, int burritoPrepTime) {
        int cookingTime = 0;
        double tempTime = 0;

        tempTime = (double)burritoQuantity / (double)cookedBurritoPerBatch;
        cookingTime = (int)Math.ceil(tempTime) * burritoPrepTime;

        return cookingTime;
    }

    public static int friesCookingTime(int friesQuantity, int cookedFriesPerBatch, int friesPrepTime) {
        int cookingTime = 0;
        double tempTime = 0;

        if (remainingFriesQuantity >= friesQuantity) {
            remainingFriesQuantity = remainingFriesQuantity - friesQuantity;
            cookingTime = 0;
        }
        else {
            tempTime = (double)friesQuantity / (double)cookedFriesPerBatch;
            cookingTime = (int)Math.ceil(tempTime) * friesPrepTime;
            remainingFriesQuantity = remainingFriesQuantity + ((cookingTime/8)*5) - friesQuantity;
        }

        return cookingTime;
    }


    public static void clearCurrentOrder() {
        foodOrder.clear();
        totalPrice = 0;
    }
}

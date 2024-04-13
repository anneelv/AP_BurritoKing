package MainProgram;

import java.util.*;
import Exceptions.*;

/*The Order class handles food ordering, cooking time, remaining fries serves,
* order payment, and also price change for foods. These orders will be working
* closely with the SalesReport to track each customer order*/

public class Order {
    private double totalPrice = 0;
    private static int remainingFriesQuantity = 0;
    private int mealCount = 0;
    ArrayList<Food> foodOrder = new ArrayList<>();
    private SalesReport sales;
    private Validation validation;
    private Burrito burrito;
    private Fries fries;
    private Soda soda;

    public Order(SalesReport sales, Validation validation) {
        this.sales = sales;
        this.validation = validation;
        burrito = new Burrito("Burrito");
        fries = new Fries("Fries");
        soda = new Soda("Soda");
    }

    /*The method to run the food ordering program*/
    public void runMenu() throws InvalidOptionException, EmptyUserInputException { mainOrder(); }

    /*The method to run changing prices of food*/
    public void runChangePriceMenu() { changePriceMenu(); }

    /*The method to display constant initial menu options lines*/
    private void displayMenu() {
        System.out.printf("%n %s %n %s %n %s %n %s %n %s %n %s %n", "> Select the food item", "1. Burrito", "2. Fries", "3. Soda", "4. Meal (1 Burrito, 1 Fries, and 1 Soda)", "5. No more");
    }

    /*The method to keep the Order option ongoing based on user input*/
    public void mainOrder() throws InvalidOptionException, EmptyUserInputException {
        boolean exit = false;
        String choice;
        do {
            displayMenu();
            System.out.print("Please enter your choice: ");
            try{
                choice = readUserInput();

                // Validating user input and continue with the loop if the input is empty
                validation.checkStringInput(choice);

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
                        exit = true;
                        break;
                    default:
                        throw new InvalidOptionException("The option that you input is invalid!");
                }
            } catch (EmptyUserInputException | InvalidOptionException e) {
                System.out.println(e.getMessage());
            }
        } while (!exit);
    }

    /*The method to set up the quantity of food ordered*/
    public void addMenu(String food) throws NotANumberException {
        int quantity;
        boolean numeric = false;
        boolean added = false;
        String orderInput;

        do {
            System.out.printf("How many %s would you like to buy: ", food);
            try {
                orderInput = readUserInput();

                // Validating user input and continue with the loop if the input is empty
                validation.checkStringInput(orderInput);

                // Validating user input and continue with the loop
                // if the input is not an integer number
                quantity = Integer.parseInt(orderInput);
                numeric = true;

                // Validating user input and continue with the loop if the input is not a valid number
                validation.checkNumberInput(quantity);

                switch (food) {
                    case "Burrito":
                        burrito.setOrderQuantity(burrito.getOrderQuantity() + quantity);
                        addMenuToArray(burrito, quantity);
                        added = true;
                        break;
                    case "Fries":
                        fries.setOrderQuantity(fries.getOrderQuantity() +quantity);
                        addMenuToArray(fries, quantity);
                        added = true;
                        break;
                    case "Soda":
                        soda.setOrderQuantity(soda.getOrderQuantity() +quantity);
                        addMenuToArray(soda, quantity);
                        added = true;
                        break;
                    case "Meal":
                        orderMeal(quantity);
                        added = true;
                        break;
                }
            } catch (NotANumberException | EmptyUserInputException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Please input a number!");
            }
        } while (!numeric || !added);
    }

    /*The method to add a food to the current order array list*/
    public void addMenuToArray(Food food, int quantity) {
        foodOrder.add(food);
        setTotalPrice(getTotalPrice() + food.getPrice() * quantity);
        System.out.printf("%d %s has been successfully added to the order!%n", quantity, food.getName());
    }

    /*The method to order a set of meal*/
    public void orderMeal(int quantity) {
        burrito.setOrderQuantity(burrito.getOrderQuantity() + quantity);
        fries.setOrderQuantity(fries.getOrderQuantity() +quantity);
        soda.setOrderQuantity(soda.getOrderQuantity() +quantity);

        // Add the food to the order array list if it's not inside the list yet
        if(!(foodOrder.contains(burrito))) {
            foodOrder.add(burrito);
        }
        if (!(foodOrder.contains(fries))) {
            foodOrder.add(fries);
        }
        if (!(foodOrder.contains(soda))) {
            foodOrder.add(soda);
        }

        setTotalPrice(getTotalPrice() + ((burrito.getPrice() * quantity + fries.getPrice() * quantity + soda.getPrice() * quantity) - (3 * quantity)));
        mealCount += quantity;
        sales.setTotalMeals(quantity);
        System.out.printf("%d Meal Set has been successfully added to the order! %n", quantity);
    }


    /*The method to organize the display to show ordered food*/
    public void getFinalOrder() {
        System.out.println("Currently you have:");
        for (Food food : foodOrder) {
            System.out.printf("+ %-15s", food.getName());
            System.out.printf("%s %-5s", food.getOrderQuantity(), "qty");
            System.out.printf("@%.2f %n", food.getPrice());
        }

        // Display a different line based on the number of Meal set ordered
        if (mealCount > 0)
            System.out.printf("Total price is $%.2f, you get discount for ordering %d Meal Set %n", getTotalPrice(), mealCount);
        else
            System.out.printf("Total price is $%.2f %n", getTotalPrice());
    }

    /*The method to handle the money input from user to pay the order*/
    private void payOrder() {
        String moneyInput;
        boolean numeric = false;
        boolean paid = false;
        double money;

        do {
            System.out.print("Please enter money: ");
            try {
                moneyInput = readUserInput();

                // Validating user input and continue with the loop if the input is empty
                validation.checkStringInput(moneyInput);

                // Validating user input and continue with the loop
                // if the input is not an integer number
                money = Double.parseDouble(moneyInput);
                numeric = true;

                // Validating user input and continue with the loop if the input is not a valid number
                validation.checkNumberInput(money);

                paid = moneyChange(money);
            } catch (NotANumberException | EmptyUserInputException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Please input a number!");
            }
        } while (!numeric || !paid);
    }

    /*The method to handle any change needed for the user*/
    public boolean moneyChange(double money) {
        if (getTotalPrice() > money) {
            System.out.printf("Sorry that's not enough to pay for order%n");
            return false;
        } else {
            System.out.printf("Change returned $%.2f%n", (money - getTotalPrice()));
            return true;
        }
    }

    public void setTotalPrice(double price) {
        this.totalPrice = price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    /*The method to set user waiting time*/
    private void setTotalWaitTime() {
        int waitTime;

        int burritoTime;
        int cookedBurritoPerBatch = 0;
        int burritoPrepTime = 0;
        int burritoQuantity = 0;

        int friesTime;
        int cookedFriesPerBatch = 0;
        int friesPrepTime = 0;
        int friesQuantity = 0;

        // Looping through the array list to get the number of food ordered
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

        // Getting the cooking time for each food (burrito and fries)
        burritoTime = burritoCookingTime(burritoQuantity, cookedBurritoPerBatch, burritoPrepTime);
        friesTime = friesCookingTime(friesQuantity, cookedFriesPerBatch, friesPrepTime);
        waitTime = calculateWaitingTime(burritoTime, friesTime);

        System.out.printf("There are %d serve(s) of Fries left for next %n", remainingFriesQuantity);
        System.out.printf("Please wait for %d minutes", waitTime);
    }

    /*The method to compare cooking time between Burrito and Fries*/
    public int calculateWaitingTime(int burritoTime, int friesTime) {
        int waitTime;
        if (burritoTime > friesTime)
            waitTime = burritoTime;
        else
            waitTime = friesTime;
        return waitTime;
    }

    /*The method to calculate time needed to cook Burrito based on the order quantity*/
    public int burritoCookingTime(int burritoQuantity, int cookedBurritoPerBatch, int burritoPrepTime) {
        int cookingTime;
        double tempTime;

        tempTime = (double) burritoQuantity / (double) cookedBurritoPerBatch;
        cookingTime = (int) Math.ceil(tempTime) * burritoPrepTime;

        return cookingTime;
    }

    /*The method to calculate time needed to cook Fries based on the order quantity and remaining Fries*/
    public int friesCookingTime(int friesQuantity, int cookedFriesPerBatch, int friesPrepTime) {
        int cookingTime = 0;
        double tempTime;
        int tempRemainingFriesQuantity = getRemainingFriesQuantity();
        int friesNeeded;

        // If there are remaining fries, then use the remaining fries first
        if (tempRemainingFriesQuantity >= friesQuantity) {
            tempRemainingFriesQuantity = tempRemainingFriesQuantity - friesQuantity;
            setRemainingFriesQuantity(tempRemainingFriesQuantity);
        } else {
            friesNeeded = friesQuantity - tempRemainingFriesQuantity;
            tempTime = (double) friesNeeded / (double) cookedFriesPerBatch;
            cookingTime = (int) Math.ceil(tempTime) * friesPrepTime;
            tempRemainingFriesQuantity = tempRemainingFriesQuantity + ((cookingTime / 8) * 5) - friesQuantity;
            setRemainingFriesQuantity(tempRemainingFriesQuantity);
        }
        return cookingTime;
    }

    public void setRemainingFriesQuantity(int remaining){
        remainingFriesQuantity = remaining;
    }

    public int getRemainingFriesQuantity() {
        return remainingFriesQuantity;
    }

    /*The method to send the details of current order to SalesReport and
    * Reset current order to empty and zero for the next order*/
    private void clearCurrentOrder() {
        sales.setLeftoverFries(remainingFriesQuantity);
        sales.setTotalSales(totalPrice);
        setupInfoForSales();
        foodOrder.clear();
        burrito.setOrderQuantity(0);
        fries.setOrderQuantity(0);
        soda.setOrderQuantity(0);
        totalPrice = 0;
        mealCount = 0;
    }

    /*The method to organize info on the current order before sending the summary of it to SalesReport*/
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
        }
        sendFoodQuantityAndPriceToSales("Burrito", burritoQuantity, (burritoPrice*burritoQuantity));
        sendFoodQuantityAndPriceToSales("Fries", friesQuantity, (friesPrice*friesQuantity));
        sendFoodQuantityAndPriceToSales("Soda", sodaQuantity, (sodaPrice*sodaQuantity));
    }

    /*The method to take the summed information and store it inside the SalesReport*/
    private void sendFoodQuantityAndPriceToSales(String foodName, int quantity, double price) {
        sales.setFoodQuantity(foodName, quantity);
        sales.setFoodPrice(foodName, price);
    }

    /*The method to read user input*/
    public static String readUserInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /*The method to display constant change price menu options lines*/
    private void displayChangePriceMenu() {
        System.out.printf("%n %s %n %s %n %s %n %s %n %s %n", "> Select the food item", "1. Burrito", "2. Fries", "3. Soda", "4. No more");
    }

    /*The method to display constant initial menu options lines*/
    private void changePriceMenu() {
        boolean exit = false;
        String choice;
        do {
            displayChangePriceMenu();
            System.out.print("Please choose a food to change the price: ");
            try {
                choice = readUserInput();

                // Validating user input and continue with the loop if the input is empty
                validation.checkStringInput(choice);

                switch (choice) {
                    case "1":
                        changePrice("Burrito");
                        break;
                    case "2":
                        changePrice("Fries");
                        break;
                    case "3":
                        changePrice("Soda");
                        break;
                    case "4":
                        System.out.printf("%n %n");
                        exit = true;
                        break;
                    default:
                        throw new InvalidOptionException("The option that you input is invalid!");
                }
            } catch (NotANumberException | EmptyUserInputException | InvalidOptionException e){
                System.out.println(e.getMessage());
            }
        } while (!exit);
    }

    /*The method to take the new price for the chosen food*/
    private void changePrice(String food){
        double newPrice;
        boolean numeric = false;
        boolean priceChanged = false;
        String priceInput;
        do {
            System.out.printf("Please input the new price for %s: ", food);
            try {
                priceInput = readUserInput();

                // Validating user input and continue with the loop if the input is empty
                validation.checkStringInput(priceInput);

                // Validating user input and continue with the loop
                // if the input is not an integer number
                newPrice = Double.parseDouble(priceInput);
                numeric = true;

                // Validating user input and continue with the loop if the input is not a valid number
                validation.checkNumberInput(newPrice);

                switch (food) {
                    case "Burrito":
                        burrito.setPrice(newPrice);
                        priceChanged = true;
                        break;
                    case "Fries":
                        fries.setPrice(newPrice);
                        priceChanged = true;
                        break;
                    case "Soda":
                        soda.setPrice(newPrice);
                        priceChanged = true;
                        break;
                }

                // Update the prices in the Sales Report
                sales.recalculateTotalSales(food, newPrice);
            } catch (NotANumberException | EmptyUserInputException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Please input a number!");
            }
        } while (!numeric || !priceChanged);
    }
}
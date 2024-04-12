import java.util.*;

public class Order {
    private Scanner input = new Scanner(System.in);
    private double totalPrice = 0;
    private static int remainingFriesQuantity = 0;
    private int mealCount = 0;
    ArrayList<Food> foodOrder = new ArrayList<Food>();
    private SalesReport sales;
    private Validation validation;
    private Burrito burrito = new Burrito("Burrito");
    private Fries fries = new Fries("Fries");
    private Soda soda = new Soda("Soda");

    public Order(SalesReport sales, Validation validation) {
        this.sales = sales;
        this.validation = validation;
    }

    public void runMenu() throws InvalidOptionException, EmptyUserInputException { mainOrder(); }

    public void runChangePriceMenu() { changePriceMenu(); }

    private void displayMenu() {
        System.out.printf("%n %s %n %s %n %s %n %s %n %s %n %s %n", "> Select the food item", "1. Burrito", "2. Fries", "3. Soda", "4. Meal (1 Burrito, 1 Fries, and 1 Soda)", "5. No more");
    }

    public void mainOrder() throws InvalidOptionException, EmptyUserInputException {
        boolean exit = false;
        String choice;
        do {
            displayMenu();
            System.out.print("Please enter your choice: ");
            try{
                choice = input.nextLine();
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

    public void addMenu(String food) throws NotANumberException {
        int quantity = 0;
        boolean numeric = false;
        boolean added = false;
        String orderInput;

        do {
            System.out.printf("How many %s would you like to buy: ", food);
            try {
                orderInput = input.nextLine();
                validation.checkStringInput(orderInput);
                quantity = Integer.parseInt(orderInput);
                numeric = true;
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

//    TODO: UNIT TEST?
    public void addMenuToArray(Food food, int quantity) {
        foodOrder.add(food);
        setTotalPrice(getTotalPrice() + food.getPrice() * quantity);
//        totalPrice = totalPrice + food.getPrice() * quantity;
        System.out.printf("%d %s has been successfully added to the order!%n", quantity, food.getName());
    }

    public void orderMeal(int quantity) {
        burrito.setOrderQuantity(burrito.getOrderQuantity() + quantity);
        fries.setOrderQuantity(fries.getOrderQuantity() +quantity);
        soda.setOrderQuantity(soda.getOrderQuantity() +quantity);

        setTotalPrice(getTotalPrice() + ((burrito.getPrice() * quantity + fries.getPrice() * quantity + soda.getPrice() * quantity) - (3 * quantity)));
//        totalPrice = totalPrice + ((burrito.getPrice() * quantity + fries.getPrice() * quantity + soda.getPrice() * quantity) - (3 * quantity));
        mealCount += quantity;
        sales.setTotalMeals(quantity);
        System.out.printf("%d Meal Set has been successfully added to the order! %n", quantity);
    }

    public void getFinalOrder() {
        System.out.println("Currently you have:");
        for (Food food : foodOrder) {
            System.out.printf("+ %-15s", food.getName());
            System.out.printf("%s %-5s", food.getOrderQuantity(), "qty");
            System.out.printf("@%.2f %n", food.getPrice());
        }
        if (mealCount > 0)
            System.out.printf("Total price is $%.2f, you get discount for ordering %d Meal Set %n", getTotalPrice(), mealCount);
        else
            System.out.printf("Total price is $%.2f %n", getTotalPrice());
    }

    private void payOrder() {
        String moneyInput;
        boolean numeric = false;
        boolean paid = false;
        double money = 0;

        do {
            System.out.print("Please enter money: ");
            try {
                moneyInput = input.nextLine();
                validation.checkStringInput(moneyInput);
                money = Double.parseDouble(moneyInput);
                validation.checkNumberInput(money);
                numeric = true;
                paid = moneyChange(money);
            } catch (NotANumberException | EmptyUserInputException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Please input a number!");
            }
        } while (!numeric || !paid);
    }

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

        waitTime = calculateWaitingTime(burritoTime, friesTime);

        System.out.printf("There are %d serve(s) of Fries left for next %n", remainingFriesQuantity);
        System.out.printf("Please wait for %d minutes", waitTime);
    }

    public int calculateWaitingTime(int burritoTime, int friesTime) {
        int waitTime = 0;
        if (burritoTime > friesTime)
            waitTime = burritoTime;
        else
            waitTime = friesTime;

        return waitTime;
    }

    public int burritoCookingTime(int burritoQuantity, int cookedBurritoPerBatch, int burritoPrepTime) {
        int cookingTime = 0;
        double tempTime = 0;

        tempTime = (double) burritoQuantity / (double) cookedBurritoPerBatch;
        cookingTime = (int) Math.ceil(tempTime) * burritoPrepTime;

        return cookingTime;
    }

    public int friesCookingTime(int friesQuantity, int cookedFriesPerBatch, int friesPrepTime) {
        int cookingTime = 0;
        double tempTime = 0;
        int tempRemainingFriesQuantity = getRemainingFriesQuantity();
        int friesNeeded = 0;

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

//    Send the details of curren order to SalesReport and
//    Reset current order to empty and zero for the next order
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


    private void sendFoodQuantityAndPriceToSales(String foodName, int quantity, double price) {
        sales.setFoodQuantity(foodName, quantity);
        sales.setFoodPrice(foodName, price);
    }

    private void displayChangePriceMenu() {
        System.out.printf("%n %s %n %s %n %s %n %s %n %s %n", "> Select the food item", "1. Burrito", "2. Fries", "3. Soda", "4. No more");
    }

    private void changePriceMenu() {
        boolean exit = false;
        String choice;
        do {
            displayChangePriceMenu();
            System.out.print("Please choose a food to change the price: ");
            try {
                choice = input.nextLine();
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

    private void changePrice(String food){
        double newPrice = 0;
        boolean numeric = false;
        boolean priceChanged = false;
        String priceInput;
        do {
            System.out.printf("Please input the new price for %s: ", food);
            try {
                priceInput = input.nextLine();
                validation.checkStringInput(priceInput);
                newPrice = Double.parseDouble(priceInput);
                validation.checkNumberInput(priceInput);
                numeric = true;

                switch (food) {
                    case "Burrito":
                        Burrito burrito = new Burrito("Burrito");
                        burrito.setPrice(newPrice);
                        priceChanged = true;
                        break;
                    case "Fries":
                        Fries fries = new Fries("Fries");
                        fries.setPrice(newPrice);
                        priceChanged = true;
                        break;
                    case "Soda":
                        Soda soda = new Soda("Soda");
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
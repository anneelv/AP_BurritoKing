package MainProgram;

import Exceptions.*;
import java.util.Scanner;

/* The Menu class provides the initial menu of the program to do food ordering,
show sales report and changing food price*/

public class Menu {

    private Order order;
    private SalesReport sales;
    private Validation validation;

    public Menu(Order order, SalesReport sales, Validation validation) {
        this.order = order;
        this.sales = sales;
        this.validation = validation;
    }

    /*The method to run the initial menu display*/
    public void run() throws EmptyUserInputException{
        mainMenu();
    }

    /*The method to operate the program and keep it going based on user input*/
    private void mainMenu() throws EmptyUserInputException {
        String choice = "";
        boolean exit = false;

        do{
            displayMenu();
            System.out.print("Please enter your choice: ");
            try {
                choice = readUserInput();

                // Validating user input and continue with the loop if the input is empty
                validation.checkStringInput(choice);

                switch (choice) {
                    case "a":
                        order.runMenu();
                        break;
                    case "b":
                        sales.showReport();
                        break;
                    case "c":
                        order.runChangePriceMenu();
                        break;
                    case "d":
                        System.out.println("Bye Bye.");
                        exit = true;
                        break;
                    default:
                        throw new InvalidOptionException("The option that you input is invalid!");
                }
            } catch (InvalidOptionException | EmptyUserInputException e) {
                System.out.println(e.getMessage());
            }
        } while (!exit);
    }

    /*The method to read user input*/
    public static String readUserInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /*The method to print out constant initial menu lines*/
    private void displayMenu() {
        System.out.printf("%n %s %n %S! %n %s %n", "========================================", "welcome to burrito king", "========================================");
        System.out.printf(" %s %n %s %n %s %n %s %n", "a) Order", "b) Show Sales Report", "c) Update Prices", "d) Exit");
    }

}

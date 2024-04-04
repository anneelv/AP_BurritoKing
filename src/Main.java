//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.*;

public class Main {
    public static void main(String[] args) {
        boolean exit = false;
        Scanner input = new Scanner(System.in);
        String choice = "";

//		Running the input in a loop until user chooses a valid option
        do {
            initialMenu();
            System.out.print("Please enter your choice: ");
            choice = input.nextLine();

//			In case user input is empty, continue with the input loop
            if (choice.isEmpty()) {
                System.out.println("Please select a valid menu option.");
                continue;
            }

            switch (choice) {
                case "a":
                    Order.OrderMenu();
                    break;
                case "b":
//				CREATE SHOW SALES REPORT CLASS
                    break;
                case "c":
//				CREATE UPDATE PRICE CLASS
                    break;
                case "d":
                    System.out.println("Bye Bye.");
                    exit = true;
                    break;
                default:
                    System.out.println("The option that you input is invalid!");
                    break;
            }
        } while (!exit);

    }

    public static void initialMenu() {
        System.out.println("========================================");
        System.out.println("Welcome to Burrito King!");
        System.out.println("========================================");
        System.out.println("a) Order");
        System.out.println("b) Show Sales Report");
        System.out.println("c) Update Prices");
        System.out.println("d) Exit");
    }
}
import java.util.Scanner;

public class Menu {
    private Scanner input = new Scanner(System.in);
    private String choice = "";
    private boolean exit = false;
    private Order order;
    private SalesReport sales;

    public Menu(Order order, SalesReport sales) {
        this.order = order;
        this.sales = sales;
    }

    public void run(){
        mainMenu();
    }

    private void mainMenu() {
        do{
            displayMenu();
            System.out.print("Please enter your choice: ");
            choice = input.nextLine();

//            TODO: IMPROVE THE INPUT VALIDATION WITH EXCEPTIONS
            if (choice.isEmpty()) {
                System.out.println("Please select a valid menu option.");
                continue;
            }

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
                    System.out.println("The option that you input is invalid!");
                    break;
            }
        } while (!exit);
    }

    private void displayMenu() {
        System.out.printf("%n %s %n %S! %n %s %n", "========================================", "welcome to burrito king", "========================================");
        System.out.printf(" %s %n %s %n %s %n %s %n", "a) Order", "b) Show Sales Report", "c) Update Prices", "d) Exit");
    }

}

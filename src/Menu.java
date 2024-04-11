import java.util.Scanner;

public class Menu {
    private Scanner input = new Scanner(System.in);
    private String choice = "";
    private boolean exit = false;
    private Order order;
    private SalesReport sales;
    private Validation validation;

    public Menu(Order order, SalesReport sales, Validation validation) {
        this.order = order;
        this.sales = sales;
        this.validation = validation;
    }

    public void run() throws InvalidOptionException, EmptyUserInputException {
        mainMenu();
    }

    private void mainMenu() throws InvalidOptionException, EmptyUserInputException{
        do{
            displayMenu();
            System.out.print("Please enter your choice: ");
            try {
                choice = input.nextLine();
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

    private void displayMenu() {
        System.out.printf("%n %s %n %S! %n %s %n", "========================================", "welcome to burrito king", "========================================");
        System.out.printf(" %s %n %s %n %s %n %s %n", "a) Order", "b) Show Sales Report", "c) Update Prices", "d) Exit");
    }

}

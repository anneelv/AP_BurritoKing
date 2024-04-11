

public class Main {
    public static void main(String[] args) throws EmptyUserInputException, InvalidOptionException {
        Validation validation = new Validation();
        SalesReport sales = new SalesReport();
        Order order = new Order(sales, validation);
        Menu menu = new Menu(order, sales, validation);
        menu.run();
    }
}

import Exceptions.EmptyUserInputException;
import MainProgram.Menu;
import MainProgram.Order;
import MainProgram.SalesReport;
import MainProgram.Validation;

/*
* The Main Class provides the entry point of the Burrito King Program
*/

public class Main {
    public static void main(String[] args) throws EmptyUserInputException {
        Validation validation = new Validation();
        SalesReport sales = new SalesReport();
        Order order = new Order(sales, validation);
        Menu menu = new Menu(order, sales, validation);
        menu.run();
    }
}

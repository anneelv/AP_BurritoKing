//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//import java.util.*;

public class Main {
    public static void main(String[] args) {
        SalesReport sales = new SalesReport();
        Order order = new Order(sales);
        Menu menu = new Menu(order, sales);
        menu.run();
    }
}

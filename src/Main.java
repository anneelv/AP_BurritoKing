

public class Main {
    public static void main(String[] args) {
        SalesReport sales = new SalesReport();
        Order order = new Order(sales);
        Menu menu = new Menu(order, sales);
        menu.run();
    }
}

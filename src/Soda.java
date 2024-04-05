public class Soda extends Food{
    private static double price;

    public Soda(String name, int orderQuantity) {
        super(name, orderQuantity);
        setPrice(2.50);
    }

    //    Getter and Setter for Fries price
    public void setPrice(double newPrice) { price = newPrice; }
    public double getPrice() { return price; }
}

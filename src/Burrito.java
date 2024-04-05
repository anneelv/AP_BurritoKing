public class Burrito extends Food{
    private static double price;
    private final int prepTime;
    private final int quantityPerBatch;

    public Burrito(String name, int orderQuantity) {
        super(name, orderQuantity);
        setPrice(7);
        prepTime = 9;
        quantityPerBatch = 2;
    }

    //    Getter and Setter for Fries price
    @Override
    public void setPrice(double newPrice) { price = newPrice; }

   @Override
    public double getPrice() { return price; }

    public int getQuantityPerBatch() { return this.quantityPerBatch; }

    public int getPrepTime() {return this.prepTime; }
}

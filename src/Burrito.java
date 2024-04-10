public class Burrito extends Food{
    private static double price;
    private final int prepTime;
    private final int quantityPerBatch;

    public Burrito(String name){
        super(name);
        setPrice(7);
        prepTime = 9;
        quantityPerBatch = 2;
    }

    public Burrito(String name, int orderQuantity) {
        super(name, orderQuantity);
        setPrice(7);
        prepTime = 9;
        quantityPerBatch = 2;
    }

    //    Getter and Setter for Fries price
    public void setPrice(double newPrice) { price = newPrice; }

    public double getPrice() { return price; }

    @Override
    public int getQuantityPerBatch() { return this.quantityPerBatch; }

    @Override
    public int getPrepTime() {return this.prepTime; }
}

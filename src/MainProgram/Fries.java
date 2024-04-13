package MainProgram;

/*The Fries class handles specific Fries objects with details unique to Fries*/

public class Fries extends Food{
    private static double price;
    private final int prepTime;
    private final int quantityPerBatch;

    public Fries(String name){
        super(name);
        setPrice(4);
        prepTime = 8;
        quantityPerBatch = 5;
    }

    /*Getter and Setter for Fries attributes*/
    public void setPrice(double newPrice) { price = newPrice; }

    public double getPrice() { return price; }

    @Override
    public int getQuantityPerBatch() { return this.quantityPerBatch; }

    @Override
    public int getPrepTime() {return this.prepTime; }
}

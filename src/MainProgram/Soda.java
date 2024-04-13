package MainProgram;

/*The Soda class handles specific Soda objects with details unique to Soda*/

public class Soda extends Food{
    private static double price;

    public Soda(String name){
        super(name);
        setPrice(2.50);
    }

    /*Getter and Setter for Soda attributes*/
    public void setPrice(double newPrice) { price = newPrice; }
    public double getPrice() { return price; }
}

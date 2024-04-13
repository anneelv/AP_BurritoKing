package MainProgram;

/*The Food class handles the foods that are available on BurritoKing*/

abstract public class Food {
    private String name;
    private int orderQuantity = 0;

    public Food(String name) {
        setName(name);
    }

    /*Getter and Setter for Food attributes*/
    public void setName(String foodName) { this.name = foodName; }

    public String getName() { return this.name; }

    public void setOrderQuantity(int quantity) { this.orderQuantity = quantity; }

    public int getOrderQuantity() { return this.orderQuantity; }

    abstract double getPrice();

    abstract void setPrice(double newPrice);

    public int getQuantityPerBatch() { return 0; }

    public int getPrepTime() { return 0; }

}
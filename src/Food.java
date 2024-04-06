abstract public class Food {
    private String name;
    private int orderQuantity;

    public Food(String name, int orderQuantity) {
        setName(name);
        setOrderQuantity(orderQuantity);
    }

    //    Getter and Setter for the constructor
    public void setName(String foodName) { this.name = foodName; }

    public String getName() { return this.name; }

    public void setOrderQuantity(int quantity) { this.orderQuantity = quantity; }

    public int getOrderQuantity() { return this.orderQuantity; }

    abstract double getPrice();

    abstract void setPrice(double newPrice);

    public int getQuantityPerBatch() { return 0; }

    public int getPrepTime() { return 0; }

}
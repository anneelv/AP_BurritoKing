public class Food {
    String name;
    double price;
    int orderQuantity;
    int prepTime;
    int quantityPerBatch;

    //	Constructor for Burrito, Fries and Soda
    public Food(String name, int orderQuantity) {
        if (name.equalsIgnoreCase("Fries")) {
            this.name = name;
            this.price = 4;
            this.orderQuantity = orderQuantity;
            this.prepTime = 8;
            this.quantityPerBatch = 5;
        }
        else if (name.equalsIgnoreCase("Burrito")) {
            this.name = name;
            this.price = 7;
            this.orderQuantity = orderQuantity;
            this.prepTime = 9;
            this.quantityPerBatch = 2;
        }
        else if (name.equalsIgnoreCase("Soda")) {
            this.name = name;
            this.price = 2.50;
            this.orderQuantity = orderQuantity;
        }
    }
}

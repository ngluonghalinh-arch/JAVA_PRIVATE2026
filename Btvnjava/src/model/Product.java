package model;

public class Product {
    private String id;
    private String name;
    private double price;
    private int stock;
    private static int count = 0;

    public Product(String id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        count++;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()){
            this.name = name;
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price > 0){
            this.price = price;
        }
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock >= 0){
            this.stock = stock;
        }
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Product.count = count;
    }

    @Override
    public String toString(){
        return id + " | " + name + " | " + price + " | tồn: " + stock;
    }
}

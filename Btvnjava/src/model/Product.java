package model;

public class Product {
    private String id;
    private String name;
    private double price;
    private int stock;

    public Product(String id, String name, double price, int stock) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID khong hop le!");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name khong hop le!");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price khong hop le!");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("Stock khong hop le!");
        }
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Giá phải lớn hơn 0");
        }
        this.price = price;
    }

    public void decreaseStock(int quantity) {
        if (quantity <= 0 || quantity > stock) {
            throw new IllegalArgumentException("Số lượng không hợp lệ");
        }
        stock -= quantity;
    }

    public String getDescription() {
        return name;
    }

    @Override
    public String toString(){
        return id + " | " + name + " | " + price + " | tồn: " + stock;
    }
}

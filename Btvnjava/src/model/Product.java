
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
        if (!Double.isFinite(price) || price <= 0) {
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

    public int getStock() {
        return stock;
    }

    public void setPrice(double price) {
        if (Double.isFinite(price) && price > 0) {
            this.price = price;
        }
    }

    public void decreaseStock(int quantity) {
        if (quantity > 0 && quantity <= stock) {
            stock -= quantity;
        }
    }

    public void increaseStock(int quantity) {
        if (quantity > 0 && quantity <= Integer.MAX_VALUE - stock) {
            stock += quantity;
        }
    }

    public String getDescription() {
        return id + " - " + name + " - Giá: " + price;
    }

    public double shippingFee(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity phai lon hon 0!"
            );
        }
        return 10000.0 * quantity;
    }

    @Override
    public String toString() {
        return id + " | " + getDescription() + " | Gia: " + price + " | Ton kho: " + stock;
    }
}

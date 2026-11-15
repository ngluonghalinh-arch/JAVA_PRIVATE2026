package model;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product khong duoc null!");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity phai lon hon 0!");
        }
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal(){
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return product.getId() + " | " + product.getName() + " | " + product.getPrice() + " | SL: " + quantity + " | Thành tiền: " + getTotal();
    }
}

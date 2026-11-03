package model;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        }
    }

    public double getTotal(){
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return product.getId() + " | " + product.getName() + " | " + product.getPrice() + " | SL: " + quantity + " | Thành tiền: " + getTotal();
    }
}

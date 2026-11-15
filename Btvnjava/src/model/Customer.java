package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer {
    private String id;
    private String name;
    private String phone;
    private List<CartItem> cart;

    public Customer(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.cart = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public List<CartItem> getCart() {
        return Collections.unmodifiableList(cart);
    }

    public void addToCard(Product product, int quantity){
        CartItem item = new CartItem(product, quantity);
        cart.add(item);
        System.out.println("Them san pham thanh cong!");
    }

    public void removeFromCart(int index) {
        if (index < 1 || index > cart.size()) {
            System.out.println("Khong tim thay san pham!");
            return;
        }
        cart.remove(index - 1);
        System.out.println("Xoa san pham thanh cong!");
    }

    @Override
    public String toString(){
        return id + " | " + name + " | " + phone;
    }
}

package model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String id;
    private Customer customer;
    private List<CartItem> items = new ArrayList<>();
    private String status = "PENDING";

    public Order(String id, Customer customer) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID Order khong hop le!");
        }
        this.id = id;
        this.customer = customer;
    }

    public String getId() { return id; }
    public Customer getCustomer() { return customer; }
    public String getStatus() { return status; }
    public List<CartItem> getItems() {return items;}

    public void addItem(CartItem item) {
        items.add(item);
    }

    public double getTotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotal();
        }
        return total;
    }

    public void confirm() {
        if (status.equals("PENDING")) {
            status = "CONFIRMED";
        }
    }

    public void cancel() {
        if (status.equals("PENDING")) {
            status = "CANCELLED";
        }
    }

    @Override
    public String toString() {
        return "Order: " + id + " | Customer: " + customer.getName() + " | Status: " + status  + " | Total: " + getTotal();
    }
}

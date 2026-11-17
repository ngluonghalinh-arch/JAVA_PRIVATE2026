package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private String id;
    private Customer customer;
    private List<CartItem> items;
    private String status;

    public Order(String id, Customer customer) {
        this.id = id;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = "PENDING";
    }

    public boolean addItem(CartItem item) {
        if (!"PENDING".equalsIgnoreCase(this.status)) {
            System.out.println("Không thể chỉnh sửa đơn hàng đã xác nhận hoặc đã hủy!");
            return false;
        }
        this.items.add(item);
        return true;
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public double getTotal() {
        double subtotal = 0;
        int totalQuantity = 0;
        for (CartItem item : items) {
            subtotal += item.getTotal();
            totalQuantity += item.getQuantity();
        }
        double shipping = 0;
        if (!items.isEmpty()) {
            shipping = items.get(0).getProduct().shippingFee(totalQuantity);
        }
        return subtotal + shipping;
    }

    public void confirm() {
        this.status = "CONFIRMED";
    }

    public void cancel() {
        this.status = "CANCELLED";
    }

    public String getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }
}
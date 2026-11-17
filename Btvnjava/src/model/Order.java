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
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID Order khong hop le!");
        }
        if (customer == null) {
            throw new IllegalArgumentException(
                    "Customer khong duoc null!"
            );
        }
        this.id = id;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = "PENDING";
    }

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public String getStatus() {
        return status;
    }

    public void addItem(CartItem item) {
        if (!status.equals("PENDING")) {
            System.out.println("Khong the them hang: Order khong con PENDING!");
            return;
        }
        if (item == null) {
            System.out.println("CartItem khong duoc null!");
            return;
        }
        Product product = item.getProduct();
        for (CartItem current : items) {
            if (current.getProduct().getId().equalsIgnoreCase(product.getId())) {
                System.out.println("San pham da ton tai trong Order!");
                return;
            }
        }
        if (item.getQuantity() > product.getStock()) {
            System.out.println("So luong vuot qua ton kho!");
            return;
        }
        items.add(item);
        System.out.println("Them san pham vao Order thanh cong!");
    }

    public void removeItem(String productId) {
        if (!status.equals("PENDING")) {
            System.out.println("Khong the xoa hang: Order khong con PENDING!");
            return;
        }
        if (productId == null || productId.trim().isEmpty()) {
            System.out.println("Ma san pham khong hop le!");
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            String id = items.get(i).getProduct().getId();
            if (id.equalsIgnoreCase(productId.trim())) {
                items.remove(i);
                System.out.println("Xoa san pham thanh cong!");
                return;
            }
        }
        System.out.println("Khong tim thay san pham trong Order!");
    }

    public double getSubtotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotal();
        }
        return total;
    }

    public double getShippingFee() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getShippingFee();
        }
        return total;
    }

    public double getTotal() {
        return getSubtotal() + getShippingFee();
    }

    public void confirm() {
        if (!status.equals("PENDING")) {
            System.out.println("Khong the xac nhan: Order khong con PENDING!");
            return;
        }
        if (items.isEmpty()) {
            System.out.println("Khong the xac nhan Order rong!");
            return;
        }
        for (CartItem item : items) {
            Product product = item.getProduct();
            if (item.getQuantity() > product.getStock()) {
                System.out.println("Khong du ton kho cho san pham: " + product.getId());
                return;
            }
        }
        for (CartItem item : items) {
            item.getProduct().decreaseStock(item.getQuantity());
        }
        status = "CONFIRMED";
        System.out.println("Xac nhan Order thanh cong!");
    }

    public void cancel() {
        if (!status.equals("PENDING")) {
            System.out.println(
                    "Khong the huy: Order khong con PENDING!"
            );
            return;
        }
        status = "CANCELLED";
        System.out.println("Huy Order thanh cong!");
    }
}
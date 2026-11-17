package model;

public class ElectronicProduct extends Product {
    private int warrantyMonths;
    public ElectronicProduct(String id, String name, double price, int stock, int warrantyMonths) {
        super(id, name, price, stock);
        if (warrantyMonths <= 0) {
            throw new IllegalArgumentException("Thoi gian bao hanh phai lon hon 0!");
        }
        this.warrantyMonths = warrantyMonths;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    @Override
    public String getDescription() {
        return getName() + " — bảo hành: " + warrantyMonths + " tháng";
    }

    @Override
    public double shippingFee(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity phai lon hon 0!");
        }
        return 30000.0 * quantity;
    }
}
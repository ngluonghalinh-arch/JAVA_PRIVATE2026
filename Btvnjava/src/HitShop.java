import model.CartItem;
import model.Customer;
import model.Product;
import model.Book;
import model.ElectronicProduct;
import model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HitShop {
    private static final Customer currentCustomer = new Customer("C01", "Linh Nguyễn", "0900000001");
    private static final List<CartItem> cart = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static final Product p01 = new Product("P01", "Áo thun", 150000, 20);
    private static final Product p02 = new Product("P02", "Bình nước", 50000, 10);
    private static final Book p03 = new Book("P03", "Java cơ bản", 120000, 5, "An Nguyen");
    private static final ElectronicProduct p04 = new ElectronicProduct("P04", "Tai nghe Bluetooth", 350000, 8, 12);
    public static void main(String[] args) {
        int choice = -1;
        do {
            System.out.println("\t\tMenu");
            printMenu();
            System.out.print("Chọn: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Lua chon khong hop le.");
                scanner.nextLine();
                continue;
            }
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> themSp();
                case 2 -> {
                    System.out.print("Nhap so thu tu mat hang can xoa: ");
                    int displayNumber = Integer.parseInt(scanner.nextLine().trim());
                    removeItem(displayNumber - 1);
                }
                case 3 -> printItems();
                case 4 -> calculateAndPrintTotal();
                case 5 -> testPolymorphism();
                case 6 -> testOrder();
                case 0 -> {
                    System.out.println("Da thoat chuong trinh.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 0);
    }

    static void themSp(){
        System.out.println("Danh sach san pham co san: ");
        System.out.println("- " + p01.getId() + ": " + p01.getName() + " - gia: " + p01.getPrice());
        System.out.println("- " + p02.getId() + ": " + p02.getName() + " - gia: " + p02.getPrice());
        System.out.println("- " + p03.getId() + ": " + p03.getName() + " - gia: " + p03.getPrice() + " (Tác giả: " + p03.getAuthor() + ")");
        System.out.println("- " + p04.getId() + ": " + p04.getName() + " - gia: " + p04.getPrice() + " (Bảo hành: " + p04.getWarrantyMonths() + " tháng)");
        System.out.print("Nhap ma san pham: ");
        String code = scanner.nextLine().trim();
        Product selectedProduct = null;
        if ("P01".equalsIgnoreCase(code)){
            selectedProduct = p01;
        } else if ("P02".equalsIgnoreCase(code)) {
            selectedProduct = p02;
        } else if ("P03".equalsIgnoreCase(code)) {
            selectedProduct = p03;
        } else if ("P04".equalsIgnoreCase(code)) {
            selectedProduct = p04;
        } else {
            System.out.println("Khong tim thay san pham!");
            return;
        }
        System.out.print("Nhap so luong: ");
        int quantity = Integer.parseInt(scanner.nextLine().trim());
        addItem(selectedProduct, quantity);
    }

    static void printMenu() {
        System.out.println("1. Thêm mặt hàng");
        System.out.println("2. Xóa theo số thứ tự");
        System.out.println("3. Xem giỏ");
        System.out.println("4. Xem tổng tiền (Tiền hàng + Phí ship)");
        System.out.println("5. Test đa hình (Description)");
        System.out.println("6. Test Order & Bảo vệ dữ liệu");
        System.out.println("0. Kết thúc");
    }

    static void addItem(Product product, int quantity) {
        if (product == null) {
            System.out.println("Khong tim thay san pham.");
            return;
        }
        if (quantity <= 0) {
            System.out.println("So luong phai lon hon 0.");
            return;
        }
        cart.add(new CartItem(product, quantity));
        System.out.println("Da them vao gio hang.");
    }

    static void removeItem(int index) {
        if (index < 0 || index >= cart.size()) {
            System.out.println("Khong tim thay san pham hoac STT khong hop le.");
            return;
        }
        cart.remove(index);
        System.out.println("Da xoa thanh cong.");
    }

    static void printItems() {
        System.out.println("Khách hàng: " + currentCustomer.getName() + " (" + currentCustomer.getPhone() + ")");
        if (cart.isEmpty()) {
            System.out.println("Gio hang dang trong.");
            return;
        }
        double totalQuantity = 0;
        for (int i = 0; i < cart.size(); i++) {
            CartItem item = cart.get(i);
            Product p = item.getProduct();
            totalQuantity += item.getQuantity();
            System.out.println((i + 1) + ". " + p.getId() + " | " + p.getName() + " | " + p.getPrice() + " | SL: " + item.getQuantity() + " | Thành tiền: " + item.getTotal());
        }
        if (!cart.isEmpty()) {
            double shippingFee = cart.get(0).getProduct().shippingFee((int) totalQuantity);
            System.out.println("--- Phí vận chuyển ước tính: " + shippingFee + " ---");
        }
    }

    static void calculateAndPrintTotal() {
        if (cart.isEmpty()) {
            System.out.println("Tổng tiền: 0.0");
            return;
        }
        double subtotal = 0;
        int totalQuantity = 0;
        for (CartItem item : cart) {
            subtotal += item.getTotal();
            totalQuantity += item.getQuantity();
        }
        double shippingFee = cart.get(0).getProduct().shippingFee(totalQuantity);
        double grandTotal = subtotal + shippingFee;
        System.out.println("Tiền hàng: " + subtotal);
        System.out.println("Phí vận chuyển (" + totalQuantity + " sản phẩm): " + shippingFee);
        System.out.println("Tổng cộng thanh toán: " + grandTotal);
    }

    static void testPolymorphism() {
        List<Product> products = new ArrayList<>();
        products.add(p01);
        products.add(p02);
        products.add(p03);
        products.add(p04);
        System.out.println("\t\tDANH SACH MO TA SAN PHAM");
        for (Product product : products) {
            System.out.println(product.getDescription());
        }
    }

    static void testOrder() {
        Order order = new Order("O01", currentCustomer);
        for (CartItem item : cart) {
            order.addItem(item);
        }
        System.out.println("\t\t\tTHONG TIN ORDER");
        System.out.println("Ma Order: " + order.getId());
        System.out.println("Khach hang: " + order.getCustomer().getName());
        if (order.getItems().isEmpty()) {
            System.out.println("Order dang trong.");
        } else {
            for (CartItem item : order.getItems()) {
                System.out.println(item);
            }
        }
        System.out.println("Tổng tiền (bao gồm phí ship): " + order.getTotal());
        System.out.println("Trang thai: " + order.getStatus());
        order.confirm();
        System.out.println("Sau khi confirm trạng thái: " + order.getStatus());
        boolean addedMore = order.addItem(new CartItem(p01, 1));
        System.out.println("Thêm mặt hàng vào đơn đã xác nhận thành công? " + addedMore);
        try {
            order.getItems().add(new CartItem(p02, 5));
        } catch (UnsupportedOperationException e) {
            System.out.println("[BẢO VỆ DỮ LIỆU THÀNH CÔNG]: Không thể sửa đổi danh sách mặt hàng của Order qua getItems() do trả về UnmodifiableList!");
        }
        order.cancel();
        System.out.println("Sau khi cancel trạng thái: " + order.getStatus());
    }
}
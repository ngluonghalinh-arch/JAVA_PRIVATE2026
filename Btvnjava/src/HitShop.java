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
    private static final Product p01 = new Product("P01", "Áo thun", 150000, 20);
    private static final Product p02 = new Product("P02", "Bình nước", 50000, 10);
    private static final Book p03 = new Book("P03", "Java cơ bản", 120000, 5, "An Nguyen");
    private static final ElectronicProduct p04 = new ElectronicProduct("P04", "Laptop", 15000000, 5, 12);
    private static Order currentOrder;
    private static int orderCounter = 1;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        do {
            printProducts();
            printMenu();
            System.out.print("Chon: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Lua chon khong hop le.");
                scanner.nextLine();
                continue;
            }
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> {
                    Product product = selectProduct(scanner);
                    if (product != null) {
                        int quantity = inputQuantity(scanner);
                        if (quantity > 0) {
                            addItem(product, quantity);
                        }
                    }
                }
                case 2 -> {
                    System.out.print("Nhap so thu tu mat hang can xoa: ");
                    int number = inputQuantity(scanner);
                    if (number > 0) {
                        removeItem(number - 1);
                    }
                }
                case 3 -> printItems();
                case 4 -> {
                    System.out.println("Tien hang: " + calculateTotal());
                }
                case 5 -> createOrder();
                case 6 -> {
                    if (currentOrder == null) {
                        System.out.println("Ban chua tao Order!");
                        break;
                    }
                    Product product = selectProduct(scanner);
                    if (product != null) {
                        int quantity = inputQuantity(scanner);
                        if (quantity > 0) {
                            currentOrder.addItem(new CartItem(product, quantity));
                        }
                    }
                }
                case 7 -> {
                    if (currentOrder == null) {
                        System.out.println("Ban chua tao Order!");
                        break;
                    }
                    System.out.print("Nhap ma san pham can xoa: ");
                    String productId = scanner.nextLine().trim();
                    currentOrder.removeItem(productId);
                }
                case 8 -> printOrder();
                case 9 -> {
                    if (currentOrder == null) {
                        System.out.println("Ban chua tao Order!");
                    } else {
                        currentOrder.confirm();
                    }
                }
                case 10 -> {
                    if (currentOrder == null) {
                        System.out.println("Ban chua tao Order!");
                    } else {
                        currentOrder.cancel();
                    }
                }
                case 0 -> {
                    System.out.println("Da thoat chuong trinh.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 0);
        scanner.close();
    }

    static void printMenu() {
        System.out.println("\n\t\t\tMENU");
        System.out.println("1. Them mat hang vao gio");
        System.out.println("2. Xoa theo so thu tu");
        System.out.println("3. Xem gio hang");
        System.out.println("4. Xem tong tien gio hang");
        System.out.println("5. Tao Order");
        System.out.println("6. Them san pham vao Order");
        System.out.println("7. Xoa san pham khoi Order");
        System.out.println("8. Xem chi tiet Order");
        System.out.println("9. Xac nhan Order");
        System.out.println("10. Huy Order");
        System.out.println("0. Ket thuc");
    }

    static void printProducts() {
        System.out.println("\n\t\t\tDANH SACH SAN PHAM");
        Product[] products = {p01, p02, p03, p04};
        for (Product product : products) {
            System.out.println(product.getId() + " | " + product.getDescription() + " | Gia: " + product.getPrice() + " | Ton kho: " + product.getStock());
        }
    }

    static Product selectProduct(Scanner scanner) {
        System.out.print("Nhap ma san pham: ");
        String code = scanner.nextLine().trim();
        if ("P01".equalsIgnoreCase(code)) return p01;
        if ("P02".equalsIgnoreCase(code)) return p02;
        if ("P03".equalsIgnoreCase(code)) return p03;
        if ("P04".equalsIgnoreCase(code)) return p04;
        System.out.println("Khong tim thay san pham!");
        return null;
    }

    static int inputQuantity(Scanner scanner) {
        System.out.print("Nhap so luong: ");
        try {
            int quantity = Integer.parseInt(scanner.nextLine().trim());
            if (quantity <= 0) {
                System.out.println("So luong phai lon hon 0!");
                return -1;
            }
            return quantity;
        } catch (NumberFormatException e) {
            System.out.println("So luong khong hop le!");
            return -1;
        }
    }

    static void addItem(Product product, int quantity) {
        if (product == null) {
            System.out.println("Khong tim thay san pham.");
            return;
        }
        if (quantity <= 0 || quantity > product.getStock()) {
            System.out.println("So luong khong hop le!");
            return;
        }
        cart.add(new CartItem(product, quantity));
        System.out.println("Da them vao gio hang.");
    }

    static void removeItem(int index) {
        if (index < 0 || index >= cart.size()) {
            System.out.println("Khong tim thay san pham.");
            return;
        }
        cart.remove(index);
        System.out.println("Da xoa thanh cong.");
    }

    static void printItems() {
        System.out.println("\n\t\t\tGIO HANG");
        System.out.println("Khach hang: " + currentCustomer);
        if (cart.isEmpty()) {
            System.out.println("Gio hang dang trong.");
            return;
        }
        for (int i = 0; i < cart.size(); i++) {
            System.out.println((i + 1) + ". " + cart.get(i));
        }
        System.out.println("Tien hang: " + calculateTotal());
    }

    static double calculateTotal() {
        double total = 0;
        for (CartItem item : cart) {
            total += item.getTotal();
        }
        return total;
    }

    static void createOrder() {
        if (currentOrder != null && currentOrder.getStatus().equals("PENDING")) {
            System.out.println("Ban dang co mot Order PENDING!");
            return;
        }
        String id = String.format("O%02d", orderCounter++);
        currentOrder = new Order(id, currentCustomer);
        System.out.println("Tao Order thanh cong: " + id);
    }

    static void printOrder() {
        if (currentOrder == null) {
            System.out.println("Ban chua tao Order!");
            return;
        }
        System.out.println("\n\t\t\tCHI TIET ORDER");
        System.out.println("Ma Order: " + currentOrder.getId());
        System.out.println("Khach hang: " + currentOrder.getCustomer());
        System.out.println("Trang thai: " + currentOrder.getStatus());
        if (currentOrder.getItems().isEmpty()) {
            System.out.println("Order dang trong.");
        } else {
            for (CartItem item : currentOrder.getItems()) {
                System.out.println(item);
            }
        }
        System.out.println("\nTien hang: " + currentOrder.getSubtotal());
        System.out.println("Phi van chuyen: " + currentOrder.getShippingFee());
        System.out.println("Tong thanh toan: " + currentOrder.getTotal()+ "\n");
    }
}
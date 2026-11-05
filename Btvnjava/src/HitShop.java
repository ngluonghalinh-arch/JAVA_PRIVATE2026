import model.CartItem;
import model.Customer;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HitShop {
    private static final Customer currentCustomer = new Customer("C01", "Linh Nguyễn", "0900000001");
    private static final List<CartItem> cart = new ArrayList<>();
    private static final Product p01 = new Product("P01", "Áo thun", 150000, 20);
    private static final Product p02 = new Product("P02", "Bình nước", 50000, 10);
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        do {
            System.out.println("===============Menu=============");
            printMenu();
            System.out.println("================================");
            System.out.print("Chọn: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Lua chon khong hop le.");
                scanner.nextLine();
                continue;
            }
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> {
                    System.out.println("Danh sach san pham co san: ");
                    System.out.println("- " + p01.getId() + ": " + p01.getName() + " - gia: " + p01.getPrice()) ;
                    System.out.println("- " + p02.getId() + ": " + p02.getName() + " - gia: " + p02.getPrice());
                    System.out.println("Nhap ma san pham: ");
                    String code = scanner.nextLine().trim();
                    Product selectedProduct = null;
                    if ("P01".equalsIgnoreCase(code)){
                        selectedProduct = p01;
                    } else if ("P02".equalsIgnoreCase(code)) {
                        selectedProduct = p02;
                    }else {
                        System.out.println("Khong tim thay san pham!");
                        break;
                    }
                    System.out.println("Nhap so luong");
                    int quantity = Integer.parseInt(scanner.nextLine().trim());
                    addItem(selectedProduct, quantity);
                }
                case 2 -> {
                    System.out.print("Nhap so thu tu mat hang can xoa: ");
                    int displayNumber = Integer.parseInt(scanner.nextLine().trim());
                    removeItem(displayNumber - 1);
                }
                case 3 -> printItems();
                case 4 -> System.out.println("Tong tien: " + calculateTotal());
                case 0 -> {
                    System.out.println("Da thoat chuong trinh.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 0);
    }
    static void printMenu() {
        System.out.println("1. Thêm mặt hàng");
        System.out.println("2. Xóa theo số thứ tự");
        System.out.println("3. Xem giỏ");
        System.out.println("4. Xem tổng tiền");
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
            System.out.println("Khong tim thay san pham.");
            return;
        }
        cart.remove(index);
        System.out.println("Da xoa thanh cong.");
    }
    static void printItems() {
        System.out.println("Khách hàng: " + currentCustomer);
        if (cart.isEmpty()) {
            System.out.println("Gio hang dang trong.");
            return;
        }
        for (int i = 0; i < cart.size(); i++) {
            CartItem item = cart.get(i);
            Product p = item.getProduct();
            System.out.println((i + 1) + ". " + p.getId() + " | " + p.getName() + " | " + p.getPrice() + " | SL: " + item.getQuantity() + " | Thành tiền: " + item.getTotal());
        }
    }
    static double calculateTotal() {
        double total = 0;
        for (CartItem item : cart) {
            total += item.getTotal();
        }
        return total;
    }
}
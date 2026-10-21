import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Tên mặt hàng: ");
        String productName = scanner.nextLine();

        System.out.print("Đơn giá: ");
        double unitPrice = scanner.nextDouble();

        System.out.print("Số lượng: ");
        int quantity = scanner.nextInt();

        double subtotal = unitPrice * quantity;
        double VAT_RATE = 0.10;
        double SHIPPING_FEE = 20000;
        System.out.println("Tên mặt hàng: " + productName);
        System.out.println("Đơn giá: " + unitPrice);
        System.out.println("Số lượng: " + quantity);
        System.out.println("Thành tiền: " + subtotal + VAT_RATE * subtotal + SHIPPING_FEE);

        scanner.close();
    }
}

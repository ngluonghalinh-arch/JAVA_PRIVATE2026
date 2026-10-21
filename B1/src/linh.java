import java.util.Scanner;

public class linh {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Chieu dai: ");
        double cD = sc.nextDouble();
        System.out.print("Chieu rong: ");
        double cR = sc.nextDouble();
        double dT = cD * cR;
        double cV = (cD + cR) * 2;
        System.out.println("-------------");
        System.out.println("Chieu dai: " + cD);
        System.out.println("Chieu rong: " + cR);
        System.out.println("Dien tich hinh chu nhat la: " + dT);
        System.out.println("Chu vi hinh chu nhat la: " + cV);
        sc.close();
    }
}

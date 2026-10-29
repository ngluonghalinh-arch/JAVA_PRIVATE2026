import java.util.ArrayList;
import java.util.Scanner;

public class BTN {
    static ArrayList<Double> scores = new ArrayList<>();
    static void addScore(Scanner sc) {
        for (int i = 0; i < 5; i++) {
            System.out.print("Nhap diem thu " + (i + 1) + ": ");
            double score = Double.parseDouble(sc.nextLine());
            if (score < 0 || score > 10) {
                System.out.println("Diem khong hop le, bo qua!");
                continue;
            }
            scores.add(score);
        }
    }
    static void printScores() {
        System.out.println("Danh sach diem:");
        for (double score : scores) {
            System.out.print(score + ", ");
        }
        System.out.println();
    }
    static double calculateAverage() {
        if (scores.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (double score : scores) {
            sum += score;
        }
        return sum / scores.size();
    }
    static int countPassed() {
        int count = 0;
        for (double score : scores) {
            if (score >= 5) {
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        addScore(sc);
        printScores();
        System.out.println("Diem trung binh: " + calculateAverage());
        System.out.println("So diem dat (>= 5): " + countPassed());
        sc.close();
    }
}

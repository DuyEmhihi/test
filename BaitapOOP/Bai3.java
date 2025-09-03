import java.util.Scanner;

class Bai3 {
    private String maSV;
    private String hoTen;
    private int tuoi;

    private static int tongSoSV = 0;

    public Bai3(String maSV, String hoTen, int tuoi) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.tuoi = tuoi;
        tongSoSV++; 
    }

    public Bai3() {
        tongSoSV++;
    }

    public void nhapThongTin() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã SV: ");
        maSV = sc.nextLine();
        System.out.print("Nhập họ tên: ");
        hoTen = sc.nextLine();
        System.out.print("Nhập tuổi: ");
        tuoi = sc.nextInt();
    }

    public void hienThiThongTin() {
        System.out.println("Mã SV: " + maSV);
        System.out.println("Họ tên: " + hoTen);
        System.out.println("Tuổi: " + tuoi);
        System.out.println("-----------------------------");
    }

    public static void hienThiTongSoSV() {
        System.out.println("Tổng số sinh viên đã tạo: " + tongSoSV);
    }
}

public class Main {
    public static void main(String[] args) {
        Bai3 sv1 = new Bai3("SV001", "Nguyen Van A", 20);
        Bai3 sv2 = new Bai3("SV002", "Tran Thi B", 21);

        Bai3 sv3 = new Bai3();
        sv3.nhapThongTin();

        sv1.hienThiThongTin();
        sv2.hienThiThongTin();
        sv3.hienThiThongTin();

        Bai3.hienThiTongSoSV();
    }
}

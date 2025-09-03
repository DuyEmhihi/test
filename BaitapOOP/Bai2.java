import java.util.*;

// Lớp TaiKhoan
class TaiKhoan {
    private String soTaiKhoan;
    private String tenChuTK;
    private double soDu;

    // Biến tĩnh: lãi suất năm (áp dụng chung cho tất cả tài khoản)
    private static double laiSuatNam = 0.05; // 5% mặc định

    // Constructor
    public TaiKhoan(String soTaiKhoan, String tenChuTK, double soDu) {
        this.soTaiKhoan = soTaiKhoan;
        this.tenChuTK = tenChuTK;
        this.soDu = soDu;
    }

    // Getter
    public String getSoTaiKhoan() {
        return soTaiKhoan;
    }

    public double getSoDu() {
        return soDu;
    }

    // Phương thức nạp tiền
    public void napTien(double soTien) {
        if (soTien > 0) {
            soDu += soTien;
            System.out.println("Nạp " + soTien + " vào tài khoản " + soTaiKhoan);
        }
    }

    // Phương thức rút tiền
    public void rutTien(double soTien) {
        if (soTien > 0 && soTien <= soDu) {
            soDu -= soTien;
            System.out.println("Rút " + soTien + " từ tài khoản " + soTaiKhoan);
        } else {
            System.out.println("Rút tiền thất bại! Số dư không đủ.");
        }
    }

    // Phương thức tính lãi 1 tháng
    public void tinhLai1Thang() {
        double lai = soDu * (laiSuatNam / 12);
        soDu += lai;
        System.out.println("Cộng lãi " + lai + " vào tài khoản " + soTaiKhoan);
    }

    // Hiển thị thông tin
    public void hienThiThongTin() {
        System.out.println("Số TK: " + soTaiKhoan);
        System.out.println("Chủ TK: " + tenChuTK);
        System.out.println("Số dư: " + soDu);
        System.out.println("---------------------------");
    }

    // Thay đổi lãi suất
    public static void thayDoiLaiSuat(double laiSuatMoi) {
        laiSuatNam = laiSuatMoi;
        System.out.println("Lãi suất mới: " + laiSuatNam);
    }
}

// Lớp Main để chạy chương trình
public class Bai2 {
    public static void main(String[] args) {
        List<TaiKhoan> danhSachTK = new ArrayList<>();

        // Tạo một số tài khoản
        TaiKhoan tk1 = new TaiKhoan("001", "Nguyen Van A", 5000);
        TaiKhoan tk2 = new TaiKhoan("002", "Tran Thi B", 10000);
        TaiKhoan tk3 = new TaiKhoan("003", "Le Van C", 7000);

        danhSachTK.add(tk1);
        danhSachTK.add(tk2);
        danhSachTK.add(tk3);

        // Thực hiện một số giao dịch
        tk1.napTien(2000);
        tk2.rutTien(3000);
        tk3.tinhLai1Thang();

        // Hiển thị danh sách tài khoản sau khi xử lý
        System.out.println("=== Danh sách tài khoản sau khi xử lý ===");
        for (TaiKhoan tk : danhSachTK) {
            tk.hienThiThongTin();
        }

        // Sắp xếp tài khoản theo số dư giảm dần
        danhSachTK.sort((a, b) -> Double.compare(b.getSoDu(), a.getSoDu()));

        System.out.println("=== Danh sách sau khi sắp xếp theo số dư giảm dần ===");
        for (TaiKhoan tk : danhSachTK) {
            tk.hienThiThongTin();
        }

        // Tìm kiếm tài khoản theo số TK
        String soTKCanTim = "002";
        System.out.println("=== Tìm kiếm tài khoản có số TK = " + soTKCanTim + " ===");
        for (TaiKhoan tk : danhSachTK) {
            if (tk.getSoTaiKhoan().equals(soTKCanTim)) {
                tk.hienThiThongTin();
            }
        }
    }
}

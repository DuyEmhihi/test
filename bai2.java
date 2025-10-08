import java.util.*;

// --------------------- Lớp sản phẩm ---------------------
abstract class Product {
    protected String id;
    protected String name;
    protected double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public abstract String toString();
}

// Điện tử
class ElectronicProduct extends Product {
    private String imei;
    private int warrantyMonths;

    public ElectronicProduct(String id, String name, double price, String imei, int warrantyMonths) {
        super(id, name, price);
        this.imei = imei;
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public String toString() {
        return String.format("[Electronic] %s - Giá: %.0f - IMEI: %s - BH: %d tháng", name, price, imei, warrantyMonths);
    }
}

// Thực phẩm
class FoodProduct extends Product {
    private String expiryDate;

    public FoodProduct(String id, String name, double price, String expiryDate) {
        super(id, name, price);
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return String.format("[Food] %s - Giá: %.0f - HSD: %s", name, price, expiryDate);
    }
}

// --------------------- Interface thanh toán ---------------------
interface PaymentMethod {
    void pay(double amount, String customerName);
}

class CashPayment implements PaymentMethod {
    public void pay(double amount, String customerName) {
        System.out.println("Khách hàng: " + customerName + ". Tổng tiền: " + amount + ". Thanh toán tiền mặt thành công.");
    }
}

class CreditCardPayment implements PaymentMethod {
    public void pay(double amount, String customerName) {
        System.out.println("Khách hàng: " + customerName + ". Tổng tiền: " + amount + ". Thanh toán bằng thẻ tín dụng thành công.");
    }
}

class MomoPayment implements PaymentMethod {
    public void pay(double amount, String customerName) {
        System.out.println("Khách hàng: " + customerName + ". Tổng tiền: " + amount + ". Thanh toán qua ví Momo thành công.");
    }
}

// --------------------- Lớp Đơn hàng ---------------------
class Order {
    private String customerName;
    private List<Product> products = new ArrayList<>();
    private PaymentMethod paymentMethod;

    public Order(String customerName) {
        this.customerName = customerName;
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public void setPaymentMethod(PaymentMethod pm) {
        this.paymentMethod = pm;
    }

    public double getTotalAmount() {
        double total = 0;
        for (Product p : products) total += p.getPrice();
        return total;
    }

    public void checkout() {
        double total = getTotalAmount();
        if (paymentMethod == null) {
            System.out.println("Vui lòng chọn phương thức thanh toán!");
            return;
        }
        paymentMethod.pay(total, customerName);
    }

    public void showOrderDetail() {
        System.out.println("\n--- Đơn hàng của " + customerName + " ---");
        for (Product p : products) {
            System.out.println(p);
        }
        System.out.println("Tổng tiền: " + getTotalAmount());
    }
}

// --------------------- Chương trình chính ---------------------
public class Main {
    public static void main(String[] args) {
        // Tạo sản phẩm
        Product p1 = new ElectronicProduct("E01", "Điện thoại iPhone 15", 20000000, "1234567890", 12);
        Product p2 = new FoodProduct("F01", "Bánh Snack", 15000, "10/10/2025");
        Product p3 = new FoodProduct("F02", "Nước ngọt Pepsi", 12000, "01/12/2025");

        // Tạo đơn hàng cho khách A
        Order orderA = new Order("Nguyễn Văn A");
        orderA.addProduct(p1);
        orderA.addProduct(p2);
        orderA.showOrderDetail();
        orderA.setPaymentMethod(new CashPayment());
        orderA.checkout();

        // Tạo đơn hàng cho khách B
        Order orderB = new Order("Nguyễn Văn B");
        orderB.addProduct(p1);
        orderB.addProduct(p3);
        orderB.showOrderDetail();
        orderB.setPaymentMethod(new CreditCardPayment());
        orderB.checkout();

        // Thử thêm phương thức thanh toán mới (mở rộng dễ dàng)
        Order orderC = new Order("Trần Thị C");
        orderC.addProduct(p2);
        orderC.setPaymentMethod(new MomoPayment());
        orderC.checkout();
    }
}

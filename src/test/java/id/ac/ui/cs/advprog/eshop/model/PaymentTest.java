package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Order order;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);

        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);

        products.add(product1);
        products.add(product2);

        this.order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");
    }

    @Test
    void testPaymentEmptyPaymentData() {
        Map<String, String> paymentData = new HashMap<>();

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                    "VOUCHER_CODE", order, paymentData);
        });
    }

    @Test
    void testPaymentInvalidMethod() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                    "GHAIB", order, paymentData);
        });
    }

    @Test
    void testPaymentSuccessStatus() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                "VOUCHER_CODE", order, paymentData, "SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testPaymentInvalidVoucherCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABCDEFG");

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                "VOUCHER_CODE", order, paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testPaymentInvalidPaymentData() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "House of Arrakis");
        paymentData.put("deliveryFee", null);

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                "CASH_ON_DELIVERY", order, paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }
}
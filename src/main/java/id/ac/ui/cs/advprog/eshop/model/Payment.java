package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;

import java.util.Map;

@Getter
class Payment {
    String id;
    String method;
    Order order;
    Map<String, String> paymentData;
    String status;

    public Payment(String id, String method, Order order, Map<String, String> paymentData) {
        this.id = id;
        this.order = order;

        if (PaymentMethod.contains(method)){
            this.method = method;
        } else {
            throw new IllegalArgumentException();
        }

        if (paymentData.isEmpty()){
            throw new IllegalArgumentException();
        } else {
            this.paymentData = paymentData;
        }

        String status = paymentDataCheck();
        setStatus(status);
    }

    public Payment(String id, String method, Order order, Map<String, String> paymentData, String status){
        this(id, method, order, paymentData);
        this.setStatus(status);
    }

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private String paymentDataCheck() {
        if (this.method.equals(PaymentMethod.VOUCHER_CODE.getValue())) {
            return checkVoucherCodePayment();
        } else {
            return checkCashOnDeliveryPayment();
        }
    }

    private String checkVoucherCodePayment() {
        String voucherCode = this.paymentData.get("voucherCode");

        if (voucherCode != null && voucherCode.length() == 16 && voucherCode.startsWith("ESHOP") &&
                hasEightNumeric(voucherCode.substring(5))) {
            return PaymentStatus.SUCCESS.getValue();
        }

        return PaymentStatus.REJECTED.getValue();
    }

    private String checkCashOnDeliveryPayment() {
        String address = this.paymentData.get("address");
        String deliveryFee = this.paymentData.get("deliveryFee");

        if (address == null || address.isBlank()) {
            return PaymentStatus.REJECTED.getValue();
        }

        if (deliveryFee == null || deliveryFee.isBlank()) {
            return PaymentStatus.REJECTED.getValue();
        }
        return PaymentStatus.SUCCESS.getValue();
    }

    private static boolean hasEightNumeric(String str) {
        int count = 0;
        for (char ch : str.toCharArray()) {
            if (Character.isDigit(ch)) {
                count++;
            }
        }
        return count == 8;
    }
}
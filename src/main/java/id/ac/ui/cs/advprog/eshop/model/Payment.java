package id.ac.ui.cs.advprog.eshop.model;

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

        if (method.equals("VOUCHER_CODE") || method.equals("CASH_ON_DELIVERY")){
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
        if (status.equals("SUCCESS") || status.equals("REJECTED")) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private String paymentDataCheck() {
        if (this.method.equals("VOUCHER_CODE")) {
            return checkVoucherCodePayment();
        } else {
            return checkCashOnDeliveryPayment();
        }
    }

    private String checkVoucherCodePayment() {
        String voucherCode = this.paymentData.get("voucherCode");

        if (voucherCode != null && voucherCode.length() == 16 && voucherCode.startsWith("ESHOP") &&
                hasEightNumeric(voucherCode.substring(5))) {
            return "SUCCESS";
        }

        return "REJECTED";
    }

    private String checkCashOnDeliveryPayment() {
        String address = this.paymentData.get("address");
        String deliveryFee = this.paymentData.get("deliveryFee");

        if (address == null || address.isBlank()) {
            return "REJECTED";
        }

        if (deliveryFee == null || deliveryFee.isBlank()) {
            return "REJECTED";
        }
        return "SUCCESS";
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
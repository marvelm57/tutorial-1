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

    }

    public Payment(String id, String method, Order order, Map<String, String> paymentData, String status) {

    }
}
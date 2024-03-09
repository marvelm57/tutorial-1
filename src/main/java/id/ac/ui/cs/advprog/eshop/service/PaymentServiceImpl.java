package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(String paymentId, Order order, String method, Map<String, String> paymentData) {
        if(paymentRepository.findById(paymentId) == null){
            Payment payment = new Payment(paymentId, method, order, paymentData);
            paymentRepository.save(payment);
            return payment;
        }
        return null;
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        Payment existingPayment = paymentRepository.findById(payment.getId());
        if(existingPayment != null){
            existingPayment.setStatus(status);
            Order order = existingPayment.getOrder();
            if (status.equals(PaymentStatus.SUCCESS.getValue())){
                order.setStatus(OrderStatus.SUCCESS.getValue());
            } else if (status.equals(PaymentStatus.REJECTED.getValue())){
                order.setStatus(OrderStatus.FAILED.getValue());
            }
            paymentRepository.save(existingPayment);
            return existingPayment;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
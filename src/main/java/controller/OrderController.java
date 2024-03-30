package controller;

import exception.*;
import model.Order;
import model.OrderResponse;
import model.OrderStatus;
import model.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.OrderService;

import java.util.Collections;
import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{userId}/order")
    public ResponseEntity<OrderResponse> placeOrder(@PathVariable String userId,
                                                    @RequestParam int qty,
                                                    @RequestParam String coupon) {
        try {
            OrderResponse orderResponse = orderService.placeOrder(userId, qty, coupon);
            return ResponseEntity.ok(orderResponse);
        } catch (InvalidQuantityException | InvalidCouponException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OrderResponse(e.getMessage()));
        }
    }

    @PostMapping("/{userId}/{orderId}/pay")
    public ResponseEntity<TransactionResponse> makePayment(@PathVariable String userId,
                                                           @PathVariable String orderId,
                                                           @RequestParam double amount) {
        try {
            TransactionResponse transactionResponse = orderService.makePayment(userId, orderId, amount);
            return ResponseEntity.ok(transactionResponse);
        } catch (PaymentException | OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new TransactionResponse(e.getMessage()));
        } catch (BankErrorException e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new TransactionResponse(e.getMessage()));
        } catch (NoResponseException e) {
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(new TransactionResponse(e.getMessage()));
        } catch (OrderAlreadyPaidException e) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new TransactionResponse(e.getMessage()));
        }
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable String userId) {
        List<Order> userOrders = orderService.getUserOrders(userId);
        return ResponseEntity.ok(userOrders);
    }

    @GetMapping("/{userId}/orders/{orderId}")
    public ResponseEntity<List<OrderStatus>> getOrderStatus(@PathVariable String userId,
                                                            @PathVariable String orderId) {
        try {
            List<OrderStatus> orderStatus = orderService.getOrderStatus(userId, orderId);
            return ResponseEntity.ok(orderStatus);
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonList(new OrderStatus(orderId, e.getMessage())));
        }
    }
}
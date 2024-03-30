package services;

import exception.*;
import model.Order;
import model.OrderResponse;
import model.OrderStatus;
import model.TransactionResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    public OrderResponse placeOrder(String userId, int quantity, String coupon) throws InvalidQuantityException, InvalidCouponException {
        // Implement the logic to place the order here
        // Validate the quantity and coupon
        // Process the order and calculate the amount
        // Generate an order response

        // For example:
        if (quantity <= 0 || quantity > getMaxQuantity()) {
            throw new InvalidQuantityException("Invalid quantity");
        }

        if (!isValidCoupon(coupon)) {
            throw new InvalidCouponException("Invalid coupon");
        }

        // Calculate the amount based on quantity, price, and coupon
        double amount = calculateAmount(quantity, coupon);

        // Process the order and generate an order ID
        String orderId = processOrder(userId, quantity, amount);

        // Construct and return the order response
        return new OrderResponse(orderId, userId, quantity, amount, coupon);
    }

    private boolean isValidCoupon(String coupon) {
        // Validate the coupon here
        // This method could check if the coupon exists, has not been used before, etc.
        return coupon.equals("OFF5") || coupon.equals("OFF10");
    }

    private double calculateAmount(int quantity, String coupon) {
        // Calculate the amount based on quantity and coupon
        // This method could apply discounts based on the coupon
        double price = 100; // Assuming a fixed price per item
        if (coupon.equals("OFF5")) {
            return quantity * price * 0.95; // Apply a 5% discount
        } else if (coupon.equals("OFF10")) {
            return quantity * price * 0.9; // Apply a 10% discount
        } else {
            return quantity * price; // No discount
        }
    }

    private String processOrder(String userId, int quantity, double amount) {
        // Process the order and generate an order ID
        // This method could save the order details to a database and return the generated order ID
        return "1001"; // Dummy order ID
    }

    private int getMaxQuantity() {
        // Return the maximum quantity of product available
        return 100; // Dummy maximum quantity
    }

    public TransactionResponse makePayment(String userId, String orderId, double amount) throws PaymentException, OrderNotFoundException, BankErrorException, NoResponseException, OrderAlreadyPaidException {
        // Dummy implementation to simulate payment process
        // You can replace this with actual payment gateway integration or other payment logic

        // Check if the order exists
        if (!orderExists(orderId)) {
            throw new OrderNotFoundException("Order not found");
        }

        // Check if the order has already been paid
        if (isOrderPaid(orderId)) {
            throw new OrderAlreadyPaidException("Order is already paid for");
        }

        // Process the payment
        int statusCode = processPaymentGateway(amount);

        // Handle different payment statuses
        switch (statusCode) {
            case 200:
                return new TransactionResponse(userId, orderId, "tran010100001", "successful");
            case 400:
                throw new PaymentException("Payment Failed as amount is invalid");
            case 504:
                throw new NoResponseException("No response from payment server");
            case 405:
                throw new OrderAlreadyPaidException("Order is already paid for");
            default:
                throw new BankErrorException("Payment Failed from bank");
        }
    }

    public List<Order> getUserOrders(String userId) {
        List<Order> userOrders = new ArrayList<>();
        userOrders.add(new Order("100", "25-11-2021", "OFF5", 950));
        userOrders.add(new Order("101", "25-11-2021", "OFF5", 950));
        for (Order order : userOrders) {
            // Assuming the order contains user ID, retrieve orders for the specific user
            if (order.getUserId().equals(userId)) {
                userOrders.add(order);
            }
        }
        return userOrders;
    }

    public List<OrderStatus> getOrderStatus(String userId, String orderId) throws OrderNotFoundException {
        List<OrderStatus> userOrderStatus = new ArrayList<>();
        boolean orderFound = false;

        for (OrderStatus orderStatus : userOrderStatus) {
            // Assuming the order status contains user ID and order ID
            if (orderStatus.getUserId().equals(userId) && orderStatus.getOrderId().equals(orderId)) {
                userOrderStatus.add(orderStatus);
                orderFound = true;
            }
        }

        if (!orderFound) {
            throw new OrderNotFoundException("Order not found");
        }

        return userOrderStatus;
    }

    private boolean orderExists(String orderId) {
        // Check if the order exists in the database
        // This is a dummy implementation, you can replace it with actual logic
        return true; // Assuming the order exists
    }

    private boolean isOrderPaid(String orderId) {
        // Check if the order has already been paid
        // This is a dummy implementation, you can replace it with actual logic
        return false; // Assuming the order has not been paid
    }

    private int processPaymentGateway(double amount) {
        // Dummy implementation to simulate payment processing
        // You can replace this with actual payment gateway integration
        // Return different status codes to simulate different payment scenarios
        return 200; // Payment successful
    }
}

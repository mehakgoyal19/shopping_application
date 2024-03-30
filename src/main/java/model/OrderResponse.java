package model;

public class OrderResponse {
    private String orderId;
    private String userId;

    public OrderResponse() {
    }

    public OrderResponse(String orderId, String userId, int quantity, double amount, String coupon) {
        this.orderId = orderId;
        this.userId = userId;
        this.quantity = quantity;
        this.amount = amount;
        this.coupon = coupon;
    }

    public OrderResponse(String message) {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    private int quantity;
    private double amount;
    private String coupon;
}

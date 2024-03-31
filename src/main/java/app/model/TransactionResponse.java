package app.model;

public class TransactionResponse {
    private String userId;
    private String orderId;
    private String transactionId;

    public TransactionResponse(){

    }

    public TransactionResponse(String message) {
    }

    public TransactionResponse(String userId, String orderId, String tran010100001, String successful) {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TransactionResponse(String userId, String orderId, String transactionId, String status, String description) {
        this.userId = userId;
        this.orderId = orderId;
        this.transactionId = transactionId;
        this.status = status;
        this.description = description;
    }

    private String status;
    private String description;

}


package laba1;
import java.text.SimpleDateFormat;

public class Order {
    private int orderId;
    private int clientId;  // каждый заказ связан с клиентом по id клиента
    private SimpleDateFormat orderDate;
    private double orderSumm;

    public Order(int orderId, int clientId, SimpleDateFormat orderDate, double orderSumm) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.orderDate = orderDate;
        this.orderSumm = orderSumm;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getClientId() {
        return clientId;
    }

    public SimpleDateFormat getOrderDate() {
        return orderDate;
    }

    public double getOrderSumm() {
        return orderSumm;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setOrderDate(SimpleDateFormat orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderSumm(double orderSumm) {
        this.orderSumm = orderSumm;
    }
    
    
    
}

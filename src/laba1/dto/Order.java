
package laba1.dto;
import java.io.Serializable;
import java.text.SimpleDateFormat;

public class Order implements Serializable
{
    private int orderId;
    private int clientId;  // каждый заказ связан с клиентом по id клиента
    private SimpleDateFormat orderDate;
    private double orderSum;

    public Order(int orderId, int clientId, SimpleDateFormat orderDate, double orderSum)
    {
        this.orderId = orderId;
        this.clientId = clientId;
        this.orderDate = orderDate;
        this.orderSum = orderSum;
    }

    public int getOrderId()
    {
        return orderId;
    }

    public int getClientId()
    {
        return clientId;
    }

    public SimpleDateFormat getOrderDate()
    {
        return orderDate;
    }

    public double getOrderSum()
    {
        return orderSum;
    }

    public void setOrderId(int orderId)
    {
        this.orderId = orderId;
    }

    public void setClientId(int clientId)
    {
        this.clientId = clientId;
    }

    public void setOrderDate(SimpleDateFormat orderDate)
    {
        this.orderDate = orderDate;
    }

    public void setOrderSum(double orderSum)
    {
        this.orderSum = orderSum;
    }
}

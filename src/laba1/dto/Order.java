
package laba1.dto;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import laba1.utils.GenerateId;
import static laba1.Constants.*;

public class Order implements Serializable
{
    private long orderId;
    private long clientId;  // каждый заказ связан с клиентом по id клиента
    private Date orderDate;
    private double orderSum;

    public Order(long orderId, long clientId, Date orderDate, double orderSum)
    {
        this.orderId = orderId;
        this.clientId = clientId;
        this.orderDate = orderDate;
        this.orderSum = orderSum;
    }
    
    public long getOrderId()
    {
        return this.orderId;
    }

    public long getClientId()
    {
        return clientId;
    }

    public Date getOrderDate()
    {
        return orderDate;
    }

    public double getOrderSum()
    {
        return orderSum;
    }

    public void setOrderId()
    {
        this.orderId = GenerateId.getId();
    }

    public void setClientId(long clientId)
    {   
        this.clientId = clientId;
    }

    public void setOrderDate(String orderDate) throws ParseException
    {   
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        this.orderDate = dateFormat.parse(orderDate);
    }

    public void setOrderSum(double orderSum)
    {
        this.orderSum = orderSum;
    }
}

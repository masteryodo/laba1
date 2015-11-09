
package laba1.dto;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import laba1.utils.GenerateId;

public class Order implements Serializable
{
    private long orderId;
    private long clientId;  // каждый заказ связан с клиентом по id клиента
    private Date orderDate;
    private double orderSum;
    private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    public Order(long orderId, long clientId, Date orderDate, double orderSum)
    {
        this.orderId = orderId;
        this.clientId = clientId;
        this.orderDate = orderDate;
        this.orderSum = orderSum;
    }

    @Override
    public String toString() {
        String res = String.valueOf(getOrderId()) + String.valueOf(getClientId()) + format.format(getOrderDate()) + getOrderSum();
        return res;
    }

    @Override
    public int hashCode() {
        return (String.valueOf(clientId) + orderDate + orderSum).hashCode(); //To change body of generated methods, choose Tools | Templates.
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

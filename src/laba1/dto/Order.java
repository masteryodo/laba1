
package laba1.dto;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import laba1.utils.GenerateId;
public class Order implements Serializable
{
    private long orderId;
    private long clientId;  // каждый заказ связан с клиентом по id клиента
    private SimpleDateFormat orderDate;
    private double orderSum;

    public Order(int orderId, int clientId, SimpleDateFormat orderDate, double orderSum)
    {
        this.orderId = orderId;
        this.clientId = clientId;
        this.orderDate = orderDate;
        this.orderSum = orderSum;
    }
    
    public long getOrderId()
    {
        return GenerateId.getId();
    }

    public long getClientId()
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
        // Нельзя создать заказ без клиента
        // при записи в модель будет проверка
        // а при вводе через контроллер выведем список id_client -> name
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

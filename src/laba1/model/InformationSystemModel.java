
package laba1.model;

import laba1.utils.XmlReaderWriter;
import laba1.dto.Client;
import laba1.dto.Order;
import java.util.*;
import javax.xml.parsers.ParserConfigurationException;

public class InformationSystemModel
{
    private final Set<Client> clientsSet;
    private final Set<Order> ordersSet;
    private final XmlReaderWriter xml;

    public InformationSystemModel() throws ParserConfigurationException
    {   
        xml = new XmlReaderWriter();
        this.ordersSet = xml.readOrdersFromXml();
        this.clientsSet = xml.readClientsFromXml();
    }

    public Set<Order> getOrders()
    {   
        return ordersSet;
    }

    public Set<Client> getClients()
    {
        return clientsSet;
    }
    
    public void addClient(Client client)
    {   
        clientsSet.add(client);
        commitClients();
    }
    public void removeClient(long id)
    {   
        if ( getClientById(id) != null)
        {
        clientsSet.remove(getClientById(id));
        commitClients();
        for (Order order : getOrdersByClientId(id)) 
        {
              removeOrder(order.getOrderId());
        }
        }
        else
        {
            System.out.println("Ошибка записи! Клиент с ID " + String.valueOf(id) +" не существует !");
        }
    }
    
    public void addOrder(Order order)
    {   
        if ( getClientById(order.getClientId()) != null)
        {
            ordersSet.add(order);
            commitClients();
        }
        else
        {
            System.out.println("Ошибка записи! Клиент с ID " + String.valueOf(order.getClientId()) +" не существует !");
        }
        
    }
    public void removeOrder(long id)
    {   
        ordersSet.remove(getOrderById(id));
        commitOrders();
    }
    public Client getClientById(long id)
    {   
        Client resultClient = null;
        if (Long.valueOf(id) != null)
        {
        for (Client client : clientsSet) {
            if ( client.getId() == id )
            {
                resultClient = client;
            }
        }
        }
        else {System.out.println("Получен id = null");}
        return resultClient;
    }
    
    public Order getOrderById(long id)
    {   
        Order resultOrder = null;
        for (Order order : ordersSet) {
            if ( order.getOrderId() == id )
            {
                resultOrder = order;
            }
        }
        return resultOrder;
    }
    
    public Set<Order> getOrdersByClientId(long id)
    {   
        HashSet<Order> resultOrder = new HashSet<>();
        for (Order order : ordersSet) {
            if ( order.getClientId() == id )
            {
                resultOrder.add(order);
            }
        }
        return resultOrder;
    }
    public void commitClients()
    {   
        xml.writeClientsToXml(clientsSet);
    }
    public void commitOrders()
    {   
        xml.writeOrdersToXml(ordersSet);
    }
    
    
    
}

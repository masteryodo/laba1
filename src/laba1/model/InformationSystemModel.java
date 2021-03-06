
package laba1.model;

import java.util.HashSet;
import laba1.utils.XmlReaderWriter;
import laba1.dto.Client;
import laba1.dto.Order;
import javax.xml.parsers.ParserConfigurationException;
import static laba1.Constants.*;
import laba1.utils.Finder;

public class InformationSystemModel
{
    private final HashSet<Client> clientsSet;
    private final HashSet<Order> ordersSet;
    private HashSet<Order> lastSearchResultOrders = null;
    private HashSet<Client> lastSearchResultClients = null;
    private final XmlReaderWriter xml;

    public InformationSystemModel() throws ParserConfigurationException
    {   
        xml = new XmlReaderWriter();
        this.ordersSet = xml.readOrdersFromXml(ORDERS_FILE);
        this.clientsSet = xml.readClientsFromXml(CLIENTS_FILE);
    }

    public HashSet<Order> getOrders()
    {   
        return ordersSet;
    }

    public HashSet<Client> getClients()
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
            commitOrders();
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
    
    public HashSet<Order> getOrdersByClientId(long id)
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
    
    /**
     * Заполняет сет lastSearchResultClients
     * после чего View может его вывести в читабельном виде на экран
     * @param mask
     */
    public void findClientsByMask(String mask)
    {
        Finder f = new Finder();
        lastSearchResultClients = f.findClientsByMask(clientsSet, mask);
    }
    /**
     * Заполняет сет lastSearchResultOrders
     * после чего View может его вывести в читабельном виде на экран
     * @param mask
     */
    public void findOrdersByMask(String mask) {
        Finder f = new Finder();
        lastSearchResultOrders = f.findOrdersByMask(ordersSet, mask);
    }
    
    public void importElementsFromXml(String filename)
    {   
        HashSet clients = new HashSet();
        HashSet orders = new HashSet();
        clients.addAll(xml.readClientsFromXml(filename));
        orders.addAll(xml.readOrdersFromXml(filename));
        clientsSet.addAll(clients);
        ordersSet.addAll(orders);
        commitClients();
        commitOrders();
    }
    
    public void commitClients()
    {   
        xml.writeClientsToXml(clientsSet);
    }
    public void commitOrders()
    {   
        xml.writeOrdersToXml(ordersSet);
    }    

    public HashSet<Order> getLastSearchResultOrders() {
        return lastSearchResultOrders;
    }

    public HashSet<Client> getLastSearchResultClients() {
        return lastSearchResultClients;
    }
    
}
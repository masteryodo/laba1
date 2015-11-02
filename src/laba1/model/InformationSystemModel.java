
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
        this.ordersSet = new HashSet<>();
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
        xml.readClientsFromXml();
        clientsSet.add(client);
        xml.writeClientsToXml(clientsSet);
    }
    
    public void addOrder(Order order)
    {   
        xml.readClientsFromXml();
        ordersSet.add(order);
        xml.writeClientsToXml(clientsSet);
    }
 
}
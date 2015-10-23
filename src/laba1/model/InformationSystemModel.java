
package laba1.model;

import laba1.utils.XmlReaderWriter;
import laba1.dto.Client;
import laba1.dto.Order;

import java.util.*;
import javax.xml.parsers.ParserConfigurationException;

public class InformationSystemModel
{
    private Set<Client> clientsSet;
    private Set<Order> ordersSet;
    private XmlReaderWriter xml = new XmlReaderWriter();

    public InformationSystemModel() throws ParserConfigurationException
    {
        this.clientsSet = new HashSet<>();
        this.ordersSet = new HashSet<>();
    }

    public Set<Order> getOrders()
    {
        return ordersSet;
    }

    public Set<Client> getClients()
    {
        return clientsSet;
    }


    /*public void showAllElements(HashSet<Client> collSet)
    {
        System.out.println(collSet);

    }*/
    
    public void addClient(Client client)
    {
        clientsSet.add(client);
    }

    //TODO: need
    public void saveToXML()
    {
        xml.writeClientsToXml(clientsSet);
    }
 
    /* void loadClientsFromXML(){
    
    }
    private void loadOrdersFromXML(){
    
    }*/
}
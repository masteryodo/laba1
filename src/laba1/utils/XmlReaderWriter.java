package laba1.utils;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import static laba1.Constants.*;
import laba1.dto.Client;
import laba1.dto.Order;
import laba1.exception.InformationSystemUiException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class XmlReaderWriter {
    private final DocumentBuilderFactory dbf;
        
    public XmlReaderWriter()
    {
        this.dbf = DocumentBuilderFactory.newInstance();
    }
    
    public HashSet<Client> readClientsFromXml(String filename)
    {   
        HashSet<Client> clientsSet = new HashSet<Client>();
        Long client_id = null;
        String name = null;
        String address = null;
        String phone = null;
        try
        {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc=db.parse(new File(filename));
            doc.getDocumentElement().normalize();
            NodeList nodeLst=doc.getElementsByTagName("Client");
            for(int i = 0; i < nodeLst.getLength(); i++)
            {   
                NodeList client = nodeLst.item(i).getChildNodes();
                for(int j = 0; j < client.getLength(); j++)
                {   
                        if(client.item(j).getNodeName().equals("client_id"))
                        {   
                            client_id = new Long(client.item(j).getLastChild().getTextContent());
                        }
                        if(client.item(j).getNodeName().equals("name"))
                        {   
                            name = client.item(j).getLastChild().getTextContent();
                        }
                        if(client.item(j).getNodeName().equals("address"))
                        {   
                            address = client.item(j).getLastChild().getTextContent();
                        }
                        if(client.item(j).getNodeName().equals("phone"))
                        {   
                            phone = client.item(j).getLastChild().getTextContent();
                        }
                }
                clientsSet.add(new Client(client_id, name, address, phone)); 
            }
        }
        catch(ParserConfigurationException | SAXException | IOException | DOMException | NumberFormatException ei)
        {
            System.out.println("Ошибка чтения БД "+ei);
        }
            return clientsSet;
    }
    
    
    public HashSet<Order> readOrdersFromXml(String filename)
    {   
        Long orderId = null;
        Long clientId = null;
        Date orderDate = null;
        double orderSum = 0;
        HashSet<Order> ordersSet = new HashSet<Order>();
        try
        {   
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc=db.parse(new File(filename));
            doc.getDocumentElement().normalize();
            NodeList nodeLst=doc.getElementsByTagName("Order");
            for(int i = 0; i < nodeLst.getLength(); i++)
            {   
                NodeList order = nodeLst.item(i).getChildNodes();
                for(int j = 0; j < order.getLength(); j++)
                {   
                        if(order.item(j).getNodeName().equals("order_id"))
                        {   
                            orderId = new Long(order.item(j).getLastChild().getTextContent());
                        }
                        if(order.item(j).getNodeName().equals("client_id"))
                        {
                            clientId = new Long(order.item(j).getLastChild().getTextContent());
                        }
                        if(order.item(j).getNodeName().equals("order_date"))
                        {
                            orderDate = dateFormat.parse(order.item(j).getLastChild().getTextContent());
                        }
                        if(order.item(j).getNodeName().equals("order_sum"))
                        {
                            orderSum = Double.parseDouble(order.item(j).getLastChild().getTextContent());
                        }
                    }
                    ordersSet.add( new Order(orderId, clientId, orderDate, orderSum));
                }
            }

        catch(ParserConfigurationException | SAXException | IOException | DOMException | NumberFormatException | ParseException e)
        {
            System.out.println("Ошибка чтения ордеров из файла !!!"+e);
        }
            return ordersSet;
    }
    
    public void writeClientsToXml(Set<Client> clientsSet)
    {
        try
        {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Element RootElement = doc.createElement("Clients");
            doc.appendChild(RootElement);
            for (Client h : clientsSet)
            {
                Element te = doc.createElement("Client"); //te это тип элемента
                RootElement.appendChild(te);

                Element clientId = doc.createElement("client_id");
                clientId.appendChild(doc.createTextNode( Long.toString(h.getId()) ) );
                te.appendChild(clientId);
                
                Element name = doc.createElement("name");
                name.appendChild(doc.createTextNode(h.getName()));
                te.appendChild(name);

                Element adres = doc.createElement("address"); 
                adres.appendChild(doc.createTextNode(h.getAddress()));
                te.appendChild(adres);

                Element tel = doc.createElement("phone");
                tel.appendChild(doc.createTextNode(h.getPhone()));
                te.appendChild(tel);
            }
            ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("clients.zip"));
            ZipEntry ze = new ZipEntry(CLIENTS_FILE);
            zout.putNextEntry(ze);
            transformer.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(CLIENTS_FILE)));
            BufferedInputStream in = new BufferedInputStream( new FileInputStream(CLIENTS_FILE));
            int length;
            byte[] buffer = new byte[1024];
            
            while((length = in.read(buffer)) > 0) {
                zout.write(buffer, 0, length);
            }
            in.close();
            zout.closeEntry();
            zout.close();
        }
        catch (FileNotFoundException e)
        {
            throw new InformationSystemUiException("Problem load file cause: ", e);
        }
        catch (TransformerConfigurationException e)
        {
            throw new InformationSystemUiException("Problem parse xml cause: ", e);
        }
        catch (TransformerException e)
        {
            throw new InformationSystemUiException("Problem transform xml cause: ", e);
        }
        catch (ParserConfigurationException e)
        {
            throw new InformationSystemUiException("Problem Initialization xml parser: ", e);

        } catch (IOException ex) {
            Logger.getLogger(XmlReaderWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public void writeOrdersToXml(Set<Order> ordersSet)
    {
        try
        {   
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Element RootElement = doc.createElement("Orders");
            doc.appendChild(RootElement);
            for (Order h : ordersSet)
            {
                Element te = doc.createElement("Order"); //te это тип элемента
                RootElement.appendChild(te);

                Element orderId = doc.createElement("order_id");
                orderId.appendChild(doc.createTextNode(Long.toString(h.getOrderId())));
                te.appendChild(orderId);
                
                Element name = doc.createElement("client_id");
                name.appendChild(doc.createTextNode(Long.toString(h.getClientId())));
                te.appendChild(name);

                Element adres = doc.createElement("order_date"); 
                adres.appendChild(doc.createTextNode( dateFormat.format(h.getOrderDate())));
                te.appendChild(adres);

                Element tel = doc.createElement("order_sum");
                tel.appendChild(doc.createTextNode(String.valueOf(h.getOrderSum())));
                te.appendChild(tel);
            }
            
            ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("orders.zip"));
            ZipEntry ze = new ZipEntry(ORDERS_FILE);
            zout.putNextEntry(ze);
            transformer.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(ORDERS_FILE)));
            BufferedInputStream in = new BufferedInputStream( new FileInputStream(ORDERS_FILE));
            int length;
            byte[] buffer = new byte[1024];
            
            while((length = in.read(buffer)) > 0) {
                zout.write(buffer, 0, length);
            }
            in.close();
            zout.closeEntry();
            zout.close();
        }
        catch (FileNotFoundException e)
        {
            throw new InformationSystemUiException("Problem load file cause: ", e);
        }
        catch (TransformerConfigurationException e)
        {
            throw new InformationSystemUiException("Problem parse xml cause: ", e);
        }
        catch (TransformerException e)
        {
            throw new InformationSystemUiException("Problem transform xml cause: ", e);
        }
        catch (ParserConfigurationException e)
        {
            throw new InformationSystemUiException("Problem Initialization xml parser: ", e);
        } catch (IOException ex) {
            Logger.getLogger(XmlReaderWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

} 


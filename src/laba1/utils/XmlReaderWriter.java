package laba1.utils;
import java.io.*;
import java.util.HashSet;
import java.util.Set;
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

public class XmlReaderWriter {
    private DocumentBuilderFactory dbf;
        
    public XmlReaderWriter()
    {
        this.dbf = DocumentBuilderFactory.newInstance();
    }
    
    public HashSet<Client> readClientsFromXml()
    {   
        HashSet<Client> clientsSet = new HashSet<Client>();
        try
        {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc=db.parse(new File(CLIENTS_FILE));
            doc.getDocumentElement().normalize();
            NodeList nodeLst=doc.getElementsByTagName("Client");
            for(int i = 0; i < nodeLst.getLength(); i++)
            {   
                NodeList client = nodeLst.item(i).getChildNodes();
                Long client_id = new Long(client.item(0).getLastChild().getTextContent());
                String name = client.item(1).getLastChild().getTextContent();
                String address = client.item(2).getLastChild().getTextContent();
                String phone = client.item(3).getLastChild().getTextContent();
                clientsSet.add(new Client(client_id, name, address, phone));
            }
        }
        catch(Exception ei)
        {
            System.out.println("Ошибка чтения БД "+ei);
        }
            return clientsSet;
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
                clientId.appendChild(doc.createTextNode(h.getId()));
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
            transformer.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(CLIENTS_FILE)));
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

        }
    }
} 


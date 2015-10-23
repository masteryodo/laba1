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

import laba1.dto.Client;
import laba1.exception.InformationSystemUiException;
import org.w3c.dom.*;

public class XmlReaderWriter {
    private File fileXml;
    private DocumentBuilderFactory dbf;
        
    public XmlReaderWriter()
    {
        this.dbf = DocumentBuilderFactory.newInstance();
    }
    
    public void readXml(File fileXML)
    {
        try
        {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc=db.parse(fileXML);
            doc.getDocumentElement().normalize();
            System.out.println("Root element ["+doc.getDocumentElement().getNodeName()+"]");
            
            /*NodeList nodeLst=doc.getElementsByTagName("point");
            System.out.println("Points");
            for(int je=0;je<nodeLst.getLength();je++)
            {
                Node fstNode=nodeLst.item(je);
                if(fstNode.getNodeType()==Node.ELEMENT_NODE)
                {
                    Element elj=(Element)fstNode;
                    NodeList nljx=elj.getElementsByTagName("x");
                    Element eljx=(Element)nljx.item(0);
                    NodeList nljxc=eljx.getChildNodes();
                    NodeList nljy=elj.getElementsByTagName("y");
                    Element eljy=(Element)nljy.item(0);
                    NodeList nljyc=eljy.getChildNodes();
                    
                    System.out.println(
                      "x, y ["+((Node)nljxc.item(0)).getNodeValue()+", "+((Node)nljyc.item(0)).getNodeValue()+"]"
                      );
                }
            }*/
        }
        catch(Exception ei)
        {

        }
    }
    
    public void writeClientsToXml(Set<Client> clientsSet)
    {
        try
        {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            //System.out.println("всего выгружается " + hs.size() + " объектов");
            Element RootElement = doc.createElement("Clients");
            //System.out.println("Создали " + RootElement.getTagName());
            doc.appendChild(RootElement);
            for (Client h : clientsSet)
            {
                Element te = doc.createElement("Client"); //te это тип элемента
                RootElement.appendChild(te);

                Element name = doc.createElement("name"); //добавляем имя
                name.appendChild(doc.createTextNode(h.getName()));
                te.appendChild(name);

                Element adres = doc.createElement("address"); //добавляем адрес
                adres.appendChild(doc.createTextNode(h.getAddress()));
                te.appendChild(adres);

                Element tel = doc.createElement("phone"); //добавляем телефон
                tel.appendChild(doc.createTextNode(h.getPhone()));
                te.appendChild(tel);
            }
            transformer.transform(new DOMSource(doc), new StreamResult(new FileOutputStream("Clients.xml")));
            //System.out.println("загрузка клиентов в файл завершена\n");
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


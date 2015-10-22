package laba1;
import java.io.*;
import java.util.HashSet;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class XmlReaderWriter {
    private File fileXml;
    private DocumentBuilderFactory dbf;
    private DocumentBuilder db;
        
    public XmlReaderWriter() throws ParserConfigurationException {
        this.dbf=DocumentBuilderFactory.newInstance();
        this.db=dbf.newDocumentBuilder();
    }
    
    public void readXml(File fileXML){
        try
        {
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
        catch(Exception ei){}
    }
    
    public void writeClientsToXml(HashSet<Client> hs) throws TransformerConfigurationException, TransformerException, FileNotFoundException{
        Document doc=db.newDocument();
        Transformer t=TransformerFactory.newInstance().newTransformer();
        System.out.println("всего выгружается "+hs.size()+" объектов");
        Element RootElement=doc.createElement("Clients");
        System.out.println("Создали "+RootElement.getTagName());
        doc.appendChild(RootElement);
        for (Client h : hs) {
            Element te=doc.createElement("Client"); //te это тип элемента
            RootElement.appendChild(te); 
            
                Element name=doc.createElement("name"); //добавляем имя
                name.appendChild(doc.createTextNode(h.getName()));
                te.appendChild(name);
                
                Element adres=doc.createElement("adres"); //добавляем адрес
                adres.appendChild(doc.createTextNode(h.getAdres()));
                te.appendChild(adres);
                
                Element tel=doc.createElement("tel"); //добавляем телефон
                tel.appendChild(doc.createTextNode(h.getTel()));
                te.appendChild(tel);
        }
    t.transform(new DOMSource(doc), new StreamResult(new FileOutputStream("Clients.xml")));
        System.out.println("загрузка клиентов в файл завершена\n");
    }
} 


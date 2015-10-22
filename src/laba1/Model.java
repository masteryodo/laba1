
package laba1;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

public class Model {
    private final String helpString= "Для выполнения операции используйте следующие аргументы: \n" +
                            "s режим просмотра пример: s клиенты(Тип)\n" +
                            "m режим изменения\n" +
                            "f режим поиска\n" +
                            "add режим добавления";
    private HashSet<Client> clientsSet = new HashSet<Client>();
    private HashSet<Order> ordersSet = new HashSet<Order>();
    private View v;
    private XmlReaderWriter xml = new XmlReaderWriter();

    public Model(View v) throws ParserConfigurationException {
        this.v = v;
    }
    
    public void showHelp(){
        v.showInfo(helpString);
            }
    public void showAllElements(HashSet<Client> collSet){
        System.out.println(collSet);
    }
    
    public void addClient(ArrayList<String> arr){
        Client cl = new Client(arr.get(0), arr.get(1), arr.get(2));
        clientsSet.add(cl);
            }
    public void saveToXML() throws TransformerException, TransformerConfigurationException, FileNotFoundException{
        xml.writeClientsToXml(clientsSet);
    }
 
    private void loadClientsFromXML(){
    
    }
    private void loadOrdersFromXML(){
    
    }
}
package laba1;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

public class Controller {
    private Model m;
    public Controller(Model m) {
        this.m = m;
    }
    
    public void getCommand() throws TransformerException, TransformerConfigurationException, FileNotFoundException {
    String s1 = "";
        Scanner sc = new Scanner(System.in);
        do { System.out.print("spr1.0: ");
             s1 = sc.nextLine();
             if(s1.equals("help")) {m.showHelp();}
             if(s1.equals("s")) { }
             if(s1.equals("add")) {add();}
             if(s1.equals("exit")) {exitDialog();}
        }while (!s1.equals("exit"));
    }
    
    public void add(){
        String str = "";
        System.out.println("Выберите тип элемента");
        System.out.println("1 Клиент");
        System.out.println("2 Заказ");
        Scanner addScanner = new Scanner(System.in);
        str = addScanner.nextLine();
        
        if (str.equals("1")){
            ArrayList<String> elementParams = new ArrayList<>();
            System.out.print("Введите имя клиента: ");
            elementParams.add(addScanner.nextLine());
            System.out.print("Введите адрес клиента: ");
            elementParams.add(addScanner.nextLine());
            System.out.print("Введите телефон клиента: ");
            elementParams.add(addScanner.nextLine());
            m.addClient(elementParams);
    }
    }

    private void exitDialog() throws TransformerException, TransformerConfigurationException, FileNotFoundException {
        System.out.print("Сохранить изменения ? (y/n): ");
        Scanner addScanner = new Scanner(System.in);
        if(addScanner.nextLine().equals("y")){
            m.saveToXML();
        }
    }
}

package laba1.controller;
import laba1.dto.Client;
import laba1.model.InformationSystemModel;
import laba1.view.View;
import laba1.utils.GenerateId;
import java.util.Scanner;

public class Controller
{
    private final InformationSystemModel model;
    private final View view;


    public Controller(InformationSystemModel model)
    {
        this.model = model;
        view = new View(model);
    }
    
    public void getCommand()
    {
        String s1 = "";
        Scanner sc = new Scanner(System.in);
        do
        { System.out.print("spr1.0: ");
             s1 = sc.nextLine();
             switch (s1)
             {
                 case "help" : view.showHelp(); break;
                 case "show" : show();          break;
                 case "add"  : add();           break;
                 case "exit" : exitDialog();    break;
                 default: 
                 System.out.println("Вы ввели неподходящий символ для справки наберите help");
             }
        }
        while (!s1.equals("exit"));
    }
    
    public void add()
    {
        String str = "";
        System.out.println("Выберите тип элемента");
        System.out.println("1 Клиент");
        System.out.println("2 Заказ");
        Scanner addScanner = new Scanner(System.in);
        str = addScanner.nextLine();
        
        switch( str )
        {   
            case "1" :
                System.out.print("Введите имя клиента: ");
                String clientName = addScanner.nextLine();
                System.out.print("Введите адрес клиента: ");
                String clientAddress = addScanner.nextLine();
                System.out.print("Введите телефон клиента: ");
                String clientPhone =  addScanner.nextLine();
                Client client = new Client(GenerateId.getId(),clientName, clientAddress, clientPhone);
                model.addClient(client);
                break;
            case "2" :
                System.out.println("Введите идентификатор клиента");
                view.showClients();
                break;
            default :
                System.out.println("Вы ввели неверное значение " + str + "попробуйте заново");
        }
    }

    private void exitDialog()
    {
        System.out.println("До свидания!");
    }

    private void show() {
        String str = "";
        System.out.println("Выберите тип элемента");
        System.out.println("1 Клиенты");
        System.out.println("2 Заказы");
        Scanner addScanner = new Scanner(System.in);
        str = addScanner.nextLine();
        
        switch ( str )
        {   
            case "1" :
                view.showClients(); 
                break;
            case "2" :
                System.out.println("не реализовано пока");
                break;
            default: 
            System.out.println("Вы ввели неверное значение " + str + "попробуйте заново");
        }
    }
}

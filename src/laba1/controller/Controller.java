package laba1.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import laba1.dto.Client;
import laba1.model.InformationSystemModel;
import laba1.view.View;
import laba1.utils.GenerateId;
import java.util.Scanner;
import laba1.dto.Order;

public class Controller
{
    private final InformationSystemModel model;
    private final View view;
    private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    public Controller(InformationSystemModel model)
    {
        this.model = model;
        view = new View(model);
    }
    
    public void getCommand() throws ParseException
    {
        String s1 = "";
        Scanner sc = new Scanner(System.in);
        do
        { System.out.print("spr1.0: ");
             s1 = sc.nextLine();
             switch (s1)
             {
                 case "help"    : view.showHelp(); break;
                 case "show"    : show();          break;
                 case "add"     : add();           break;
                 case "remove"  : remove();        break;
                 case "exit"    : exitDialog();    break;
                 default: 
                 System.out.println("Вы ввели неподходящий символ для справки наберите help");
             }
        }
        while (!s1.equals("exit"));
    }
    
    public void add() throws ParseException
    {
        String str = "";
        System.out.println("Выберите тип элемента");
        System.out.println("1 Клиент");
        System.out.println("2 Заказ");
        System.out.print("spr1.0: ");
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
                System.out.println("Введите идентификатор клиента, для удобства используйте Copy / Paste");
                view.showClients();
                System.out.print("spr1.0: ");
                long clientId = Long.parseLong(addScanner.nextLine());
                long orderId = GenerateId.getId();
                System.out.print("Введите дату в формате дд.мм.гггг: ");
                Date orderDate = null;
                double orderSum = 0;
                try {
                    orderDate = format.parse(addScanner.nextLine());
                    System.out.print("Введите сумму заказа: ");
                    orderSum = Double.parseDouble(addScanner.nextLine());
                } catch (Exception e) {
                    System.out.println("Вы ввели неверное значение попробуйте заново");
                    return;
                }
                
                if (orderDate != null && orderSum != 0) {
                    Order order = new Order(orderId, clientId, orderDate, orderSum);
                    model.addOrder(order);
                }
                else 
                {
                    System.out.println("Ошибка ввода данных!");
                }
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
        System.out.print("spr1.0: ");
        Scanner addScanner = new Scanner(System.in);
        str = addScanner.nextLine();
        
        switch ( str )
        {   
            case "1" :
                view.showClients(); 
                break;
            case "2" :
                view.showOredrs();
                break;
            default: 
            System.out.println("Вы ввели неверное значение " + str + "попробуйте заново");
        }
    }

    private void remove() {
        String str = "";
        System.out.println("Выберите тип элемента");
        System.out.println("1 Клиент");
        System.out.println("2 Заказ");
        System.out.print("spr1.0: ");
        Scanner addScanner = new Scanner(System.in);
        str = addScanner.nextLine();
        long id;
        
        switch( str )
        {   
            case "1" :
                System.out.println("Введите ID клиента для удаления, для удобства используйте Copy / Paste");
                view.showClients();
                id = Long.valueOf(addScanner.nextLine());
                System.out.println(id);
                model.removeClient(id);
                break;
            case "2" :
                System.out.println("Введите ID клиента для удаления, для удобства используйте Copy / Paste");
                view.showOredrs();
                id = Long.getLong(addScanner.nextLine());
                model.removeOrder(id);
                break;
            default :
                System.out.println("Вы ввели неверное значение " + str + "попробуйте заново");
        }
    }
    private void modify() {
        String str = "";
        System.out.println("Выберите тип элемента");
        System.out.println("1 Клиент");
        System.out.println("2 Заказ");
        System.out.print("spr1.0: ");
        Scanner addScanner = new Scanner(System.in);
        str = addScanner.nextLine();
        long id;
        
        switch( str )
        {   
            case "1" :
                System.out.println("в разработке");
                view.showClients();
                id = Long.valueOf(addScanner.nextLine());
                
                
                break;
            case "2" :
                System.out.println("в разработке");
                view.showOredrs();
                id = Long.getLong(addScanner.nextLine());

                break;
            default :
                System.out.println("Вы ввели неверное значение " + str + "попробуйте заново");
        }
    }
}

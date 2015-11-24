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
    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    public Controller(InformationSystemModel model)
    {
        this.model = model;
        view = new View(model);
    }
    
    public void getCommand() throws ParseException
    {
        String s1;
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
                 case "modify"  : modify();        break;
                 case "find"    : find();          break;
                 case "import"  : importFromXml(); break;
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
                long clientId = Long.valueOf(addScanner.nextLine());
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
        String str;
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
        String str;
        System.out.println("Выберите тип элемента");
        System.out.println("1 Клиент");
        System.out.println("2 Заказ");
        System.out.print("spr1.0: ");
        Scanner addScanner = new Scanner(System.in);
        str = addScanner.nextLine();
        long id = 0;
        
        switch( str )
        {   
            case "1" :
                System.out.println("Введите ID клиента для удаления, для удобства используйте Copy / Paste");
                view.showClients();
                System.out.print("spr1.0: ");
                id = Long.valueOf(addScanner.nextLine());
                System.out.println("Удаляется клиент с id " + id);
                model.removeClient(id);
                break;
            case "2" :
                System.out.println("Введите ID клиента для удаления, для удобства используйте Copy / Paste");
                view.showOredrs();
                System.out.print("spr1.0: ");
                id = Long.valueOf(addScanner.nextLine());
                model.removeOrder(id);
                break;
            default :
                System.out.println("Вы ввели неверное значение " + str + "попробуйте заново");
        }
    }
    private void modify() throws ParseException {
        String str;
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
                System.out.println("Введите ID клиента для изменения, для удобства используйте Copy / Paste");
                view.showClients();
                System.out.print("spr1.0: ");
                id = Long.valueOf(addScanner.nextLine());
                Client client = model.getClientById(id);
                if (client != null)
                {
                    System.out.print("Имя клиента: " + client.getName()+" введите новое имя (Enter - оставить без изменений): ");
                    String clientName = addScanner.nextLine();
                    if( !clientName.equals("")) {client.setName(clientName);}
                    
                    System.out.print("Адрес клиента: " + client.getAddress() + " введите новый адрес (Enter - оставить без изменений): ");
                    String clientAddress = addScanner.nextLine();
                    if( !clientAddress.equals("")) {client.setAddress(clientAddress);}
                    
                    System.out.print("Телефон клиента: " + client.getPhone() + " введите новый телефон (Enter - оставить без изменений): ");
                    String clientPhone =  addScanner.nextLine();
                    if( !clientPhone.equals("")) { client.setPhone(clientPhone); }
                }
                else {System.out.println("Неверный ID клиента !");}
                model.commitClients();
                break;
            case "2" :
                System.out.println("Введите ID заказа для изменения, для удобства используйте Copy / Paste");
                view.showOredrs();
                System.out.print("spr1.0: ");
                id = Long.valueOf(addScanner.nextLine());
                Order order = model.getOrderById(id);
                
                System.out.println("Тек значение: " + order.getClientId() + 
                                 " Введите ID клиента(Enter - оставить без изменений): ");
                view.showClients();
                System.out.print("spr1.0: ");
                String client_id = addScanner.nextLine();
                
                if( !client_id.equals("")) 
                {   
                    if (model.getClientById(Long.valueOf(client_id)) != null)
                    {
                        order.setClientId(Long.valueOf(client_id));
                    }
                    else 
                    {
                        System.out.println("Нет такого клиента попробуйте занаво");
                        return;
                    }
                }
                
                System.out.print("Тек значение: " + format.format(order.getOrderDate()) + 
                                 " Введите дату в формате дд.мм.гггг(Enter - оставить без изменений): ");
                String orderDate = addScanner.nextLine();
                
                if( !orderDate.equals("")) 
                {
                    order.setOrderDate(orderDate);
                }
                
                System.out.print("Тек значение: " +order.getOrderSum() + "Введите сумму заказа(Enter - оставить без изменений): ");
                String orderSumm = addScanner.nextLine();
                if( !orderSumm.equals("")) 
                {
                    try {
                        order.setOrderSum(Double.parseDouble(orderSumm));
                    } catch (Exception e) {
                        System.out.println("Ошибка ввода данных "+e);
                    }
                } 
                model.commitOrders();
                
                break;
            default :
                System.out.println("Вы ввели неверное значение " + str + "попробуйте заново");
        }
    }

    private void find() {
        String str;
        System.out.println("Выберите тип элемента");
        System.out.println("1 Клиент");
        System.out.println("2 Заказ");
        System.out.print("spr1.0: ");
        Scanner scanner = new Scanner(System.in);
        str = scanner.nextLine();
        String mask;
        switch( str )
        {   
            case "1" :
                System.out.println("Введите маску для поиска клиента (возможны символы '*' и '?')");
                System.out.print("spr1.0: ");
                mask = scanner.nextLine();
                model.findClientsByMask(mask);
                view.showLastSearchClients();
                break;
            case "2" :
                System.out.println("Введите маску для поиска заказа (возможны символы '*' и '?')");
                System.out.print("spr1.0: ");
                mask = scanner.nextLine();
                model.findOrdersByMask(mask);
                view.showLastSearchOrders();
                break;
            default :
                System.out.println("Вы ввели неверное значение " + str + "попробуйте заново");
        }
    }
    private void importFromXml() {
        String str = "";
        System.out.println("Введите имя файла (можно использовать подготовленный для теста data2import.xml)");
        System.out.print("spr1.0: ");
        Scanner scanner = new Scanner(System.in);
        str = scanner.nextLine();
        model.importElementsFromXml(str);
        try {
            
            System.out.println("Импорт успешно завершен");
        } catch (Exception e) {
            System.out.println("Импорт завершился ошибкой "+e);
        }
        
    }
}

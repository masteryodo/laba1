
package laba1.view;

import java.text.SimpleDateFormat;
import java.util.Set;
import laba1.dto.*;
import laba1.model.InformationSystemModel;
import static laba1.Constants.*;

public class View
{
    private final InformationSystemModel model;
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    public View(InformationSystemModel model)
    {
        this.model = model;
    }

    public void showHelp()
    {
        StringBuilder showView = new StringBuilder();
        showView.append("Для выполнения операции используйте следующие аргументы: \n")
                .append("show режим просмотра\n")
                .append("modify режим изменения\n")
                .append("find режим поиска\n")
                .append("add режим добавления\n")
                .append("remove режим удаления\n")
                .append("import режим импортирования из файла\n");
        
        System.out.println(showView.toString());
    }

    public void showClients()
    {
        for(Client client: model.getClients())
        {
            System.out.println(client.getId() + " \t\t" + client.getName() + " \t\t" + client.getPhone() + " \t\t" + client.getAddress());
        }
    }
    public void showOredrs()
    {   
        Set<Order> orders = model.getOrders();
        if (orders != null) 
        {   
            System.out.println("order_id \t\t client_id \t\t date \t\t\t summ \t\t client_name");
            for(Order order: orders )
            {
                System.out.print(order.getOrderId() + 
                "\t\t" + order.getClientId() +
                "\t\t" + format.format(order.getOrderDate()) + "\t\t" + order.getOrderSum());
                try 
                {
                    System.out.println("\t\t" + model.getClientById(order.getClientId()).getName());
                } 
                catch (Exception e) {
                    System.out.println("\t\t!!! НЕТ ТАКОГО КЛИЕНТА В БАЗЕ !!!");
                }
            }
        }
        else { System.out.println("Нет заказов для отображения"); }
        
    }
    
    public void showInfo(String arg)
    {


    }
    
    public void showElements(String arg){
        
            }
    
}

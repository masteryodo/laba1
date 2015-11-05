
package laba1.view;

import laba1.dto.*;
import laba1.model.InformationSystemModel;

public class View
{
    private final InformationSystemModel model;

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
        for(Order order: model.getOrders())
        {
            System.out.println(order.getOrderId() + "\t\t" + model.getClientById(order.getClientId()).getName() +
                    "\t\t" + order.getOrderDate().toString() + "\t\t" + order.getOrderSum());
        }
    }
    
    public void showInfo(String arg)
    {


    }
    
    public void showElements(String arg){
        
            }
    
}

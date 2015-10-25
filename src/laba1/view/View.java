
package laba1.view;

import laba1.dto.Client;
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
                .append("add режим добавления")
                .append("remove режим удаления")
                .append("import режим импортирования из файла");
        
        System.out.println(showView.toString());
    }

    public void showClients()
    {
        for(Client client: model.getClients())
        {
            System.out.println(client.getId() +" -> "+client.getName());
        }
    }
    
    public void showInfo(String arg)
    {


    }
    
    public void showElements(String arg){
        
            }
    
}


package laba1.view;

import laba1.dto.Client;
import laba1.model.InformationSystemModel;

public class View
{
    private final InformationSystemModel model;

    public View(InformationSystemModel model)
    {
        this.model = model;
        //TODO:need create method for show spravka
        /*System.out.println("Добрый день! вы в программе Справка 1.0");
        System.out.println("Если вам нужна помощь наберите help и нажмите enter");
        System.out.println("Для выхода наберите exit и нажмите enter");*/
    }

    public void showHelp()
    {
        StringBuilder showView = new StringBuilder();
        showView.append("Для выполнения операции используйте следующие аргументы: \n")
                .append("s режим просмотра пример: s клиенты(Тип)\n")
                .append("m режим изменения\n")
                .append("f режим поиска\n")
                .append("add режим добавления");
        System.out.println(showView.toString());
    }

    public void showClient()
    {
        for(Client client: model.getClients())
        {
            System.out.println("Hello client " + client.getId());
        }
    }
    
    public void showInfo(String arg)
    {


    }
    
    public void showElements(String arg){
        
            }
    
}

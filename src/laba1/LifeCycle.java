
package laba1;

import java.text.ParseException;
import laba1.controller.Controller;
import laba1.model.InformationSystemModel;

import javax.xml.parsers.ParserConfigurationException;

public class LifeCycle
{
    public static void main(String[] args) throws ParserConfigurationException, ParseException
    {
        InformationSystemModel informationSystemModel = new InformationSystemModel();
        Controller controller = new Controller(informationSystemModel);
        System.out.println("Добрый день! вы в программе Справка 1.0");
        System.out.println("Если вам нужна помощь наберите help и нажмите enter");
        System.out.println("Для выхода наберите exit и нажмите enter");
        controller.getCommand();
    }
    
}

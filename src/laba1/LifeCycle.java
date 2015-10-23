
package laba1;

import laba1.controller.Controller;
import laba1.model.InformationSystemModel;

import javax.xml.parsers.ParserConfigurationException;

public class LifeCycle
{
    public static void main(String[] args) throws ParserConfigurationException
    {
        InformationSystemModel informationSystemModel = new InformationSystemModel();
        Controller controller = new Controller(informationSystemModel);
        controller.getCommand();
    }
    
}

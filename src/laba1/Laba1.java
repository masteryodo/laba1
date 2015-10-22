
package laba1;

import javax.xml.parsers.ParserConfigurationException;

public class Laba1 {

    public static void main(String[] args) throws ParserConfigurationException {
        View v = new View();
        Model model = new Model(v);
        Controller controller = new Controller(model);
        controller.getCommand();
        
    }
    
}

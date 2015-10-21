
package laba1;

public class Laba1 {

    public static void main(String[] args) {
        View v = new View();
        Model model = new Model(v);
        Controller controller = new Controller(model);
        controller.getCommand();
    }
    
}

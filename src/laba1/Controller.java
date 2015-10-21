package laba1;

import java.util.Scanner;

public class Controller {
    private Model m;
    public Controller(Model m) {
        this.m = m;
    }
    
    
    public void getCommand() {
    String s1 = "";
        Scanner sc = new Scanner(System.in);
        do { System.out.print("spr1.0: ");
             s1 = sc.nextLine();
             
             if(s1.equals("help")) {m.showHelp();}
             if(s1.equals("s")) { }
             
        }while (!s1.equals("exit"));
    }
    
}

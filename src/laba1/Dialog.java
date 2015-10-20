
package laba1;

import java.util.Scanner;

public class Dialog{

    public void sayHello(){
        System.out.println("Добрый день! вы в программе Справка 1.0");
        System.out.println("Если вам нужна помощь наберите help и нажмите enter");
        System.out.println("Для выхода наберите exit и нажмите enter");
        getCommand();
    }
    
    public void getCommand() {
    System.out.print("spr1.0: ");
    String s1 = "";
        Scanner sc = new Scanner(System.in);
        while (!s1.equals("exit")) {
             s1 = sc.nextLine();
             if(s1.equals("help")) {showHelp();}
        }
    }
    
    public void showHelp(){
        System.out.println("Для выполнения операции используйте следующие аргументы: ");
        System.out.println("-s просмотреть все записи");
        System.out.println("-m модифицировать запись, через пробел номер записи");
            }
    
    
}

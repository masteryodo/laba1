
package laba1;

public class Model {
    private final String helpString= "Для выполнения операции используйте следующие аргументы: \n" +
                            "s режим просмотра пример: s клиенты(Тип)\n" +
                            "m режим изменения\n" +
                            "f режим поиска\n" +
                            "add режим добавления";
        
    private View v;
    public Model(View v) {
        this.v = v;
    }
    
    public void showHelp(){
        v.showInfo(helpString);
            }
    
}
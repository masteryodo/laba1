
package laba1.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import laba1.dto.Client;
import laba1.dto.Order;

public class Finder {

    /**
     * Поиск Клиентов по маске
     * @param clients Set в котором нужно найти совпадения
     * @param mask маска по которой ищется совпадение с учетом спецсимволов * и ?
     * @return возвращает HashSet клдиентов которые попадают под условия поиска
     */
    public Set<Client> findClientsByMask(Set<Client> clients, String mask){
        HashSet<Client> resultSet = new HashSet<Client>();
        for (Client client : clients) 
        {
            if(findMatchByMask(client.toString(), mask) == true )
            {
                resultSet.add(client);
            }
        }
        return resultSet;
    }
    
    /**
     * Поиск ордеров по маске
     * @param orders Set ордеров в котором нужно найти совпадения
     * @param mask маска по которой ищется совпадение с учетом спецсимволов * и ?
     * @return возвращает HashSet ордеров которые попадают под условия поиска
     */
    public Set<Order> findOrdersByMask(Set<Order> orders, String mask){
        HashSet<Order> resultSet = new HashSet<Order>();
        for (Order order : orders) 
        {
            if(findMatchByMask(order.toString(), mask) == true )
            {
                resultSet.add(order);
            }
        }
        return resultSet;
    }
    
    /**
     * Метод для поиска совпадений в строке по маске с учетом спецсимволов * и ?
     * @param inString - строка в которой нужно найти совпадение
     * @param maskStr - строка которую нужно найти с учетом спецсимволов * и ?
     * @return - возвращает true/false
     */
    public boolean findMatchByMask(String inString, String maskStr){
        boolean result = false;
        String str = inString;
        StringBuilder mask = new StringBuilder();
        mask.append("^");
        maskStr = maskStr.replace("*", ".*");
        maskStr = maskStr.replace("?", ".");
        mask.append(maskStr);
        mask.append("$");
        Pattern p = Pattern.compile(mask.toString());
        Matcher m = p.matcher(str);
        int counter = 0;
            while(m.find()) { counter++; }
        if (counter > 0) 
        {
            result = true;
        }
        return result;    
    }
}

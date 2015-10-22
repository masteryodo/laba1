
package laba1;

public class Client {
    private static int id;
    private String name;
    private String adres;
    private String tel;

    public Client(String name, String adres, String tel) {
        this.id = id+1;
        this.name = name;
        this.adres = adres;
        this.tel = tel;
    }

    @Override
    public int hashCode() {
        return (name+adres+tel).hashCode();
    }

    public static void setId(int id) {
        Client.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public static int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAdres() {
        return adres;
    }

    public String getTel() {
        return String.valueOf(tel);
    }
    

}

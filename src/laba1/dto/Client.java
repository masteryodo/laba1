
package laba1.dto;

import java.io.Serializable;
import java.util.Set;

public class Client implements Serializable
{
    private final long id;
    private String name;
    private String address;
    private String phone;
    private Set<Order> orders;

    public Client(final long id, final String name, final String address, final String phone)
    {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public int hashCode()
    {
        return (name + address + phone).hashCode();
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getAddress()
    {
        return address;
    }

    public String getPhone()
    {
        return phone;
    }
    

}

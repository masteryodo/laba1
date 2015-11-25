
package laba1.dto;

public class Client
{
    private final long id;
    private String name;
    private String address;
    private String phone;

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
    
    @Override
    public boolean equals(Object obj){
        if (this == obj)
        {
            return true;
        }
	if (obj == null)
        { 
            return false; 
        }
        if (getClass() != obj.getClass())
        { 
            return false; 
        }
        Client other = (Client) obj;
	if (!name.equals(other.name))
        {
            return false;
        }
	if (!address.equals(other.address))
        {
            return false;
        }
	if (!phone.equals(other.phone))
        {
            return false; 
        }
	return true;
    }
    
    @Override
    public String toString() 
    {
        String res = String.valueOf(getId()) + getName() + getAddress() + getPhone();
        return res;
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

package project.database.DTO;

public class ArchiveDTO
{
    private int id;
    private String name;
    private String address;

    public ArchiveDTO(int id , String name , String address)
    {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getId()
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

    public String toString()
    {
        return name + " " + address;
    }
}

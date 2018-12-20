package project.userinterface.entities;

import project.database.DTO.ArchiveDTO;

public class Archive
{
    private int id;
    private String name;
    private String address;

    Archive(int id , String name , String address)
    {
        this.id = id;
        this.name = name;
        this.address = address;
    }
    Archive(ArchiveDTO archive)
    {
        this.id = archive.getId();
        this.name = archive.getName();
        this.address = archive.getAddress();
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

    public ArchiveDTO toDTO()
    {
        return new ArchiveDTO(id , name , address);
    }

    @Override
    public String toString()
    {
        return name + " " + address;
    }
}

package project.logic.entities;

import project.database.DTO.ClientDTO;

public class Client
{
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private int archiveId;
    private int fineSum;

    Client(int id , String firstName , String middleName , String lastName , int archiveId , int fineSum)
    {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.archiveId = archiveId;
        this.fineSum = fineSum;
    }

    Client(ClientDTO client)
    {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.middleName = client.getMiddleName();
        this.lastName = client.getLastName();
        this.archiveId = client.getArchiveId();
        this.fineSum = client.getFineSum();
    }

    public int getId()
    {
        return id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public int getArchiveId()
    {
        return archiveId;
    }

    public int getFineSum()
    {
        return fineSum;
    }

    public ClientDTO toDTO()
    {
        return new ClientDTO(id , firstName , middleName , lastName , archiveId , fineSum);
    }

    public String toString()
    {
        return firstName + " " + middleName + " " + lastName;
    }
}

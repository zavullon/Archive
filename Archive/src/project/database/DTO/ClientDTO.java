package project.database.DTO;

public class ClientDTO
{
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private int archiveId;
    private int fineSum;

    public ClientDTO(int id , String firstName , String middleName , String lastName , int archiveId , int fineSum)
    {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.archiveId = archiveId;
        this.fineSum = fineSum;
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

    public String toString()
    {
        return firstName + " " + middleName + " " + lastName;
    }
}

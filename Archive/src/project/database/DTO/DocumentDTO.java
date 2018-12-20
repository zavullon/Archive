package project.database.DTO;

public class DocumentDTO
{
    private int id;
    private int archiveId;
    private String name;
    private int amountOfCopies;
    private int amountOfCopiesInUse;
    private int fine;

    public DocumentDTO(int id , int archiveId , String name , int amountOfCopies , int amountOfCopiesInUse , int fine)
    {
        this.id = id;
        this.archiveId = archiveId;
        this.name = name;
        this.amountOfCopies = amountOfCopies;
        this.amountOfCopiesInUse = amountOfCopiesInUse;
        this.fine = fine;
    }

    public int getId()
    {
        return id;
    }

    public int getArchiveId()
    {
        return archiveId;
    }

    public String getName()
    {
        return name;
    }

    public int getAmountOfCopies()
    {
        return amountOfCopies;
    }

    public int getAmountOfCopiesInUse()
    {
        return amountOfCopiesInUse;
    }

    public int getFine()
    {
        return fine;
    }

    public String toString()
    {
        return name;
    }
}

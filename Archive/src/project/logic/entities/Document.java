package project.logic.entities;

import project.database.DTO.DocumentDTO;

public class Document
{
    private int id;
    private int archiveId;
    private String name;
    private int amountOfCopies;
    private int amountOfCopiesInUse;
    private int fine;

    Document(int id , int archiveId , String name , int amountOfCopies , int amountOfCopiesInUse , int fine)
    {
        this.id = id;
        this.archiveId = archiveId;
        this.name = name;
        this.amountOfCopies = amountOfCopies;
        this.amountOfCopiesInUse = amountOfCopiesInUse;
        this.fine = fine;
    }

    Document(DocumentDTO document)
    {
        this.id = document.getId();
        this.archiveId = document.getArchiveId();
        this.name = document.getName();
        this.amountOfCopies = document.getAmountOfCopies();
        this.amountOfCopiesInUse = document.getAmountOfCopiesInUse();
        this.fine = document.getFine();
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

    public DocumentDTO toDTO()
    {
        return new DocumentDTO(id , archiveId , name , amountOfCopies , amountOfCopiesInUse , fine);
    }

    public String toString()
    {
        return name;
    }
}

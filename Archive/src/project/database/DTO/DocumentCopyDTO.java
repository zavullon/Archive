package project.database.DTO;

import project.util.Date;

public class DocumentCopyDTO
{
    private int id;
    private int documentId;
    private String status;
    private Date takenDate;
    private Date returnDate;

    public DocumentCopyDTO(int id , int documentId , String status , Date takenDate , Date returnDate)
    {
        this.id = id;
        this.documentId = documentId;
        this.status = status;
        this.takenDate = takenDate;
        this.returnDate = returnDate;
    }

    public int getId()
    {
        return id;
    }

    public int getDocumentId()
    {
        return documentId;
    }

    public String getStatus()
    {
        return status;
    }

    public Date getTakenDate()
    {
        return takenDate;
    }

    public Date getReturnDate()
    {
        return returnDate;
    }

    public String toString()
    {
        return documentId + " " + status + " " + takenDate.toString() + " " + returnDate.toString();
    }
}

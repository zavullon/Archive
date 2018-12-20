package project.logic.entities;

import project.database.DTO.DocumentCopyDTO;
import project.util.Date;

public class DocumentCopy
{
    private int id;
    private int documentId;
    private String status;
    private Date takenDate;
    private Date returnDate;

    DocumentCopy(int id , int documentId , String status , Date takenDate , Date returnDate)
    {
        this.id = id;
        this.documentId = documentId;
        this.status = status;
        this.takenDate = takenDate;
        this.returnDate = returnDate;
    }

    DocumentCopy(DocumentCopyDTO documentCopy)
    {
        this.id = documentCopy.getId();
        this.documentId = documentCopy.getDocumentId();
        this.status = documentCopy.getStatus();
        this.takenDate = documentCopy.getTakenDate();
        this.returnDate = documentCopy.getReturnDate();
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

    public DocumentCopyDTO toDTO()
    {
        return new DocumentCopyDTO(id , documentId , status , takenDate , returnDate);
    }

    public String toString()
    {
        return documentId + " " + status + " " + takenDate.toString() + " " + returnDate.toString();
    }
}

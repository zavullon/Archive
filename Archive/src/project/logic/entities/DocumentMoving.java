package project.logic.entities;

import project.database.DTO.DocumentMovingDTO;
import project.util.Date;

public class DocumentMoving
{
    private int clientId;
    private int copyId;
    private int employeeId;
    private String type;
    private Date date;

    public DocumentMoving(int clientId , int copyId , int employeeId , String type , Date date)
    {
        this.clientId = clientId;
        this.copyId = copyId;
        this.employeeId = employeeId;
        this.type = type;
        this.date = date;
    }

    DocumentMoving(DocumentMovingDTO documentMoving)
    {
        this.clientId = documentMoving.getClientId();
        this.copyId = documentMoving.getCopyId();
        this.employeeId = documentMoving.getEmployeeId();
        this.type = documentMoving.getType();
        this.date = documentMoving.getDate();
    }

    public int getClientId()
    {
        return clientId;
    }

    public int getCopyId()
    {
        return copyId;
    }

    public int getEmployeeId()
    {
        return employeeId;
    }

    public String getType()
    {
        return type;
    }

    public Date getDate()
    {
        return date;
    }

    public DocumentMovingDTO toDTO()
    {
        return new DocumentMovingDTO(clientId , copyId , employeeId , type , date);
    }

    public String toString()
    {
        return clientId + " " + copyId + " " + employeeId + " " + type + " " + date.toString();
    }
}

package project.database.DTO;

import project.util.Date;

public class DocumentMovingDTO
{
    private int clientId;
    private int copyId;
    private int employeeId;
    private String type;
    private Date date;

    public DocumentMovingDTO(int clientId , int copyId , int employeeId , String type , Date date)
    {
        this.clientId = clientId;
        this.copyId = copyId;
        this.employeeId = employeeId;
        this.type = type;
        this.date = date;
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

    public String toString()
    {
        return clientId + " " + copyId + " " + employeeId + " " + type + " " + date.toString();
    }
}

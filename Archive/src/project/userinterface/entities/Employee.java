package project.userinterface.entities;

import project.database.DTO.EmployeeDTO;

public class Employee
{
    private int id;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private int archiveId;
    private String job;
    private int workHoursLastMonth;
    private int salary;

    public Employee(int id                          ,
             String password                 ,
             String firstName                ,
             String middleName               ,
             String lastName                 ,
             int archiveId                   ,
             String job                      ,
             int workHoursLastMonth          ,
             int salary)
    {
        this.id = id;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.archiveId = archiveId;
        this.job = job;
        this.workHoursLastMonth = workHoursLastMonth;
        this.salary = salary;
    }

    public Employee(EmployeeDTO employee)
    {
        this.id = employee.getId();
        this.password = employee.getPassword();
        this.firstName = employee.getFirstName();
        this.middleName = employee.getMiddleName();
        this.lastName = employee.getLastName();
        this.archiveId = employee.getArchiveId();
        this.job = employee.getJob();
        this.workHoursLastMonth = employee.getWorkHoursLastMonth();
        this.salary = employee.getSalary();
    }

    public Employee(int id , String password)
    {
        this.id = id;
        this.password = password;
    }

    public int getId()
    {
        return id;
    }

    public String getPassword()
    {
        return password;
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

    public String getJob()
    {
        return job;
    }

    public int getWorkHoursLastMonth()
    {
        return workHoursLastMonth;
    }

    public int getSalary()
    {
        return salary;
    }

    public EmployeeDTO toDTO()
    {
        return new EmployeeDTO(id , password , firstName , middleName , lastName , archiveId , job , workHoursLastMonth , salary);
    }

    public String toString()
    {
        return firstName + " " + middleName + " " + lastName + " " + job;
    }
}

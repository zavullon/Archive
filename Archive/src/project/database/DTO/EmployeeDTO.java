package project.database.DTO;

public class EmployeeDTO
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

    public EmployeeDTO(int id ,
                       String password ,
                       String firstName ,
                       String middleName ,
                       String lastName ,
                       int archiveId ,
                       String job ,
                       int workHoursLastMonth ,
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

    public String toString()
    {
        return firstName + " " + middleName + " " + lastName + " " + job;
    }
}

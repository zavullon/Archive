package project.database;

import project.database.DTO.EmployeeDTO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class EmployeeSQLAccessObject implements EmployeeDAO
{
    private static EmployeeSQLAccessObject ourInstance = new EmployeeSQLAccessObject();
    private Connection connection;
    private String url = "jdbc:sqlserver://169.254.220.86:63293;database=Archive";
    private String login = "admin";
    private String password = "QEADZCwsx";

    public static EmployeeSQLAccessObject getInstance()
    {
        return ourInstance;
    }

    private EmployeeSQLAccessObject()
    {
        try
        {
            connection = DriverManager.getConnection(url , login , password);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public EmployeeDTO getById(int id)
     {
        try(PreparedStatement statement = connection.prepareStatement("" +
                "select Employee_ID , Password , Employee_First_Name , Employee_Middle_Name , Employee_Last_Name , Archive_ID , Job_Name , Work_Hours_Last_Month , Salary " +
                "from Employee " +
                "where Employee_ID = ?"))
        {
            statement.setInt(1 , id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
            {
                return new EmployeeDTO(resultSet.getInt(1) ,
                        resultSet.getString(2) ,
                        resultSet.getString(3) ,
                        resultSet.getString(4) ,
                        resultSet.getString(5) ,
                        resultSet.getInt(6) ,
                        resultSet.getString(7) ,
                        resultSet.getInt(8) ,
                        resultSet.getInt(9));
            }
            else
            {
                return null;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public EmployeeDTO get(EmployeeDTO entity)
    {
        try(PreparedStatement statement = connection.prepareStatement("" +
                "select Employee_ID , Password , Employee_First_Name , Employee_Middle_Name , Employee_Last_Name , Archive_ID , Job_Name , Work_Hours_Last_Month , Salary " +
                "from Employee " +
                "where Employee_First_Name = ? " +
                "and Employee_Middle_Name = ? " +
                "and Employee_Last_Name = ? " +
                "and Archive_ID = ? " +
                "and Job_Name = ? " +
                "and Work_Hours_Last_Month = ? " +
                "and Salary = ?"))
        {
            statement.setString(1 , entity.getFirstName());
            statement.setString(2 , entity.getMiddleName());
            statement.setString(3 , entity.getLastName());
            statement.setInt(4 , entity.getId());
            statement.setString(5 , entity.getJob());
            statement.setInt(6 , entity.getWorkHoursLastMonth());
            statement.setInt(7 , entity.getSalary());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new EmployeeDTO(resultSet.getInt(1) ,
                    resultSet.getString(2) ,
                    resultSet.getString(3) ,
                    resultSet.getString(4) ,
                    resultSet.getString(5) ,
                    resultSet.getInt(6) ,
                    resultSet.getString(7) ,
                    resultSet.getInt(8) ,
                    resultSet.getInt(9));
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<EmployeeDTO> getAll()
    {
        try(PreparedStatement statement = connection.prepareStatement("" +
                "select Employee_ID , Password , Employee_First_Name , Employee_Middle_Name , Employee_Last_Name , Archive_ID , Job_Name , Work_Hours_Last_Month , Salary " +
                "from Employee "))
        {
            List<EmployeeDTO> result = new LinkedList<>();
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
            {
                result.add(new EmployeeDTO(resultSet.getInt(1) ,
                        resultSet.getString(2) ,
                        resultSet.getString(3) ,
                        resultSet.getString(4) ,
                        resultSet.getString(5) ,
                        resultSet.getInt(6) ,
                        resultSet.getString(7) ,
                        resultSet.getInt(8) ,
                        resultSet.getInt(9)));
            }
            return result;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void add(EmployeeDTO entity)
    {
        try(PreparedStatement statement = connection.prepareStatement("" +
                "insert into Employee values(? , ? , ? , ? , ? , ? , ? , ? , ?)"))
        {
            statement.setInt(1 , getMaxID() + 1);
            statement.setString(2 , entity.getPassword());
            statement.setString(3 , entity.getFirstName());
            statement.setString(4 , entity.getMiddleName());
            statement.setString(5 , entity.getLastName());
            statement.setInt(6 , entity.getArchiveId());
            statement.setString(7 , entity.getJob());
            statement.setInt(8 , entity.getWorkHoursLastMonth());
            statement.setInt(9 , entity.getSalary());
            statement.execute();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(EmployeeDTO entity)
    {
        try(PreparedStatement statement = connection.prepareStatement("" +
                "delete from Employee " +
                "where Employee_ID = ?"))
        {
            statement.setInt(1 , entity.getId());
            statement.execute();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void update(EmployeeDTO oldEntity, EmployeeDTO newEntity)
    {
        try(PreparedStatement statement = connection.prepareStatement("" +
                "update Employee " +
                "set Employee_First_Name = ? , " +
                "Employee_Middle_Name = ? , " +
                "Employee_Last_Name = ? , " +
                "Archive_ID = ? , " +
                "Job_Name = ? , " +
                "Work_Hours_Last_Month = ? , " +
                "Salary = ? " +
                "where Employee_ID = ?"))
        {
            statement.setString(1 , newEntity.getFirstName());
            statement.setString(2 , newEntity.getMiddleName());
            statement.setString(3 , newEntity.getLastName());
            statement.setInt(4 , newEntity.getArchiveId());
            statement.setString(5 , newEntity.getJob());
            statement.setInt(6 , newEntity.getWorkHoursLastMonth());
            statement.setInt(7 , newEntity.getSalary());
            statement.setInt(8 , oldEntity.getId());
            statement.execute();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public int getMaxID()
    {
        try(PreparedStatement statement = connection.prepareStatement("" +
                "select max(Employee_ID) from Employee"))
        {
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) return resultSet.getInt(1);
            else return 0;
        }
        catch(SQLException e)
        {
            return -1;
        }
    }
}

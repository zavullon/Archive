package project.database;

import project.database.DTO.DocumentMovingDTO;
import project.util.Date;

import java.awt.geom.RectangularShape;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DocumentMovingSQLAccessObject implements DocumentMovingDAO
{
    private static DocumentMovingSQLAccessObject ourInstance = new DocumentMovingSQLAccessObject();
    private Connection connection;
    private String url = "jdbc:sqlserver://169.254.220.86:63293;database=Archive";
    private String login = "admin";
    private String password = "QEADZCwsx";

    public static DocumentMovingSQLAccessObject getInstance()
    {
        return ourInstance;
    }

    private DocumentMovingSQLAccessObject()
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
    public DocumentMovingDTO get(DocumentMovingDTO entity)
    {
        try(PreparedStatement statement = connection.prepareStatement("set dateformat ymd " +
                "select Client_ID , Copy_ID , Employee_ID , Moving_Type , Moving_Date " +
                "from Documents_Moving " +
                "where Client_ID = ? " +
                "and Copy_ID = ? " +
                "and Employee_ID = ?" +
                "and Moving_Type = ? " +
                "and Moving_Date = ? "))
        {
            statement.setInt(1 , entity.getClientId());
            statement.setInt(2 , entity.getCopyId());
            statement.setInt(3 , entity.getEmployeeId());
            statement.setString(4 , entity.getType());
            statement.setString(5 , entity.getDate().toString());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new DocumentMovingDTO(resultSet.getInt(1) ,
                    resultSet.getInt(2) ,
                    resultSet.getInt(3) ,
                    resultSet.getString(4) ,
                    new Date(resultSet.getString(5)));
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DocumentMovingDTO> getAll()
    {
        try(PreparedStatement statement = connection.prepareStatement("set dateformat ymd " +
                "select Client_ID , Copy_ID , Employee_ID , Moving_Type , Moving_Date " +
                "from Documents_Moving"))
        {
            List<DocumentMovingDTO> result = new LinkedList<>();
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
            {
                result.add(new DocumentMovingDTO(resultSet.getInt(1) ,
                        resultSet.getInt(2) ,
                        resultSet.getInt(3) ,
                        resultSet.getString(4) ,
                        new Date(resultSet.getString(5))));
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
    public void add(DocumentMovingDTO entity)
    {
        try(PreparedStatement statement = connection.prepareStatement("set dateformat ymd " +
                "insert into Documents_Moving values(? , ? , ? , ? , ?)"))
        {
            setStatement(entity , statement);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(DocumentMovingDTO entity)
    {
        try(PreparedStatement statement = connection.prepareStatement("set dateformat ymd " +
                "delete from Documents_Moving " +
                "where Client_ID = ? " +
                "and Copy_ID = ? " +
                "and Employee_ID = ?" +
                "and Moving_Type = ? " +
                "and Moving_Date = ?"))
        {
            setStatement(entity , statement);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByIds(int copyId , int clientId , int employeeId)
    {
        try(PreparedStatement statement = connection.prepareStatement("set dateformat ymd " +
                "delete from Documents_Moving " +
                "where Client_ID = ? " +
                "and Copy_ID = ? " +
                "and Employee_ID = ?"))
        {
            statement.setInt(1 , clientId);
            statement.setInt(2 , copyId);
            statement.setInt(3 , employeeId);
            statement.execute();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void update(DocumentMovingDTO oldEntity , DocumentMovingDTO newEntity)
    {
        try(PreparedStatement statement = connection.prepareStatement("set dateformat ymd " +
                "update Documents_Moving " +
                "set Moving_Type = ? ," +
                "Moving_Date = ? " +
                "where Client_ID = ? " +
                "and Copy_ID = ? " +
                "and Employee_ID = ? " +
                "and Moving_Type = ? " +
                "and Moving_Date = ?"))
        {
            statement.setString(1 , newEntity.getType());
            statement.setString(2 , newEntity.getDate().toString());
            statement.setInt(3 , oldEntity.getClientId());
            statement.setInt(4 , oldEntity.getCopyId());
            statement.setInt(5 , oldEntity.getEmployeeId());
            statement.setString(6 , oldEntity.getType());
            statement.setString(7 , oldEntity.getDate().toString());
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
        return -1;
    }

    @Override
    public List<DocumentMovingDTO> getByClientId(int clientId)
    {
        try(PreparedStatement statement = connection.prepareStatement("set dateformat ymd " +
                "select Client_ID , Copy_ID , Employee_ID , Moving_Type , Moving_Date " +
                "from Documents_Moving " +
                "where Client_ID = ? "))
        {
            return getDocumentMovingDTOS(clientId , statement);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DocumentMovingDTO> getByCopyId(int copyId)
    {
        try(PreparedStatement statement = connection.prepareStatement("set dateformat ymd " +
                "select Client_ID , Copy_ID , Employee_ID , Moving_Type , Moving_Date " +
                "from Documents_Moving " +
                "where Copy_ID = ? "))
        {
            return getDocumentMovingDTOS(copyId , statement);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DocumentMovingDTO> getByEmployeeId(int employeeId)
    {
        try(PreparedStatement statement = connection.prepareStatement("set dateformat ymd " +
                "select Client_ID , Copy_ID , Employee_ID , Moving_Type , Moving_Date " +
                "from Documents_Moving " +
                "where Employee_ID = ? "))
        {
            return getDocumentMovingDTOS(employeeId , statement);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private List<DocumentMovingDTO> getDocumentMovingDTOS(int employeeId , PreparedStatement statement) throws SQLException
    {
        List<DocumentMovingDTO> result = new LinkedList<>();
        statement.setInt(1 , employeeId);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next())
        {
            result.add(new DocumentMovingDTO(resultSet.getInt(1) ,
                    resultSet.getInt(2) ,
                    resultSet.getInt(3) ,
                    resultSet.getString(4) ,
                    new Date(resultSet.getString(5))));
        }
        return result;
    }

    private void setStatement(DocumentMovingDTO entity , PreparedStatement statement) throws SQLException
    {
        statement.setInt(1 , entity.getClientId());
        statement.setInt(2 , entity.getCopyId());
        statement.setInt(3 , entity.getEmployeeId());
        statement.setString(4 , entity.getType());
        statement.setString(5 , entity.getDate().toString());
        statement.execute();
    }
}

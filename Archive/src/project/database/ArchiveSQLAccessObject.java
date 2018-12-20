package project.database;

import project.database.DTO.ArchiveDTO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ArchiveSQLAccessObject implements ArchiveDAO
{
    private static ArchiveSQLAccessObject ourInstance = new ArchiveSQLAccessObject();
    private Connection connection;
    private String url = "jdbc:sqlserver://169.254.220.86:63293;database=Archive";
    private String login = "admin";
    private String password = "QEADZCwsx";

    public static ArchiveSQLAccessObject getInstance()
    {
        return ourInstance;
    }

    private ArchiveSQLAccessObject()
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
    public ArchiveDTO getById(int id)
    {
        try(PreparedStatement statement = connection.prepareStatement("" +
                "select Archive_ID , Archive_Name , Archive_Adress " +
                "from Archive " +
                "where Archive_ID = ?"))
        {
            statement.setInt(1 , id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new ArchiveDTO(resultSet.getInt(1) ,
                    resultSet.getString(2) ,
                    resultSet.getString(3));
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArchiveDTO get(ArchiveDTO entity)
    {
        try(PreparedStatement statement = connection.prepareStatement("" +
                "select Archive_ID , Archive_Name , Archive_Adress " +
                "from Archive " +
                "where Archive_Name = ? " +
                "and Archive_Adress = ?"))
        {
            statement.setString(1 , entity.getName());
            statement.setString(2 , entity.getAddress());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new ArchiveDTO(resultSet.getInt(1) ,
                    resultSet.getString(2) ,
                    resultSet.getString(3));
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ArchiveDTO> getAll()
    {
        List<ArchiveDTO> result = new LinkedList<>();
        try(PreparedStatement statement = connection.prepareStatement("" +
                "select Archive_ID , Archive_Name , Archive_Adress " +
                "from Archive"))
        {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
            {
                result.add(new ArchiveDTO(resultSet.getInt(1) ,
                        resultSet.getString(2) ,
                        resultSet.getString(3)));
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
    public void add(ArchiveDTO entity)
    {
        try(PreparedStatement statement = connection.prepareStatement("" +
                "insert into Archive values(? , ? , ?)"))
        {
            statement.setInt(1 , getMaxID() + 1);
            statement.setString(2 , entity.getName());
            statement.setString(3 , entity.getAddress());
            statement.execute();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(ArchiveDTO entity)
    {
        try(PreparedStatement statement = connection.prepareStatement("" +
                "delete from Archive " +
                "where Archive_ID = ? " +
                "and Archive_Name = ? " +
                "and Archive_Adress = ?"))
        {
            statement.setInt(1 , entity.getId());
            statement.setString(2 , entity.getName());
            statement.setString(3 , entity.getAddress());
            statement.execute();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ArchiveDTO oldEntity , ArchiveDTO newEntity)
    {
        try(PreparedStatement statement = connection.prepareStatement("" +
                "update Archive " +
                "set Archive_Name = ? , Archive_Adress = ? " +
                "where Archive_ID = ?"))
        {
            statement.setString(1 , newEntity.getName());
            statement.setString(2 , newEntity.getAddress());
            statement.setInt(3 , oldEntity.getId());
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
                "select max(Archive_ID) from Archive"))
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

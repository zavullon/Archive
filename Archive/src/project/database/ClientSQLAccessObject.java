package project.database;

import project.database.DTO.ClientDTO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ClientSQLAccessObject implements ClientDAO
{
    private static ClientSQLAccessObject ourInstance = new ClientSQLAccessObject();
    private Connection connection;
    private String url = "jdbc:sqlserver://169.254.220.86:63293;database=Archive";
    private String login = "admin";
    private String password = "QEADZCwsx";

    public static ClientSQLAccessObject getInstance()
    {
        return ourInstance;
    }

    private ClientSQLAccessObject()
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
    public ClientDTO getByClientId(int clientId)
    {
        try(PreparedStatement statement = connection.prepareStatement("" +
                "select Client_ID , Client_First_Name , Client_Middle_Name , Client_Last_Name , Archive_ID , Fine_Sum " +
                "from Client " +
                "where Client_ID = ?"))
        {
            statement.setInt(1 , clientId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new ClientDTO(resultSet.getInt(1) ,
                    resultSet.getString(2) ,
                    resultSet.getString(3) ,
                    resultSet.getString(4) ,
                    resultSet.getInt(5) ,
                    resultSet.getInt(6));
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ClientDTO> getByArchiveId(int archiveId)
    {
        List<ClientDTO> result = new LinkedList<>();
        try(PreparedStatement statement = connection.prepareStatement("" +
                "select Client_ID , Client_First_Name , Client_Middle_Name , Client_Last_Name , Archive_ID , Fine_Sum " +
                "from Client " +
                "where Archive_ID = ?"))
        {
            statement.setInt(1 , archiveId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
            {
                result.add(new ClientDTO(resultSet.getInt(1) ,
                        resultSet.getString(2) ,
                        resultSet.getString(3) ,
                        resultSet.getString(4) ,
                        resultSet.getInt(5) ,
                        resultSet.getInt(6)));
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
    public ClientDTO get(ClientDTO entity)
    {
        try(PreparedStatement statement = connection.prepareStatement("" +
                "select Client_ID , Client_First_Name , Client_Middle_Name , Client_Last_Name , Archive_ID , Fine_Sum " +
                "from Client " +
                "where Client_First_Name = ? " +
                "and Client_Middle_Name = ? " +
                "and Client_Last_Name = ? " +
                "and Archive_ID = ?"))
        {
            statement.setString(1 , entity.getFirstName());
            statement.setString(2 , entity.getMiddleName());
            statement.setString(3 , entity.getLastName());
            statement.setInt(4 , entity.getArchiveId());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new ClientDTO(resultSet.getInt(1) ,
                    resultSet.getString(2) ,
                    resultSet.getString(3) ,
                    resultSet.getString(4) ,
                    resultSet.getInt(5) ,
                    resultSet.getInt(6));
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ClientDTO> getAll()
    {
        List<ClientDTO> result = new LinkedList<>();
        try(PreparedStatement statement = connection.prepareStatement("" +
                "select Client_ID , Client_First_Name , Client_Middle_Name , Client_Last_Name , Archive_ID , Fine_Sum " +
                "from Client"))
        {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
            {
                result.add(new ClientDTO(resultSet.getInt(1) ,
                        resultSet.getString(2) ,
                        resultSet.getString(3) ,
                        resultSet.getString(4) ,
                        resultSet.getInt(5) ,
                        resultSet.getInt(6)));
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
    public void add(ClientDTO entity)
    {
        try(PreparedStatement statement = connection.prepareStatement("" +
                "insert into Client values(? , ? , ? , ? , ? , ?)"))
        {
            statement.setInt(1 , getMaxID() + 1);
            statement.setString(2 , entity.getFirstName());
            statement.setString(3 , entity.getMiddleName());
            statement.setString(4 , entity.getLastName());
            statement.setInt(5 , entity.getArchiveId());
            statement.setInt(6 , entity.getFineSum());
            statement.execute();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(ClientDTO entity)
    {
        try(PreparedStatement statement = connection.prepareStatement("" +
                "delete from Client " +
                "where Client_ID = ?"))
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
    public void update(ClientDTO oldEntity, ClientDTO newEntity)
    {
        try(PreparedStatement statement = connection.prepareStatement("" +
                "update Client " +
                "set Client_First_Name = ? , " +
                "Client_Middle_Name = ? , " +
                "Client_Last_Name = ? , " +
                "Archive_ID = ? , " +
                "Fine_Sum = ? " +
                "where Client_ID = ?"))
        {
            statement.setString(1 , newEntity.getFirstName());
            statement.setString(2 , newEntity.getMiddleName());
            statement.setString(3 , newEntity.getLastName());
            statement.setInt(4 , newEntity.getArchiveId());
            statement.setInt(5 , newEntity.getFineSum());
            statement.setInt(6 , oldEntity.getId());
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
                "select max(Client_ID) from Client"))
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

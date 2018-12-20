package project.database;

import project.database.DTO.DocumentCopyDTO;
import project.util.Date;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DocumentCopySQLAccessObject implements DocumentCopyDAO
{
    private static DocumentCopySQLAccessObject ourInstance = new DocumentCopySQLAccessObject();
    private Connection connection;
    private String url = "jdbc:sqlserver://169.254.220.86:63293;database=Archive";
    private String login = "admin";
    private String password = "QEADZCwsx";

    public static DocumentCopySQLAccessObject getInstance()
    {
        return ourInstance;
    }

    private DocumentCopySQLAccessObject()
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
    public DocumentCopyDTO getById(int id)
    {
        try(PreparedStatement statement = connection.prepareStatement("set dateformat ymd " +
                "select Copy_ID , Document_ID , Copy_Status , Taken_Date , Return_Date " +
                "from Document_Copy " +
                "where Copy_ID = ?"))
        {
            statement.setInt(1 , id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new DocumentCopyDTO(resultSet.getInt(1) ,
                    resultSet.getInt(2) ,
                    resultSet.getString(3) ,
                    resultSet.getString(4) == null ? null : new Date(resultSet.getString(4)) ,
                    resultSet.getString(5) == null ? null : new Date(resultSet.getString(5)));
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteByDocumentID(int documentID)
    {
        try(PreparedStatement statement = connection.prepareStatement("set dateformat ymd " +
                "delete from Document_Copy " +
                "where Document_ID = ?"))
        {
            statement.setInt(1 , documentID);
            statement.execute();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public DocumentCopyDTO getFreeCopy(int documentID)
    {
        try(PreparedStatement statement = connection.prepareStatement("set dateformat ymd " +
                "select Copy_ID , Document_ID , Copy_Status , Taken_Date , Return_Date " +
                "from Document_Copy " +
                "where Document_ID = ? " +
                "and Copy_Status = 'NotInUse'"))
        {
            statement.setInt(1 , documentID);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
            {
                return new DocumentCopyDTO(resultSet.getInt(1) ,
                        resultSet.getInt(2) ,
                        resultSet.getString(3) ,
                        resultSet.getString(4) == null ? null : new Date(resultSet.getString(4)) ,
                        resultSet.getString(5) == null ? null : new Date(resultSet.getString(5)));
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
    public DocumentCopyDTO get(DocumentCopyDTO entity)
    {
        try(PreparedStatement statement = connection.prepareStatement("set dateformat ymd " +
                "select Copy_ID , Document_ID , Copy_Status , Taken_Date , Return_Date " +
                "from Document_Copy " +
                "where Document_ID = ? " +
                "and Copy_Status = ? " +
                "and Taken_Date = ? " +
                "and Return_Date = ?"))
        {
            statement.setInt(1 , entity.getDocumentId());
            statement.setString(2 , entity.getStatus());
            statement.setString(3 , entity.getTakenDate().toString());
            statement.setString(4 , entity.getReturnDate().toString());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new DocumentCopyDTO(resultSet.getInt(1) ,
                    resultSet.getInt(2) ,
                    resultSet.getString(3) ,
                    new Date(resultSet.getString(4)) ,
                    new Date(resultSet.getString(5)));
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DocumentCopyDTO> getAll()
    {
        List<DocumentCopyDTO> result = new LinkedList<>();
        try(PreparedStatement statement = connection.prepareStatement("set dateformat ymd " +
                "select Copy_ID , Document_ID , Copy_Status , Taken_Date , Return_Date " +
                "from Document_Copy"))
        {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
            {
                result.add(new DocumentCopyDTO(resultSet.getInt(1) ,
                        resultSet.getInt(2) ,
                        resultSet.getString(3) ,
                        new Date(resultSet.getString(4)) ,
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
    public void add(DocumentCopyDTO entity)
    {
        try(PreparedStatement statement = connection.prepareStatement("set dateformat ymd " +
                "insert into Document_Copy values(? , ? , ? , ? , ?)"))
        {
            statement.setInt(1 , getMaxID() + 1);
            statement.setInt(2 , entity.getDocumentId());
            statement.setString(3 , entity.getStatus());
            statement.setString(4 , entity.getTakenDate().toString());
            statement.setString(5 , entity.getReturnDate().toString());
            statement.execute();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(DocumentCopyDTO entity)
    {
        try(PreparedStatement statement = connection.prepareStatement("set dateformat ymd " +
                "delete from Document_Copy " +
                "where Copy_ID = ?"))
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
    public void update(DocumentCopyDTO oldEntity, DocumentCopyDTO newEntity)
    {
        try(PreparedStatement statement = connection.prepareStatement("set dateformat ymd " +
                "update Document_Copy " +
                "set Document_ID = ? , " +
                "Copy_Status = ? , " +
                "Taken_Date = ? , " +
                "Return_Date = ? " +
                "where Copy_ID = ?"))
        {
            statement.setInt(1 , newEntity.getDocumentId());
            statement.setString(2 , newEntity.getStatus());
            statement.setString(3 , newEntity.getTakenDate() == null ? null : newEntity.getTakenDate().toString());
            statement.setString(4 , newEntity.getReturnDate() == null ? null : newEntity.getReturnDate().toString());
            statement.setInt(5 , oldEntity.getId());
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
                "select max(Copy_ID) from Document_Copy"))
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

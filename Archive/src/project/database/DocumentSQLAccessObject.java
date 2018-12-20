package project.database;

import project.database.DTO.DocumentDTO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DocumentSQLAccessObject implements DocumentDAO
{
    private static DocumentSQLAccessObject ourInstance = new DocumentSQLAccessObject();
    private Connection connection;
    private String url = "jdbc:sqlserver://169.254.220.86:63293;database=Archive";
    private String login = "admin";
    private String password = "QEADZCwsx";

    public static DocumentSQLAccessObject getInstance()
    {
        return ourInstance;
    }

    private DocumentSQLAccessObject()
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
    public DocumentDTO getByDocumentId(int documentId)
    {
        try(PreparedStatement statement = connection.prepareStatement("" +
                "select Document_ID , Archive_ID , Document_Name , Amount_Of_Copies , Amount_Of_Copies_In_Use , Fine " +
                "from Document " +
                "where Document_ID = ?"))
        {
            statement.setInt(1 , documentId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new DocumentDTO(resultSet.getInt(1) ,
                    resultSet.getInt(2) ,
                    resultSet.getString(3) ,
                    resultSet.getInt(4) ,
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
    public List<DocumentDTO> getByArchiveId(int archiveId)
    {
        try(PreparedStatement statement = connection.prepareStatement("" +
                "select Document_ID , Archive_ID , Docuemnt_Name , Amount_Of_Copies , Amount_Of_Copies_In_Use , Fine " +
                "from Document " +
                "where Archive_ID = ?"))
        {
            List<DocumentDTO> result = new LinkedList<>();
            statement.setInt(1 , archiveId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
            {
                result.add(new DocumentDTO(resultSet.getInt(1) ,
                        resultSet.getInt(2) ,
                        resultSet.getString(3) ,
                        resultSet.getInt(4) ,
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
    public DocumentDTO get(DocumentDTO entity)
    {
        try(PreparedStatement statement = connection.prepareStatement("" +
                "select Document_ID , Archive_ID , Docuemnt_Name , Amount_Of_Copies , Amount_Of_Copies_In_Use , Fine " +
                "from Document " +
                "where Document_Name = ? " +
                "and Amount_Of_Copies = ? " +
                "and Amount_Of_Copies_In_Use = ? " +
                "and Fine = ?"))
        {
            statement.setString(1 , entity.getName());
            statement.setInt(2 , entity.getAmountOfCopies());
            statement.setInt(3 , entity.getAmountOfCopiesInUse());
            statement.setInt(4 , entity.getFine());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new DocumentDTO(resultSet.getInt(1) ,
                    resultSet.getInt(2) ,
                    resultSet.getString(3) ,
                    resultSet.getInt(4) ,
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
    public List<DocumentDTO> getAll()
    {
        try(PreparedStatement statement = connection.prepareStatement("" +
                "select Document_ID , Archive_ID , Document_Name , Amount_Of_Copies , Amount_Of_Copies_In_Use , Fine " +
                "from Document"))
        {
            List<DocumentDTO> result = new LinkedList<>();
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
            {
                result.add(new DocumentDTO(resultSet.getInt(1) ,
                        resultSet.getInt(2) ,
                        resultSet.getString(3) ,
                        resultSet.getInt(4) ,
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
    public void add(DocumentDTO entity)
    {
        try(PreparedStatement statement = connection.prepareStatement("" +
                "insert into Document values(? , ? , ? , ? , ? , ?)"))
        {
            statement.setInt(1 , getMaxID() + 1);
            statement.setInt(2 , entity.getArchiveId());
            statement.setString(3 , entity.getName());
            statement.setInt(4 , entity.getAmountOfCopies());
            statement.setInt(5 , entity.getAmountOfCopiesInUse());
            statement.setInt(6 , entity.getFine());
            statement.execute();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(DocumentDTO entity)
    {
        try(PreparedStatement statement = connection.prepareStatement("" +
                "delete from Document " +
                "where Document_ID = ?"))
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
    public void update(DocumentDTO oldEntity , DocumentDTO newEntity)
    {
        try(PreparedStatement statement = connection.prepareStatement("" +
                "update Document " +
                "set Document_Name = ? , " +
                "Amount_Of_Copies = ? , " +
                "Amount_Of_Copies_In_Use = ? , " +
                "Fine = ? " +
                "where Document_ID = ?"))
        {
            statement.setString(1 , newEntity.getName());
            statement.setInt(2 , newEntity.getAmountOfCopies());
            statement.setInt(3 , newEntity.getAmountOfCopiesInUse());
            statement.setInt(4 , newEntity.getFine());
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
                "select max(Document_ID) from Document"))
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

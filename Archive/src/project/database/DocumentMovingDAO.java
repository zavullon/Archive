package project.database;

import project.database.DTO.DocumentMovingDTO;

import java.util.List;

public interface DocumentMovingDAO extends DAO<DocumentMovingDTO>
{
    public List<DocumentMovingDTO> getByClientId(int clientId);
    public List<DocumentMovingDTO> getByCopyId(int copyId);
    public List<DocumentMovingDTO> getByEmployeeId(int employeeId);
    public void deleteByIds(int copyId , int clientId , int employeeId);
}

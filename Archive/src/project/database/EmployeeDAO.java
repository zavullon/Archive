package project.database;

import project.database.DTO.EmployeeDTO;

public interface EmployeeDAO extends DAO<EmployeeDTO>
{
    public EmployeeDTO getById(int id);
}

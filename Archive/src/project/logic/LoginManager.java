package project.logic;

import project.database.DTO.EmployeeDTO;

public interface LoginManager
{
    public EmployeeDTO getEmployeeByID(EmployeeDTO employee);
}

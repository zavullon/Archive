package project.logic;

import project.database.DTO.EmployeeDTO;
import project.database.EmployeeDAO;
import project.database.EmployeeSQLAccessObject;

public class LoginManagerForSQL implements LoginManager
{
    @Override
    public EmployeeDTO getEmployeeByID(EmployeeDTO employee)
    {
        EmployeeDAO employeeDAO = EmployeeSQLAccessObject.getInstance();
        EmployeeDTO result = employeeDAO.getById(employee.getId());
        if(result != null && result.getPassword().equals(employee.getPassword())) return result;
        else return null;
    }
}

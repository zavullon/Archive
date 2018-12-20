package project.logic;

import project.database.DTO.DocumentCopyDTO;
import project.database.DTO.DocumentDTO;
import project.database.DTO.EmployeeDTO;

public interface AdminManager
{
    public void addEmployee(EmployeeDTO employee);
    public void deleteEmployee(EmployeeDTO employee);
    public void updateEmployee(EmployeeDTO oldEmployee , EmployeeDTO newEmployee);
    public void addDocument(DocumentDTO document);
    public void deleteDocument(DocumentDTO document);
    public void updateDocument(DocumentDTO oldDocument , DocumentDTO newDocument);
    public void addDocumentCopy(DocumentCopyDTO documentCopy);
    public void deleteDocumentCopy(DocumentCopyDTO documentCopy);
    public void updateDocumentCopy(DocumentCopyDTO oldDocumentCopy , DocumentCopyDTO newDocumentCopy);
}

package project.logic;

import project.database.*;
import project.database.DTO.DocumentCopyDTO;
import project.database.DTO.DocumentDTO;
import project.database.DTO.EmployeeDTO;

public class AdminManagerForSQLDatabase implements AdminManager
{

    @Override
    public void addEmployee(EmployeeDTO employee)
    {
        EmployeeDAO employeeDAO = EmployeeSQLAccessObject.getInstance();
        employeeDAO.add(employee);
    }

    @Override
    public void deleteEmployee(EmployeeDTO employee)
    {
        EmployeeDAO employeeDAO = EmployeeSQLAccessObject.getInstance();
        employeeDAO.delete(employeeDAO.get(employee));
    }

    @Override
    public void updateEmployee(EmployeeDTO oldEmployee, EmployeeDTO newEmployee)
    {
        EmployeeDAO employeeDAO = EmployeeSQLAccessObject.getInstance();
        employeeDAO.update(employeeDAO.get(oldEmployee) , newEmployee);
    }

    @Override
    public void addDocument(DocumentDTO document)
    {
        DocumentDAO documentDAO = DocumentSQLAccessObject.getInstance();
        documentDAO.add(document);
    }

    @Override
    public void deleteDocument(DocumentDTO document)
    {
        DocumentDAO documentDAO = DocumentSQLAccessObject.getInstance();
        DocumentCopyDAO documentCopyDAO = DocumentCopySQLAccessObject.getInstance();
        documentCopyDAO.deleteByDocumentID(document.getId());
        documentDAO.delete(documentDAO.get(document));
    }

    @Override
    public void updateDocument(DocumentDTO oldDocument, DocumentDTO newDocument)
    {
        DocumentDAO documentDAO = DocumentSQLAccessObject.getInstance();
        documentDAO.update(documentDAO.get(oldDocument) , newDocument);
    }

    @Override
    public void addDocumentCopy(DocumentCopyDTO documentCopy)
    {
        DocumentCopyDAO documentCopyDAO = DocumentCopySQLAccessObject.getInstance();
        DocumentDAO documentDAO = DocumentSQLAccessObject.getInstance();
        DocumentDTO oldDocument = documentDAO.getByDocumentId(documentCopy.getDocumentId());
        DocumentDTO newDocument = new DocumentDTO(oldDocument.getId() ,
                oldDocument.getArchiveId() ,
                oldDocument.getName() ,
                oldDocument.getAmountOfCopies() + 1 ,
                oldDocument.getAmountOfCopiesInUse() ,
                oldDocument.getFine());
        documentDAO.update(oldDocument , newDocument);
        documentCopyDAO.add(documentCopy);
    }

    @Override
    public void deleteDocumentCopy(DocumentCopyDTO documentCopy)
    {
        DocumentCopyDAO documentCopyDAO = DocumentCopySQLAccessObject.getInstance();
        DocumentDAO documentDAO = DocumentSQLAccessObject.getInstance();
        DocumentDTO oldDocument = documentDAO.getByDocumentId(documentCopy.getDocumentId());
        DocumentDTO newDocument = new DocumentDTO(oldDocument.getId() ,
                oldDocument.getArchiveId() ,
                oldDocument.getName() ,
                oldDocument.getAmountOfCopies() - 1 ,
                oldDocument.getAmountOfCopiesInUse() - (documentCopy.getStatus() == "InUse" ? 1 : 0) ,
                oldDocument.getFine());
        documentDAO.update(oldDocument , newDocument);
        documentCopyDAO.delete(documentCopyDAO.get(documentCopy));
    }

    @Override
    public void updateDocumentCopy(DocumentCopyDTO oldDocumentCopy, DocumentCopyDTO newDocumentCopy)
    {
        DocumentCopyDAO documentCopyDAO = DocumentCopySQLAccessObject.getInstance();
        documentCopyDAO.update(documentCopyDAO.get(oldDocumentCopy) , newDocumentCopy);
    }
}

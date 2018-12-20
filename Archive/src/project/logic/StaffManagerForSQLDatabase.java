package project.logic;

import project.database.*;
import project.database.DTO.*;
import project.util.Date;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class StaffManagerForSQLDatabase implements StaffManager
{

    @Override
    public void addClient(ClientDTO client)
    {
        ClientDAO clientDAO = ClientSQLAccessObject.getInstance();
        clientDAO.add(client);
    }

    @Override
    public void deleteClient(ClientDTO client)
    {
        ClientDAO clientDAO = ClientSQLAccessObject.getInstance();
        clientDAO.delete(clientDAO.get(client));
    }

    @Override
    public void updateClient(ClientDTO oldEntity , ClientDTO newEntity)
    {
        ClientDAO clientDAO = ClientSQLAccessObject.getInstance();
        clientDAO.update(clientDAO.get(oldEntity) , newEntity);
    }

    @Override
    public void addDocumentMoving(DocumentMovingDTO entity)
    {
        DocumentMovingDAO documentMovingDAO = DocumentMovingSQLAccessObject.getInstance();
        DocumentCopyDAO documentCopyDAO = DocumentCopySQLAccessObject.getInstance();
        ClientDAO clientDAO = ClientSQLAccessObject.getInstance();
        DocumentDAO documentDAO = DocumentSQLAccessObject.getInstance();
        documentMovingDAO.deleteByIds(entity.getCopyId() , entity.getClientId() , entity.getEmployeeId());
        if(entity.getType().equals("Returned"))
        {
            DocumentCopyDTO documentCopy = documentCopyDAO.getById(entity.getCopyId());
            if(documentCopyDAO.getById(entity.getCopyId()).getReturnDate().before(Date.getToday()))
            {
                ClientDTO client = clientDAO.getByClientId(entity.getClientId());
                clientDAO.update(client , new ClientDTO(client.getId() ,
                        client.getFirstName() ,
                        client.getMiddleName() ,
                        client.getLastName() ,
                        client.getArchiveId() ,
                        client.getFineSum() + documentDAO
                                .getByDocumentId(documentCopy
                                        .getDocumentId())
                                .getFine()));
            }
            documentCopyDAO.update(documentCopy , new DocumentCopyDTO(documentCopy.getId() ,
                    documentCopy.getDocumentId() ,
                    "NotInUse" ,
                    null ,
                    null));
        }
        documentMovingDAO.add(entity);
    }

    @Override
    public DocumentCopyDTO getFreeCopy(int documentID)
    {
        DocumentCopyDAO documentCopyDAO = DocumentCopySQLAccessObject.getInstance();
        return documentCopyDAO.getFreeCopy(documentID);
    }

    @Override
    public List<ClientDTO> getClients()
    {
        ClientDAO clientDAO = ClientSQLAccessObject.getInstance();
        return clientDAO.getAll();
    }

    @Override
    public ClientDTO getByClientId(int id)
    {
        ClientDAO clientDAO = ClientSQLAccessObject.getInstance();
        return clientDAO.getByClientId(id);
    }

    @Override
    public List<Integer> getTakenCopiesByClientId(int id)
    {
        DocumentMovingDAO documentMovingDAO = DocumentMovingSQLAccessObject.getInstance();
        return documentMovingDAO.getByClientId(id)
                .stream()
                .filter((dm) -> dm.getType().equals("Taken"))
                .map(DocumentMovingDTO::getCopyId)
                .collect(Collectors.toList());
    }

    @Override
    public boolean takeDocument(int clientId , int documentId , int employeeId , Date returnDate)
    {
        DocumentCopyDAO documentCopyDAO = DocumentCopySQLAccessObject.getInstance();
        DocumentMovingDAO documentMovingDAO = DocumentMovingSQLAccessObject.getInstance();
        DocumentCopyDTO documentCopy = documentCopyDAO.getFreeCopy(documentId);
        if(documentCopy != null)
        {
            documentCopyDAO.update(documentCopy ,
                    new DocumentCopyDTO(documentCopy.getId() ,
                            documentCopy.getDocumentId() ,
                            "InUse" , Date.getToday() ,
                            returnDate));
            documentMovingDAO.add(new DocumentMovingDTO(clientId , documentCopy.getId() , employeeId , "Taken" , Date.getToday()));
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public List<Integer> getDocumentIds()
    {
        DocumentDAO documentDAO = DocumentSQLAccessObject.getInstance();
        return documentDAO.getAll().stream().map((d) -> d.getId()).collect(Collectors.toList());
    }

    @Override
    public List<ArchiveDTO> getAllArchives()
    {
        ArchiveDAO archiveDAO = ArchiveSQLAccessObject.getInstance();
        return archiveDAO.getAll();
    }
}

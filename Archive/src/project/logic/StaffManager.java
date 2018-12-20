package project.logic;

import project.database.DTO.*;
import project.userinterface.entities.Client;
import project.util.Date;

import java.util.List;

public interface StaffManager
{
    public void addClient(ClientDTO client);
    public void deleteClient(ClientDTO client);
    public void updateClient(ClientDTO oldEntity , ClientDTO newEntity);
    public void addDocumentMoving(DocumentMovingDTO entity);
    public DocumentCopyDTO getFreeCopy(int documentID);
    public List<ClientDTO> getClients();
    public ClientDTO getByClientId(int id);
    public List<Integer> getTakenCopiesByClientId(int id);
    public boolean takeDocument(int clientId , int documentId , int employeeId , Date returnDate);
    public List<Integer> getDocumentIds();
    public List<ArchiveDTO> getAllArchives();
}

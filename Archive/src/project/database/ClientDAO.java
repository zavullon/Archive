package project.database;

import project.database.DTO.ClientDTO;

import java.util.List;

public interface ClientDAO extends DAO<ClientDTO>
{
    public ClientDTO getByClientId(int clientId);
    public List<ClientDTO> getByArchiveId(int archiveId);
}

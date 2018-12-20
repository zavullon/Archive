package project.database;

import project.database.DTO.DocumentCopyDTO;

public interface DocumentCopyDAO extends DAO<DocumentCopyDTO>
{
    public DocumentCopyDTO getById(int id);
    public void deleteByDocumentID(int documentID);
    public DocumentCopyDTO getFreeCopy(int documentID);
}

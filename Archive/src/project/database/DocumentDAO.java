package project.database;

import project.database.DTO.DocumentDTO;

import java.util.List;

public interface DocumentDAO extends DAO<DocumentDTO>
{
    public DocumentDTO getByDocumentId(int documentId);
    public List<DocumentDTO> getByArchiveId(int archiveId);
}

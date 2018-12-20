package project.database;

import project.database.DTO.ArchiveDTO;

public interface ArchiveDAO extends DAO<ArchiveDTO>
{
    public ArchiveDTO getById(int id);
}

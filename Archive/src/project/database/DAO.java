package project.database;

import java.util.List;

public interface DAO<T>
{
    public T get(T entity);
    public List<T> getAll();
    public void add(T entity);
    public void delete(T entity);
    public void update(T oldEntity , T newEntity);
    public int getMaxID();
}

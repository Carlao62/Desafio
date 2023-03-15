package DAO;
import java.util.List;
public interface Persistencia<T> {
    public int  insert       (T   obj);
    public List<T> read();
    public void delete       (String id);
    public T    findByCodigo (String id);
    public void update       (String   id, String obj);
}
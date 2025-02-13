package Repository;

import javafx.collections.ObservableList;

public interface CrudDao<T,ID> extends SuperDao{
    boolean save(T entity);

    boolean update(T entity);

    boolean delete(ID id);

    ObservableList<T> getAll();
}

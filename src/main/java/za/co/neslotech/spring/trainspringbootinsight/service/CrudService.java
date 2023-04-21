package za.co.neslotech.spring.trainspringbootinsight.service;

import java.util.List;

public interface CrudService<T, I> {

    T create(T entity);

    T update(T entity);

    List<T> findAll();

    T findById(I id);

    void delete(T entity);

    I getEntityId(T entity);

    String[] getUpdateColumnExclusions();
}

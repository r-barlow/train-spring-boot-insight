package za.co.neslotech.spring.trainspringbootinsight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, I> {

    T update(T entity);
    List<T> findAll();
    T findById(I id);
    void delete(T entity);
    I getEntityId(T entity);
    String[] getUpdateColumnExclusions();
}

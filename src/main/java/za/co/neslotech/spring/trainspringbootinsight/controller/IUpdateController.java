package za.co.neslotech.spring.trainspringbootinsight.controller;

import org.springframework.http.ResponseEntity;

public interface IUpdateController<T, I> {

    ResponseEntity<Object> update(final I id, final T entity);
}

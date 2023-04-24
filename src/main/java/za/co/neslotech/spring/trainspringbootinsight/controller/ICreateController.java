package za.co.neslotech.spring.trainspringbootinsight.controller;

import org.springframework.http.ResponseEntity;

public interface ICreateController<T> {

    ResponseEntity<T> create(final T entity);
}

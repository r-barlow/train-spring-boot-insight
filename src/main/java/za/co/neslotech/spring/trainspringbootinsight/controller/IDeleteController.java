package za.co.neslotech.spring.trainspringbootinsight.controller;

import org.springframework.http.ResponseEntity;

public interface IDeleteController<T, I> {

    ResponseEntity<Void> delete(final I id);
}

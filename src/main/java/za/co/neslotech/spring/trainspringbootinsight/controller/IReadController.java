package za.co.neslotech.spring.trainspringbootinsight.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IReadController<T, I> {

    ResponseEntity<List<T>> list();

    ResponseEntity<T> get(final I id);
}

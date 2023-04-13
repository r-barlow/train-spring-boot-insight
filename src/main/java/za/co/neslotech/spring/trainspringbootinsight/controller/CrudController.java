package za.co.neslotech.spring.trainspringbootinsight.controller;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * A generic REST API Controller with CRUD operations.
 * The child class should use the following annotations
 *      1. @RestController
 * @param <T> The entity class.
 * @param <I> The primary key field type.
 */
public abstract class CrudController<T, I> {

    protected abstract String[] getUpdateColumnExclusions();
    protected abstract String getEntityId(T entity);
    protected abstract T saveAndFlush(T entity);
    protected abstract List<T> findAll();
    protected abstract Optional<T> findById(I id);
    protected abstract void delete(T entity);

    @GetMapping
    public ResponseEntity<?> list(){
        return new ResponseEntity<>(findAll(), HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<?> get(final @PathVariable I id){
        try {
            return ResponseEntity.ok(findById(id)
                    .orElseThrow(() ->
                            new EntityNotFoundException(String.format("Entity with id %s not found!", id))
                    ));
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<URI> create(final HttpServletRequest request, final @RequestBody T entity){
        var newEntity = saveAndFlush(entity);
        var location = URI.create(String.format("%s/%s", request.getRequestURL(),
                getEntityId(newEntity)));
        return ResponseEntity.created(location).body(location);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteItem(final @PathVariable I id) {

        try {
            T entity = findById(id)
                    .orElseThrow(() ->
                            new EntityNotFoundException(String.format("Entity with id %s not found!", id))
                    );

            delete(entity);
            return ResponseEntity.ok(String.format("Entity with id %s deleted successfully!", id));
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(final HttpServletRequest request, final @PathVariable I id,
                                         final @RequestBody T entity){

        try {
            T existingEntity = findById(id)
                    .orElseThrow(() ->
                            new EntityNotFoundException(String.format("Entity with id %s not found!", id))
                    );

            BeanUtils.copyProperties(entity, existingEntity, getUpdateColumnExclusions());

            var location = URI.create(request.getRequestURL().toString());
            return ResponseEntity.status(HttpStatus.OK).location(location).body(location);
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}

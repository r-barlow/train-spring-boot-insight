package za.co.neslotech.spring.trainspringbootinsight.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import za.co.neslotech.spring.trainspringbootinsight.service.CrudService;

import java.util.List;

/**
 * A generic REST API Controller with CRUD operations.
 * The child class should use the following annotations.
 *      1. @RestController
 * @param <T> The entity class.
 * @param <I> The primary key field type.
 */
public abstract class CrudController<T, I> {

    protected abstract CrudService<T, I> getService();

    @GetMapping
    public ResponseEntity<List<T>> list(){
        return ResponseEntity.ok(getService().findAll());
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<T> get(final @PathVariable I id){
            return ResponseEntity.ok(getService().findById(id));
    }

    @PostMapping
    public ResponseEntity<T> create(final @RequestBody T entity){
        final var newEntity = getService().create(entity);
        final var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(getService().getEntityId(newEntity))
                .toUri();
        return ResponseEntity.created(location).body(newEntity);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(final @PathVariable I id) {
        final T entity = getService().findById(id);

        getService().delete(entity);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<T> update(final @PathVariable I id, final @RequestBody T entity){
        final T existingEntity = getService().findById(id);

        BeanUtils.copyProperties(entity, existingEntity, getService().getUpdateColumnExclusions());
        getService().update(entity);

        return ResponseEntity.ok(existingEntity);
    }
}

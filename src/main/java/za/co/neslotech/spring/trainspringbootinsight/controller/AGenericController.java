package za.co.neslotech.spring.trainspringbootinsight.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * A generic REST API Controller with CRUD operations.
 * The child class should use the following annotations
 *      1. @RestController
 * @param <T> The entity class.
 * @param <I> The primary key field type.
 */
public abstract class AGenericController <T, I> {

    protected abstract JpaRepository<T, I> getRepository();
    protected abstract String getEntityNotFoundDescription(final I id);
    protected abstract String[] getUpdateColumnExclusions();

    @GetMapping
    public List<T> list(){
        return getRepository().findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public T get(final @PathVariable I id){
        return getRepository().findById(id).orElseThrow(() ->
                new EntityNotFoundException(getEntityNotFoundDescription(id))
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public T create(final @RequestBody T entity){
        return getRepository().saveAndFlush(entity);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(final @PathVariable I id) {
        T entity = getRepository().findById(id).orElseThrow(() ->
                new EntityNotFoundException(getEntityNotFoundDescription(id))
        );

        getRepository().delete(entity);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public T update(final @PathVariable I id, final @RequestBody T entity){

        T existingEntity = getRepository().findById(id).orElseThrow(() ->
                new EntityNotFoundException(getEntityNotFoundDescription(id))
        );

        BeanUtils.copyProperties(entity, existingEntity, getUpdateColumnExclusions());

        return getRepository().saveAndFlush(existingEntity);
    }
}

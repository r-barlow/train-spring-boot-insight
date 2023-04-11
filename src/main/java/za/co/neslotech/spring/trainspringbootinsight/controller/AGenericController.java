package za.co.neslotech.spring.trainspringbootinsight.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    protected abstract String getEntityNotFoundDescription(I id);
    protected abstract String[] getUpdateColumnExclusions();

    @GetMapping
    public List<T> list(){
        return getRepository().findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public T get(@PathVariable I id){
        return getRepository().findById(id).orElseThrow(() ->
                new EntityNotFoundException(getEntityNotFoundDescription(id))
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public T create(@RequestBody T entity){
        return getRepository().saveAndFlush(entity);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable I id) {
        T entity = getRepository().findById(id).orElseThrow(() ->
                new EntityNotFoundException(getEntityNotFoundDescription(id))
        );

        getRepository().delete(entity);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public T update(@PathVariable I id, @RequestBody T entity){

        T existingEntity = getRepository().findById(id).orElseThrow(() ->
                new EntityNotFoundException(getEntityNotFoundDescription(id))
        );

        BeanUtils.copyProperties(entity, existingEntity, getUpdateColumnExclusions());

        return getRepository().saveAndFlush(existingEntity);
    }
}

package za.co.neslotech.spring.trainspringbootinsight.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import za.co.neslotech.spring.trainspringbootinsight.model.Category;
import za.co.neslotech.spring.trainspringbootinsight.repository.ICategoryRepository;

import java.util.List;

@Service
public class CategoryService implements CrudService<Category, Long> {

    private final ICategoryRepository repository;

    public CategoryService(final ICategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category create(final Category entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public Category update(final Category entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public Category findById(final Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                                     new EntityNotFoundException(
                                             String.format("The Category entity with id %d was not found!", id))
                );
    }

    @Override
    public void delete(final Category entity) {
        repository.delete(entity);
    }

    @Override
    public Long getEntityId(final Category entity) {
        return entity.getId();
    }

    @Override
    public String[] getUpdateColumnExclusions() {
        return new String[]{"id"};
    }
}

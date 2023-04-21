package za.co.neslotech.spring.trainspringbootinsight.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import za.co.neslotech.spring.trainspringbootinsight.model.Budget;
import za.co.neslotech.spring.trainspringbootinsight.repository.IBudgetRepository;

import java.util.List;

@Service
public class BudgetService implements CrudService<Budget, Long> {

    private final IBudgetRepository repository;

    public BudgetService(final IBudgetRepository repository) {
        this.repository = repository;
    }

    @Override
    public Budget create(final Budget entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public Budget update(final Budget entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public List<Budget> findAll() {
        return repository.findAll();
    }

    @Override
    public Budget findById(final Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                                     new EntityNotFoundException(
                                             String.format("The Budget entity with id %d was not found!", id))
                );
    }

    @Override
    public void delete(final Budget entity) {
        repository.delete(entity);
    }

    @Override
    public Long getEntityId(final Budget entity) {
        return entity.getId();
    }

    @Override
    public String[] getUpdateColumnExclusions() {
        return new String[]{"id"};
    }
}

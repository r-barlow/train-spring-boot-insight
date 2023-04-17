package za.co.neslotech.spring.trainspringbootinsight.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import za.co.neslotech.spring.trainspringbootinsight.model.Transaction;
import za.co.neslotech.spring.trainspringbootinsight.repository.ITransactionRepository;

import java.util.List;

@Service
public class TransactionService implements CrudService<Transaction, Long> {

    private final ITransactionRepository repository;

    public TransactionService(final ITransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Transaction create(final Transaction entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public Transaction update(final Transaction entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public List<Transaction> findAll() {
        return repository.findAll();
    }

    @Override
    public Transaction findById(final Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                String.format("The Transaction entity with id %d was not found!", id))
                );
    }

    @Override
    public void delete(final Transaction entity) {
        repository.delete(entity);
    }

    @Override
    public Long getEntityId(final Transaction entity) {
        return entity.getId();
    }

    @Override
    public String[] getUpdateColumnExclusions() {
        return new String[]{"id"};
    }
}

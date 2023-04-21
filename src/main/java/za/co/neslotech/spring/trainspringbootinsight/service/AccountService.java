package za.co.neslotech.spring.trainspringbootinsight.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import za.co.neslotech.spring.trainspringbootinsight.model.Account;
import za.co.neslotech.spring.trainspringbootinsight.repository.IAccountRepository;

import java.util.List;

@Service
public class AccountService implements CrudService<Account, Long> {

    private final IAccountRepository repository;

    public AccountService(final IAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Account create(final Account entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public Account update(final Account entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public List<Account> findAll() {
        return repository.findAll();
    }

    @Override
    public Account findById(final Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                                     new EntityNotFoundException(
                                             String.format("The Account entity with id %d was not found!", id))
                );
    }

    @Override
    public void delete(final Account entity) {
        repository.delete(entity);
    }

    @Override
    public Long getEntityId(final Account entity) {
        return entity.getId();
    }

    @Override
    public String[] getUpdateColumnExclusions() {
        return new String[]{"id"};
    }
}

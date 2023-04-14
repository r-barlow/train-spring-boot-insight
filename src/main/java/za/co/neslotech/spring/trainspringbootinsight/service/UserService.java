package za.co.neslotech.spring.trainspringbootinsight.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import za.co.neslotech.spring.trainspringbootinsight.model.User;
import za.co.neslotech.spring.trainspringbootinsight.repository.IUserRepository;

import java.util.List;

@Service
public class UserService implements CrudService<User, Long> {

    private final IUserRepository repository;

    public UserService(final IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User create(final User entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public User update(final User entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findById(final Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("The User entity with the id %d was not found!", id))
                );
    }

    @Override
    public void delete(final User entity) {
        repository.delete(entity);
    }

    @Override
    public Long getEntityId(final User entity) {
        return entity.getId();
    }

    @Override
    public String[] getUpdateColumnExclusions() {
        return new String[]{"id"};
    }
}
package za.co.neslotech.spring.trainspringbootinsight.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import za.co.neslotech.spring.trainspringbootinsight.model.User;
import za.co.neslotech.spring.trainspringbootinsight.repository.IUserRepository;

import java.util.List;

@Service
public class UserService implements CrudService<User, Long> {

    private final IUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(final IUserRepository repository, @Lazy final PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(final User entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
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
                                     new EntityNotFoundException(
                                             String.format("The User entity with the id %d was not found!", id))
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
        return new String[]{"id", "username", "password"};
    }

    @Bean
    public UserDetailsService getUserDetailsService() {
        return this::findByUsername;
    }

    public User findByUsername(final String username) {
        return repository.findByUsername(username)
                .orElseThrow(() ->
                                     new UsernameNotFoundException(
                                             String.format("The User with username '%s' was not found!", username))
                );
    }

    public boolean verify(final User user, final String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }
}

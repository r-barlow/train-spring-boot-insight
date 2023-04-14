package za.co.neslotech.spring.trainspringbootinsight.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.neslotech.spring.trainspringbootinsight.model.Rollover;
import za.co.neslotech.spring.trainspringbootinsight.repository.IRolloverRepository;

import java.util.List;

@Service
public class RolloverService implements CrudService<Rollover, Long> {

    private final IRolloverRepository repository;

    @Autowired
    public RolloverService(IRolloverRepository repository) {
        this.repository = repository;
    }

    @Override
    public Rollover update(final Rollover entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public List<Rollover> findAll() {
        return repository.findAll();
    }

    @Override
    public Rollover findById(final Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                String.format("The Rollover entity with the id %d was not found!", id))
                );
    }

    @Override
    public void delete(final Rollover entity) {
        repository.delete(entity);
    }

    @Override
    public Long getEntityId(final Rollover entity) {
        return entity.getId();
    }

    @Override
    public String[] getUpdateColumnExclusions() {
        return new String[]{"id"};
    }
}

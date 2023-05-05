package za.co.neslotech.spring.trainspringbootinsight.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import za.co.neslotech.spring.trainspringbootinsight.model.User;
import za.co.neslotech.spring.trainspringbootinsight.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController implements IReadController<User, Long>,
        IUpdateController<User, Long>, IDeleteController<User, Long> {

    private final UserService service;

    public UserController(final UserService service) {
        this.service = service;
    }

    @Override
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<User>> list() {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> get(final @PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping(
            path = "/find/{username}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> get(final @PathVariable String username) {
        return ResponseEntity.ok(service.findByUsername(username));
    }

    @Override
    @DeleteMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> delete(final @PathVariable Long id) {

        final User entity = service.findById(id);

        service.delete(entity);
        return ResponseEntity.noContent()
                .build();
    }

    @Override
    @PutMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> update(final @PathVariable Long id, final @RequestBody User entity) {

        final User existingEntity = service.findById(id);
        BeanUtils.copyProperties(entity, existingEntity, service.getUpdateColumnExclusions());
        service.update(existingEntity);

        return ResponseEntity.ok(existingEntity);
    }
}

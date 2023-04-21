package za.co.neslotech.spring.trainspringbootinsight.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.neslotech.spring.trainspringbootinsight.model.User;
import za.co.neslotech.spring.trainspringbootinsight.service.CrudService;
import za.co.neslotech.spring.trainspringbootinsight.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController extends CrudController<User, Long> {

    private final UserService service;

    public UserController(final UserService service) {
        setCreateEnabled(false);
        this.service = service;
    }

    @Override
    protected CrudService<User, Long> getService() {
        return service;
    }
}

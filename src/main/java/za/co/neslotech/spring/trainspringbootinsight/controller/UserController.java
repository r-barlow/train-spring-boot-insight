package za.co.neslotech.spring.trainspringbootinsight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.neslotech.spring.trainspringbootinsight.model.User;
import za.co.neslotech.spring.trainspringbootinsight.repository.IUserRepository;

@RestController
@RequestMapping("/api/v1/users")
public class UserController extends AGenericController<User, Long>{

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected JpaRepository<User, Long> getRepository() {
        return userRepository;
    }

    @Override
    protected String getEntityNotFoundDescription(final Long id) {
        return String.format("The User with id %d was not found!", id);
    }

    @Override
    protected String[] getUpdateColumnExclusions() {
        return new String[]{"id"};
    }
}

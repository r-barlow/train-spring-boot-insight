package za.co.neslotech.spring.trainspringbootinsight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.neslotech.spring.trainspringbootinsight.model.User;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}

package za.co.neslotech.spring.trainspringbootinsight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.neslotech.spring.trainspringbootinsight.model.User;

public interface IUserRepository extends JpaRepository<User, Long> {

}

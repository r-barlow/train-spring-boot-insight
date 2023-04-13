package za.co.neslotech.spring.trainspringbootinsight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.neslotech.spring.trainspringbootinsight.model.Account;

public interface IAccountRepository extends JpaRepository<Account, Long> {

}

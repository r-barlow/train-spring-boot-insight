package za.co.neslotech.spring.trainspringbootinsight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.neslotech.spring.trainspringbootinsight.model.Transaction;

public interface ITransactionRepository extends JpaRepository<Transaction, Long> {

}

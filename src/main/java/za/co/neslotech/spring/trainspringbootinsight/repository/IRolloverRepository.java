package za.co.neslotech.spring.trainspringbootinsight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.neslotech.spring.trainspringbootinsight.model.Rollover;

public interface IRolloverRepository extends JpaRepository<Rollover, Long> {

}

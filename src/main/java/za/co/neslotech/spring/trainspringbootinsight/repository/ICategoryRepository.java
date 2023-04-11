package za.co.neslotech.spring.trainspringbootinsight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.neslotech.spring.trainspringbootinsight.model.Category;

public interface ICategoryRepository extends JpaRepository<Category, Long> {

}

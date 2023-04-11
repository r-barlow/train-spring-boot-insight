package za.co.neslotech.spring.trainspringbootinsight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.neslotech.spring.trainspringbootinsight.model.Category;
import za.co.neslotech.spring.trainspringbootinsight.repository.ICategoryRepository;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController extends AGenericController<Category, Long> {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    protected JpaRepository<Category, Long> getRepository() {
        return categoryRepository;
    }

    @Override
    protected String getEntityNotFoundDescription(Long id) {
        return String.format("The Category with id %d was not found!", id);
    }

    @Override
    protected String[] getUpdateColumnExclusions() {
        return new String[]{"categoryId"};
    }
}

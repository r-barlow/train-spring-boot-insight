package za.co.neslotech.spring.trainspringbootinsight.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.neslotech.spring.trainspringbootinsight.model.Category;
import za.co.neslotech.spring.trainspringbootinsight.service.CategoryService;
import za.co.neslotech.spring.trainspringbootinsight.service.CrudService;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController extends CrudController<Category, Long> {

    private final CategoryService service;

    public CategoryController(final CategoryService service) {
        this.service = service;
    }

    @Override
    protected CrudService<Category, Long> getService() {
        return service;
    }
}

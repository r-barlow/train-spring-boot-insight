package za.co.neslotech.spring.trainspringbootinsight.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.neslotech.spring.trainspringbootinsight.model.Budget;
import za.co.neslotech.spring.trainspringbootinsight.service.BudgetService;
import za.co.neslotech.spring.trainspringbootinsight.service.CrudService;

@RestController
@RequestMapping("/api/v1/budgets")
public class BudgetController extends CrudController<Budget, Long> {

    private final BudgetService service;

    public BudgetController(final BudgetService service) {
        this.service = service;
    }

    @Override
    protected CrudService<Budget, Long> getService() {
        return service;
    }
}

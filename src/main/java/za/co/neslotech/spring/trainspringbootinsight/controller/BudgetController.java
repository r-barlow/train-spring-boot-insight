package za.co.neslotech.spring.trainspringbootinsight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.neslotech.spring.trainspringbootinsight.model.Budget;
import za.co.neslotech.spring.trainspringbootinsight.repository.IBudgetRepository;

@RestController
@RequestMapping("/api/v1/budgets")
public class BudgetController extends AGenericController<Budget, Long>{

    @Autowired
    private IBudgetRepository budgetRepository;

    @Override
    protected JpaRepository<Budget, Long> getRepository() {
        return budgetRepository;
    }

    @Override
    protected String getEntityNotFoundDescription(Long id) {
        return String.format("The Budget with id %d was not found!", id);
    }

    @Override
    protected String[] getUpdateColumnExclusions() {
        return new String[]{"budgetId"};
    }
}

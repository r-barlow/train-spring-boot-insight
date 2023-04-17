package za.co.neslotech.spring.trainspringbootinsight.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.neslotech.spring.trainspringbootinsight.model.Transaction;
import za.co.neslotech.spring.trainspringbootinsight.service.CrudService;
import za.co.neslotech.spring.trainspringbootinsight.service.TransactionService;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController extends CrudController<Transaction, Long> {

    private final TransactionService service;

    public TransactionController(final TransactionService service) {
        this.service = service;
    }

    @Override
    protected CrudService<Transaction, Long> getService() {
        return service;
    }
}

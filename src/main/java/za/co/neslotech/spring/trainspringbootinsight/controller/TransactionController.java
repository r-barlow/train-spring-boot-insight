package za.co.neslotech.spring.trainspringbootinsight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.neslotech.spring.trainspringbootinsight.model.Transaction;
import za.co.neslotech.spring.trainspringbootinsight.repository.ITransactionRepository;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController extends AGenericController<Transaction, Long> {

    @Autowired
    private ITransactionRepository transactionRepository;

    @Override
    protected JpaRepository<Transaction, Long> getRepository() {
        return transactionRepository;
    }

    @Override
    protected String getEntityNotFoundDescription(Long id) {
        return String.format("The Transaction with id %d was not found!", id);
    }

    @Override
    protected String[] getUpdateColumnExclusions() {
        return new String[]{"transactionId"};
    }
}

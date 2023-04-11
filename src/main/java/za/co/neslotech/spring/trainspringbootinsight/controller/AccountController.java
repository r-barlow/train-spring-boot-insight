package za.co.neslotech.spring.trainspringbootinsight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.neslotech.spring.trainspringbootinsight.model.Account;
import za.co.neslotech.spring.trainspringbootinsight.repository.IAccountRepository;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController extends AGenericController<Account, Long> {

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    protected JpaRepository<Account, Long> getRepository() {
        return accountRepository;
    }

    @Override
    protected String getEntityNotFoundDescription(Long id) {
        return String.format("The Account with id %d was not found!", id);
    }

    @Override
    protected String[] getUpdateColumnExclusions() {
        return new String[]{"accountId"};
    }
}

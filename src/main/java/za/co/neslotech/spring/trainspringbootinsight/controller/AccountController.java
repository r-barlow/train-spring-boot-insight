package za.co.neslotech.spring.trainspringbootinsight.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.neslotech.spring.trainspringbootinsight.model.Account;
import za.co.neslotech.spring.trainspringbootinsight.service.AccountService;
import za.co.neslotech.spring.trainspringbootinsight.service.CrudService;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController extends CrudController<Account, Long> {

    private final AccountService service;

    public AccountController(final AccountService service) {
        this.service = service;
    }

    @Override
    protected CrudService<Account, Long> getService() {
        return service;
    }
}

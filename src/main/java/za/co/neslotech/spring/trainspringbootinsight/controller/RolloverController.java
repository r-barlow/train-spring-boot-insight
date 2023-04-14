package za.co.neslotech.spring.trainspringbootinsight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.neslotech.spring.trainspringbootinsight.model.Rollover;
import za.co.neslotech.spring.trainspringbootinsight.service.CrudService;
import za.co.neslotech.spring.trainspringbootinsight.service.RolloverService;

@RestController
@RequestMapping("/api/v1/rollovers")
public class RolloverController extends CrudController<Rollover, Long> {

    private final RolloverService service;

    @Autowired
    public RolloverController(final RolloverService service) {
        this.service = service;
    }

    @Override
    protected CrudService<Rollover, Long> getService() {
        return service;
    }
}

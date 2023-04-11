package za.co.neslotech.spring.trainspringbootinsight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.neslotech.spring.trainspringbootinsight.model.Rollover;
import za.co.neslotech.spring.trainspringbootinsight.repository.IRolloverRepository;

@RestController
@RequestMapping("/api/v1/rollovers")
public class RolloverController extends AGenericController<Rollover, Long> {

    @Autowired
    private IRolloverRepository rolloverRepository;

    @Override
    protected JpaRepository<Rollover, Long> getRepository() {
        return rolloverRepository;
    }

    @Override
    protected String getEntityNotFoundDescription(Long id) {
        return String.format("The Rollover with id %d was not found!", id);
    }

    @Override
    protected String[] getUpdateColumnExclusions() {
        return new String[]{"rolloverId"};
    }
}

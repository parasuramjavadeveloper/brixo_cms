package cms.brixo.se.controller;

import cms.brixo.se.dto.CreditsInfo;
import cms.brixo.se.entity.Debtor;
import cms.brixo.se.service.CreditManageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/v1/debtor")
/**
 * Rest services implementation for getAll credits operation.
* @author Parasuram
* @since 15-01-2021
 */
public class CreditManageController {

    @Autowired
    private CreditManageService service;

    @GetMapping
    public ResponseEntity<CreditsInfo> getDebtorPaymentPlans()
            throws NumberFormatException, InterruptedException, JsonMappingException, JsonProcessingException {
        log.info("In Controller getDebtorPaymentPlans method");
        return ResponseEntity.ok(service.getDebtorPaymentPlans());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Debtor> getDebtor(@PathVariable(value = "id") Integer id)
            throws NumberFormatException, InterruptedException, JsonMappingException, JsonProcessingException {
        log.info("In Controller getDebtorPaymentPlans method");
        return ResponseEntity.ok(service.getDebtorAndCreditsInfo(id));
    }
}

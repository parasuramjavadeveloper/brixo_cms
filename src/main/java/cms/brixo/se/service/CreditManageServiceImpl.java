package cms.brixo.se.service;

import cms.brixo.se.dto.CreditsInfo;
import cms.brixo.se.entity.Debtor;
import cms.brixo.se.entity.PaymentPlan;
import cms.brixo.se.exception.ResourceNotFoundException;
import cms.brixo.se.repository.DebtorRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
/**
 * Implementation of Get All Credits operation.
 *
 * @author Parasuram
 * @since 15-01-2021
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class CreditManageServiceImpl implements CreditManageService {

    @Autowired
    private DebtorRepository debtorRepository;

    @Autowired
    private ObjectFactory<CreditsInfo> creditsInfoObjectFactory;

    @Autowired
    public RestTemplate restTemplate;

    @Value("${spring.url}")
    private String apiUrl;

    private final Integer STATUS_CODE = 200;

    @Override
    /**
     * Gets Debtors and its Payment Plans for their credits
     **/
    public CreditsInfo getDebtorPaymentPlans() {
        CreditsInfo creditsInfo = creditsInfoObjectFactory.getObject();
        creditsInfo.setMessage("Success");
        creditsInfo.setStatus_code(STATUS_CODE);
        creditsInfo.setResponse(debtorRepository.findAll());
        return creditsInfo;
    }

    @Override
    /**
     * Gets Debtor Details and its Payment Plans for their credits
     * Can find his Payment Plan paid or not by isPaid flag
     **/
    public Debtor getDebtorAndCreditsInfo(Integer id) {
        Optional<Debtor> debtorOptional = debtorRepository.findById(id);
        if (debtorOptional.isPresent()) {
            return debtorOptional.get();
        }
        throw new ResourceNotFoundException("Debtor not found");
    }

    /**
     * Gets Debtor Approved Applications from Brixo API
     * And Saves Debtor details with PaymentPlans into the database
     **/
    @Override
    public void getDebtors() {
        log.info("Before Getting Debtors from Brixo API");
        CreditsInfo brixoResponse = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Accept", "application/json");
            headers.add("Content-Type", "application/json");
            headers.add("Username", "interview");
            headers.add("Password", "Hgn7epI0hg1wS");
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> creditResponse = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);
            String response = creditResponse.getBody();
            brixoResponse = new Gson().fromJson(response, CreditsInfo.class);
            log.info("Getting Debtors from Brixo API\t" + brixoResponse.toString());
            List<Debtor> debtors = new ArrayList<>();
            brixoResponse.getResponse().stream().forEach(application -> {
                List<PaymentPlan> paymentPlans = getPaymentPlans(application.getApprovedAmount(), Integer.parseInt(application.getPaybackPeriod()), application.getInterestRate(), application.getInvoiceFee());
                debtors.add(new Debtor(application.getId(), application.getFirstName(), application.getLastName(), application.getSsn(), application.getPhone(), application.getEmail(), application.getLoanType(),
                        application.getApprovedAmount(), application.getPaybackPeriod(), application.getInterestRate(), application.getInvoiceFee(), application.getStatus(), application.getCreated_at(), application.getUpdated_at(), paymentPlans));
            });
            debtorRepository.saveAll(debtors);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculates the PaymentPlans of a debtor for approvedAmount,Months,Interest and Invoice
     **/
    private List<PaymentPlan> getPaymentPlans(Double amount, Integer months, Double interest, Double invoiceFee) {

        List<PaymentPlan> paymentPlans = new ArrayList<>();
        Double amortization = amount / months;
        Long interestRate = Math.round(amount * interest / 100 / 12);
        Double totalToBePaid = 0.0;
        Double debitBalance = amount;
        for (int i = 0; i < months; i++) {
            PaymentPlan paymentPlan = new PaymentPlan();
            paymentPlan.setInterestRate(Math.round(debitBalance / amount * interestRate));
            paymentPlan.setAmortization(Math.round(amortization));
            paymentPlan.setInvoiceFee(invoiceFee);
            totalToBePaid = amortization + paymentPlan.getInterestRate() + invoiceFee;
            debitBalance = debitBalance - amortization;
            paymentPlan.setTotalToBePaid(Math.round(totalToBePaid));
            paymentPlan.setDebitBalance(Math.round(debitBalance));
            paymentPlan.setIsPaid("DUE");
            paymentPlans.add(paymentPlan);
        }
        return paymentPlans;
    }


}

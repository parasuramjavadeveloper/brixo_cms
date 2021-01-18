package cms.brixo.se.scheduler;

import cms.brixo.se.entity.Debtor;
import cms.brixo.se.entity.PaymentPlan;
import cms.brixo.se.repository.DebtorRepository;
import cms.brixo.se.repository.PaymentPlanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Scheduler to send Payment Remainder emails to Debtors on monthly basis.
 *
 * @author Parasuram
 * @since 17-01-2021
 */
@Slf4j
@Component
public class PaymentsRemainderScheduler {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    DebtorRepository debtorRepository;

    @Autowired
    PaymentPlanRepository paymentPlanRepository;

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Scheduled(cron="${spring.scheduling.job.cron}")
    public void paymentRemainder() {
        List<Debtor> debtorList = debtorRepository.findAll();
        int size = debtorList.size();
        log.info("Total Debtors : " + size);
        SimpleMailMessage message = new SimpleMailMessage();
        for (int i = 0; i < size; i++) {
            log.info("Remaining Payment Plans : " + debtorList.get(i).getPaymentPlan().size());
            if (debtorList.get(i).getPaymentPlan().size() > 0) {
                PaymentPlan paymentPlan = null;
                int paymentPlanSize = debtorList.get(i).getPaymentPlan().size();
                for (int j = 0; j < paymentPlanSize; j++) {
                    if (debtorList.get(i).getPaymentPlan().get(j).getIsPaid().equals("DUE")) {
                        paymentPlan = debtorList.get(i).getPaymentPlan().get(j);
                        message.setFrom(senderEmail);
                        message.setReplyTo(senderEmail);
                        message.setSentDate(new Date());
                        message.setTo(debtorList.get(i).getEmail());
                        message.setSubject("Payment Remainder");
                        message.setText("Hello " + debtorList.get(i).getFirstName() + "the payment " + paymentPlan.getTotalToBePaid() + "kr of this month is due Kindly pay the payment on the time");
                        log.info("Mail Message\t" + message.toString());
                        javaMailSender.send(message);
                        paymentPlan.setIsPaid("PAID");
                        paymentPlanRepository.saveAndFlush(paymentPlan);
                        break;
                    }
                }
            }
        }
    }
}

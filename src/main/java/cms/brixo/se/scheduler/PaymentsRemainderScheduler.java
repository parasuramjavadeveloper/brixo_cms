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
import java.util.Optional;

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

    @Scheduled(cron = "${spring.scheduling.job.cron}")
    public void paymentRemainder() {
        List<Debtor> debtorList = debtorRepository.findAll();
        debtorList.forEach(debtor -> {
            int size = debtor.getPaymentPlan().size();
            SimpleMailMessage mailMessage = null;
            if (size > 0) {
                List<PaymentPlan> payments = debtor.getPaymentPlan();
                Optional<PaymentPlan> optionalPaymentPlan = payments.stream().filter(plan -> plan.getIsPaid().equalsIgnoreCase("DUE")).findFirst();
                if (optionalPaymentPlan.isPresent()) {
                    PaymentPlan payment = optionalPaymentPlan.get();
                    debtor.setEmail("parasuramyerramsetty@gmail.com");
                    mailMessage = simpleMailMessage(debtor.getEmail());
                    mailMessage.setSubject("Payment Remainder");
                    mailMessage.setText("Hello " + debtor.getFirstName() + "the payment " + payment.getTotalToBePaid() + "kr of this month is due Kindly pay the payment on the time");
                    log.info("Mail Message\t" + mailMessage.toString());
                    javaMailSender.send(mailMessage);
                    payment.setIsPaid("PAID");
                    paymentPlanRepository.saveAndFlush(payment);
                } else {
                    debtor.setEmail("parasuramyerramsetty@gmail.com");
                    mailMessage = simpleMailMessage(debtor.getEmail());
                    mailMessage.setTo(debtor.getEmail());
                    mailMessage.setSubject("No Payment Dues");
                    mailMessage.setText("Hello " + debtor.getFirstName() + "Thank you for choosing Brixo.You have successfully paid all your dues. We are happy to serve you again.\n");
                    log.info("Mail Message\t" + mailMessage.toString());
                    javaMailSender.send(mailMessage);
                }
            }
        });
    }

    private SimpleMailMessage simpleMailMessage(String email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(senderEmail);
        mailMessage.setReplyTo(senderEmail);
        mailMessage.setTo(email);
        mailMessage.setSentDate(new Date());
        return mailMessage;
    }
}

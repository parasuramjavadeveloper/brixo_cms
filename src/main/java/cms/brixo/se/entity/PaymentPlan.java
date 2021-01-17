package cms.brixo.se.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PAYMENTPLAN")
/**
 * * Persistent class for PaymentPlan entity stored in table PAYMENTPLAN.
 @author Parasuram
 @since 15-01-2021
 */
public class PaymentPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long amortization;
    private long interestRate;
    private Double invoiceFee;
    private long totalToBePaid;
    private long debitBalance;
    private String isPaid;

}

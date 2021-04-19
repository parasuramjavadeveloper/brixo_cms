package cms.brixo.se.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DEBTOR")
/**
 * Persistent class for Debtor entity stored in table DEBTOR.
 * @author Parasuram
 * @since 15-01-2021
 */
public class Debtor {

    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String ssn;
    private String phone;
    private String email;
    private String loanType;
    private Double approvedAmount;
    private String paybackPeriod;
    private Double interestRate;
    private Double invoiceFee;
    private String status;
    private String created_at;
    private String updated_at;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "debit_id")
    private List<PaymentPlan> paymentPlan;


}

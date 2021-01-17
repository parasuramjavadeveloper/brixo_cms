package cms.brixo.se.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(value = Include.NON_NULL)
/**
 * Application Class for Representing Each Application
 * @author Parasuram
 * @since 15-01-2021
 */
public class Application {

    private int id;
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


}

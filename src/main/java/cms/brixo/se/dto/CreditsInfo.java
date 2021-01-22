package cms.brixo.se.dto;

import cms.brixo.se.entity.Debtor;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(value = Include.NON_NULL)
@Component
@Scope(scopeName = "prototype")
/**
 CreditsInfo Class for representing debtor Info Along with credits and PaymentPlans
 *@author Parasuram
 *@since 15-01-2021
 */
public class CreditsInfo {

    private int status_code;
    private String Message;
    List<Debtor> Response = new ArrayList<Debtor>();


}

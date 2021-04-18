package cms.brixo.se.utils;

import cms.brixo.se.dto.CreditsInfo;
import cms.brixo.se.entity.Debtor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * @author Parasuram
 */
public class MockUtils {

    public static CreditsInfo getCreditsInfo() {
        CreditsInfo creditsInfo = new CreditsInfo();
        creditsInfo.setStatus_code(200);
        creditsInfo.setMessage("SUCCESS");
        creditsInfo.setResponse(debtors());
        return creditsInfo;
    }

    private static List<Debtor> debtors() {
        List<Debtor> debtors = new ArrayList<>();
        debtors.add(debtor());
        return debtors;
    }

    public static Optional<Debtor> debtorOptional(){
        Optional<Debtor> optionalDebtor =  Optional.of(debtor());
        return optionalDebtor;
    }

    public static Optional<Debtor> debtorEmptyOptional(){
        Optional<Debtor> empty =  Optional.empty();
        return empty;
    }

    private static Debtor debtor(){
        Debtor debtor = new Debtor();
        debtor.setId(101);
        debtor.setEmail("test1@brixo.se");
        debtor.setApprovedAmount(1000.0);
        debtor.setFirstName("Adam");
        debtor.setLastName("Marklund");
        debtor.setInterestRate(25.0);
        debtor.setInvoiceFee(10.0);
        debtor.setLoanType("straight_amortization");
        debtor.setPaybackPeriod("3");
        debtor.setStatus("approved");
        debtor.setSsn("198504231234");
        debtor.setPhone("0714256810");
        return debtor;
    }

}

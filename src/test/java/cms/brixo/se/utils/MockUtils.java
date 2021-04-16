package cms.brixo.se.utils;

import cms.brixo.se.dto.CreditsInfo;
import cms.brixo.se.entity.Debtor;

import java.util.ArrayList;
import java.util.List;


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
        return debtors;
    }

}

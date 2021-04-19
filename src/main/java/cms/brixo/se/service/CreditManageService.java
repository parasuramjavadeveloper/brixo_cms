package cms.brixo.se.service;

import cms.brixo.se.dto.CreditsInfo;
import cms.brixo.se.entity.Debtor;

public interface CreditManageService {
    CreditsInfo getDebtorPaymentPlans();

    Debtor getDebtorAndCreditsInfo(Integer id);

    void getDebtors();

}

package cms.brixo.se.service;

import cms.brixo.se.dto.CreditsInfo;
import cms.brixo.se.entity.Debtor;

public interface CreditManageService {
    public CreditsInfo getDebtorPaymentPlans();

    public Debtor getDebtorAndCreditsInfo(Integer id);

}

package cms.brixo.se.service;

import cms.brixo.se.dto.CreditsInfo;
import cms.brixo.se.entity.Debtor;
import cms.brixo.se.exception.ResourceNotFoundException;
import cms.brixo.se.repository.DebtorRepository;
import cms.brixo.se.repository.PaymentPlanRepository;
import cms.brixo.se.utils.MockUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.ObjectFactory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Parasuram
 */
@RunWith(MockitoJUnitRunner.class)
public class CreditManageServiceTest {
    @InjectMocks
    private CreditManageServiceImpl creditManageService;

    @Mock
    private DebtorRepository debtorRepository;

    @Mock
    private PaymentPlanRepository paymentPlanRepository;

    @Mock
    private ObjectFactory<CreditsInfo> creditsInfoObjectFactory;

    @Before
    public void setUp() {
        creditManageService.setCreditsInfoObjectFactory(creditsInfoObjectFactory);
        creditManageService.setDebtorRepository(debtorRepository);
    }

    @Test
    public void testGetDebtorPaymentPlans() {
        when(creditsInfoObjectFactory.getObject()).thenReturn(MockUtils.getCreditsInfo());
        when(debtorRepository.findAll()).thenReturn(MockUtils.getCreditsInfo().getResponse());
        Assert.assertNotNull(creditManageService.getDebtorPaymentPlans());
    }

    @Test
    public void testGetDebtorAndCreditsInfoById() {
        when(debtorRepository.findById(any())).thenReturn(MockUtils.debtorOptional());
        Debtor debtor = creditManageService.getDebtorAndCreditsInfo(101);
        Assert.assertNotNull(debtor);
        Assert.assertEquals(debtor.getFirstName(), "Adam");
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetDebtorAndCreditsInfoNotFoundById() {
        when(debtorRepository.findById(any())).thenReturn(MockUtils.debtorEmptyOptional());
        creditManageService.getDebtorAndCreditsInfo(1001);
    }
}
package cms.brixo.se.service;

import cms.brixo.se.dto.CreditsInfo;
import cms.brixo.se.entity.Debtor;
import cms.brixo.se.exception.ResourceNotFoundException;
import cms.brixo.se.repository.DebtorRepository;
import cms.brixo.se.repository.PaymentPlanRepository;
import cms.brixo.se.utils.MockUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.ObjectFactory;

import java.io.File;
import java.io.IOException;

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

    @Mock
    private ObjectMapper objectMapper;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

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

    @Test
    public void testGetDebtorsPaymentPlansFromJsonFile() throws IOException {

        creditManageService.setFilePath("src//test//resources//debtor.json");
        creditManageService.setObjectMapper(objectMapper);
        when(debtorRepository.saveAll(any())).thenReturn(MockUtils.getCreditsInfo().getResponse());
        when(objectMapper.readValue(new File("src//test//resources//debtor.json"), CreditsInfo.class)).thenReturn(MockUtils.getCreditsInfo());
        creditManageService.getDebtors();
    }

    @Test(expected = ResourceNotFoundException.class)
    @Ignore
    public void testGetDebtorsPaymentPlansFromInvalidFilePath() {
    	   creditManageService.getDebtors();
       // expectedEx.expect(ResourceNotFoundException.class);
        //expectedEx.expectMessage("File Path cannot be null or empty");
       // creditManageService.setObjectMapper(objectMapper);
        //when(debtorRepository.saveAll(any())).thenReturn(MockUtils.getCreditsInfo().getResponse());
        //when(objectMapper.readValue(new File("src//test//resources//debtor.json"), CreditsInfo.class)).thenReturn(MockUtils.getCreditsInfo());
     
    }

}

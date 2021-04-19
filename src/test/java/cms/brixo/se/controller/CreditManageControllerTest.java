package cms.brixo.se.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Parasuram
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class CreditManageControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/**
	 * Test to test getDebtorsPaymentPlans
	 **/
	@Test
	public void testGetDebtorPaymentPlans() throws Exception {

		MvcResult mvcResult = mockMvc.perform(get("/api/v1/debtor").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("message").value("Success")).andExpect(jsonPath("status_code").value(200))
				.andExpect(jsonPath("response.[0].id").value(101))
				.andExpect(jsonPath("response.[0].approvedAmount").value(1000))
				.andExpect(jsonPath("response.[0].paybackPeriod").value("3"))
				.andExpect(jsonPath("response.[0].interestRate").value(25))
				.andExpect(jsonPath("response.[0].invoiceFee").value(10))
				.andExpect(jsonPath("response.[0].paymentPlan[0].totalToBePaid").value(364))
				.andExpect(jsonPath("response.[0].paymentPlan[0].debitBalance").value(667))
				.andExpect(jsonPath("response.[0].paymentPlan[0].amortization").value(333)).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(), 200);
	}

	/**
	 * Test getDebtor
	 **/
	@Test
	public void testGetDebtor() throws Exception {

		MvcResult mvcResult = mockMvc.perform(get("/api/v1/debtor/101").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("id").value(101)).andExpect(jsonPath("approvedAmount").value(1000))
				.andExpect(jsonPath("paybackPeriod").value("3")).andExpect(jsonPath("interestRate").value(25))
				.andExpect(jsonPath("invoiceFee").value(10))
				.andExpect(jsonPath("paymentPlan[0].totalToBePaid").value(364))
				.andExpect(jsonPath("paymentPlan[0].debitBalance").value(667))
				.andExpect(jsonPath("paymentPlan[0].amortization").value(333)).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(), 200);
	}

	/**
	 * Test getDebtor
	 **/
	@Test
	public void testGetDebtorNotFound() throws Exception {

		MvcResult mvcResult = mockMvc.perform(get("/api/v1/debtor/1001").accept(MediaType.APPLICATION_JSON))
				.andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(), 404);
	}

}

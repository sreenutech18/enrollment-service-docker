package com.keybank.validator;






import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.keybank.exception.EnrollmentRequestInvalidException;
import com.keybank.model.EnrollmentRequest;

class EnrollmentValidatorTest {

	EnrollmentValidator validator = null;
	EnrollmentRequest enrollmentRequest = null;
	
	@BeforeEach
	public void setUp() throws Exception {
		
		validator = new EnrollmentValidator();

		enrollmentRequest = buildEnrollmentRequest();

	}

	@Test
	public void testValidateRequest_Null_Scenario() {

		try {
			EnrollmentValidator validator = new EnrollmentValidator();

			validator.validateRequest(null);
		} catch (EnrollmentRequestInvalidException e) {

			assertEquals("enr001", e.getRespCode());
			assertEquals("No Enrollment details", e.getRespMsg());

		}

	}

	@Test
	public void test_MobileNumber_Null_Scenario() {

		try {
			
			enrollmentRequest.setMobileNum(null);

			validator.validateRequest(enrollmentRequest);
		} catch (EnrollmentRequestInvalidException e) {

			assertEquals("enr002", e.getRespCode());

		}

	}

	

	@Test
	public void test_MobileNumber_Empty_Scenario() {

		try {
			enrollmentRequest.setMobileNum(" ");
			validator.validateRequest(enrollmentRequest);
		} catch (EnrollmentRequestInvalidException e) {

			assertEquals("enr002", e.getRespCode());

		}

	}

	@Test
	public void test_BillDate_Null_Scenario() {

		try {
		
			enrollmentRequest.setBillDate(null);

			validator.validateRequest(enrollmentRequest);
		} catch (EnrollmentRequestInvalidException e) {

			assertEquals("enr003", e.getRespCode());

		}

	}
	
	@Test
	public void test_BillDate_Empty_Scenario() {

		try {
		
			enrollmentRequest.setBillDate(" ");

			validator.validateRequest(enrollmentRequest);
		} catch (EnrollmentRequestInvalidException e) {

			assertEquals("enr003", e.getRespCode());

		}

	}
	
	@Test
	public void test_PaymentDate_Null_Scenario() {

		try {
		
			enrollmentRequest.setPaymentDate(null);

			validator.validateRequest(enrollmentRequest);
		} catch (EnrollmentRequestInvalidException e) {

			assertEquals("enr004", e.getRespCode());

		}

	}
	
	@Test
	public void test_PaymentDate_Empty_Scenario() {

		try {
		
			enrollmentRequest.setPaymentDate(" ");

			validator.validateRequest(enrollmentRequest);
		} catch (EnrollmentRequestInvalidException e) {

			assertEquals("enr004", e.getRespCode());

		}

	}
	
	@Test
	public void test_ProviderType_Null_Scenario() {

		try {
		
			enrollmentRequest.setProviderType(null);

			validator.validateRequest(enrollmentRequest);
		} catch (EnrollmentRequestInvalidException e) {

			assertEquals("enr005", e.getRespCode());

		}

	}
	
	@Test
	public void test_ProviderType_Emptyl_Scenario() {

		try {
		
			enrollmentRequest.setProviderType(" ");

			validator.validateRequest(enrollmentRequest);
		} catch (EnrollmentRequestInvalidException e) {

			assertEquals("enr005", e.getRespCode());

		}

	}
	
	
	private EnrollmentRequest buildEnrollmentRequest() {
		EnrollmentRequest enrollmentRequest = new EnrollmentRequest();
		enrollmentRequest.setMobileNum("9223456781");
		enrollmentRequest.setAmount(1000.0f);
		enrollmentRequest.setBillDate("15-04-2022");
		enrollmentRequest.setCustomerName("sreenu");
		enrollmentRequest.setPaymentDate("24-04-2022");
		enrollmentRequest.setProviderType("o2");
		return enrollmentRequest;
	}

	@AfterEach
	public void tearDown() throws Exception {
		
		validator = null;
		enrollmentRequest = null;
	}

}

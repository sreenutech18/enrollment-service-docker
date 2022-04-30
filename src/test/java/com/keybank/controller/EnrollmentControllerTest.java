package com.keybank.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.keybank.dao.EnrollmentDao;
import com.keybank.model.EnrollmentRequest;
import com.keybank.model.EnrollmentResponse;
import com.keybank.service.EnrollmentService;
import com.keybank.service.EnrollmentServiceImpl;
import com.keybank.validator.EnrollmentValidator;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ EnrollmentController.class, EnrollmentValidator.class, EnrollmentService.class,
		EnrollmentServiceImpl.class })
class EnrollmentControllerTest {

	@InjectMocks // it will create the controller objects and inject mock object into controller
					// class
	EnrollmentController enrollmentController;

	@Mock
	EnrollmentValidator validator;

	@Mock
	EnrollmentService enrollmentService;
	
	

	@BeforeEach
	void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void testEnrollment() throws Exception {
		
		//validator expectation
		PowerMockito.doCallRealMethod().when(validator).validateRequest(ArgumentMatchers.any(EnrollmentRequest.class));
		
		//service expectation
		PowerMockito.when(enrollmentService.enrollment(ArgumentMatchers.any(EnrollmentRequest.class),
				ArgumentMatchers.anyString())).thenReturn(buildEnrollResp());

		EnrollmentRequest enrollmentRequest = new EnrollmentRequest();

		enrollmentRequest.setMobileNum("1212121");
		enrollmentRequest.setCustomerName("sreenu");
		enrollmentRequest.setPaymentDate("03-04-2022");
		enrollmentRequest.setBillDate("15-04-2022");
		enrollmentRequest.setAmount(1000.0f);
		enrollmentRequest.setProviderType("O2");

		EnrollmentResponse enrollmentResp = enrollmentController.enrollment(enrollmentRequest, "web", "abc123xyz",
				"03-04-2022 10:45:20");

		assertNotNull(enrollmentResp);
		assertEquals("0", enrollmentResp.getRespCode());
	}

	private EnrollmentResponse buildEnrollResp() {

		System.out.println("Entered into mock enroll resp");

		EnrollmentResponse enrollResp = new EnrollmentResponse();

		enrollResp.setRespCode("0");
		enrollResp.setRespMsg("success");
		enrollResp.setEnrollmentStatus("enrollment successful");

		return enrollResp;
	}

}

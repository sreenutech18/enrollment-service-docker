package com.keybank.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.keybank.builder.EnrollmentRequestBuilder;
import com.keybank.builder.EnrollmentResponseBuilder;
import com.keybank.dao.EnrollmentDao;
import com.keybank.exception.BusinessException;
import com.keybank.exception.O2ServiceException;
import com.keybank.exception.SystemException;
import com.keybank.model.Accountdetails;
import com.keybank.model.EnrollmentDaoRequest;
import com.keybank.model.EnrollmentDaoResponse;
import com.keybank.model.EnrollmentRequest;
import com.keybank.model.EnrollmentResponse;
import com.keybank.model.O2ServiceRequest;
import com.keybank.model.O2ServiceResponse;
import com.keybank.svcclient.O2ServiceClient;

@RunWith(PowerMockRunner.class)
@PrepareForTest({EnrollmentServiceImpl.class,EnrollmentRequestBuilder.class,EnrollmentResponseBuilder.class,O2ServiceClient.class,EnrollmentDao.class})
class EnrollmentServiceImplTest {

	@InjectMocks
	EnrollmentServiceImpl mockenrollmentServiceImpl;

	@Mock
	EnrollmentRequestBuilder enrollmentRequestBuilder;
	@Mock
	EnrollmentResponseBuilder enrollmentResponseBuilder;
	@Mock
	O2ServiceClient o2ServiceClient;
	@Mock
	EnrollmentDao enrollmentDao;

	@BeforeEach
	void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testEnrollment_Success_Scenarios() throws O2ServiceException, BusinessException, SystemException {

		PowerMockito.when(enrollmentRequestBuilder.buildO2Request(ArgumentMatchers.any(EnrollmentRequest.class)))
				.thenCallRealMethod();
		
		PowerMockito.when(o2ServiceClient.enrollment(ArgumentMatchers.any(O2ServiceRequest.class)))
				.thenReturn(BuildO2ServiceResp());
		
		PowerMockito.when(enrollmentRequestBuilder.buildEnrollDaoRequest(ArgumentMatchers.any(EnrollmentRequest.class), ArgumentMatchers.anyString()))
		.thenCallRealMethod();
		
		PowerMockito.when(enrollmentDao.enrollment(ArgumentMatchers.any(EnrollmentDaoRequest.class)))
		.thenReturn(BuildEnrollDaoResp());
		
		PowerMockito.when(enrollmentResponseBuilder.buildEnrollResponse(ArgumentMatchers.any(EnrollmentDaoResponse.class)))
		.thenCallRealMethod();
		
		
		EnrollmentRequest enrollmentRequest = new EnrollmentRequest();
		Accountdetails accountDetails = new Accountdetails();
		accountDetails.setCardNumber("052456789900");
		accountDetails.setCvv("123");
		accountDetails.setExpDate("12/2022");
		accountDetails.setNameOnCard("sreenu");
		
		enrollmentRequest.setAccountDetails(accountDetails);
		enrollmentRequest.setCustomerName("sreenu");
		enrollmentRequest.setMobileNum("9109987652");
		enrollmentRequest.setBillDate("07-APR-2022");
		enrollmentRequest.setAmount(1000.0f);
		enrollmentRequest.setProviderType("O2");
		enrollmentRequest.setPaymentDate("15-APR-2022");
		
		
		EnrollmentResponse enrollmentResponse = mockenrollmentServiceImpl.enrollment(enrollmentRequest, "web");
		
		System.out.println("enrollmentResponse is :  "+enrollmentResponse);
		assertNotNull(enrollmentResponse);
		
		assertEquals("0", enrollmentResponse.getRespCode());
		

	}
	
	
	//testcase 2 : test business exception scenario from dao
	
	@Test
	void testEnrollment_BusinessException_Scenarios() {
		EnrollmentResponse enrollmentResponse;
		try {
			PowerMockito.when(enrollmentRequestBuilder.buildO2Request(ArgumentMatchers.any(EnrollmentRequest.class)))
					.thenCallRealMethod();
			
			PowerMockito.when(o2ServiceClient.enrollment(ArgumentMatchers.any(O2ServiceRequest.class)))
					.thenReturn(BuildO2ServiceResp());
			
			PowerMockito.when(enrollmentRequestBuilder.buildEnrollDaoRequest(ArgumentMatchers.any(EnrollmentRequest.class), ArgumentMatchers.anyString()))
			.thenCallRealMethod();
			
			PowerMockito.when(enrollmentDao.enrollment(ArgumentMatchers.any(EnrollmentDaoRequest.class)))
			.thenThrow(new BusinessException("100","invalid mobilenumber"));
			
			PowerMockito.when(enrollmentResponseBuilder.buildEnrollResponse(ArgumentMatchers.any(EnrollmentDaoResponse.class)))
			.thenCallRealMethod();
			
			
			EnrollmentRequest enrollmentRequest = new EnrollmentRequest();
			Accountdetails accountDetails = new Accountdetails();
			accountDetails.setCardNumber("052456789900");
			accountDetails.setCvv("123");
			accountDetails.setExpDate("12/2022");
			accountDetails.setNameOnCard("sreenu");
			
			enrollmentRequest.setAccountDetails(accountDetails);
			enrollmentRequest.setCustomerName("sreenu");
			enrollmentRequest.setMobileNum("9109987652");
			enrollmentRequest.setBillDate("07-APR-2022");
			enrollmentRequest.setAmount(1000.0f);
			enrollmentRequest.setProviderType("O2");
			enrollmentRequest.setPaymentDate("15-APR-2022");
			
			
			enrollmentResponse = mockenrollmentServiceImpl.enrollment(enrollmentRequest, "web");
			
			System.out.println("enrollmentResponse is :  "+enrollmentResponse);
		} catch (O2ServiceException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			System.out.println("Entered into Business Exception******");
			assertEquals("100", e.getRespCode());
		} catch (SystemException e) {
			e.printStackTrace();
		}
		
		
		

	}
	
	
	
	//test case 3 : system exception scenario from dao
	
	@Test
	void testEnrollment_SystemException_Scenarios() {

		EnrollmentResponse enrollmentResponse;
		try {
			PowerMockito.when(enrollmentRequestBuilder.buildO2Request(ArgumentMatchers.any(EnrollmentRequest.class)))
					.thenCallRealMethod();
			
			PowerMockito.when(o2ServiceClient.enrollment(ArgumentMatchers.any(O2ServiceRequest.class)))
					.thenReturn(BuildO2ServiceResp());
			
			PowerMockito.when(enrollmentRequestBuilder.buildEnrollDaoRequest(ArgumentMatchers.any(EnrollmentRequest.class), ArgumentMatchers.anyString()))
			.thenCallRealMethod();
			
			PowerMockito.when(enrollmentDao.enrollment(ArgumentMatchers.any(EnrollmentDaoRequest.class)))
			.thenThrow(new SystemException("105","SQL Grammer Error"));
			
			PowerMockito.when(enrollmentResponseBuilder.buildEnrollResponse(ArgumentMatchers.any(EnrollmentDaoResponse.class)))
			.thenCallRealMethod();
			
			
			EnrollmentRequest enrollmentRequest = new EnrollmentRequest();
			Accountdetails accountDetails = new Accountdetails();
			accountDetails.setCardNumber("052456789900");
			accountDetails.setCvv("123");
			accountDetails.setExpDate("12/2022");
			accountDetails.setNameOnCard("sreenu");
			
			enrollmentRequest.setAccountDetails(accountDetails);
			enrollmentRequest.setCustomerName("sreenu");
			enrollmentRequest.setMobileNum("9109987652");
			enrollmentRequest.setBillDate("07-APR-2022");
			enrollmentRequest.setAmount(1000.0f);
			enrollmentRequest.setProviderType("O2");
			enrollmentRequest.setPaymentDate("15-APR-2022");
			
			
			enrollmentResponse = mockenrollmentServiceImpl.enrollment(enrollmentRequest, "web");
			
			System.out.println("enrollmentResponse is :  "+enrollmentResponse);
		} catch (O2ServiceException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			System.out.println("Entered into System Exception : "+e.getRespCode());
			assertEquals("105", e.getRespCode());
		}
		
		
		

	}
	
	//test case 4 : test some exception scenario from o2 service

	
	private EnrollmentDaoResponse BuildEnrollDaoResp() {
		
		System.out.println("Entered into mock dao response");
		
		EnrollmentDaoResponse enrollmentDaoResp = new EnrollmentDaoResponse();
		
		enrollmentDaoResp.setRespCode("0");
		enrollmentDaoResp.setRespMsg("success");
		
		return enrollmentDaoResp;
	}

	
	private O2ServiceResponse BuildO2ServiceResp() {
		
		System.out.println("Entered into mock o2 response");
		
		O2ServiceResponse o2Response = new O2ServiceResponse();
		
		o2Response.setRespCode("0");
		o2Response.setRespMsg("success");
		o2Response.setDescription("enrollment is successfull");
		
		return o2Response;
	}

}

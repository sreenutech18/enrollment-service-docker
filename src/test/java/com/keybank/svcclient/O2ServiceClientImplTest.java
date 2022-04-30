package com.keybank.svcclient;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.keybank.exception.O2ServiceException;
import com.keybank.model.O2ServiceRequest;
import com.keybank.model.O2ServiceResponse;



@RunWith(PowerMockRunner.class)
@PrepareForTest({ O2ServiceClient.class, O2ServiceClientImpl.class, RestTemplate.class, ResponseEntity.class })
class O2ServiceClientImplTest {

	@InjectMocks
	O2ServiceClientImpl o2ServiceClientImpl;

	@Mock
	RestTemplate restTemplate;
	
	@BeforeEach
	void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
	}


	//@Test
	void testEnrollment_Success_Scenario() throws O2ServiceException {
		
		
		Mockito.when(restTemplate.exchange(Mockito.anyString(), 
				Mockito.<HttpMethod>any(), Mockito.<HttpEntity<O2ServiceRequest>>any(), 
				Mockito.<Class<O2ServiceResponse>> any(),
				Mockito.<String, Object> anyMap())).thenReturn(buildResponseEntity());
		

		O2ServiceRequest request = new O2ServiceRequest();

		request.setAmount(1000.0f);
		request.setBillDate("01-04-2022");
		request.setClienId("web");
		request.setCorrlationId("abc123xyz");
		request.setCustomerName("sreenu");
		request.setMobileNum("121212121");
		request.setMsgTS("07-04-2022");
		request.setPaymentDate("15-APR-2022");
		
		System.out.println("o2ServiceClientImpl is :"+o2ServiceClientImpl);

		O2ServiceResponse o2Response = o2ServiceClientImpl.enrollment(request);

		assertNotNull(o2Response);
		assertEquals("0", o2Response.getRespCode());

	}


	
	private ResponseEntity<O2ServiceResponse> buildResponseEntity() {
		
		System.out.println("Entered into mock response");
		
		O2ServiceResponse o2ServiceResponse = new O2ServiceResponse();
		
		o2ServiceResponse.setRespCode("0");
		o2ServiceResponse.setRespMsg("success");
		o2ServiceResponse.setValid(true);
		
		
		ResponseEntity<O2ServiceResponse> responseEntity = new ResponseEntity<O2ServiceResponse>(o2ServiceResponse,HttpStatus.OK);
		
		System.out.println("return responseEntity is :"+responseEntity);
		
		return responseEntity;
	}

}

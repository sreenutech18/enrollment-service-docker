/** @Copyright 2022 Keybank pvt ltd. All rights are reserved, you should not
 *  disclose the information outside otherwise terms and conditions will apply
 */
package com.keybank.svcclient;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.keybank.exception.O2ServiceException;
import com.keybank.model.O2ServiceRequest;
import com.keybank.model.O2ServiceResponse;
import com.keybank.util.EnrollmentConstant;

/**
 * @author sreenu,09-Mar-2022 Description :
 */

@Component
public class O2ServiceClientImpl implements O2ServiceClient {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(O2ServiceClientImpl.class);
	
	@Value("${o2ServiceUrl}")
	private String serviceUri;
	
	@Autowired
	RestTemplate restTemplate;
	
	

	@Override
	public O2ServiceResponse enrollment(O2ServiceRequest request) throws O2ServiceException {

		LOGGER.debug(" Entered O2 Service ");
		LOGGER.info("O2ServiceRequest is :", request);

		// get the request from service

		// prepare the request for O2Service like add headers, body

		// Create RestTemplate and make call to O2Service , get the response

		O2ServiceResponse o2Serviceresponse;
		try {
			//String uri = "http://localhost:9090/v1/mobile/verify";
			//RestTemplate restTemplate = new RestTemplate();
			//restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set(EnrollmentConstant.CLIENT_ID, EnrollmentConstant.HDFC);
			headers.set(EnrollmentConstant.MSGTS, new Date().toString());
			headers.set(EnrollmentConstant.REQUESTID, UUID.randomUUID().toString().substring(0,15));
			headers.set(EnrollmentConstant.VERSION, EnrollmentConstant.versionNO);

			HttpEntity<O2ServiceRequest> requestEntity = new HttpEntity<O2ServiceRequest>(request, headers);
			System.out.println("serviceUri is ***:"+serviceUri);
			ResponseEntity<O2ServiceResponse> responseEntity = restTemplate.exchange(serviceUri, HttpMethod.POST, requestEntity,
					O2ServiceResponse.class);
			
			System.out.println("responseEntity is :"+responseEntity);

			o2Serviceresponse = null;
			if (responseEntity.getStatusCode().is2xxSuccessful()) {

				o2Serviceresponse = responseEntity.getBody(); // O2ServiceResponse object
															   
				System.out.println("o2Serviceresponse is :"+o2Serviceresponse);

			}else if( responseEntity.getStatusCode().is4xxClientError()) {
				
				throw new O2ServiceException("400","client side errors");
			}else if( responseEntity.getStatusCode().is5xxServerError()) {
				throw new O2ServiceException("500","server side errors");
			}

			System.out.println(" resposneEntity is :" + responseEntity);
			System.out.println("responseEntity :" + responseEntity.getBody());
		} catch (RestClientException e) {
			
			LOGGER.error(" error getting while invoking o2 service :", e.getMessage());
			
			throw new O2ServiceException("222",e.getMessage());
			
		} catch (O2ServiceException o2se) {
			
			LOGGER.error(" getting failure response from o2 service :", o2se.getMessage());
			throw o2se;
		}

		LOGGER.debug(" Exit from  O2 Service ");
		LOGGER.info("o2Serviceresponse is :", o2Serviceresponse);

		return o2Serviceresponse;
	}

}

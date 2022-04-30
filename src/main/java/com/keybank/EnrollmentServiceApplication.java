package com.keybank;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EnrollmentServiceApplication {
	
	@Value("${connectionTimeout}")
	private int connectionTimeout;
	
	@Value("${readTimeout}")
	private int readTimeout;

	public static void main(String[] args) {
		SpringApplication.run(EnrollmentServiceApplication.class, args);
	}
	
	
	  @Bean 
	  
	  public RestTemplate restTemplate() {
	  
	  HttpComponentsClientHttpRequestFactory httpRequestFactory = new
	  HttpComponentsClientHttpRequestFactory();
	  httpRequestFactory.setConnectTimeout(connectionTimeout);
	  httpRequestFactory.setReadTimeout(readTimeout);
	  
	  return new RestTemplate(httpRequestFactory); 
	  
	  }
	 

}

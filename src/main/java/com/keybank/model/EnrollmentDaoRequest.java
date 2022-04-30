/** @Copyright 2022 Keybank pvt ltd. All rights are reserved, you should not
 *  disclose the information outside otherwise terms and conditions will apply
 */
package com.keybank.model;

import lombok.Data;

/**
 * @author sreenu,10-Mar-2022
 * Description :
 */
@Data
public class EnrollmentDaoRequest {
	
	private String clientId;
	private String mobileNum;
	private String paymentDate;
	private String billDate;
	private String providerType;
	private String customerName;
	private float amount;
	private String cardNum;
	private String cvvNum;
	private String nameOncard;
	private String expDate;

}

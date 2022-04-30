/** @Copyright 2022 Keybank pvt ltd. All rights are reserved, you should not
 *  disclose the information outside otherwise terms and conditions will apply
 */
package com.keybank.model;

import lombok.Data;

/**
 * @author sreenu,11-Mar-2022
 * Description :
 */

@Data
public class O2ServiceRequest {
	
	private String clienId;
	private String msgTS;
	private String corrlationId;
	
	private String mobileNum;
	private String paymentDate;
	private String billDate;
	private float amount;
	private String customerName;
	

}

/** @Copyright 2022 Keybank pvt ltd. All rights are reserved, you should not
 *  disclose the information outside otherwise terms and conditions will apply
 */
package com.keybank.model;

import lombok.Data;

/**
 * @author sreenu,03-Apr-2022
 * Description :
 */
@Data
public class Accountdetails {
	
	private String cardNumber;
	private String cvv;
	private String nameOnCard;
	private String expDate;

}

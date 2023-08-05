package com.baemin.service;

import java.io.IOException;

public interface PaymentService {

	String getToken() throws IOException;
	
	int paymentInfo(String imp_uid, String access_token) throws IOException;
	
	public void payMentCancle(String access_token, String imp_uid, int amount, String reason) throws IOException;
	
}

package com.baemin.service;

import java.util.List;

public interface MailService {

	void sendUsernames(String email, List<String> usernames);

	void sendAuthNum(String email, String authNum);

	
}

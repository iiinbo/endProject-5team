package com.baemin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class) // 시큐리티로 인해 최초접속 시 로그인 검증기능을 해제함.
public class BaeminVerMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaeminVerMysqlApplication.class, args);
	}

}

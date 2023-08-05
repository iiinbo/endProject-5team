/*package com.baemin.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.baemin.login.LoginDetailService;
import com.baemin.login.LoginFail;
import com.baemin.login.LoginService;
import com.baemin.login.LoginSuccess;
import com.baemin.login.OAuthUserInfo;
import com.baemin.login.OauthUserService;*/


/*@EnableWebSecurity
@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter {*/
	/*
	 * @Autowired private LoginFail loginFail;
	 * 
	 * @Autowired private LoginSuccess loginSuccess;
	 * 
	 * @Autowired private LoginDetailService loginDetailService;
	 * 
	 * @Autowired private OauthUserService oauthUserService;
	 * 
	 * 
	 * @Bean public BCryptPasswordEncoder encodePwd() { return new
	 * BCryptPasswordEncoder(); } // 지피티로 추가한코드 private final OAuthUserInfo
	 * oauthUserInfo; // 지피티로 추가한코드 public SecurityConfig(OAuthUserInfo
	 * oauthUserInfo) { this.oauthUserInfo = oauthUserInfo; }
	 * 
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * //OAuthUserInfo oauthUserInfo = oauthUserInfo(); // OAuthUserInfo 빈의 메소드를
	 * 호출하여 인스턴스 생성 http.csrf().disable();
	 * 
	 * http.authorizeRequests() .antMatchers("/admin/**").hasRole("ADMIN")
	 * .antMatchers("/user/**").hasAnyRole("ADMIN, USER") .anyRequest().permitAll()
	 * .and() .formLogin() .loginPage("/") // 인증 필요한 페이지 접근시 이동페이지
	 * .loginProcessingUrl("/login") .successHandler(loginSuccess)
	 * .failureHandler(loginFail) .and() .logout() .logoutSuccessUrl("/myPage")
	 * .and() .rememberMe() .key("rememberKey")
	 * .rememberMeCookieName("rememberMeCookieName")
	 * .rememberMeParameter("remember-me") .tokenValiditySeconds(60 * 60 * 24 * 7)
	 * .userDetailsService(loginDetailService) .and() .oauth2Login() .loginPage("/")
	 * .successHandler(loginSuccess) .userInfoEndpoint()
	 * .userService(oauthUserService)
	 * 
	 * ;
	 * 
	 * }
	 * 
	 * private OAuthUserInfo oauthUserInfo() { // TODO Auto-generated method stub
	 * return null; }
	 */
//}

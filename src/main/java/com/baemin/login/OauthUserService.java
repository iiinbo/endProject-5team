package com.baemin.login;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.baemin.dao.UserDAO;
import com.baemin.dto.Join;
import com.baemin.dto.User;

@Service
public class OauthUserService extends DefaultOAuth2UserService {

	@Autowired
	private SqlSession sql;
	
	@Autowired
	private UserDAO userDAO;
	
	//@Autowired
	//private OAuthUserInfo oauthUserInfo;
	
	/*
	 * @Override public OAuth2User loadUser(OAuth2UserRequest userRequest) throws
	 * OAuth2AuthenticationException {
	 * 
	 * OAuth2User oauth2user = super.loadUser(userRequest);
	 * 
	 * String provider = userRequest.getClientRegistration().getRegistrationId();
	 * 
	 * String username = oauthUserInfo.getUsername(provider, oauth2user);
	 * 
	 * User user = sql.selectOne("user.login", username);
	 * 
	 * if(user == null) { // 첫 로그인시 유저정보 생성 Join join =
	 * oauthUserInfo.createUser(provider, username, oauth2user); userDAO.join(join);
	 * user = sql.selectOne("user.login", username); }
	 * 
	 * LoginService loginService = new LoginService(); loginService.setUser(user);
	 * loginService.setProvider(provider);
	 * 
	 * return loginService; }
	 */

	
	
}

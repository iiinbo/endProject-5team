/*
 * package com.baemin.login;
 * 
 * import java.util.Map; import java.util.UUID;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Lazy; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.security.oauth2.core.user.OAuth2User; import
 * org.springframework.stereotype.Service;
 * 
 * import com.baemin.dto.Join; import com.baemin.dto.User;
 * 
 * @Service
 * 
 * @Lazy public class OAuthUserInfo { private final BCryptPasswordEncoder
 * encodePwd; // 생성자 주입을 위해 필드 추가
 * 
 * //@Autowired 기존코드 //private BCryptPasswordEncoder encodePwd;
 * 
 * @Autowired public OAuthUserInfo(BCryptPasswordEncoder encodePwd) {
 * this.encodePwd = encodePwd; }
 * 
 * public Join createUser(String provider, String username, OAuth2User
 * oauth2user) { UUID uid = UUID.randomUUID();
 * 
 * String password = encodePwd.encode(uid.toString()); String email = ""; String
 * phone = ""; switch (provider) {
 * 
 * case "google": email = oauth2user.getAttribute("email"); phone =
 * oauth2user.getAttribute("phone") == null ? "" :
 * oauth2user.getAttribute("phone"); break;
 * 
 * case "naver": Map<String, Object> response =
 * oauth2user.getAttribute("response"); email = (String) response.get("email");
 * phone = (String) response.get("mobile") == null ? "" : (String)
 * response.get("mobile"); break;
 * 
 * case "kakao": Map<String, Object> kakaoAccount =
 * oauth2user.getAttribute("kakao_account"); email = (String)
 * kakaoAccount.get("email"); phone = oauth2user.getAttribute("phone") == null ?
 * "" : oauth2user.getAttribute("phone"); break; }
 * 
 * 
 * phone = phone.replace("-", "");
 * 
 * Join join = new Join(); join.setUsername(username);
 * join.setPassword(password); join.setEmail(email); join.setNickname(username);
 * join.setPhone(phone);
 * 
 * return join; }
 * 
 * 
 * 
 * public String getUsername(String provider,OAuth2User oauth2user) { String id
 * = ""; switch (provider) {
 * 
 * case "google": id = oauth2user.getAttribute("sub"); break;
 * 
 * case "naver": Map<String, Object> response =
 * oauth2user.getAttribute("response"); id = (String) response.get("id"); break;
 * 
 * case "kakao": id = oauth2user.getAttribute("id").toString(); break;
 * 
 * } return provider + "_" + id;
 * 
 * }
 * 
 * }
 */

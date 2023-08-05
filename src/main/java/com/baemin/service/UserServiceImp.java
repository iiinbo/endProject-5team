package com.baemin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baemin.dao.AdminDAO;
import com.baemin.dao.UserDAO;
import com.baemin.dto.Join;
import com.baemin.dto.Point;
import com.baemin.dto.Review;
import com.baemin.login.LoginService;
import com.baemin.util.UserInfoSessionUpdate;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public void join(Join join) {
		userDAO.join(join);
	}
	

	@Override
	public int overlapCheck(String value, String valueType) {
		return userDAO.overlapCheck(value, valueType);
	}
	
	
	@Override
	public List<Point> myPoint(long id) {
		return userDAO.myPoint(id);
	}

	@Transactional
	@Override
	public ResponseEntity<Map<String, Object>> pointRegist(String giftCardNum, LoginService user) {
		long userId = user.getUser().getId();
		
		Map<String, Object> giftCard = userDAO.getGiftCart(giftCardNum, userId);
		
		if(giftCard != null) {
			String info = giftCard.get("info").toString();
			int point = Integer.parseInt(giftCard.get("point").toString());
			int id = Integer.parseInt(giftCard.get("userId").toString());
			
			if(id == 0) { // 등록한 적 없을 때
				userDAO.pointRegist(userId, info, giftCardNum, point);
				UserInfoSessionUpdate.sessionUpdate(point+"", "point", user);
				return new ResponseEntity<Map<String,Object>>(giftCard, HttpStatus.OK);
				
			} else { // 이미 등록한 카드
				Map<String, Object> errorMsg = new HashMap<>();
				errorMsg.put("errorMsg", "이미 등록한 번호입니다");
				
				return new ResponseEntity<Map<String,Object>>(errorMsg, HttpStatus.BAD_REQUEST);
			}
		}
		Map<String, Object> errorMsg = new HashMap<>();
		errorMsg.put("errorMsg", "잘못된 번호입니다");
		
		return new ResponseEntity<Map<String,Object>>(errorMsg, HttpStatus.BAD_REQUEST);
	}


	@Override
	public List<Review> myReviewList(long id) {
		return userDAO.myReviewList(id);
	}


	@Override
	public void deleteReview(long id, String orderNum) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", id);
		map.put("orderNum", orderNum);
		userDAO.deleteReview(map);
	}


	@Override
	public void modifyInfo(String username, String valueType, String value) {
		Map<String, Object> map = new HashMap<>();
		map.put("username", username);
		map.put("valueType", valueType);
		map.put("value", value);
		userDAO.modifyInfo(map);
	}


	@Override
	public List<String> findId(String email) {
		return userDAO.findId(email);
	}


	@Override
	public boolean emailCheck(String username, String email) {
		Map<String, Object> map = new HashMap<>();
		map.put("username", username);
		map.put("email", email);
		String result = userDAO.emailCheck(map);
		if("1".equals(result)) {
			return true;
		}
		return false;
	}


	@Override
	public boolean phoneCheck(String username, String phone) {
		Map<String, Object> map = new HashMap<>();
		map.put("username", username);
		map.put("phone", phone);
		String result = userDAO.phoneCheck(map);
		if("1".equals(result)) {
			return true;
		}
		return false;
	}

	
	
	
}

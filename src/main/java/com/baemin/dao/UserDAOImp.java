package com.baemin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baemin.dto.Join;
import com.baemin.dto.Point;
import com.baemin.dto.Review;
import com.baemin.dto.User;

@Repository
public class UserDAOImp implements UserDAO {

	@Autowired
	private SqlSession sql;
	
	@Override
	public void join(Join join) {
		sql.insert("user.join" , join);
	}
	
	@Override
	public int overlapCheck(String value, String valueType) {
		Map<String, String> map = new HashMap<>();
		map.put("value", value);
		map.put("valueType", valueType);
		
		return sql.selectOne("user.overlapCheck" ,map);
	}
	
	@Override
	public List<Point> myPoint(long id) {
		return sql.selectList("user.myPoint", id);
	}

	@Override
	public Map<String, Object> getGiftCart(String giftCardNum, long userId) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("giftCardNum", giftCardNum);
		return sql.selectOne("user.getGiftCart", map);
	}
	
	@Override
	public void pointRegist(long userId, String info, String giftCardNum, int point) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("info", info);
		map.put("giftCardNum", giftCardNum);
		map.put("point", point);
		sql.insert("user.pointRegist", map);
	}
	

	@Override
	public List<Review> myReviewList(long id) {
		return sql.selectList("user.myReviewList", id);
	}

	@Override
	public void deleteReview(Map<String, Object> map) {
		sql.delete("user.deleteReview", map);
		
	}

	@Override
	public void modifyInfo(Map<String, Object> map) {
		sql.update("user.modifyInfo", map);
	}

	@Override
	public List<String> findId(String email) {
		return sql.selectList("user.findId", email);
	}

	@Override
	public String emailCheck(Map<String, Object> map) {
		return sql.selectOne("user.emailCheck", map);
	}

	@Override
	public String phoneCheck(Map<String, Object> map) {
		return sql.selectOne("user.phoneCheck", map);
	}


	

}

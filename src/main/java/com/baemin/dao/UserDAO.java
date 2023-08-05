package com.baemin.dao;

import java.util.List;
import java.util.Map;

import com.baemin.dto.Join;
import com.baemin.dto.Point;
import com.baemin.dto.Review;
import com.baemin.dto.User;

public interface UserDAO {
	void join(Join join);
	
	int overlapCheck(String value, String valueType);

	List<Point> myPoint(long id);

	Map<String, Object> getGiftCart(String giftCardNum, long userId);

	void pointRegist(long userId, String info, String giftCardNum, int point);

	List<Review> myReviewList(long id);

	void deleteReview(Map<String, Object> map);

	void modifyInfo(Map<String, Object> map);

	List<String> findId(String email);

	String emailCheck(Map<String, Object> map);

	String phoneCheck(Map<String, Object> map);


}

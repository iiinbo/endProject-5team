package com.baemin.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.baemin.dto.CartList;
import com.baemin.dto.OrderInfo;
import com.baemin.dto.OrderList;
import com.baemin.login.LoginService;
import com.baemin.util.Page;

public interface OrderService {

	//	장바구니에 담긴 금액과 db의 금액이 같은지 확인
	long orderPriceCheck(CartList cartList);
	
	public String order(CartList cart, OrderInfo info, LoginService user);
	
	// 주문목록
	List<OrderList> orderList(long userId, Page p);
	
	// 주문목록 상세보기
	OrderList orderListDetail(String orderNum);
}

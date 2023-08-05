package com.baemin.dao;

import java.util.List;
import java.util.Map;

import com.baemin.dto.Cart;
import com.baemin.dto.OrderDetail;
import com.baemin.dto.OrderInfo;
import com.baemin.dto.OrderList;

public interface OrderDAO {

	// 메뉴 총합가격 계산시 배달팁 가져오기
	int getDeleveryTip(long storeId);
	
	//	메뉴 총합가격 계산시 음식가격
	List<Integer> foodPriceList(List<Cart> cartList);
	
	//	메뉴 총합가격 계산시 음식 추가 옵션가격
	List<Integer> optionPriceList(List<Cart> cart);
	
	// 주문 정보 입력
	void order(OrderInfo info);
	
	// 주문 상세정보 입력
	void orderDetail(OrderDetail[] detail, long userId);

	List<OrderList> orderList(Map<String, Object> map);
	
	// 주문목록 상세보기 페이지
	OrderList orderListDetail(String orderNum);
}


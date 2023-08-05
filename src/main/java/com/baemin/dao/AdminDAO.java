package com.baemin.dao;

import java.util.List;
import java.util.Map;

import com.baemin.dto.Food;
import com.baemin.dto.OrderCancle;
import com.baemin.dto.OrderList;
import com.baemin.dto.Sales;
import com.baemin.dto.SalesDetail;
import com.baemin.dto.Store;

public interface AdminDAO {
	
	int pointUpdate(long userId, String info, int point);

	List<Store> myStore(long userId);

	void storeInfoUpdate(Store store);
	
	long addMenu(Food food);

	void addMenuOption(List<Map<String, Object>> optionList);

	void updateMenu(Map<String, Object> map);

	void deleteMenuOption(long foodId);

	void deleteMenu(long storeId, long[] deleteNumber);

	void bossComment(long storeId, String orderNum, String bossComment);

	List<Long> getMyStoreId(long userId);

	List<OrderList> order(Map<String, Object> map);

	void orderAccept(String orderNum, int time, long userId);

	void orderCancle(OrderCancle orderCancle);

	void orderComplete(Map<String, Object> map);

	List<Sales> sales(Map<String, Object> map);

	List<SalesDetail> salesDetail(long storeId, String date);

	



	



}

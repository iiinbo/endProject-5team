package com.baemin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.baemin.dto.OrderList;
import com.baemin.dto.Sales;
import com.google.gson.Gson;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

//@SpringBootTest
public class GsonTest {

	@Autowired
	private SqlSession sql;
	
	
	@Test
	public void cal() {
		Calendar cal = Calendar.getInstance();
		
		cal.set(2022, 1, 29);
		
		
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		
		System.out.println(fm.format(cal.getTime()));
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
//	@Test
	public void gson() {
		String s = "{\"foodId\":5,\"foodName\":\"불고기피자\",\"foodPrice\":5000,\"amount\":1,\"totalPrice\":5500,\"optionName\":[\"치즈추가\"],\"optionPrice\":[500],\"optionId\":[6]}/,{\"foodId\":5,\"foodName\":\"불고기피자\",\"foodPrice\":5000,\"amount\":1,\"totalPrice\":5000}";
		
		
		System.out.println(s);
		
		String[] arr=  s.split("/");
		
		for(int i=0;i<arr.length;i++) {
			System.out.println(arr[i]);
		}
		
		
		
		Gson gson = new Gson();
		
		gson.fromJson(arr[1], OrderList.class);
	}
	
	
	

	
	
//	@Test
	public void sales() {
//		String term = "week";
//		String term = "month";
		String term = "year";
		
		List<Sales> cal = getCal(term);
		
		List<Sales> list = getSales(term, cal); 
		
		
		
		int count = 0;
		for(int i=0;i<cal.size();i++) {
			if(list.size() == count) {
				break;
			}
			
			String calDate = cal.get(i).getOrderDate();
			String salesDate = list.get(count).getOrderDate();
			
			if(calDate.equals(salesDate)) {
				cal.set(i, list.get(count));
				count++;
			}
			
			System.out.println(cal.get(i));
		}
		
		
	}
	
	
	public List<Sales> getSales(String term, List<Sales> cal) {
		if(term.equals("week")) {
			Map<String, Object> map = new HashMap<>();			
			
			map.put("storeId", 1);
			map.put("startDate", cal.get(0).getOrderDate());
			map.put("endDate", cal.get(cal.size()-1).getOrderDate());
			map.put("dateFormat", "%Y-%m-%d");
			
			
			List<Sales> list = sql.selectList("admin.test", map);
			return list;
		}
		
		
		if(term.equals("month")) {
			Map<String, Object> map = new HashMap<>();			
			
			map.put("storeId", 1);
			map.put("startDate", cal.get(0).getOrderDate());
			map.put("endDate", cal.get(cal.size()-1).getOrderDate());
			map.put("dateFormat", "%Y-%m-%d");
			
			List<Sales> list = sql.selectList("admin.test", map);
			
			return list;
		}
		
		
		
		if(term.equals("year")) {
			Map<String, Object> map = new HashMap<>();			
			
			map.put("storeId", 1);
			map.put("startDate", cal.get(0).getOrderDate());
			map.put("endDate", cal.get(cal.size()-1).getOrderDate());
			map.put("dateFormat", "%Y-%m-01");
			
			
			List<Sales> list = sql.selectList("admin.test", map);
			
			return list;
		}
		
		
		
		
		return null;
	}
	
	
	
	
	public List<Sales> getCal(String term) {
		
		if(term.equals("week")) {
			Calendar cal = Calendar.getInstance();
			
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
			
			Calendar date = Calendar.getInstance();
			
			// 이번주 월요일로 세팅
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			int firstDay = cal.get(Calendar.DATE);
			
			int lastDay = 7;
			
			List<Sales> dateArr = new ArrayList<>(lastDay + 1);
			for(int i=0;i<lastDay;i++) {
				date.set(year, month, firstDay+i);
				dateArr.add(new Sales(fm.format(date.getTime())));
			}
			
			return dateArr;
		}
		
		
		if(term.equals("month")) {
			
			Calendar cal = Calendar.getInstance();
			
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
			
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			int firstDay = 1;
			
			int lastDay = cal.getActualMaximum(Calendar.DATE);
			
			List<Sales> dateArr = new ArrayList<>(lastDay+1);
			
			for(int i=0;i<lastDay;i++) {
				cal.set(year, month, firstDay + i);
				dateArr.add(new Sales(fm.format(cal.getTime())));
			}
			
			return dateArr;
		}
			
			
		if(term.equals("year")) {
			Calendar cal = Calendar.getInstance();
			
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-01");
			
			int year = cal.get(Calendar.YEAR);
			int month = 0;
			int firstDay = 1;
			
			int lastDay = 12;
			
			List<Sales> dateArr = new ArrayList<>(lastDay + 1);
			for(int i=0;i<lastDay;i++) {
				cal.set(year, month+i, firstDay);
				dateArr.add(new Sales(fm.format(cal.getTime())));
			}
			
			return dateArr;
		}
		
		
		
		
		return null;
	}
	
	
	
	
	
	
	
//	@Test
	public void 월매출() {
		Calendar cal = Calendar.getInstance();
		
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int firstDay = 1;
		
		int lastDay = cal.getActualMaximum(Calendar.DATE);
		
		List<Sales> dateArr = new ArrayList<>(lastDay+1);

		for(int i=0;i<lastDay;i++) {
			cal.set(year, month, firstDay + i);
			dateArr.add(new Sales(fm.format(cal.getTime())));
		}
		
		
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("storeId", 1);
		map.put("startDate", dateArr.get(0).getOrderDate());
		map.put("endDate", dateArr.get(dateArr.size()-1).getOrderDate());
		map.put("dateFormat", "%Y-%m-%d");
		
		
		System.out.println(map);
		
		List<Sales> list = sql.selectList("admin.test", map);
		
		
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
		
		System.out.println();
		
		
		
		int count = 0;
		for(int i=0;i<dateArr.size();i++) {
			if(list.size() == count) {
				break;
			}
			
			String calDate = dateArr.get(i).getOrderDate();
			String salesDate = list.get(count).getOrderDate();
			
			if(calDate.equals(salesDate)) {
				dateArr.set(i, list.get(count));
				count++;
			}
		}
		
		
		dateArr.set(dateArr.size()-1, list.get(list.size()-1));
		for(int i=0;i<dateArr.size();i++) {
			System.out.println(dateArr.get(i));
		}
		
		
		
		
	}
	
	
	public void 주() {
		Calendar cal = Calendar.getInstance();
		
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar date = Calendar.getInstance();
		
		// 이번주 월요일로 세팅
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int firstDay = cal.get(Calendar.DATE);
		
		List<Sales> dateArr = new ArrayList<>(7);
		for(int i=0;i<7;i++) {
			date.set(year, month, firstDay+i);
			dateArr.add(new Sales(fm.format(date.getTime())));
		}
		
		List<Sales> list = sql.selectList("admin.test");
		
		
		int count = 0;
		for(int i=0;i<dateArr.size();i++) {
			if(list.size() == count) {
				break;
			}
			
			String calDate = dateArr.get(i).getOrderDate();
			String salesDate = list.get(count).getOrderDate();
			
			if(calDate.equals(salesDate)) {
				dateArr.set(i, list.get(count));
				count++;
			}
			
			System.out.println(dateArr.get(i));
		}
	}
	
	
	
	
}

package com.baemin.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baemin.dto.Sales;

public class SalesCalendar {
	
	public List<Sales> getCal(String term, String date) {
		
		
		if(date != null) {
			Calendar cal = Calendar.getInstance();
						
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
			
			String[] ymd = date.split("-");

			int year = Integer.parseInt(ymd[0]);
			int month = Integer.parseInt(ymd[1]) -1;
			int day = Integer.parseInt(ymd[2]);
			
			cal.set(year, month, day);
						
			int firstDay = 1;
						
			int lastDay = cal.getActualMaximum(Calendar.DATE);
			
			List<Sales> dateArr = new ArrayList<>(lastDay + 1);
			for(int i=0;i<lastDay;i++) {
				cal.set(year, month, firstDay+i);
				dateArr.add(new Sales(fm.format(cal.getTime())));
			}
			
			return dateArr;
		}
		
		
		
		if(term.equals("week")) {
			Calendar cal = Calendar.getInstance();
			
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
			
			Calendar d = Calendar.getInstance();
			
			// 이번주 월요일로 세팅
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			int firstDay = cal.get(Calendar.DATE);
			
			int lastDay = 7;
			
			List<Sales> dateArr = new ArrayList<>(lastDay + 1);
			for(int i=0;i<lastDay;i++) {
				d.set(year, month, firstDay+i);
				dateArr.add(new Sales(fm.format(d.getTime())));
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
}

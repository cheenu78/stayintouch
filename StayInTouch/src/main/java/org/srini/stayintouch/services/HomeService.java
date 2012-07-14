package org.srini.stayintouch.services;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("homeService")
@Transactional
public class HomeService {

	private static final Logger logger = LoggerFactory.getLogger(HomeService.class);
	
	public List<Integer> getYears(){
		logger.info("executing get years of home service");
		ArrayList<Integer> list = new ArrayList<Integer>();
		Calendar calendar = GregorianCalendar.getInstance();
		int year = calendar.get(GregorianCalendar.YEAR);
		int startYear = year - 100;
		
		for(int i = year; i >= startYear; i--){
			Integer integer = Integer.valueOf(i);
			list.add(integer);
		}
		return list;
	}
	
	public Map<Integer, String> getMonths(){
		logger.info("executing get months of home service");
		Map<Integer, String> map = new TreeMap<Integer, String>();
		
		DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
        String[] months = dateFormatSymbols.getMonths();
         
        for (int i = 0; i < months.length - 1; i++) 
        {
            String month = months[i];
            map.put((i+1), month);
        }
		
		return map;
	}
	
	public List<Integer> getDays(int year, int month){
		logger.info("executing get years of home service");
		ArrayList<Integer> list = new ArrayList<Integer>();
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(year, month, 1);
		
		int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		for(int i = 1; i <= maxDays; i++){
			list.add(i);
		}
		return list;
	}
}

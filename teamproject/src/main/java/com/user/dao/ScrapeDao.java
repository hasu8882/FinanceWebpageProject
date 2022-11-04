package com.user.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.user.dto.DailyAsset;
import com.user.dto.ETFVo;
import com.user.dto.StockVo;
import com.user.util.ScrapManager;

public class ScrapeDao {
	
	//Single tone
	// 1. 생성자 private : 객체를 외부에서 만들지 못하도록
	// 2. 객체 생성 private static : 자신이 객체를 생성	
	// 3. 객체 제공 기능 getInstance() : 자신의 객체(단지 1개)를 사용할 수 있도록 제공
	
	private ScrapeDao(){
		
	}
	private static ScrapeDao instance= new ScrapeDao();
	
	public static ScrapeDao getInstance() {
		return instance;
	}
	
	//get biz. day
	public String getBizDay(String date) {
		String bizDay = date;
		if (date == null) {
			Document doc = ScrapManager.getNaverFinance();
			Elements elem = doc.select("#time");
			bizDay = elem.text().substring(0,10).replace(".","-");
		} 
		
		return bizDay;
	}
	// get stock list
	public List<Elements> getFinanceIndex() {
		
		List<Elements> elemList = new ArrayList<Elements>();
		Document doc = ScrapManager.getNaverFinance();
		Elements elem1 = doc.select(".section_strategy > ul li span a");
		Elements elem2 = doc.select(".section1 > div:nth-child(1) > table:nth-child(2) > tbody:nth-child(4) > tr:nth-child(1)");
		Elements elem3 = doc.select(".section1 > div:nth-child(2) > table:nth-child(2) > tbody:nth-child(4) > tr");
		Elements elem4 = doc.select(".section_stock > div > div.heading_area");
		Elements elem5 = doc.select("div.aside_area:nth-child(1) > table:nth-child(2) > tbody:nth-child(4) > tr");
		
		elemList.add(elem1);//finance news
		elemList.add(elem2);//exchange rate, KRW/USD
		elemList.add(elem3);//exchange rate, international
		elemList.add(elem4);//korea index
		elemList.add(elem5);//foreign index
		
		return elemList;
	}
	
	public List<StockVo> getStockList(int mktID, String day){
		//행기준으로 분리
		List<StockVo> stockList = new ArrayList<StockVo>();
		Connection.Response res = ScrapManager.scrapeKRXstock(mktID, day);
		String[] dataset = res.body().split("\n");
		//','기준으로 분리
//		System.out.println("data: "+ dataset[1]);
		for (String str:dataset) {
			StockVo sVo= new StockVo();
			String[] data = str.split(",");	
			if (data[0].equals("종목코드")) {
				continue;
			}
			sVo.setLast_update(Date.valueOf(day));
			sVo.setCode(data[0].substring(1,data[0].length()-1));
			sVo.setName(data[1].substring(1,data[1].length()-1));
			sVo.setPrice(Integer.parseInt(data[2].substring(1,data[2].length()-1)));
			sVo.setLast_update(Date.valueOf(day));
			if (mktID == 0) {
				sVo.setMaketType("KOSPI");
			} else {
				sVo.setMaketType("KOSDAQ");
			}
			
			sVo.setRange_day(Integer.parseInt(data[3].substring(1,data[3].length()-1)));
			sVo.setChange_day(Float.parseFloat(data[4].substring(1,data[4].length()-1)));
			if (data[5].equals("")) {
				sVo.setEPS(0);
			} else {
				sVo.setEPS(Integer.parseInt(data[5].substring(1,data[5].length()-1)));
			}
			if (data[6].equals("")) {
				sVo.setPER(0);
			} else {
				sVo.setPER(Float.parseFloat(data[6].substring(1,data[6].length()-1)));
			}
			if (data[7].equals("")) {
				sVo.setFwdEPS(0);
			} else {
				sVo.setFwdEPS(Integer.parseInt(data[7].substring(1,data[7].length()-1)));
			}				
			if (data[8].equals("")) {
				sVo.setFwdPER(0);
			} else {
				sVo.setFwdPER(Float.parseFloat(data[8].substring(1,data[8].length()-1)));
			}
			if (data[9].equals("")) {
				sVo.setBPS(0);
			} else {
				sVo.setBPS(Integer.parseInt(data[9].substring(1,data[9].length()-1)));
			}
			if (data[10].equals("")) {
				sVo.setPBR(0);
			} else {
				sVo.setPBR(Float.parseFloat(data[10].substring(1,data[10].length()-1)));
			}
			if (data[11].equals("")) {
				sVo.setDividend(0);
			} else {
				sVo.setDividend(Integer.parseInt(data[11].substring(1,data[11].length()-1)));
			}
			if (data[12].equals("")) {
				sVo.setDividend_yield(0);
			} else {
				sVo.setDividend_yield(Float.parseFloat(data[12].substring(1,data[12].length()-1)));
			}
			
			stockList.add(sVo);
		}		
		
  
	return stockList;
}
	
	public List<ETFVo> getETFList(String bizDay) {
		
		List<ETFVo> etfList = new ArrayList<ETFVo>();
		
		Connection.Response res = ScrapManager.scrapeKRXetf("2022-10-07");
		String[] dataset = res.body().split("\n");
//		System.out.println("ETF: " +dataset[0]);
//		System.out.println("1st firm: " +dataset[1]);
		//','기준으로 분리
//		System.out.println("data: "+ dataset[1]);
		for (String str:dataset) {
			ETFVo eVo= new ETFVo();
			String[] fields = str.split(",");
			if (fields[0].equals("종목코드")) {
				eVo.setFieldNames(fields);
				eVo.setCode("000000");
				etfList.add(eVo);
				continue;
			}
			// "," 제거
			//" "로 분리
			
			String[] data = StringUtils.substringsBetween(str, "\"", "\"");
//			for (String s:data) {
//				System.out.print(s+"\t");	
//			}
//			System.out.println("");
			eVo.setLast_update(Date.valueOf(bizDay));
			eVo.setCode(data[0]);
//			System.out.println("code: " + eVo.getCode());
			eVo.setName(data[1]);
			eVo.setPrice(Integer.parseInt(data[2]));
			eVo.setLast_update(Date.valueOf(bizDay));			
			eVo.setRange_day(Integer.parseInt(data[3]));
			eVo.setChange_day(Float.parseFloat(data[4].substring(1,data[4].length()-1)));
			if (data[5].equals("")) {
				eVo.setNAV(0);
			} else {
				eVo.setNAV(Double.parseDouble(data[5]));
			}
			if (data[6].equals("")) {
				eVo.setOpen(0);
			} else {
				eVo.setOpen(Integer.parseInt(data[6]));
			}
			if (data[7].equals("")) {
				eVo.setHigh(0);
			} else {
				eVo.setHigh(Integer.parseInt(data[7]));
			}				
			if (data[8].equals("")) {
				eVo.setLow(0);
			} else {
				eVo.setLow(Integer.parseInt(data[8]));
			}
			if (data[9].equals("")) {
				eVo.setVolume(0);
			} else {
				eVo.setVolume(Integer.parseInt(data[9]));
			}
			if (data[10].equals("")) {
				eVo.setTradeAMT(0);
			} else {
//				System.out.println("거래대금1: "+ data[10]);
//				System.out.println("거래대금2: "+ data[10].substring(1,data[10].length()-1));
//				System.out.println("거래대금3: "+ Long.parseLong(data[10].substring(1,data[10].length()-1)));
				eVo.setTradeAMT(Long.parseLong(data[10]));
			}
			if (data[11].equals("")) {
				eVo.setMarketCap(0);
			} else {
				eVo.setMarketCap(Long.parseLong(data[11]));
			}
			if (data[12].equals("")) {
				eVo.setNetAsset(0);
			} else {
				eVo.setNetAsset(Long.parseLong(data[12]));
			}
			eVo.setIndexName(data[14]);
//			System.out.println("지수명: "+ data[14]);
//			System.out.println("지수: "+ data[15]);		
			eVo.setCloseIndex(Float.parseFloat(data[15]));
			eVo.setRangeIndex(Float.parseFloat(data[16]));
			eVo.setChangeIndex(Float.parseFloat(data[17]));
			
			
			etfList.add(eVo);
		}		
		
		return etfList;
		
	}
	
	public List<DailyAsset> getDailyAsset(Map<String, String[]> map, int maxPageNum){
		
		List<DailyAsset> dailyAssetList = new ArrayList<DailyAsset>();
//		Map<String, String[]> hlist = getETFHoldings("KR7069500007", "2022-10-07");	
//		System.out.println("key set: " + hlist.keySet());
		for (String code:map.keySet()) {			
			for (int i=1;i<=maxPageNum;i++) {				
				Document doc = ScrapManager.connDailyNaverStock(code, i);
				Elements elem1 = doc.select(".type2 > tbody:nth-child(1) > tr");				
				for(Element e1:elem1) {
					//DailyAsset
					if (e1.text().length() > 22) {
						DailyAsset dAsset = new DailyAsset();
						Elements elem2 = e1.select("td > span");
						String[] dArr = elem2.text().split(" ");
						dAsset.setCode(code);
						//insert code name
//						System.out.println("name:" + hlist.get( "105560"));
						dAsset.setName(map.get(code)[0]);						
						Date date = Date.valueOf(dArr[0].replace(".","-"));
						dAsset.setDate(date);
						dAsset.setClose(Integer.parseInt(dArr[1].replace(",","")));
						dAsset.setOpen(Integer.parseInt(dArr[3].replace(",","")));
						dAsset.setHigh(Integer.parseInt(dArr[4].replace(",","")));
						dAsset.setLow(Integer.parseInt(dArr[5].replace(",","")));
						dAsset.setTradeVol(Integer.parseInt(dArr[6].replace(",","")));
						dailyAssetList.add(dAsset);						
					}
					
				}
			}
		}
		return 	dailyAssetList;	
	}
	
	public Map<String, String[]> getETFHoldings(String fullCodeETF, String day){	
	
		Connection.Response res =  ScrapManager.ConnCodeSet(fullCodeETF, day);
		String[] dataset = res.body().split("\n");		
//		System.out.println("data length: "+dataset.length);
		//Map
		Map<String, String[]> map = new HashMap<>();
		for(int i=1;i<dataset.length;i++) {
			String[] dArr =dataset[i].split(","); 
			String key = dArr[0].replace("\"", "");
//			System.out.println("key: "+ key);
			String[] values = ArrayUtils.remove(dArr, 0);
//			System.out.println("value: "+values[0] );
			map.put(key, values);
//			System.out.println("map:  "+ map.get(key)[0]);
		}	
		
		return map;
	}

	public List<DailyAsset> getDailyETF(List<ETFVo> etfList, int maxPageNum){
        List<DailyAsset> dailyETFList = new ArrayList<DailyAsset>();        
//        Map<String, String[]> hlist = getETFHoldings("KR7069500007", "2022-10-07");    
//        System.out.println("key set: " + hlist.keySet());
        for (int j=0;j<etfList.size();j++) {            
            for (int i=1;i<=maxPageNum;i++) {
            	String code = etfList.get(j).getCode();
            	Document doc = ScrapManager.connDailyNaverStock(code, i);
                Elements elem1 = doc.select(".type2 > tbody:nth-child(1) > tr"); 
                for(Element e1:elem1) {
                    //DailyAsset
                    if (e1.text().length() > 22) {
                        DailyAsset dAsset = new DailyAsset();
                        Elements elem2 = e1.select("td > span");
                        String[] dArr = elem2.text().split(" ");
                        dAsset.setCode(code);
                        //insert code name
//                        System.out.println("name:" + hlist.get( "105560"));
                        dAsset.setName(etfList.get(j).getName());                        
                        Date date = Date.valueOf(dArr[0].replace(".","-"));
                        dAsset.setDate(date);
                        dAsset.setClose(Integer.parseInt(dArr[1].replace(",","")));
                        dAsset.setOpen(Integer.parseInt(dArr[3].replace(",","")));
                        dAsset.setHigh(Integer.parseInt(dArr[4].replace(",","")));
                        dAsset.setLow(Integer.parseInt(dArr[5].replace(",","")));
                        dAsset.setTradeVol(Integer.parseInt(dArr[6].replace(",","")));
                        dailyETFList.add(dAsset);                        
                    }
                    
                }
            }
            	                 
                  
            }
        return dailyETFList;
              
    }
	//	public static void main(String[] args) {
//		ScrapeDao scrapeDao = new ScrapeDao();
//				List<Elements> elemList = getFinanceData();
//		Elements elem = elemList.get(0); 
//		for (Element e:elem) {
//			System.out.println("finance news: " + e);
//		}
//		
//		List<StockVo> stockList = getStock(0,"2022-10-07");
//		StockVo stock = stockList.get(10); 
//		System.out.println("Company: "+ stock.getName());
//		scrapeDao.getETFList("2022-10-07");
//		String[] codeSet = {"005930"};
//		List<DailyAsset> dailyList = scrapeDao.getDailyAsset(codeSet, 3);
//		for( int i=0;i<dailyList.size();i++) {
//			System.out.println(dailyList.get(i).toString());
//		}		
		
//		 Map<String, String[]> hlist = getETFHoldings("KR7069500007", "2022-10-07");
//		 System.out.println("Key Set: " + hlist.keySet());
//		 System.out.println("length: " + hlist.size());
//		 System.out.println("value: "+ hlist.get("008560")[2]);
//		
//		String[] codeSet = {"008560", "105560", "093370", "006260", "294870", "001440"};
//		
//		List<DailyAsset> dailyList = scrapeDao.getDailyAsset(codeSet, 3);
//		for( int i=0;i<dailyList.size();i++) {
//			System.out.print(dailyList.get(i).getCode()+"\t");
//			System.out.print(dailyList.get(i).getName()+"\t");
//			System.out.print(dailyList.get(i).getDate()+"\t");
//			System.out.println(dailyList.get(i).getClose());
//		}		
//				
//		
//	}


	
	
	

}

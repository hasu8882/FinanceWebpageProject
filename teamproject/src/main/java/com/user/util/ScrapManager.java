package com.user.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ScrapManager {
	

	//KRX data:KOPSPI, KOSDAQ
	public static Connection.Response scrapeKRXstock(int mktID, String day){
		
		String mkt ="STK";
		String otp_url = "http://data.krx.co.kr/comm/fileDn/GenerateOTP/generate.cmd";
		String down_url = "http://data.krx.co.kr/comm/fileDn/download_csv/download.cmd";	
		Document doc = null;
		Connection.Response res = null;
		if (mktID == 1) {
			mkt="KSQ";
		}		

		try {
			//1. get otp key
			
			Map<String,String> otp_data = new HashMap<>();
			otp_data.put("mktId", mkt);
			otp_data.put("trdDd",day.replace("-",""));
			otp_data.put("name", "fileDown");
			otp_data.put("csvxls_isNo", "false");
			otp_data.put("url", "dbms/MDC/STAT/standard/MDCSTAT03501");
			doc = Jsoup.connect(otp_url)
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:102.0) Gecko/20100101 Firefox/102.0")
					.data(otp_data)
					.post();
			String otp_key = doc.text();
//			System.out.println("opt: "+ doc.text());
			//2. get_kospi
			
			
			Map<String,String> header_dic = new HashMap<>();
			header_dic.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
			header_dic.put("Accept-Encoding", "gzip, deflate");
			header_dic.put("Accept-Language", "en-US,en;q=0.5");			
			header_dic.put("Connection", "gzip");
			header_dic.put("Content-Length", "613");
			header_dic.put("Content-Type", "application/x-www-form-urlencoded");
			header_dic.put("Cookie", "__smVisitorID=s1AIIyV79ae; JSESSIONID=tkIXy847uQGdVtVDGqqTXurp7muslW1519qLQlPZnfwsZ6O6bRMu53EVSt6JKTdB.bWRjX2RvbWFpbi9tZGNvd2FwMS1tZGNhcHAxMQ==");
			header_dic.put("Host", "data.krx.co.kr");
			header_dic.put("Origin", "http://data.krx.co.kr");
			header_dic.put("Referer", "http://data.krx.co.kr/contents/MDC/MDI/mdiLoader/index.cmd?menuId=MDC0201020502");
			header_dic.put("Upgrade-Insecure-Requests", "1");
			header_dic.put("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:105.0) Gecko/20100101 Firefox/105.0");
			
			res = Jsoup.connect(down_url)
					.headers(header_dic)
					.data("code",otp_key)
					.method(Method.POST)
					.ignoreContentType(true)
					.execute()
					.charset("euc-kr");
//			System.out.println("Encoding type:"+ res.charset());
//			System.out.println("data:"+ res.charset("euc-kr"));
//			res.charset("euc-kr");//change euc-kr
//			System.out.println("res data: "+ res.body());
		}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("IO error");
		}
		return res;
	}
	//KRX ETF
	public static Connection.Response scrapeKRXetf(String day){
		
		String mkt ="STK";
		String otp_url = "http://data.krx.co.kr/comm/fileDn/GenerateOTP/generate.cmd";
		String down_url = "http://data.krx.co.kr/comm/fileDn/download_csv/download.cmd";	
		Document doc = null;
		Connection.Response res = null;

		try {
			//1. get otp key
	
			Map<String,String> otp_data = new HashMap<>();
			otp_data.put("locale", "ko_KR");
			otp_data.put("trdDd",day.replace("-",""));
			otp_data.put("name", "fileDown");
			otp_data.put("csvxls_isNo", "false");
			otp_data.put("url", "dbms/MDC/STAT/standard/MDCSTAT04301");
			doc = Jsoup.connect(otp_url)
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:102.0) Gecko/20100101 Firefox/102.0")
					.data(otp_data)
					.post();
			String otp_key = doc.text();
//			System.out.println("opt: "+ doc.text());
			//2. get_kospi
			Map<String,String> header_dic = new HashMap<>();
			header_dic.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
			header_dic.put("Accept-Encoding", "gzip, deflate");
			header_dic.put("Accept-Language", "en-US,en;q=0.5");			
			header_dic.put("Connection", "keep-alive");
			header_dic.put("Content-Length", "281");
			header_dic.put("Content-Type", "application/x-www-form-urlencoded");
			header_dic.put("Host", "data.krx.co.kr");
			header_dic.put("Origin", "http://data.krx.co.kr");
			header_dic.put("Referer", "http://data.krx.co.kr/contents/MDC/MDI/mdiLoader/index.cmd?menuId=MDC0201030101");
			header_dic.put("Upgrade-Insecure-Requests", "1");
			header_dic.put("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:105.0) Gecko/20100101 Firefox/105.0");
			
			res = Jsoup.connect(down_url)
					.headers(header_dic)
					.data("code",otp_key)
					.method(Method.POST)
					.ignoreContentType(true)
					.execute()
					.charset("euc-kr");
//			System.out.println("Encoding type:"+ res.charset());
//			System.out.println("data:"+ res.charset("euc-kr"));
//			res.charset("euc-kr");//change euc-kr
//			System.out.println("res data: "+ res.body());
		}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("IO error");
		}
		return res;
	}
	
	//Naver finance index Scrape
	static public Document getNaverFinance() {
		Document doc = null; 
		String url_naver_finance="https://finance.naver.com";
		try {
			doc = Jsoup.connect(url_naver_finance).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
	
	static public Document connDailyNaverStock(String code, int pageNum) {
		Document doc = null;
        String stock_url="https://finance.naver.com/item/sise_day.naver?code="+code+
                "&page="+ String.valueOf(pageNum);
        try {
            doc = Jsoup.connect(stock_url).get();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return doc;
		
	}
	
	static public Connection.Response ConnCodeSet(String code, String day){
	
		String otp_url = "http://data.krx.co.kr/comm/fileDn/GenerateOTP/generate.cmd";
		String down_url = "http://data.krx.co.kr/comm/fileDn/download_csv/download.cmd";	
		Document doc = null;
		Connection.Response res = null;

		try {
			//1. get otp key
			Map<String,String> otp_data = new HashMap<>();
			otp_data.put("locale","ko_KR");
			otp_data.put("trdDd",day.replace("-",""));
			otp_data.put("isuCd", code);//표준코드로 요청
//			otp_data.put("isuCd2", code);
			otp_data.put("name", "fileDown");
			otp_data.put("csvxls_isNo", "false");
			otp_data.put("url", "dbms/MDC/STAT/standard/MDCSTAT05001");
			doc = Jsoup.connect(otp_url)
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:102.0) Gecko/20100101 Firefox/102.0")
					.data(otp_data)
					.post();
			String otp_key = doc.text();
//			System.out.println("opt: "+ doc.text());
			
			
			//2. get holdings		
			
			Map<String,String> header_dic = new HashMap<>();
			header_dic.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
			header_dic.put("Accept-Encoding", "gzip, deflate");
			header_dic.put("Accept-Language", "ko-KR,ko;q=0.8,en-US;q=0.5,en;q=0.3");			
			header_dic.put("Connection", "keep-alive");
			header_dic.put("Content-Length", "543");
			header_dic.put("Content-Type", "application/x-www-form-urlencoded");
			header_dic.put("Host", "data.krx.co.kr");
			header_dic.put("Origin", "http://data.krx.co.kr");
			header_dic.put("Referer", "http://data.krx.co.kr/contents/MDC/MDI/mdiLoader/index.cmd?menuId=MDC0201");
			header_dic.put("Upgrade-Insecure-Requests", "1");
			header_dic.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:105.0) Gecko/20100101 Firefox/105.0");
			
			res = Jsoup.connect(down_url)
					.headers(header_dic)
					.data("code",otp_key)
					.method(Method.POST)
					.ignoreContentType(true)
					.execute()
					.charset("euc-kr");
		}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("IO error");
		}
		return res;
		
	}
	
	
	
//	public static void main(String[] args) {
//	List<Elements> elemList = getFinanceData();
//	Elements elem = elemList.get(0); 
//	for (Element e:elem) {
//		System.out.println("finance news: " + e);
//	}
	
//	List<StockVo> stockList = getStockList(0,"2022-10-07");
//	StockVo stock = stockList.get(10); 
//	System.out.println("Company: "+ stock.getName());
//		Connection.Response res = ConnCodeSet("KR7069500007","2022-10-07");
//		String[] dataset = res.body().split("\n");
//		System.out.println(": " +res.body());		
//}
//	
}
	
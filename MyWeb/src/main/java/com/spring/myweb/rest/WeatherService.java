package com.spring.myweb.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherService {
	
	private final IWeatherMaooer mapper;
	
	//propertice 에 작성된 값을 일거오는 아노테이션
	@Value("${weather.serviceKey}")
	private String serviceKey;
	@Value("${weather.reqUrl}")
	private String reqUrl;
	
	
	public void getShortTermForecast(String area1 , String area2) {

		LocalDateTime ldt = LocalDateTime.now();
		String baseDate =  DateTimeFormatter.ofPattern("yyyyMMdd").format(ldt);
		log.info("baseDate : {}" , baseDate);
		
		Map<String,String> map =  mapper.getCoord(area1.trim(),area2.trim());
		log.info(" map 결과 : {}",map);

		//RestTemplate 이용해서  api 요청을 해 보자
		
		//요청 헤더 설정(api에서 원하는 헤더 설정이 있다면 사용하세요)
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		
		
		//요청 파라미터 설정.
		MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
		params.add("serviceKey",serviceKey);
		params.add("PageNo","1");
		params.add("numOfRow","200");
		params.add("dataType",baseDate);
		params.add("base_time","0500");   
		params.add("nx",String.valueOf(map.get("NX")));
		params.add("ny",String.valueOf(map.get("NY")));
		
		//REST 방식의 통신을 보내줄 객체 생성
		RestTemplate template = new RestTemplate();
		
		//위에서 세팅한 header정보와parameter 를 하나의 엔터티로 포장
		HttpEntity<Object> requestEntity = new HttpEntity<>(params,headers);
		
		//통신을 보내면서 응답데이터를 바로 리턴.
        //param1: 요청 url
        //param2: 요청 방식(method)
        //param3: 헤더와 요청파라미터 정보 엔티티 객체
        //param4: 응답 데이터를 받을 객체의 타입 (ex: dto, String, map...)
		ResponseEntity<Map> responseEntity = 
				template.exchange(reqUrl,HttpMethod.GET,requestEntity,Map.class);
		log.info("여기!!!!!!?????????????????????");
		//응답데이터에서 필요한 정보를 가져오자.
		Map<String,String > responseDate = (Map<String, String>) responseEntity.getBody();
		log.info("요청에 따른 응답 데이터:{}",responseDate);
		
		//api 에서 제공하는 예제 코드를 그대로 활용한 방식
//		try {
//			
//			StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"); /*URL*/
//	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=AhRLFncHyKyRsP084YgLCYjzsT99yvmiHfoiXWFPaYrZwfvIL%2FvhvG1ZQt%2F2gxE0DZYh1O8MjVm68Mba6wbujg%3D%3D"); /*Service Key*/
//	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
//	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("200", "UTF-8")); /*한 페이지 결과 수*/
//	        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
//	        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8")); /*‘21년 6월 28일 발표*/
//	        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0500", "UTF-8")); /*06시 발표(정시단위) */
//	        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(String.valueOf(map.get("NX")), "UTF-8")); /*예보지점의 X 좌표값*/
//	        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(String.valueOf(map.get("NY")), "UTF-8")); /*예보지점의 Y 좌표값*/
//
//	        log.info("완성된 url : {}",urlBuilder.toString());
//	        URL url = new URL(urlBuilder.toString());
//	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//	        conn.setRequestMethod("GET");
//	        conn.setRequestProperty("Content-Type", "application/json");
//	        System.out.println("Response code: " + conn.getResponseCode());
//	        BufferedReader rd;
//	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//	        } else {
//	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//	        }
//	        StringBuilder sb = new StringBuilder();
//	        String line;
//	        while ((line = rd.readLine()) != null) {
//	            sb.append(line);
//	        }
//	        rd.close();
//	        conn.disconnect();
//	        System.out.println(sb.toString());		
//
//	        //StringBuilder 객체를 String 으로 변환
//	        String jsonString = sb.toString();
//	        
//	        JSONParser parser = new JSONParser();
//	        JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
//			
//	        //"response" 라는 이름의 키에 해당하는 JSON 데이터를 가져옵니다.
//	        JSONObject response = (JSONObject) jsonObject.get("response");
//	        
//	        //response 안에서 body 키에 해당하는 JSON 데이터를 가져옵니다.
//	        JSONObject body = (JSONObject) response.get("body");
//	        
//	        //body안에서 item키에 해당하는 JSON데이터 중 item 키를 가진 JSON 데이터를 가져옵니다.
//	        JSONArray itemArray = (JSONArray) ((JSONObject) body.get("items")).get("item");
//	        
//	        //item내부의 각각의 객체에 대한 반복문을 작성합니다.
//	        for(Object obj : itemArray) {
//	        	
//	        	//형변환 진행
//	        	JSONObject item = (JSONObject) obj;
//	        	//"category" 키에 해당하는 단일 값을 가져옵니다.
//	        	String category = (String) item.get("category");
//	        	//"fcstValue" 키에 해당하는 단일 값을 가져옵니다.
//	        	String fcstValue = (String) item.get("fcstValue");
//	        	
//	        	if(category.equals("TMX") || category.equals("TMN")){
//	        		log.info("category : {} , fcstValue : {}" ,category, fcstValue);
//	        	}
//	        }
//	        
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}

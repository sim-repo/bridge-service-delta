package com.simple.server.http;

import java.nio.charset.Charset;

import org.apache.commons.net.util.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.simple.server.config.AppConfig;
import com.simple.server.config.ContentType;
import com.simple.server.util.ObjectConverter;

public class HttpImpl implements IHttp {
		
		
	@Override
	public void sendHttp(Object msg, String url, ContentType contentType, Boolean useAuth) throws Exception{	
	
		try{														
			prepareAndSend(msg, url , contentType, useAuth);
		}				
		catch(RestClientException ex){
			if(ex.getCause()==null){
				throw new Exception(String.format("HttpImpl(UniMinMsg) url: < %s >, content-type: < %s >, < %s >", 
						url, 
						contentType, 
						ex.getMessage()));
			}else{
				throw new Exception(String.format("HttpImpl(UniMinMsg) url: <%s>, content-type: < %s >, < %s >, < %s >", 
						url, 
						contentType, 
						ex.getMessage(),
						ex.getCause()
						));
			}		
		}		
	}
	
	
	public void prepareAndSend(Object msg, String url, ContentType contentType, Boolean useAuth) throws Exception{
		
		String converted = null;
		String sContentType = null;
		
		if(ContentType.XmlPlainText.equals(contentType)){
			converted = ObjectConverter.objectToXml(msg);
			sContentType = "text/plain;charset=utf-8";								
		}
		else if(ContentType.ApplicationJson.equals(contentType)){
			converted = ObjectConverter.objectToJson(msg);
			sContentType = "application/json;charset=utf-8";								
		}
		else if(ContentType.ApplicationXml.equals(contentType)){
			converted = ObjectConverter.objectToXml(msg);
			sContentType = "application/xml;charset=utf-8";								
		}
		else{
			converted = ObjectConverter.objectToJson(msg);
			sContentType = "text/plain;charset=utf-8";									
		}
		post(converted, sContentType, url, useAuth);	
	}
	
		
	public void post(String body, String url, String contentType, Boolean useAuth) throws Exception{		
		RestTemplate restTemplate = new RestTemplate();
		if(useAuth){			
			HttpEntity<String> entity = null;			
			entity = new HttpEntity<String>("", createHeaders(AppConfig.ACC, AppConfig.PSW));
			restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		}{
			HttpHeaders headers = new HttpHeaders();	
			headers.add("Content-Type",contentType);
			HttpEntity<String> entity = new HttpEntity<String>(body,headers);		
			restTemplate.postForLocation(url, entity);
		}
	}
		
	
	public static HttpHeaders createHeaders(String username, String password) {
		return new HttpHeaders() {
			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
				setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			}
		};
	}
}

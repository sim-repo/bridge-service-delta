package com.simple.server.http;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.simple.server.config.ContentType;
import com.simple.server.domain.contract.AContract;
import com.simple.server.domain.contract.IContract;
import com.simple.server.domain.contract.UniMinMsg;
import com.simple.server.util.ObjectConverter;

public class HttpImpl implements IHttp {
		
	@Override
	public void sendHttp(IContract contract) throws Exception{
		AContract msg = (AContract)contract;
		try{						
			ContentType contentType = msg.getResponseContentType();
			String body = null;
			String sContentType = null;
			
			if(ContentType.XmlPlainText.equals(contentType)){
				body = ObjectConverter.ObjectToXml(msg);
				sContentType = "text/plain;charset=utf-8";								
				send(msg, body, sContentType);
			}
			else if(ContentType.ApplicationJson.equals(contentType)){
				body = ObjectConverter.ObjectToJson(msg);
				sContentType = "application/json;charset=utf-8";								
				send(msg, body, sContentType);
			}
			else if(ContentType.ApplicationXml.equals(contentType)){
				body = ObjectConverter.ObjectToXml(msg);
				sContentType = "application/xml;charset=utf-8";								
				send(msg, body, sContentType);
			}
			else{
				body = ObjectConverter.ObjectToJson(msg);
				sContentType = "text/plain;charset=utf-8";
				send(msg, body, sContentType);							
			}
		}
		catch(RestClientException ex){
			throw new Exception(String.format("HttpResponse: sendJsonTextPlain, url: <%s>. %s", msg.getResponseURI(), ex.getMessage()));
		}
	}
	
	@Override
	public void sendHttp(UniMinMsg msg) throws Exception{	
		try{						
			ContentType contentType = msg.getContentType();
			String body = null;
			String sContentType = null;
			
			if(ContentType.XmlPlainText.equals(contentType)){
				body = ObjectConverter.ObjectToXml(msg);
				sContentType = "text/plain;charset=utf-8";								
				send(msg, body, sContentType);
			}
			else if(ContentType.ApplicationJson.equals(contentType)){
				body = ObjectConverter.ObjectToJson(msg);
				sContentType = "application/json;charset=utf-8";								
				send(msg, body, sContentType);
			}
			else if(ContentType.ApplicationXml.equals(contentType)){
				body = ObjectConverter.ObjectToXml(msg);
				sContentType = "application/xml;charset=utf-8";								
				send(msg, body, sContentType);
			}
			else{
				body = ObjectConverter.ObjectToJson(msg);
				sContentType = "text/plain;charset=utf-8";
				send(msg, body, sContentType);							
			}
		}
		catch(RestClientException ex){
			throw new Exception(String.format("HttpResponse: sendJsonTextPlain, url: <%s>. %s", msg.getUrl(), ex.getMessage()));
		}
	}
	
	
	public void send(IContract contract, String body, String contentType) throws Exception{
		AContract msg = (AContract)contract;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();	
		headers.add("Content-Type",contentType);
		HttpEntity<String> entity = new HttpEntity<String>(body,headers);
		String responseUrl = msg.getResponseURI();
		restTemplate.postForLocation(responseUrl, entity);		
	}
	
	public void send(UniMinMsg uniMinMsg, String body, String contentType) throws Exception{
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();	
		headers.add("Content-Type",contentType);
		HttpEntity<String> entity = new HttpEntity<String>(body,headers);
		String responseUrl = uniMinMsg.getUrl();
		restTemplate.postForLocation(responseUrl, entity);		
	}
}

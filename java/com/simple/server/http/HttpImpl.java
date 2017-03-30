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
		ContentType contentType = msg.getResponseContentType();
		String body = null;
		String sContentType = null;
		
		try{									
						
			if(ContentType.XmlPlainText.equals(contentType)){
				body = ObjectConverter.objectToXml(msg);
				sContentType = "text/plain;charset=utf-8";								
				send(msg, body, sContentType);
			}
			else if(ContentType.ApplicationJson.equals(contentType)){
				body = ObjectConverter.objectToJson(msg);				
				sContentType = "application/json;charset=utf-8";								
				send(msg, body, sContentType);
			}
			else if(ContentType.ApplicationXml.equals(contentType)){
				body = ObjectConverter.objectToXml(msg);
				sContentType = "application/xml;charset=utf-8";								
				send(msg, body, sContentType);
			}
			else{				
				body = ObjectConverter.objectToJson(msg);
				sContentType = "text/plain;charset=utf-8";
				send(msg, body, sContentType);							
			}
			//System.out.println("bridge:::::sendHttp(IContract) "+body);
		}
		catch(RestClientException ex){
			//System.out.println("bridge:::::sendHttp(IContract) "+body);
			if(ex.getCause()==null){
				throw new Exception(String.format("HttpImpl(IContract) url: < %s >, content-type: < %s >, < %s >", 
						msg.getResponseURI(), 
						contentType, 
						ex.getMessage()));
			}else{
				throw new Exception(String.format("HttpImpl(IContract) url: < %s >, content-type: < %s >, < %s >, < %s >", 
						msg.getResponseURI(), 
						contentType, 
						ex.getMessage(),
						ex.getCause()
						));
			}		
		}
	}
	
	@Override
	public void sendHttp(UniMinMsg msg) throws Exception{	
		ContentType contentType = msg.getContentType();
		String body = null;
		String sContentType = null;
		try{									
					
			if(ContentType.XmlPlainText.equals(contentType)){
				body = ObjectConverter.objectToXml(msg);
				sContentType = "text/plain;charset=utf-8";								
				send(msg, body, sContentType);
			}
			else if(ContentType.ApplicationJson.equals(contentType)){
				body = ObjectConverter.objectToJson(msg);
				sContentType = "application/json;charset=utf-8";								
				send(msg, body, sContentType);
			}
			else if(ContentType.ApplicationXml.equals(contentType)){
				body = ObjectConverter.objectToXml(msg);
				sContentType = "application/xml;charset=utf-8";								
				send(msg, body, sContentType);
			}
			else{
				body = ObjectConverter.objectToJson(msg);
				sContentType = "text/plain;charset=utf-8";
				send(msg, body, sContentType);							
			}
			//System.out.println("bridge:::::sendHttp(UniMinMsg) "+body);
		}				
		catch(RestClientException ex){
			//System.out.println("bridge:::::error Http(UniMinMsg) "+body);
			if(ex.getCause()==null){
				throw new Exception(String.format("HttpImpl(UniMinMsg) url: < %s >, content-type: < %s >, < %s >", 
						msg.getUrl(), 
						contentType, 
						ex.getMessage()));
			}else{
				throw new Exception(String.format("HttpImpl(UniMinMsg) url: <%s>, content-type: < %s >, < %s >, < %s >", 
						msg.getUrl(), 
						contentType, 
						ex.getMessage(),
						ex.getCause()
						));
			}		
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

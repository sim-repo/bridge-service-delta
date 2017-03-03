package com.simple.server.response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.simple.server.config.ContentType;
import com.simple.server.domain.contract.AContract;
import com.simple.server.domain.contract.IContract;
import com.simple.server.util.ObjectConverter;

public class HttpResponse implements Response {
		
	
	public void reply(IContract contract) throws Exception{
		AContract msg = (AContract)contract;
		
		ContentType contentType = msg.getResponseContentType();
		
		if(ContentType.JsonPlainText.equals(contentType)){
			sendJsonTextPlain(msg);
		}
		else if(ContentType.XmlPlainText.equals(contentType)){
			sendXmlTextPlain(msg);
		}
		else if(ContentType.ApplicationJson.equals(contentType)){
			sendApplicationJson(msg);
		}
		else if(ContentType.ApplicationXml.equals(contentType)){
			sendApplicationXml(msg);
		}
		else{sendJsonTextPlain(msg);}							
	}
	
	public void sendJsonTextPlain(IContract contract) throws Exception{	
		AContract msg = (AContract)contract;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();		
		String json = ObjectConverter.ObjectToJson(msg);
		headers.add("Content-Type","text/plain;charset=utf-8");
		HttpEntity<String> entity = new HttpEntity<String>(json,headers);
		String responseUrl = msg.getResponseURI();
		restTemplate.postForLocation(responseUrl, entity);
	}	
	
	public void sendXmlTextPlain(IContract contract) throws Exception{		
		AContract msg = (AContract)contract;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();		
		String json = ObjectConverter.ObjectToXml(msg);
		headers.add("Content-Type","text/plain;charset=utf-8");
		HttpEntity<String> entity = new HttpEntity<String>(json,headers);
		String responseUrl = msg.getResponseURI();
		restTemplate.postForLocation(responseUrl, entity);
	}
	
	public void sendApplicationJson(IContract contract) throws Exception{
		AContract msg = (AContract)contract;
		RestTemplate restTemplate = new RestTemplate();
		String json = ObjectConverter.ObjectToJson(msg);
		HttpHeaders headers = new HttpHeaders();				
		headers.add("Content-Type","application/json;charset=utf-8");
		HttpEntity<String> entity = new HttpEntity<String>(json,headers);
		String responseUrl = msg.getResponseURI();
		restTemplate.postForLocation(responseUrl, entity);
	}
	
	public void sendApplicationXml(IContract contract) throws Exception{
		AContract msg = (AContract)contract;
		RestTemplate restTemplate = new RestTemplate();
		String xml = ObjectConverter.ObjectToXml(msg);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type","application/xml;charset=utf-8");		
		HttpEntity<String> entity = new HttpEntity<String>(xml,headers);
		String responseUrl = msg.getResponseURI();
		restTemplate.postForLocation(responseUrl, entity);
	}
	
}

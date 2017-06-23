package com.simple.server.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

public class ObjectConverter {
	private ObjectConverter(){}
		
	public static String objectToJson(Object object){
		StringWriter writer = new StringWriter();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		try {
			mapper.writeValue(writer, object);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return writer.toString();
	}
	
	public static Object jsonToObject(String json, Object object){
		ObjectMapper mapper = new ObjectMapper();
		final ObjectReader reader = mapper.reader();
		try {
			object = reader.forType(object.getClass()).readValue(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return object;
	}
	
	public static <T> List<T> jsonToObjects(String json, Class<T> clazz){
		ObjectMapper mapper = new ObjectMapper().enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		TypeFactory t = TypeFactory.defaultInstance();
				
		//final ObjectReader reader = mapper.reader();
		List<T> res = null;
		try {
			
			res = mapper.readValue(json,t.constructCollectionType(ArrayList.class,clazz));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}
		
	public static String objectToXml(Object object) throws Exception{
		XmlMapper xmlMapper = new XmlMapper();
		xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
		String xml = xmlMapper.writeValueAsString(object);		
		return xml;
	}
	
	
	public static String jsonToXml(String json, Boolean useDeclaration) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = mapper.readValue(json, new TypeReference<Map<String,Object>>(){});
		XmlMapper xmlMapper = new XmlMapper();
		xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, useDeclaration);		
		String xml = xmlMapper.writeValueAsString(map);		
		return xml;
	}
	
	public static String xmlToJson(String xml) throws Exception{
		JSONObject jObject = XML.toJSONObject(xml);
	    ObjectMapper mapper = new ObjectMapper();	   
	    Object json = mapper.readValue(jObject.toString(), Object.class);
	    String res = mapper.writeValueAsString(json);
		return res;
	}
	
	public static String listMapToJson(List<Map<String, Object>> list) throws Exception{       
	    JSONArray json_arr=new JSONArray();
	    for (Map<String, Object> map : list) {
	        JSONObject json_obj=new JSONObject();
	        for (Map.Entry<String, Object> entry : map.entrySet()) {
	            String key = entry.getKey();
	            Object value = entry.getValue();
	            try {
	                json_obj.put(key,value);
	            } catch (JSONException e) {
	                e.printStackTrace();
	            }                           
	        }
	        json_arr.put(json_obj);
	    }
	    return json_arr.toString();
	}
	
	
	public static String listMapToJsonFirstObj(List<Map<String, Object>> list) throws Exception{       	   
		JSONObject json_obj=new JSONObject();
	    for (Map<String, Object> map : list) {
	        
	        for (Map.Entry<String, Object> entry : map.entrySet()) {
	            String key = entry.getKey();
	            Object value = entry.getValue();
	            try {
	                json_obj.put(key,value);
	            } catch (JSONException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }                           
	        }
	        return json_obj.toString();
	    }
	    return null;
	}
	
	public static boolean isValidJSON(final String json) throws IOException {
	    boolean valid = true;
	    ObjectMapper mapper = new ObjectMapper();
	    try{ 
	    	mapper.readTree(json);
	    } catch(JsonProcessingException e){
	        valid = false;
	    }
	    return valid;
	}
	
}

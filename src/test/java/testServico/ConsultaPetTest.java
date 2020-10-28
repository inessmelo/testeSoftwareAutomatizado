package testServico;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Test;

import util.RestUtil;

public class ConsultaPetTest {
	
	//https://petstore.swagger.io/v2/pet
	private String url = "https://petstore.swagger.io/v2";
	private String endpoint = "/pet";
	
	/*@Test
	public void consultaPet() {
		RestUtil.setUrl(url);            //setando a url
		RestUtil.setEndpoint(endpoint);  //setando o endpoint
		
		//montando o POST no JSON via STRING - apenas copiar e colar o BODY do JSON
		String json = "{  \r\n" + 
				"  \"category\": {\r\n" + 
				"    \"id\": 0,\r\n" + 
				"    \"name\": \"string\"\r\n" + 
				"  },\r\n" + 
				"  \"name\": \"doggie\",\r\n" + 
				"  \"photoUrls\": [\r\n" + 
				"    \"string\"\r\n" + 
				"  ],\r\n" + 
				"  \"tags\": [\r\n" + 
				"    {\r\n" + 
				"      \"id\": 0,\r\n" + 
				"      \"name\": \"string\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"status\": \"available\"\r\n" + 
				"}";
		
		RestUtil.post(json);
		
		assertEquals(200, RestUtil.getStatus());
	}*/
	
	@Test
	public void consultaPetMap() {
		RestUtil.setUrl(url);
		RestUtil.setEndpoint(endpoint);
		
		//criando as listas
		LinkedHashMap<String, Object> json = new LinkedHashMap<String, Object>();		
		LinkedHashMap<String, Object> category = new LinkedHashMap<String, Object>();		
		List<String> listaFoto = new ArrayList<String>();
		LinkedHashMap<String, Object> tag = new LinkedHashMap<String, Object>();
		List<LinkedHashMap<String, Object>> listaTag = new ArrayList<LinkedHashMap<String,Object>>();
		
		//inserindo o POST no JSON 
		category.put("id", 0);
		category.put("name", "teste");
		json.put("category", category);
		json.put("name", "ines");
		listaFoto.add("string");
		json.put("photoUrls", listaFoto);
		tag.put("id", 0);
		tag.put("name", "amelia");
		listaTag.add(tag);
		json.put("tags", listaTag);
		json.put("status", "available");
		
		RestUtil.post(json);
		
		//verificando a validadção dos campos
		assertEquals(200, RestUtil.getStatus());
		assertEquals("ines", RestUtil.getJson("name"));
		assertEquals("teste", RestUtil.getJson("category.name"));		
		assertEquals("amelia", RestUtil.getJson("tags[0].name"));  //primerio item da lista tag
		
		assertNotEquals("", RestUtil.getJson("id"));   //verifica se o campo está vazio
		assertNotEquals(null, RestUtil.getJson("id")); //verifica se o campo está nulo				
	}

}

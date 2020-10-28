package testServico;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Test;

import entidade.Category;
import entidade.Pet;
import entidade.Tag;
import util.RestUtil;

public class ConsultaPetTest {
	
	//https://petstore.swagger.io/v2/pet
	private String url = "https://petstore.swagger.io/v2";
	private String endpoint = "/pet";
		
	@Test
	public void consultaPetMap() {
		RestUtil.setUrl(url);
		RestUtil.setEndpoint(endpoint);
		
		//inserindo o POST no JSON com as listas
		Category category = new Category(0, "poodle");
		List<Object> listaFotos = new ArrayList<Object>();
		listaFotos.add("string");
		Tag tag = new Tag(0, "veronica");
		List<LinkedHashMap<String, Object>> tags = new ArrayList<LinkedHashMap<String, Object>>();
		tags.add(tag.get());
		Pet pet = new Pet(category, "dog", listaFotos, tags, "available");
			
		
		RestUtil.post(pet.get());
		
		//verificando a validadção dos campos
		assertEquals(200, RestUtil.getStatus());
		assertEquals("dog", RestUtil.getJson("name"));
		assertEquals("poodle", RestUtil.getJson("category.name"));		
		assertEquals("veronica", RestUtil.getJson("tags[0].name"));  //primerio item da lista tag
		
		assertNotEquals("", RestUtil.getJson("id"));   //verifica se o campo está vazio
		assertNotEquals(null, RestUtil.getJson("id")); //verifica se o campo está nulo				
	}


}

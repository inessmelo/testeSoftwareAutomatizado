package testServico;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import entidade.Pet;
import massa.PetMassa;
import util.RestUtil;

public class ConsultaPet {

	//utilizando swagger PetStore
	// https://petstore.swagger.io/v2/pet
	private String url = "https://petstore.swagger.io/v2";
	private String endpoint = "/pet";

	@Test
	public void consultaPetMap() {
		RestUtil.setUrl(url);
		RestUtil.setEndpoint(endpoint);

		// inserindo a massa criada do Pet
		Pet pet = PetMassa.gerarMassaPet();

		// fazendo a consulta
		RestUtil.post(pet.get());

		// verificando a validad��o dos campos
		assertEquals(200, RestUtil.getStatus());
		assertEquals("dog", RestUtil.getJson("name"));
		assertEquals("poodle", RestUtil.getJson("category.name"));
		assertEquals("veronica", RestUtil.getJson("tags[0].name")); // primerio item da lista tag
		
		assertNotEquals("", RestUtil.getJson("id")); // verifica se o campo est� vazio
		assertNotEquals(null, RestUtil.getJson("id")); // verifica se o campo est� nulo
	}

}

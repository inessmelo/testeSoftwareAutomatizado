package testServico;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ConsultaCepTest {

	// http://viacep.com.br/ws/26260230/json
	private String url = "http://viacep.com.br/ws/";

	@Test
	public void consultaCep() {

		String cep = "26260230";
		String endpoint = cep.concat("/json");

		//setando a url
		RestAssured.baseURI = url;

		RestAssured.given().relaxedHTTPSValidation()
		.contentType(ContentType.JSON)
		.when().get(endpoint)
		.then().statusCode(200);
	}
	
	@Test
	public void validarDadosCepHeader() {
		
		String cep = "26260230";
		String endpoint = cep.concat("/json");
		LinkedHashMap<String, String> cabecalho = new LinkedHashMap<String, String>();
		cabecalho.put("cliente_id", "cep");
		cabecalho.put("Authorization", "Basic aW5lc21lbG86MTIzNDU=");
		
		//setando a url
		RestAssured.baseURI = url;
		
		//pegando tudo que está no response
		Response response = RestAssured.given()
				.relaxedHTTPSValidation()  //evita o SSL
				.contentType(ContentType.JSON) 
				.headers(cabecalho)     //o cabeçalho identificado acima
				.when().get(endpoint)   //fazendo um GET do cep
				.then().statusCode(200) //status igual a 200
				.extract().response();  //extraindo todo o response
		
		//seleciona o body do response
		JsonPath json = response.getBody().jsonPath();
		
		//o que se espera na consulta do response
		assertEquals("Rua Dom Manuel", json.get("logradouro"));
		assertEquals("Nova Iguaçu", json.get("localidade"));
	}
	
	@Test
	public void validarDadosCepParam() {
		
		String cep = "26260230";
		String endpoint = cep.concat("/json");
		LinkedHashMap<String, String> parametro = new LinkedHashMap<String, String>();
		parametro.put("cliente_id", "cep");
		parametro.put("Authorization", "Basic aW5lc21lbG86MTIzNDU=");
		
		//setando a url
		RestAssured.baseURI = url;
		
		//pegando tudo que está no response
		Response response = RestAssured.given()
				.relaxedHTTPSValidation()  //desconsidera se tiver HTTPS
				.contentType(ContentType.JSON) //tipo de envio (JSON)
				.params(parametro)      //add os paramentros identificado acima
				.when().get(endpoint)   //fazendo um GET do cep e clicar no SEND
				.then().statusCode(200) //validando o status 
				.extract().response();  //extraindo todo o response
		
		//seleciona o body do response e pega o JSON
		JsonPath json = response.getBody().jsonPath();
		
		//o que se espera na consulta do response
		assertEquals("Rua Dom Manuel", json.get("logradouro"));
		assertEquals("Nova Iguaçu", json.get("localidade"));
	}

}

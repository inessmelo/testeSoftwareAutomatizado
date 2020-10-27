package testServico;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ConsultaCepTest {

	// http://viacep.com.br/ws/26260230/json
	private String url = "http://viacep.com.br/ws/";

	@Test
	public void consultaCep() {

		String cep = "26260230";
		String endpoint = cep.concat("/json");

		RestAssured.baseURI = url;  //setando a URL

		Response response = get(endpoint);   //chamando o método criado
		
		assertEquals(200, response.statusCode()); //validando o status do response
	}
	
	@Test
	public void validarDadosCepHeader() {
		
		String cep = "26260230";
		String endpoint = cep.concat("/json");
		LinkedHashMap<String, String> header = new LinkedHashMap<String, String>();
		header.put("cliente_id", "cep");
		header.put("Authorization", "Basic aW5lc21lbG86MTIzNDU=");
		
		RestAssured.baseURI = url;  //setando a URL
		//ResponseSpecification request = initRequest(ContentType.JSON, header); //chamando o método e passando o tipo do contentType
		
		//pegando tudo que está no response
		Response response = initRequest(ContentType.JSON)
				.headers(header)
				.when().get(endpoint)     //fazendo um GET do cep
				.then().statusCode(200)   //validando o status 
				.extract().response();    //extraindo todo o response
		
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
		LinkedHashMap<String, String> param = new LinkedHashMap<String, String>();
		param.put("cliente_id", "cep");
		param.put("Authorization", "Basic aW5lc21lbG86MTIzNDU=");
		
		RestAssured.baseURI = url;  //setando a URL
		
		//pegando tudo que está no response
		Response response = initRequest(ContentType.JSON) //chamando o método e passando o tipo do contentType
				.params(param)        //add os paramentros identificado acima
				.when().get(endpoint)     //fazendo um GET do cep e clicar no SEND
				.then().statusCode(200)   //validando o status 
				.extract().response();    //extraindo todo o response
		
		//seleciona o body do response e pega o JSON
		JsonPath json = response.getBody().jsonPath();
		
		//o que se espera na consulta do response
		assertEquals("Rua Dom Manuel", json.get("logradouro"));
		assertEquals("Nova Iguaçu", json.get("localidade"));
	}
	
	//criando método para contentType 
	public RequestSpecification initRequest(ContentType contentType) {
		return RestAssured.given()
		.relaxedHTTPSValidation()      //desconsidera se tiver HTTPS
		.contentType(contentType);     //tipo de envio (JSON)
	}
	
	//criando método para o HEADER
	public RequestSpecification initRequest(ContentType contentType, LinkedHashMap<String, String> header) {
		return RestAssured.given()
		.relaxedHTTPSValidation()      //desconsidera se tiver HTTPS
		.contentType(contentType)      //tipo de envio (JSON)
		.headers(header);           //validando o header
	}
		
	//criando método para p RESPONSE
	public Response get(String endpoint) {
		return initRequest(ContentType.JSON) //chamando o método e passando o tipo do contentType		
		.when().get(endpoint)     //fazendo um GET do cep e clicar no SEND
		.then()
		.extract().response();   //extraindo todo o response 

	}

}

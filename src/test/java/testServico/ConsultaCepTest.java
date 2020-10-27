package testServico;

import java.util.LinkedHashMap;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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
	public void consultaCepHeader() {
		
		String cep = "26260230";
		String endpoint = cep.concat("/json");
		LinkedHashMap<String, String> header = new LinkedHashMap<String, String>();
		header.put(key, value);
		header.put("Authorization", "Basic aW5lc21lbG86MTIzNDU=");
		
		//setando a url
		RestAssured.baseURI = url;
		
		//pegando tudo que está no response do Postman
		Response response = RestAssured.given()
				.relaxedHTTPSValidation()
				.contentType(ContentType.JSON)
				.headers(header)
				.when().get(endpoint)   //fazendo um GET do cep
				.then().statusCode(200) //status igual a 200
				.extract().response();  //extraindo todo o response
	}

}

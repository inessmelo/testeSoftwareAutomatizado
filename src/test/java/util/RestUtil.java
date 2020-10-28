package util;

import java.util.LinkedHashMap;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestUtil {

	public static String url;
	public static Response response;

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		RestUtil.url = url;
		RestAssured.baseURI = url; // setando o url
	}

	public static Response getResponse() {
		return response;
	}

	public static void setResponse(Response response) {
		RestUtil.response = response;
	}

	// criando m�todo para contentType
	public static RequestSpecification initRequest(ContentType contentType) {
		return RestAssured.given().relaxedHTTPSValidation() // desconsidera se tiver HTTPS
				.contentType(contentType); // tipo de envio (JSON)
	}

	// criando m�todo para p RESPONSE
	public static void get(String endpoint) {
		Response response = initRequest(ContentType.JSON) // chamando o m�todo e passando o tipo do contentType
				.when().get(endpoint) // fazendo um GET do cep e clicar no SEND
				.then().extract().response(); // extraindo todo o response
		setResponse(response); // guardando o valor do response
	}

	public static void getHeaders(String endpoint, LinkedHashMap<String, String> header) {
		Response response = RestUtil.initRequest(ContentType.JSON) // chamando o m�todo e passando o tipo do contentType
				.headers(header).when().get(endpoint) // fazendo um GET do cep
				.then().extract().response(); // extraindo todo o response
		setResponse(response); // guardando o valor do response
	}

	public static void getParams(String endpoint, LinkedHashMap<String, String> param) {
		Response response = RestUtil.initRequest(ContentType.JSON) // chamando o m�todo e passando o tipo do contentType
				.params(param) // add os paramentros identificado acima
				.when().get(endpoint) // fazendo um GET do cep e clicar no SEND
				.then().extract().response(); // extraindo todo o response
		setResponse(response); // guardando o valor do response
	}

	// criando m�todo para o HEADER
	public RequestSpecification initRequest(ContentType contentType, LinkedHashMap<String, String> header) {
		return RestAssured.given().relaxedHTTPSValidation() // desconsidera se tiver HTTPS
				.contentType(contentType) // tipo de envio (JSON)
				.headers(header); // validando o header
	}

	// retorna o status do response
	public static int getStatus() {
		return getResponse().statusCode();
	}
	
	//retorna o JSON do Body
	public static Object getJson(String key) {
		JsonPath json = getResponse().getBody().jsonPath();
		return json.get(key);
	}
}

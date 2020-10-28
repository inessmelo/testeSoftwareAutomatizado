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
	public static String endpoint;
	
	public static String getEndpoint() {
		return endpoint;
	}

	public static void setEndpoint(String endpoint) {
		RestUtil.endpoint = endpoint;
	}

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

	// criando método para contentType
	public static RequestSpecification initRequest(ContentType contentType) {
		return RestAssured.given().relaxedHTTPSValidation() // desconsidera se tiver HTTPS
				.contentType(contentType);                  // tipo de envio (JSON)
	}
	
	//criando o método POST para o RESPONSE
	public static void post(Object json) {
		Response response = initRequest(ContentType.JSON) // chamando o método e passando o tipo do contentType
				.body(json)                    //mandando o objeto
				.when().post(endpoint)         // fazendo um POST do cep e clicando no SEND
				.then().extract().response();  // extraindo todo o response
		setResponse(response);                 // guardando o valor do response
	}
	
	//criando um POST com HEADER para o RESPONSE
	public static void postHeader(Object json, LinkedHashMap<String, String> header) {
		Response response = initRequest(ContentType.JSON) // chamando o método e passando o tipo do contentType
				.headers(header)               //mandando o header 
				.body(json)                    //mandando o objeto
				.when().post(endpoint)         // fazendo um POST do cep e clicando no SEND
				.then().extract().response();  // extraindo todo o response
		setResponse(response);                 // guardando o valor do response
	}

	// criando método GET para o RESPONSE
	public static void get() {
		Response response = initRequest(ContentType.JSON) // chamando o método e passando o tipo do contentType
				.when().get(endpoint)         // fazendo um GET do cep e clicando no SEND
				.then().extract().response(); // extraindo todo o response
		setResponse(response);                // guardando o valor do response
	}

	public static void getHeaders(LinkedHashMap<String, String> header) {
		Response response = RestUtil.initRequest(ContentType.JSON) // chamando o método e passando o tipo do contentType
				.headers(header)              //pegando o header
				.when().get(endpoint)         // fazendo um GET do cep
				.then().extract().response(); // extraindo todo o response
		setResponse(response);                // guardando o valor do response
	}
	
	public static void getHeadersParams(LinkedHashMap<String, String> header,
			LinkedHashMap<String, String> param) {
		Response response = initRequest(ContentType.JSON)
				.headers(header).params(param) //pegando o header e o param
				.when().get(endpoint)          // fazendo um GET do cep
				.then().extract().response();  // extraindo todo o response
		setResponse(response);                 // guardando o valor do response
	}

	public static void getParams(LinkedHashMap<String, String> param) {
		Response response = RestUtil.initRequest(ContentType.JSON) // chamando o método e passando o tipo do contentType
				.params(param)                // add os paramentros identificado acima
				.when().get(endpoint)         // fazendo um GET do cep e clicar no SEND
				.then().extract().response(); // extraindo todo o response
		setResponse(response);                // guardando o valor do response
	}

	// criando método para o HEADER
	public RequestSpecification initRequest(ContentType contentType, LinkedHashMap<String, String> header) {
		return RestAssured.given().relaxedHTTPSValidation() // desconsidera se tiver HTTPS
				.contentType(contentType)                   // tipo de envio (JSON)
				.headers(header);                           // validando o header
	}

	// retorna o status do response
	public static int getStatus() {
		return getResponse().statusCode();       
	}
	
	//retorna o JSON do Body
	public static Object getJson(String key) {
		JsonPath json = getResponse().getBody().jsonPath();  //seleciona todo o body do response em JSON
		return json.get(key);                                //retorna o valor do JSON
	}
}

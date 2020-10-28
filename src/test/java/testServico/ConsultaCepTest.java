package testServico;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;

import org.junit.Test;

import util.RestUtil;

public class ConsultaCepTest {

	// http://viacep.com.br/ws/26260230/json
	private String url = "http://viacep.com.br/ws/";

	@Test
	public void consultaCep() {

		String cep = "26260230";
		String endpoint = cep.concat("/json");

		RestUtil.setUrl(url); // setando a URL
		RestUtil.get(endpoint); // chamando o método get na classe RestUtil

		assertEquals(200, RestUtil.getStatus()); // validando o status do response
	}

	@Test
	public void validarDadosCepHeader() {

		String cep = "26260230";
		String endpoint = cep.concat("/json");
		LinkedHashMap<String, String> header = new LinkedHashMap<String, String>();
		header.put("cliente_id", "cep");
		header.put("Authorization", "Basic aW5lc21lbG86MTIzNDU=");

		RestUtil.setUrl(url); // setando a URL
		RestUtil.getHeaders(endpoint, header);

		// o que se espera na consulta do response
		assertEquals(200, RestUtil.getStatus()); // validando o status do response
		assertEquals("Rua Dom Manuel", RestUtil.getJson("logradouro"));
		assertEquals("Nova Iguaçu", RestUtil.getJson("localidade"));
	}

	@Test
	public void validarDadosCepParam() {

		String cep = "26260230";
		String endpoint = cep.concat("/json");
		LinkedHashMap<String, String> param = new LinkedHashMap<String, String>();
		param.put("cliente_id", "cep");
		param.put("Authorization", "Basic aW5lc21lbG86MTIzNDU=");

		RestUtil.setUrl(url); // setando a URL
		RestUtil.getParams(endpoint, param);

		// o que se espera na consulta do response
		assertEquals(200, RestUtil.getStatus()); // validando o status do response
		assertEquals("Rua Dom Manuel", RestUtil.getJson("logradouro"));
		assertEquals("Nova Iguaçu", RestUtil.getJson("localidade"));
	}
}

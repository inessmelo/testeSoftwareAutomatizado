package steps;

import static org.junit.Assert.assertEquals;

import entidade.Pet;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import massa.PetMassa;
import util.RestUtil;

public class PetSteps {

	String url, endpoint;
	Pet pet;

	@Dado("que tenho um payload valido do pet")
	public void queTenhoUmPayloadValido() {
		url = PetMassa.url; // setando a URL
		endpoint = PetMassa.endpoint; // setando o endpoint
		pet = PetMassa.gerarMassaPet(); // setando o pet já com a massa
		RestUtil.setUrl(url);
		RestUtil.setEndpoint(endpoint);
	}

	@Quando("realizo uma requisicao de post do pet")
	public void realizoUmaRequisicaoDePostDoPet() {
		RestUtil.post(pet.get());
	}

	@Entao("valido o status code {int}")
	public void validoOStatusCode(int statusCode) {
		assertEquals(200, RestUtil.getStatus()); // validando
	}

	@Entao("valido o campo {string} com o valor {string}")
	public void validoOCampoComOValor(String campo, String valor) {
		assertEquals(valor, RestUtil.getJson(campo));
	}

	@Entao("guardo o id gerado do pet")
	public void guardoOIdGeradoDoPet() {
		PetMassa.id = RestUtil.getJson("id").toString(); // guardando o id do pet gerado e transformando para String
	}

	@Quando("altero o valor do nome do pet para {string}")
	public void alteroOValorDoNomeDoPetPara(String nome) {
		pet.setPet("name", nome);
	}

}

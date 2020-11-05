package entidade;

import java.util.LinkedHashMap;
import java.util.List;

public class Pet {
	
	private LinkedHashMap<String, Object> pet = new LinkedHashMap<String, Object>();
	
	//criando o contrutor
	public Pet(Category categoria, Object nome, List<Object> fotoURL,
			List<LinkedHashMap<String, Object>> tags, Object status) {
		pet.put("category", categoria.get());
		pet.put("name", nome);
		pet.put("photoUrls", fotoURL);
		pet.put("tags", tags);
		pet.put("status", status);
	}
	
	//criando o m�todo para guardar a chave e o valor
	public void setPet(String chave, Object valor) {
		pet.put(chave, valor);
	}
	
	//inserindo todos os dados tag em uma lista
	public void setPet(LinkedHashMap<String, Object> mapa) {
		pet.putAll(mapa);
	}
	
	//removendo Pet
	public void removePet(String chave) {
		pet.remove(chave);
	}
	
	//retornando o pet
	public LinkedHashMap<String, Object> get() {
		return pet;
	}

}

package entidade;

import java.util.LinkedHashMap;

public class Category {
	
	private LinkedHashMap<String, Object> category = new LinkedHashMap<String, Object>();
	
	//criando construtor
	public Category (Object id, Object nome) {
		category.put("id", id);
		category.put("name", nome);
	}
	
	//criando um metodo com a chave o valor
	public void setCategory(String chave, String valor) {
		category.put(chave, valor);
	}
	
	 //inserindo os dados category em uma lista
	public void setCategory(LinkedHashMap<String, Object> mapa) {
		category.putAll(mapa);
	}

	//retornando category
	public LinkedHashMap<String, Object> get() {
		return category;
	}
}

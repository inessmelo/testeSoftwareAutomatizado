package entidade;

import java.util.LinkedHashMap;

public class Tag {
	
	private LinkedHashMap<String, Object> tag = new LinkedHashMap<String, Object>();
	
	//criando construtor
	public Tag(Object id, Object nome) {
		tag.put("id", id);
		tag.put("name", nome);
	}
	
	//criando o método para guardar a chave e o valor
	public void setTag(String chave, String valor) {
		tag.put(chave, valor);
	}
	
	//inserindo todos os dados tag em uma lista
	public void setTag(LinkedHashMap<String, Object> mapa) {
		tag.putAll(mapa);
	}
	
	//retornando a tag
	public LinkedHashMap<String, Object> get() {
		return tag;
	}

}

package massa;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import entidade.Category;
import entidade.Pet;
import entidade.Tag;

public class PetMassa {
	
	public static Category category;
	public static List<Object> fotoUrl = new ArrayList<Object>();
	public static Tag tags;
	public static List<LinkedHashMap<String, Object>> listaTag = new ArrayList<LinkedHashMap<String,Object>>();
	public static Pet pet;
	public static String url = "https://petstore.swagger.io/v2";
	public static String endpoint = "/pet";
	public static String id;
	
	//inserindo o POST no JSON com as listas
	public static Pet gerarMassaPet() { 
		category = new Category(0, "poodle");
		fotoUrl.add("string");
		tags = new Tag(0, "veronica");
		listaTag.add(tags.get());
		pet = new Pet(category, "dog", fotoUrl, listaTag, "available");
		return pet;
	}

}

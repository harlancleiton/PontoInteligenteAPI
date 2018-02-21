package br.harlan.api.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "teste")
public class TesteDocument {
	@Id
	private String id;
	private String name;
	
	public TesteDocument() {
	}
	
	public TesteDocument(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "TesteDocument [id=" + id + ", name=" + name + "]";
	}
}
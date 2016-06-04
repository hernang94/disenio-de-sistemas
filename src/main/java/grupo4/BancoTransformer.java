package grupo4;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BancoTransformer implements Adaptadores{
	private ComponenteBanco componente;
	private ObjectMapper objectMapper;

	public BancoTransformer() {
		this.objectMapper = new ObjectMapper();
	}
	
	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}
	
	public <T> T desdeJson(String json, Class<T> typeReference) {
		try {
			return this.objectMapper.readValue(json, typeReference);
		} catch (IOException e) {

			throw new RuntimeException("Error reading a json", e);
		}
	}
	
	public List<Poi>buscarPois(String criterio){
		List<String>jsons= componente.getJsonBanco(criterio);
		return jsons.stream().map(json->desdeJson(json, Banco.class)).collect(Collectors.toList());
	}

	public void setComponente(ComponenteBanco componenteBanco) {
		this.componente=componenteBanco;
		
	}
	
}

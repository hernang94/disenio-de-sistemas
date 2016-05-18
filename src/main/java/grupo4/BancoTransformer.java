package grupo4;

import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BancoTransformer {

	private ObjectMapper objectMapper;

	public BancoTransformer() {
		this.objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}
	
	public <T> T fromJson(String json, Class<T> typeReference) {
		try {
			return this.objectMapper.readValue(json, typeReference);
		} catch (IOException e) {

			throw new RuntimeException("Error al leer de JSON", e);
		}
	}
}

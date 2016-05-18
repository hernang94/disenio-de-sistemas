package grupo4;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BancoTransformer {

	private ObjectMapper objectMapper;

	public BancoTransformer() {
		this.objectMapper = new ObjectMapper();
	}
	
	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}
}

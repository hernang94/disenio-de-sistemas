package grupo4.ComponentesExternos;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import DTOexterno.BajaPoiExterna;

public class BajaPoiAdapter {
	private ObjectMapper objectMapper;
	private ComponenteBajaPois componente;

	public BajaPoiAdapter(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public List<BajaPoiExterna> obtenerPoisABajar() {
		String jsons = componente.getJsonBajaDePois();
		return convertirJson(jsons);

	}

	public List<BajaPoiExterna> convertirJson(String jsons) {
		if (jsons.contains("status_code")) {
			throw new RuntimeException("Error 400");
		} else {
			List<BajaPoiExterna> lista = new ArrayList<>();
			try {
				lista = objectMapper.readValue(jsons,
						TypeFactory.defaultInstance().constructCollectionLikeType(List.class, BajaPoiExterna.class));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return lista;
		}
	}

	public void setComponente(ComponenteBajaPois componente) {
		this.componente = componente;
	}

}

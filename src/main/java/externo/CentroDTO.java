package externo;

import java.util.ArrayList;
import java.util.List;

public class CentroDTO {
	private int comuna;
	private String zonas;
	private String director;
	private String domicilio;
	private String telefono;
	private List<ServicioDTO> serviciosDTO = new ArrayList<>();

	public int getComuna() {
		return comuna;
	}

	public String getZonas() {
		return zonas;
	}

	public String getDirector() {
		return director;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public List<ServicioDTO> getServiciosDTO() {
		return serviciosDTO;
	}

}

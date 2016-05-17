package externo;

import java.util.List;

public interface InterfazCentroDTO {

	public int getComuna();
	public String getZonas();
	public String getDirector();
	public String getDomicilio();
	public String getTelefono();
	public List<ServicioDTO> getServiciosDTO();
}

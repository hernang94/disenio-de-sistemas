package grupo4.PoiDTOs;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mongodb.morphia.annotations.Embedded;

import grupo4.HerramientasExternas.Punto;
import grupo4.POIs.Horario;

public class BancoDTO extends PoiDTO{
	@Embedded
	private Map<DayOfWeek, Horario> hashHorario;
	@Embedded
	private List<ServicioDTO> listaServicios;
	
	public BancoDTO(String nombre, List<String> palabrasClaves, Punto ubicacion,Map<DayOfWeek, Horario> horarios, List<ServicioDTO> listaServicios) {
		super(nombre, palabrasClaves, ubicacion);
		this.hashHorario = horarios;
		this.listaServicios = listaServicios;
	}

	public List<ServicioDTO> getListaServicios() {
		return listaServicios;
	}
	
}

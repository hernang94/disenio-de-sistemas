package grupo4.POIs;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.OneToMany;

//import com.fasterxml.jackson.annotation.JsonProperty;

public class Banco extends Poi {
	private Map<DayOfWeek, Horario> hashHorario;
	@OneToMany
	private List<Servicio> listaServicios = new ArrayList<>();

	public Banco(int id,Map<DayOfWeek, Horario> horarios, String nombre, List<String> palabrasClaves) {
		super(id,nombre, palabrasClaves);
		this.hashHorario = horarios;
	}

	public List<Servicio> getListaServicios() {
		return listaServicios;
	}

	public void setListaServicios(List<Servicio> listaServicios) {
		this.listaServicios = listaServicios;
	}

	public boolean estaDisponible(LocalDateTime horaConsulta) {
		DayOfWeek dia = horaConsulta.getDayOfWeek();
		return (hashHorario.get(dia) != null) && (hashHorario.get(dia).estaEnHorario(horaConsulta));
	}

}

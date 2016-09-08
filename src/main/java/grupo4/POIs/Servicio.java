package grupo4.POIs;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Map;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Servicio {
	@Id @GeneratedValue
	private int id;
	private String nombre;
	private Map<DayOfWeek, Horario> hashHorario;

	public Servicio(String unNombre, Map<DayOfWeek, Horario> horarios) {
		this.nombre = unNombre;
		hashHorario = horarios;
	}

	public boolean estaDisponible(LocalDateTime fechaConsulta) {
		DayOfWeek dia = fechaConsulta.getDayOfWeek();
		if (hashHorario.get(dia) != null) {
			return hashHorario.get(dia).estaEnHorario(fechaConsulta);
		}
		return false;
	}

	public String getNombre() {
		return nombre;
	}
}

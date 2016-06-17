package grupo4.POIs;

import java.time.LocalDateTime;
import java.util.Map;

public class Servicio {
	private String nombre;
	private Map<Integer,Horario>hashHorario;

	public Servicio(String unNombre, Map<Integer,Horario> horarios) {
		this.nombre = unNombre;
		hashHorario=horarios;
	}
	
	public boolean estaDisponible(LocalDateTime fechaConsulta) {
		int dia=fechaConsulta.getDayOfWeek().getValue();
		if(hashHorario.get(dia)!=null){
			return hashHorario.get(dia).estaEnHorario(fechaConsulta);
		}
		return false;
	}

	public String getNombre() {
		return nombre;
	}
}

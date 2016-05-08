package grupo4;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Servicio {
	private String nombre;
	private Horario horario;
	private Map<Integer,Horario>hashHorario;

	public Servicio(String unNombre) {
		this.nombre = unNombre;
		hashHorario=new HashMap<>();
	}

	public void cargarHorariosa(int dia,String horaDesde, String horaHasta){
		horario= new Horario(horaDesde, horaHasta);
		hashHorario.put(dia, horario);
	}
		
	public boolean estaDisponible(LocalDateTime fechaConsulta) {
		int dia = fechaConsulta.getDayOfWeek().getValue();
		return (hashHorario.get(dia).estaEnHorario(fechaConsulta));
	}

	public String getNombre() {
		return nombre;
	}
}

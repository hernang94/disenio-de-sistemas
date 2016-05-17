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
		inicializarHash(hashHorario);
	}

	public void inicializarHash(Map<Integer,Horario> hashmap){
		for (int i = 0; i < 7; i++) {
			hashmap.put(i, null);
		}
	}
		
	public void cargarHorario(int dia,String horaDesde, String horaHasta){
		horario= new Horario(horaDesde, horaHasta);
		hashHorario.replace(dia, horario);
	}
	
	public boolean estaDisponible(LocalDateTime fechaConsulta) {
		int dia = fechaConsulta.getDayOfWeek().getValue();
		return (hashHorario.get(dia)!=null)&&(hashHorario.get(dia).estaEnHorario(fechaConsulta));
	}

	public String getNombre() {
		return nombre;
	}
}

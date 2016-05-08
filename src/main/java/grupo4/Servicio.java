package grupo4;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Servicio {
	private String nombre;
	private Horario horario;
	private Map<Integer,Horario>hashHorario;

	public Servicio(String unNombre, String horaDesde, String horaHasta, int diaDesde, int diaHasta) {
		this.nombre = unNombre;
		this.horario =  new Horario(horaDesde, horaHasta);
		hashHorario=new HashMap<>();
		inicializarHash(hashHorario);
		cargarHash(hashHorario, horario, diaDesde, diaHasta);
	}

	public void inicializarHash(Map<Integer,Horario> hashmap){
		for (int i = 0; i < 7; i++) {
			hashmap.put(i, null);
		}
	}
	public void cargarHash(Map<Integer,Horario> hashmap,Horario horario,int desde, int hasta){
		for(int i=desde; i<=hasta;i++){
			hashmap.replace(i,horario);
		}
	}
		
	public boolean estaDisponible(LocalDateTime fechaConsulta) {
		int dia = fechaConsulta.getDayOfWeek().getValue();
		return (hashHorario.get(dia)!=null)&&(hashHorario.get(dia).estaEnHorario(fechaConsulta));
	}

	public String getNombre() {
		return nombre;
	}
}

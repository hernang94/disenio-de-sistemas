package grupo4;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Banco extends Poi {
	private Horario horario;
	private Map<Integer,Horario> hashHorario;

	public Banco(String horaDesde, String horaHasta) {
		this.horario = new Horario(horaDesde, horaHasta);
		this.hashHorario= new HashMap<>();
		cargarHash(this.hashHorario,this.horario);
	}
	public void cargarHash(Map<Integer,Horario> hashmap,Horario horario){
		for(int i=0; i<5;i++){
			hashmap.put(i+1,horario);
		}
	}
	public boolean estaDisponible(LocalDateTime horaConsulta) {
		int dia = horaConsulta.getDayOfWeek().getValue();
		return (hashHorario.get(dia).estaEnHorario(horaConsulta));
	}
}

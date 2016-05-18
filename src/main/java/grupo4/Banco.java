package grupo4;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Banco extends Poi {
	private Horario horario;
	private Map<Integer,Horario> hashHorario;
	private List<Servicio>listaServicios= new ArrayList<>();

	public Banco() {
		this.horario = new Horario("10:00", "15:00");
		super.setCoordenadas();
		this.hashHorario= new HashMap<>();
		inicializarHash(hashHorario);
		cargarHash(this.hashHorario,this.horario);
	}

	public void cargarHash(Map<Integer,Horario> hashmap,Horario horario){
		for(int i=0; i<5;i++){
			hashmap.replace(i+1,horario);
		}
	}
	
	public List<Servicio> getListaServicios() {
		return listaServicios;
	}

	public void setListaServicios(List<Servicio> listaServicios) {
		this.listaServicios = listaServicios;
	}

	public boolean estaDisponible(LocalDateTime horaConsulta) {
		int dia = horaConsulta.getDayOfWeek().getValue();
		return (hashHorario.get(dia)!=null)&&(hashHorario.get(dia).estaEnHorario(horaConsulta));
	}
}

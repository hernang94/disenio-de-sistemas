package grupo4;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import com.fasterxml.jackson.annotation.JsonProperty;


public class Banco extends Poi {
	private Map<Integer,Horario> hashHorario;
	private List<Servicio>listaServicios= new ArrayList<>();

	public Banco(Map<Integer,Horario> horarios,String nombre) {
		super(nombre);
		this.hashHorario= horarios;
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

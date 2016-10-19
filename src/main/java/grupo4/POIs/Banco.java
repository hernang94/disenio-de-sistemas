package grupo4.POIs;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import grupo4.HerramientasExternas.Punto;

@Entity
@DiscriminatorValue(value = "Banco")
public class Banco extends Poi {
	@ElementCollection
	@MapKeyJoinColumn(name = "Dia_de_la_semana")
	private Map<DayOfWeek, Horario> hashHorario;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="idPoi")
	private List<Servicio> listaServicios = new ArrayList<>();
	
	public Banco(){
		super();
	}
	public Banco(Map<DayOfWeek, Horario> horarios, String nombre, List<String> palabrasClaves,Punto coordenadas) {
		super(nombre, palabrasClaves,coordenadas);
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

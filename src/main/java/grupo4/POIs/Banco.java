package grupo4.POIs;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
@Entity
public class Banco extends Poi {
	@ElementCollection
	@CollectionTable(name = "Horario")
	@MapKeyJoinColumn(name = "Dia_de_la_semana")
	@Column(name = "Horario")
	private Map<DayOfWeek, Horario> hashHorario;
	@OneToMany
	private List<Servicio> listaServicios = new ArrayList<>();

	public Banco(Map<DayOfWeek, Horario> horarios, String nombre, List<String> palabrasClaves) {
		super(nombre, palabrasClaves);
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

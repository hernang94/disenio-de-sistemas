package grupo4.POIs;


import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyJoinColumn;
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
	public Banco(Map<DayOfWeek, Horario> horarios, String nombre, String direccion, List<String> palabrasClaves,Punto coordenadas) {
		super(nombre,direccion, palabrasClaves,coordenadas);
		this.hashHorario = horarios;
	}

	public List<Servicio> getListaServicios() {
		return listaServicios;
	}

	public void setListaServicios(List<Servicio> listaServicios) {
		this.listaServicios = listaServicios;
	}

	public List<String> getServicios(){
		List<String> nombreServicios=new ArrayList<>();
		listaServicios.stream().forEach(servicio->nombreServicios.add(servicio.getNombre()));
		return nombreServicios;
	}
	
	public List<String> getHorarios(){
		List<String> horarios=new ArrayList<>();
		hashHorario.forEach((K,V)->horarios.add("Dia: "+K.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("es", "ES"))+" Desde: "+V.getDesde()+" Hasta: "+V.getHasta()+"\n"));
		return horarios;
		
	}
	public boolean estaDisponible(LocalDateTime horaConsulta) {
		DayOfWeek dia = horaConsulta.getDayOfWeek();
		return (hashHorario.get(dia) != null) && (hashHorario.get(dia).estaEnHorario(horaConsulta));
	}
}

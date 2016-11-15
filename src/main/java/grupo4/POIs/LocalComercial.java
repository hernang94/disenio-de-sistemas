package grupo4.POIs;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MapKeyJoinColumn;
import grupo4.HerramientasExternas.Punto;

@Entity
@DiscriminatorValue(value = "Local_Comercial")
public class LocalComercial extends Poi {
	@Enumerated(EnumType.STRING)
	private Rubro rubro;
	@ElementCollection
	@CollectionTable(name = "Horario_Manana")
	@MapKeyJoinColumn(name = "Dia_de_la_semana")
	@Column(name = " Local_Comercial_Horario_Mañana")
	private Map<DayOfWeek, Horario> local_Comercial_Horario_Mañana;
	@ElementCollection
	@CollectionTable(name = "Horario_Tarde")
	@MapKeyJoinColumn(name = "Dia_de_la_semana")
	@Column(name = " Local_Comercial_Horario_Tarde")
	private Map<DayOfWeek, Horario> local_Comercial_Horario_Tarde;

	public LocalComercial(){
		super();
	}
	public LocalComercial(Rubro rubro, Map<DayOfWeek, Horario> horariosManiana, Map<DayOfWeek, Horario> horariosTarde,
			String nombre, List<String> palabrasClaves,Punto coordenadas) {
		super(nombre, palabrasClaves,coordenadas);
		this.rubro = rubro;
		this.local_Comercial_Horario_Mañana = horariosManiana;
		this.local_Comercial_Horario_Tarde = horariosTarde;
	}

	public boolean estaCerca(Punto unPunto) {
		return (super.calcularDistancia(unPunto) <= rubro.getRadio());

	}

	public boolean encuentraNombre(String criterio) {
		return ((criterio.equalsIgnoreCase(rubro.getNombre())) || super.cumpleCriterio(criterio));
	}

	public boolean estaDisponible(LocalDateTime horaConsulta) {
		DayOfWeek dia = horaConsulta.getDayOfWeek();
		return evaluarDisponibilidad(dia, horaConsulta);
	}

	private boolean evaluarDisponibilidad(DayOfWeek dia, LocalDateTime horaConsulta) {
		boolean criterio1 = (local_Comercial_Horario_Mañana.get(dia).estaEnHorario(horaConsulta));
		boolean criterio2 = (local_Comercial_Horario_Tarde.get(dia).estaEnHorario(horaConsulta));
		return (criterio1 || criterio2);
	}
}

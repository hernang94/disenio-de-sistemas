package grupo4.POIs;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.uqbar.geodds.Point;

public class LocalComercial extends Poi {

	private Rubro rubro;
	private Map<DayOfWeek, Horario> hashManana;
	private Map<DayOfWeek, Horario> hashTarde;

	public LocalComercial(int id, Rubro rubro, Map<DayOfWeek, Horario> horariosManiana,
			Map<DayOfWeek, Horario> horariosTarde, String nombre, List<String> palabrasClaves) {
		super(id, nombre, palabrasClaves);
		this.rubro = rubro;
		this.hashManana = horariosManiana;
		this.hashTarde = horariosTarde;
	}

	public boolean estaCerca(Point unPunto) {
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
		boolean criterio1 = (hashManana.get(dia).estaEnHorario(horaConsulta));
		boolean criterio2 = (hashTarde.get(dia).estaEnHorario(horaConsulta));
		return (criterio1 || criterio2);
	}
}

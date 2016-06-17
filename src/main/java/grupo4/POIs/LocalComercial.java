package grupo4.POIs;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.uqbar.geodds.Point;

public class LocalComercial extends Poi {
	private Rubro rubro;
	private Map<Integer,Horario> hashManana;
	private Map<Integer,Horario> hashTarde;
	public LocalComercial(Rubro rubro,Map<Integer,Horario> horariosManiana, Map<Integer,Horario> horariosTarde,String nombre, List<String> palabrasClaves) {
		super(nombre,palabrasClaves);
		this.rubro = rubro;
		this.hashManana=horariosManiana;
		this.hashTarde=horariosTarde;
	}

	public boolean estaCerca(Point unPunto) {
		return (super.calcularDistancia(unPunto) <= rubro.getRadio());

	}

	public boolean encuentraNombre(String criterio) {
		return ((criterio.equalsIgnoreCase(rubro.getNombre())) || super.cumpleCriterio(criterio));
	}

	public boolean estaDisponible(LocalDateTime horaConsulta) {
		int dia=horaConsulta.getDayOfWeek().getValue();
		return evaluarDisponibilidad(dia,horaConsulta);
	}

	private boolean evaluarDisponibilidad(int dia, LocalDateTime horaConsulta) {
		boolean criterio1 = (hashManana.get(dia).estaEnHorario(horaConsulta));
		boolean criterio2 = (hashTarde.get(dia).estaEnHorario(horaConsulta));
		return (criterio1 || criterio2);
	}
}

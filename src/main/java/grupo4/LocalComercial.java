package grupo4;

import java.time.LocalDateTime;
import java.util.Map;

import org.uqbar.geodds.Point;

public class LocalComercial extends Poi {
	private Rubro rubro;
	private Map<Integer,Horario> hashManana;
	private Map<Integer,Horario> hashTarde;
	public LocalComercial(Rubro rubro,Map<Integer,Horario> horariosManiana, Map<Integer,Horario> horariosTarde,String nombre) {
		super(nombre);
		this.rubro = rubro;
		this.hashManana=horariosManiana;
		this.hashTarde=horariosTarde;
	}

	public boolean estaCerca(Point unPunto) {
		return (super.calcularDistancia(unPunto) <= rubro.getRadio());

	}

	public boolean encuentraNombre(String criterio) {
		return ((criterio.equalsIgnoreCase(rubro.getNombre())) || super.encuentraNombre(criterio));
	}

	public boolean estaDisponible(LocalDateTime horaConsulta) {
		int dia=horaConsulta.getDayOfWeek().getValue();
		boolean criterio1 = (hashManana.get(dia).estaEnHorario(horaConsulta));
		boolean criterio2 = (hashTarde.get(dia).estaEnHorario(horaConsulta));
		boolean criterio3 = (hashManana.get(dia)!=null);
		boolean criterio4 = (hashTarde.get(dia)!=null);
		return criterio3&&criterio4&&(criterio1 || criterio2);
	}
}

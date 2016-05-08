package grupo4;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.uqbar.geodds.Point;

public class LocalComercial extends Poi {
	private Rubro rubro;
	private Horario horarioM;
	private Horario horarioT;
	private Map<Integer,Horario> hashMañana;
	private Map<Integer,Horario> hashTarde;
	public LocalComercial(Rubro rubro) {
		this.rubro = rubro;
		this.hashMañana=new HashMap<>();
		this.hashTarde=new HashMap<>();
	}

	public boolean estaCerca(Point unPunto) {
		return (super.calcularDistancia(unPunto) <= rubro.getRadio());

	}

	public boolean encuentraNombre(String criterio) {
		return ((criterio.equalsIgnoreCase(rubro.getNombre())) || super.encuentraNombre(criterio));
	}
	public void cargarHorariosMañana(int dia,String horaDesde, String horaHasta){
		horarioM= new Horario(horaDesde, horaHasta);
		hashMañana.put(dia, horarioM);
	}
	public void cargarHorariosTarde(int dia,String horaDesde, String horaHasta){
		horarioT= new Horario(horaDesde, horaHasta);
		hashTarde.put(dia, horarioT);
	}


	public boolean estaDisponible(LocalDateTime horaConsulta) {
		int dia=horaConsulta.getDayOfWeek().getValue();
		boolean criterio1 = (hashMañana.get(dia).estaEnHorario(horaConsulta));
		boolean criterio2 = (hashTarde.get(dia).estaEnHorario(horaConsulta));
		return (criterio1 || criterio2);
	}
}

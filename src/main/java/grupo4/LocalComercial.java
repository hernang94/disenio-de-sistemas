package grupo4;

import java.time.LocalDateTime;

import org.uqbar.geodds.Point;

public class LocalComercial extends Poi {
	private Rubro rubro;
	private Horario horarioM;
	private Horario horarioT;

	public LocalComercial(Rubro rubro, String desdeM, String hastaM, String desdeT, String hastaT, int diaDesde,
			int diaHasta) {
		this.rubro = rubro;
		horarioM = new Horario(desdeM, hastaM, diaDesde, diaHasta);
		horarioT = new Horario(desdeT, hastaT, diaDesde, diaHasta);
	}

	public boolean estaCerca(Point unPunto) {
		return (super.calcularDistancia(unPunto) <= rubro.getRadio());

	}

	public boolean encuentraNombre(String criterio) {
		return ((criterio.equalsIgnoreCase(rubro.getNombre())) || super.encuentraNombre(criterio));
	}

	public boolean estaDisponible(LocalDateTime horaConsulta) {
		boolean criterio1 = (horarioM.estaEnHorario(horaConsulta) || horarioT.estaEnHorario(horaConsulta));
		boolean criterio2 = (horarioM.estaEnDia(horaConsulta) && horarioT.estaEnDia(horaConsulta));
		return (criterio1 && criterio2);
	}
}

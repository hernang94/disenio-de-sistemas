package grupo4;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class CGP extends Poi {
	private List<Servicio> servicios = new ArrayList<>();
	private Polygon comuna;

	public CGP(Polygon comuna) {
		this.comuna = comuna;
	}

	public void addServicio(Servicio unServicio) {
		this.servicios.add(unServicio);
	}

	public boolean estaCerca(Point unaCoordenada) {
		return comuna.isInsideOld(unaCoordenada);
	}

	public boolean estaDisponible(LocalDateTime horaConsulta) {
		return servicios.stream().anyMatch(servicio -> servicio.estaDisponible(horaConsulta));
	}

	public boolean coincideCon(String criterio) {
		return (criterio.equals(this.nombre)) || (servicios.stream()
				.anyMatch(servicio -> servicio.getNombre().toLowerCase().contains(criterio.toLowerCase())));
	}

}

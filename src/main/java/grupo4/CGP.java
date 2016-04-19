package grupo4;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class CGP extends Poi {
	List<Servicio> servicios = new ArrayList<>();
	private Polygon comuna;

	public CGP(Polygon comuna) {
		this.comuna = comuna;
	}

	public void addServicio(Servicio unservicio) {
		this.servicios.add(unservicio);
	}

	public boolean estaCerca(Point unaCoordenada) {
		return comuna.isInsideOld(unaCoordenada);
	}

	public boolean estaDisponible(LocalDateTime hora_consulta) {
		return servicios.stream().anyMatch(servicio -> servicio.estaDisponible(hora_consulta));
	}

	public boolean coincideCon(String criterio) {
		if (criterio.equals(this.nombre)) {
			return true;
		}
		return servicios.stream()
				.anyMatch(servicio -> servicio.getNombre().toLowerCase().contains(criterio.toLowerCase()));
	}

}

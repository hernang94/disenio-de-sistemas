package grupo4;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class CGP extends Poi {
	List<Servicio> servicios = new ArrayList<>();
	private Polygon comuna;

	public CGP(Polygon comuna,String unnombre) {
		this.nombre = unnombre;
		this.comuna = comuna;
	}

	public boolean estaCerca(Point unaCoordenada) {
		return comuna.isInside(unaCoordenada);
	}

	public boolean estaDisponible() {
		return servicios.stream().anyMatch(servicio -> servicio.estaDisponible());
	}

	public boolean estaDisponible(String nombre) {
		return servicios.stream()
				.anyMatch(servicio -> ((servicio.estaDisponible()) && (nombre.equalsIgnoreCase(servicio.getNombre()))));
	}
	public boolean coincideCon(String criterio){
		return servicios.stream()
				.anyMatch(servicio -> servicio.getNombre().toLowerCase().contains(criterio.toLowerCase()));
	}
	
}

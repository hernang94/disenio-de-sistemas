package grupo4.POIs;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class CGP extends Poi {
	private List<Servicio> servicios = new ArrayList<>();
	private Polygon comuna;

	public CGP(int id, Polygon comuna, String nombre, List<String> palabrasClaves) {
		super(id, nombre, palabrasClaves);
		this.comuna = comuna;
	}

	public void addServicio(Servicio unServicio) {
		this.servicios.add(unServicio);
	}

	public boolean estaCerca(Point unaCoordenada) {
		return comuna.isInsideOld(unaCoordenada);
	}

	public boolean estaDisponible(LocalDateTime fechaConsulta) {
		return servicios.stream().anyMatch(servicio -> servicio.estaDisponible(fechaConsulta));
	}

	public boolean estaDisponible(LocalDateTime fechaConsulta, Servicio servicio) {
		return encontrarServicio(servicio).estaDisponible(fechaConsulta);
	}

	public boolean cumpleCriterio(String criterio) {
		try {
			LocalDateTime aux = LocalDateTime.parse(criterio);
			return this.estaDisponible(aux);
		} catch (DateTimeParseException exc) {
			return (criterio.equals(this.nombre)) || (servicios.stream()
					.anyMatch(servicio -> servicio.getNombre().toLowerCase().contains(criterio.toLowerCase())));
		}
	}

	public Servicio encontrarServicio(Servicio servicio) {
		return servicios.stream().filter(unServicio -> unServicio.getNombre().equalsIgnoreCase((servicio.getNombre())))
				.findFirst().get();
	}
}

package grupo4.POIs;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import grupo4.HerramientasExternas.Poligono;
@Entity
public class CGP extends Poi {
	@OneToMany
	private List<Servicio> servicios = new ArrayList<>();
	// Preguntar a juliet :)
	@Transient
	private Poligono comuna;

	public CGP(Poligono comuna, String nombre, List<String> palabrasClaves) {
		super(nombre, palabrasClaves);
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

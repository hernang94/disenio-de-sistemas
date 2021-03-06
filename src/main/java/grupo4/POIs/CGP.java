package grupo4.POIs;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


import grupo4.HerramientasExternas.Poligono;
import grupo4.HerramientasExternas.Punto;

@Entity
@DiscriminatorValue(value = "CGP")
public class CGP extends Poi {
	
	public CGP(){
		super();
	}
	@OneToMany(cascade = CascadeType.ALL)
	private List<Servicio> servicios = new ArrayList<>();
	@Embedded
	private Poligono comuna;

	public CGP(Poligono comuna, String nombre,String direccion, List<String> palabrasClaves,Punto coordenadas) {
		super(nombre, direccion,palabrasClaves,coordenadas);
		this.comuna = comuna;
	}

	public void addServicio(Servicio unServicio) {
		this.servicios.add(unServicio);
	}

	public boolean estaCerca(Punto unaCoordenada) {
		return comuna.isInsideOld(unaCoordenada);
	}
	
	public List<String> getServicios(){
		List<String> nombreServicios=new ArrayList<>();
		servicios.stream().forEach(servicio->nombreServicios.add(servicio.getNombre()));
		return nombreServicios;
	}

	public Poligono getComuna(){
		return comuna;
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

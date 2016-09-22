package grupo4.Acciones;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import grupo4.POIs.Poi;
import grupo4.Repositorios.RepositorioDePois;
import grupo4.Repositorios.ResultadoDeBusqueda;

@Entity
@Table(name = "Usuarios")
public class Usuario {
	@Id
	@GeneratedValue
	private int idUsuario;
	@Column(name = "terminal", unique = true)
	private String terminal;
	private int comuna;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idUsuario")
	private List<ObserverDeBusqueda> observers = new ArrayList<>();

	@SuppressWarnings("unused")
	private Usuario() {
	}

	public Usuario(String terminal, int comuna) {
		this.terminal = terminal;
		this.comuna = comuna;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void agregarObserver(ObserverDeBusqueda observer) {
		observers.add(observer);
	}

	public void quitarObserver(IdObserver idObserverAQuitar) {
		if (observers.stream().anyMatch(unObserver -> unObserver.getId().equals(idObserverAQuitar))) {
			ObserverDeBusqueda observer = observers.stream()
					.filter(unObserver -> unObserver.getId().equals(idObserverAQuitar)).findFirst().get();
			observers.remove(observer);
		}
	}

	public void busquedaLibre(String criterio) {
		List<Poi> listaAux;
		LocalDateTime tiempoInicio = LocalDateTime.now();
		listaAux = RepositorioDePois.getInstancia().busquedaLibre(criterio);
		LocalDateTime tiempoFin = LocalDateTime.now();
		long diferencia = calcularDiferencia(tiempoInicio, tiempoFin);
		ResultadoDeBusqueda resultadoAux = new ResultadoDeBusqueda(this.getTerminal(), diferencia, criterio,
				tiempoInicio.toLocalDate(), listaAux.size());
		observers.stream().forEach(observer -> observer.realizarAccion(resultadoAux));
	}

	public long calcularDiferencia(LocalDateTime tiempoinicio, LocalDateTime tiempofin) {
		return ChronoUnit.SECONDS.between(tiempoinicio, tiempofin);
	}

	public String getTerminal() {
		return terminal;
	}

	public int getComuna() {
		return comuna;
	}

	public List<ObserverDeBusqueda> getObservers() {
		return observers;
	}

}

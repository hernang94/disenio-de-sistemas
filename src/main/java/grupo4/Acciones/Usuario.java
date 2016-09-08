package grupo4.Acciones;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import grupo4.POIs.Poi;
import grupo4.Repositorios.RepositorioDePois;
import grupo4.Repositorios.ResultadoDeBusqueda;

@Entity
@Table(name="Usuarios")
public class Usuario {
	@Id @GeneratedValue
	private long id;
	@Column(name="Usuario")
	private String terminal;
	private int comuna;
	@Transient
	private RepositorioDePois repositorio;
	//Se persiste o no?
	private List<ObserverDeBusqueda> observers = new ArrayList<>();
	
	@SuppressWarnings("unused")
	private Usuario(){}

	public Usuario(String terminal, RepositorioDePois repositorio, int comuna) {
		this.terminal = terminal;
		this.repositorio = repositorio;
		this.comuna = comuna;
	}

	public void agregarObserver(ObserverDeBusqueda observer) {
		observers.add(observer);
	}

	public void quitarObserver(ObserverDeBusqueda observer) {
		observers.remove(observer);
	}

	public void busquedaLibre(String criterio) {
		List<Poi> listaAux;
		LocalDateTime tiempoInicio = LocalDateTime.now();
		listaAux = repositorio.busquedaLibre(criterio);
		LocalDateTime tiempoFin = LocalDateTime.now();
		long diferencia = calcularDiferencia(tiempoInicio, tiempoFin);
		ResultadoDeBusqueda resultadoAux = new ResultadoDeBusqueda(terminal, diferencia, criterio,
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

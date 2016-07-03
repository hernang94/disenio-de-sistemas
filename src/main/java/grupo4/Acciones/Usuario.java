package grupo4.Acciones;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import grupo4.POIs.Poi;
import grupo4.Repositorios.RepositorioDePois;

public class Usuario {

	private String terminal;
	private RepositorioDePois repositorio;
	private List<Observers> observers = new ArrayList<>();
	
	public Usuario(String terminal, RepositorioDePois repositorio) {
		this.terminal = terminal;
		this.repositorio = repositorio;
	}
	
	public void agregarObserver(Observers observer){
		observers.add(observer);
	}
	
	public void quitarObserver(Observers observer){
		observers.remove(observer);
	}
	
	public void busquedaLibre(String criterio) {
		List<Poi> listaAux;
		LocalDateTime tiempoInicio = LocalDateTime.now();
		listaAux=repositorio.busquedaLibre(criterio);
		LocalDateTime tiempoFin = LocalDateTime.now();
		long diferencia=calcularDiferencia(tiempoInicio,tiempoFin);
		observers.stream().forEach(observer -> observer.evaluarNotificacion(diferencia));
		int cantBuscada = listaAux.size();
		observers.stream()
				.forEach(observer -> observer.agregarBusqueda(criterio,diferencia, tiempoInicio, cantBuscada));
	}
	
	public long calcularDiferencia(LocalDateTime tiempoinicio, LocalDateTime tiempofin){
		return ChronoUnit.SECONDS.between(tiempoinicio, tiempofin);
	}
	
	public void obtenerReporteTotalPorFecha() {
		observers.stream().forEach(unObserver -> unObserver.reporteTotalPorFecha());
	}
	
	public void reporteParcial() {
		observers.stream().forEach(observer -> observer.reporteParcial());
	}

	public void reporteTotal() {
		observers.stream().forEach(observer -> observer.reporteTotal());
	}
}

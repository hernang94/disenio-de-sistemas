package grupo4.Repositorios;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.hamcrest.core.IsNull;
import org.uqbar.geodds.Point;

import grupo4.Acciones.ObserverBusqueda;
import grupo4.Acciones.Observers;
import grupo4.ComponentesExternos.Adaptadores;
import grupo4.POIs.Poi;
import grupo4.POIs.Servicio;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class RepositorioDePois implements ObserverBusqueda {
	private String nombre;
	private List<Poi> listaDePois = new ArrayList<>();
	private List<Adaptadores> listaAdaptadores = new ArrayList<>();
	private List<Observers> listaObservers = new ArrayList<>();
	long tiempoEstipulado;
	PrintWriter writer;

	public RepositorioDePois(String unNombre, long tiempoEstipulado, PrintWriter writer) {
		this.nombre = unNombre;
		this.tiempoEstipulado= tiempoEstipulado;
		this.writer=writer;
	}

	public String getNombre() {
		return nombre;
	}

	public void agregarObserver(Observers unObserver){
		listaObservers.add(unObserver);
	}
	
	public void quitarObserver (Observers unObserver){
		listaObservers.remove(unObserver);
	}
	
	public void agregarAdaptador(Adaptadores adaptador) {
		listaAdaptadores.add(adaptador);
	}

	public void quitarAdaptador(Adaptadores adaptador) {
		listaAdaptadores.remove(adaptador);
	}

	public void agregarPoi(Poi unPoi) {
			try {
				if (!repositorioContienePoi(unPoi.getNombre())) {
					listaDePois.add(unPoi);
				}
				else{
				throw new Exception("Poi ya existente");
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
	}

	public void bajaPoi(Poi unPoi) {
		try {
			if (!repositorioContienePoi(unPoi.getNombre())) {
				listaDePois.remove(unPoi);
			}
			else{
			throw new Exception("No existe el Poi");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void modificarPoi(Poi unPoi) {
		if (repositorioContienePoi(unPoi.getNombre())) {
			Poi poiAux = listaDePois.stream().filter(poi -> poi.getNombre().equalsIgnoreCase(unPoi.getNombre()))
					.findFirst().get();
			bajaPoi(poiAux);
			agregarPoi(unPoi);
		}
	}

	private boolean repositorioContienePoi(String nombre) {
		return listaDePois.stream().anyMatch(unPoi -> unPoi.getNombre().equalsIgnoreCase(nombre));
	}

	public List<Poi> busquedaLibre(String criterio) {
		List<Poi> listaAux = new ArrayList<>();
		long diferencia;
		LocalDateTime tiempoInicio = LocalDateTime.now();
		listaAux = filtrarPorCriterio(criterio);
		LocalDateTime tiempoFin = LocalDateTime.now();
		diferencia=calcularDiferencia(tiempoInicio, tiempoFin);
		if(diferencia>tiempoEstipulado){
			listaObservers.stream().forEach(observer->observer.notificar(writer));
		}
		int cantBuscada= listaAux.size();
		listaObservers.stream().forEach(observer->observer.agregarBusqueda(diferencia,criterio,tiempoInicio,cantBuscada));
		listaDePois.addAll(listaAux);
		return listaAux;
	}
	
	public void obtenerReporteTotalPorFecha(){
		listaObservers.stream().forEach(unObserver->unObserver.reporteTotalPorFecha(writer));
	}

	public boolean consultaDisponibilidad(LocalDateTime fecha, String criterio) {
		Poi poiAux;
		poiAux = obtenerSegunCriterio(criterio);
		if(poiAux==null){
			return false;
		}
		return poiAux.estaDisponible(fecha);
	}

	public boolean consultaDisponibilidad(LocalDateTime fecha, Servicio servicio) {
		Poi poiAux = obtenerSegunCriterio(servicio.getNombre());
		if(poiAux==null){
			return false;
		}
		return poiAux.estaDisponible(fecha, servicio);
	}

	public boolean consultaDisponibilidad(LocalDateTime fecha) {
		Poi poiAux;
		poiAux=obtenerSegunCriterio(fecha.toString());
		return poiAux!=null;
	}

	public List<Poi> filtrarPorCriterio(String criterio) {
		List<Poi> listaFiltrada = listaDePois.stream().filter(unPoi -> unPoi.cumpleCriterio(criterio))
				.collect(Collectors.toList());
		if (listaFiltrada.isEmpty()) {
			listaAdaptadores.stream()
					.forEach(unComponente -> listaFiltrada.addAll((unComponente.buscarPois(criterio))));
		}
		return listaFiltrada;
	}

	public boolean consultaCercania(String criterio, Point ubicacionSolicitada) {
		Poi poi_aux;
		poi_aux = obtenerSegunCriterio(criterio);
		return poi_aux.estaCerca(ubicacionSolicitada);
	}

	public Poi obtenerSegunCriterio(String criterio) {
		try{
		Poi poiAux;
		poiAux=listaDePois.stream().filter(unPoi -> unPoi.cumpleCriterio(criterio)).findFirst().get();
		return poiAux;
		}catch(NoSuchElementException excepcion){
			return null;
		}
		
	}
	
	public long calcularDiferencia(LocalDateTime tiempoinicio, LocalDateTime tiempofin) {
		long diferencia = ChronoUnit.SECONDS.between(tiempoinicio, tiempofin);
		return diferencia;
	}
	public void reporteParcial(PrintWriter writer){
		listaObservers.stream().forEach(observer->observer.reporteParcial(writer));
	}
	public void reporteTotal(PrintWriter writer){
		listaObservers.stream().forEach(observer->observer.reporteTotal(writer));
	}
	
}
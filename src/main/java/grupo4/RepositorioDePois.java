package grupo4;

import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.geodds.Point;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class RepositorioDePois implements Busqueda {
	private String nombre;
	private List<Poi> listaDePois = new ArrayList<>();
	private List<Adaptadores> listaAdaptadores = new ArrayList<>();
	private List<Observers> listaObservers = new ArrayList<>();
	long tiempoEstipulado;
	

	public RepositorioDePois(String unNombre, long tiempoEstipulado) {
		this.nombre = unNombre;
		this.tiempoEstipulado= tiempoEstipulado;
	}

	public String getNombre() {
		return nombre;
	}

	public void agregarAdaptador(Adaptadores adaptador) {
		listaAdaptadores.add(adaptador);
	}

	public void quitarAdaptador(Adaptadores adaptador) {
		listaAdaptadores.remove(adaptador);
	}

	public void agregarPoi(Poi unPoi) {
		if (!repositorioContienePoi(unPoi.getNombre())) {
			listaDePois.add(unPoi);
		}
	}

	public void bajaPoi(Poi unPoi) {
		if (repositorioContienePoi(unPoi.getNombre())) {
			listaDePois.remove(unPoi);
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
			listaObservers.stream().forEach(observer->observer.notificar());
		}
		int cantBuscada= listaAux.size();
		listaObservers.stream().forEach(observer->observer.agregarBusqueda(diferencia,criterio,tiempoInicio,cantBuscada));
		listaDePois.addAll(listaAux);
		return listaAux;
	}
	
	public void obtenerReporteTotalPorFecha(){
		listaObservers.stream().forEach(unObserver->unObserver.reporteTotalPorFecha());
	}

	public boolean consultaDisponibilidad(LocalDateTime fecha, String criterio) {
		Poi poiAux;
		poiAux = obtenerSegunCriterio(criterio);
		return poiAux.estaDisponible(fecha);
	}

	public boolean consultaDisponibilidad(LocalDateTime fecha, Servicio servicio) {
		Poi poiAux = obtenerSegunCriterio(servicio.getNombre());
		return poiAux.estaDisponible(fecha, servicio);
	}

	public boolean consultaDisponibilidad(LocalDateTime fecha) {
		return encontrarCGPS().stream().anyMatch(unCGP -> unCGP.estaDisponible(fecha));
	}

	public List<Poi> encontrarCGPS() {// Consideramos que
		// todos los CGP por defecto tiene un formato de nombre de tipo:
		// "CGP(Nro de CGP)"
		return listaDePois.stream().filter(unPoi -> unPoi.getNombre().contains("CGP")).collect(Collectors.toList());
	}

	public List<Poi> filtrarPorCriterio(String criterio) {
		List<Poi> listaFiltrada = listaDePois.stream().filter(unPoi -> unPoi.encuentraNombre(criterio))
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
		return listaDePois.stream().filter(unPoi -> unPoi.encuentraNombre(criterio)).findFirst().get();
	}
	
	public long calcularDiferencia(LocalDateTime tiempoinicio, LocalDateTime tiempofin) {
		long diferencia = ChronoUnit.SECONDS.between(tiempoinicio, tiempofin);
		return diferencia;
	}
	
}
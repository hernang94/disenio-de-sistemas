package grupo4.Repositorios;

import java.util.List;
import java.util.stream.Collectors;
import org.uqbar.geodds.Point;
import grupo4.ComponentesExternos.Adaptadores;
import grupo4.POIs.Poi;
import grupo4.POIs.Servicio;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class RepositorioDePois{
	private static RepositorioDePois instancia= new RepositorioDePois();
	private String nombre;
	private List<Poi> listaDePois = new ArrayList<>();
	private List<Adaptadores> listaAdaptadores = new ArrayList<>();

	//public static volatile ModuleState instance = null;
	private RepositorioDePois(){
	}
	

	public  void reset() {
		listaDePois.clear();
		listaAdaptadores.clear();
	}
	
	public static RepositorioDePois getInstancia() {
		return instancia;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
		} else {
			throw new RuntimeException("Poi ya existente");
		}
	}

	public void bajaPoi(Poi unPoi) {
		if (repositorioContienePoi(unPoi.getNombre())) {
			listaDePois.remove(unPoi);
		} else {
			throw new RuntimeException("No existe el Poi");
		}
	}

	private boolean repositorioContienePoi(String nombre) {
		return listaDePois.stream().anyMatch(unPoi -> unPoi.getNombre().equalsIgnoreCase(nombre));
	}

	public List<Poi> busquedaLibre(String criterio) {
		List<Poi> listaAux = new ArrayList<>();
		listaAux = filtrarPorCriterio(criterio);
		return listaAux;
	}



	public boolean consultaDisponibilidad(LocalDateTime fecha, String criterio) {
		Poi poiAux;
		poiAux = obtenerSegunCriterio(criterio);
		if (poiAux == null) {
			return false;
		}
		return poiAux.estaDisponible(fecha);
	}

	public boolean consultaDisponibilidad(LocalDateTime fecha, Servicio servicio) {
		Poi poiAux = obtenerSegunCriterio(servicio.getNombre());
		if (poiAux == null) {
			return false;
		}
		return poiAux.estaDisponible(fecha, servicio);
	}

	public boolean consultaDisponibilidad(LocalDateTime fecha) {
		Poi poiAux;
		poiAux = obtenerSegunCriterio(fecha.toString());
		return poiAux != null;
	}

	public List<Poi> filtrarPorCriterio(String criterio) {
		List<Poi> listaFiltrada = listaDePois.stream().filter(unPoi -> unPoi.cumpleCriterio(criterio))
				.collect(Collectors.toList());
		if (listaFiltrada.isEmpty()) {
			listaAdaptadores.stream()
					.forEach(unComponente -> listaFiltrada.addAll((unComponente.buscarPois(criterio))));
			listaDePois.addAll(listaFiltrada);
		}
		return listaFiltrada;
	}

	public boolean consultaCercania(String criterio, Point ubicacionSolicitada) {
		Poi poi_aux;
		poi_aux = obtenerSegunCriterio(criterio);
		return poi_aux.estaCerca(ubicacionSolicitada);
	}

	public Poi obtenerSegunCriterio(String criterio) {
		return listaDePois.stream().filter(unPoi -> unPoi.cumpleCriterio(criterio)).findFirst().orElse(null);
	}


}
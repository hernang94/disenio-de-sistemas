package grupo4.Repositorios;

import java.util.List;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import grupo4.ComponentesExternos.BuscadorDePois;
import grupo4.HerramientasExternas.Cache;
import grupo4.HerramientasExternas.Punto;
import grupo4.POIs.Poi;
import grupo4.POIs.Servicio;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class RepositorioDePois implements WithGlobalEntityManager {
	private static RepositorioDePois instancia;
	private List<BuscadorDePois> origenesExternos = new ArrayList<>();
	
	public void reset() {
		origenesExternos.clear();
	}

	public static RepositorioDePois getInstancia() {
		if(instancia==null){
			instancia=new RepositorioDePois();
		}
		return instancia;
	}

	public void agregarOrigenExterno(BuscadorDePois origenExterno) {
		origenesExternos.add(origenExterno);
	}

	public void quitarAdaptador(BuscadorDePois adaptador) {
		origenesExternos.remove(adaptador);
	}

	public void agregarPoi(Poi unPoi) {
		if (entityManager().find(Poi.class, unPoi.getId()) != null) {
			throw new RuntimeException("Poi ya existente");
		} else {
			entityManager().persist(unPoi);
		}

	}

	public Poi obtenerPoi(int idPoi){
		return entityManager().find(Poi.class, idPoi);
	}
	public void bajaPoi(int id) {
		try {
			entityManager().getTransaction().begin();
			entityManager().remove((Poi) entityManager().find(Poi.class, id));
			entityManager().getTransaction().commit();
		} catch (Exception e) {
			throw new RuntimeException("No existe el Poi");
		}
	}

	public List<Poi> busquedaLibre(String criterio) {
		List<Poi> listaAux = new ArrayList<>();
		listaAux = filtrarPorCriterio(criterio);
		return listaAux;
	}
	public List<Poi> busquedaPorNombre(String nombre){
		return filtrarPorCriterio(nombre).stream().filter(poi->poi.getNombre().contains(nombre)).collect(Collectors.toList());
	}
	public List<Poi> busquedaPorTipo(String tipo){
		return filtrarPorCriterio(tipo).stream().filter(poi->poi.getTipo().equalsIgnoreCase(tipo)).collect(Collectors.toList());
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
		List<Poi> listaAux = new ArrayList<>();
		if(Cache.getInstancia().criterioEstaEnCache(criterio)){
			listaAux.addAll(Cache.getInstancia().obtenerPois(criterio));
		}
		listaAux.addAll(this.obtenerPoisLocales().stream().filter(unPoi -> unPoi.cumpleCriterio(criterio))
				.collect(Collectors.toList())); 
		if (listaAux.isEmpty()) {
				origenesExternos.stream()
				.forEach(unComponente -> listaAux.addAll((unComponente.buscarPois(criterio))));
		}
		return listaAux;
	}

	public boolean consultaCercania(String criterio, Punto ubicacionSolicitada) {
		Poi poi_aux;
		poi_aux = obtenerSegunCriterio(criterio);
		return poi_aux.estaCerca(ubicacionSolicitada);
	}

	public Poi obtenerSegunCriterio(String criterio) {
		List<Poi> poisEnBD = this.obtenerPoisLocales();
		return poisEnBD.stream().filter(unPoi -> unPoi.cumpleCriterio(criterio)).findFirst().orElse(null);
	}

	public void cambiarPalabrasClaves(String palabraFantasia, List<String> palabrasClaves) {
		if (repositorioContienePoi(palabraFantasia)) {
			List<Poi> poisEnBD = this.obtenerPoisLocales();
			poisEnBD.stream().forEach(poi -> poi.reemplazarPalabrasClaves(palabrasClaves));
		}
	}

	private boolean repositorioContienePoi(String palabraFantasia) {
		List<Poi> poisEnBD = this.obtenerPoisLocales();
		return poisEnBD.stream().anyMatch(unPoi -> unPoi.getNombre().equalsIgnoreCase(palabraFantasia));
	}

	@SuppressWarnings("unchecked")
	private List<Poi> obtenerPoisLocales() {
		return (List<Poi>) entityManager().createQuery("from Poi").getResultList();
	}
}
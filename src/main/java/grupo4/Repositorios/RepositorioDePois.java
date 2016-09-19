package grupo4.Repositorios;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import grupo4.ComponentesExternos.BuscadorDePois;
import grupo4.HerramientasExternas.Punto;
import grupo4.POIs.Poi;
import grupo4.POIs.Servicio;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class RepositorioDePois {
	private static RepositorioDePois instancia = new RepositorioDePois();
	private List<BuscadorDePois> origenesExternos = new ArrayList<>();
	EntityManager manager= PerThreadEntityManagers.getEntityManager();

	public void reset() {
		origenesExternos.clear();
	}

	public static RepositorioDePois getInstancia() {
		return instancia;
	}

	public void agregarOrigenExterno(BuscadorDePois origenExterno) {
		origenesExternos.add(origenExterno);
	}

	public void quitarAdaptador(BuscadorDePois adaptador) {
		origenesExternos.remove(adaptador);
	}

	public void agregarPoi(Poi unPoi) {
		//if (!repositorioContienePoi(unPoi.getId())) {
			//listaDePois.add(unPoi);
		try {
			manager.persist(unPoi);
			manager.flush();
		} catch (EntityExistsException e) {
			throw new RuntimeException("Poi ya existente");
		}
	}

	public void bajaPoi(int id) {
		/*if (repositorioContienePoi(id)) {
			listaDePois.remove(obtenerPoi(id));
		} else {
			
		}*/
		if(manager.createQuery("delete from Poi where idPoi=:id").setParameter("id", id).executeUpdate()<1){
			throw new RuntimeException("No existe el Poi");
		};
	}

/*	private boolean repositorioContienePoi(Integer id) {
		return listaDePois.stream().anyMatch(unPoi -> unPoi.getId() == id);
	}

	private Poi obtenerPoi(int id) {
		return listaDePois.stream().filter(poi -> poi.getId() == id).findFirst().get();
	}*/

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
		@SuppressWarnings("unchecked")
		List<Poi> listaAux = (List<Poi>) manager.createQuery("from Poi").getResultList();
		List<Poi> listaFiltrada= listaAux.stream().filter(unPoi -> unPoi.cumpleCriterio(criterio))
				.collect(Collectors.toList());
		if (listaFiltrada.isEmpty()) {
			origenesExternos.stream()
					.forEach(unComponente -> listaFiltrada.addAll((unComponente.buscarPois(criterio))));
			listaFiltrada.forEach(unPoi->manager.persist(unPoi));
			manager.flush();
		}
		return listaFiltrada;
	}

	public boolean consultaCercania(String criterio, Punto ubicacionSolicitada) {
		Poi poi_aux;
		poi_aux = obtenerSegunCriterio(criterio);
		return poi_aux.estaCerca(ubicacionSolicitada);
	}

	public Poi obtenerSegunCriterio(String criterio) {
		List<Poi> poisEnBD= this.consultarBD();
		return poisEnBD.stream().filter(unPoi -> unPoi.cumpleCriterio(criterio)).findFirst().orElse(null);
	}

	public void cambiarPalabrasClaves(String palabraFantasia, List<String> palabrasClaves) {
		if (repositorioContienePoi(palabraFantasia)) {
			List<Poi> poisEnBD= this.consultarBD();
			poisEnBD.stream().forEach(poi -> poi.reemplazarPalabrasClaves(palabrasClaves));
			poisEnBD.stream().forEach(poi -> manager.merge(poi));
		}
	}

	private boolean repositorioContienePoi(String palabraFantasia) {
		List<Poi> poisEnBD= this.consultarBD();
		return poisEnBD.stream().anyMatch(unPoi -> unPoi.getNombre().equalsIgnoreCase(palabraFantasia));
	}
	
	@SuppressWarnings("unchecked")
	private List<Poi> consultarBD(){
		return (List<Poi>) manager.createQuery("from Poi").getResultList();
	}

}
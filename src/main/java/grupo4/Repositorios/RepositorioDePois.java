package grupo4.Repositorios;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.eclipse.xtend.lib.macro.services.GlobalTypeLookup;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import grupo4.ComponentesExternos.BuscadorDePois;
import grupo4.HerramientasExternas.Punto;
import grupo4.POIs.Poi;
import grupo4.POIs.Servicio;
import redis.clients.jedis.JedisPool;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class RepositorioDePois implements WithGlobalEntityManager {
	private static RepositorioDePois instancia = new RepositorioDePois();
	private List<BuscadorDePois> origenesExternos = new ArrayList<>();
	private ObjectMapper objectMapper= new ObjectMapper();
	
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
		if (entityManager().find(Poi.class, unPoi.getId()) != null) {
			throw new RuntimeException("Poi ya existente");
		} else {
			entityManager().persist(unPoi);
		}

	}

	public void bajaPoi(int id) {
		try {
			entityManager().remove((Poi) entityManager().find(Poi.class, id));
		} catch (Exception e) {
			throw new RuntimeException("No existe el Poi");
		}
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
		List<Poi> listaAux = this.consultarBD();
		List<Poi> listaFiltrada = listaAux.stream().filter(unPoi -> unPoi.cumpleCriterio(criterio))
				.collect(Collectors.toList());
		if (listaFiltrada.isEmpty()) {
			if(poisEstanEnCache(criterio)){
				listaFiltrada.addAll(getPoisEnCache(criterio));
			}
			else{
				origenesExternos.stream()
				.forEach(unComponente -> listaFiltrada.addAll((unComponente.buscarPois(criterio))));
				actualizarCache(criterio,listaFiltrada);
			}
		}
		return listaFiltrada;
	}

	private void actualizarCache(String criterio, List<Poi> listaFiltrada) {
		try {
			String poisDeServiciosExternos = objectMapper.writeValueAsString(listaFiltrada);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private List<Poi> getPoisEnCache(String criterio) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean poisEstanEnCache(String criterio) {
		
		return false;
	}

	public boolean consultaCercania(String criterio, Punto ubicacionSolicitada) {
		Poi poi_aux;
		poi_aux = obtenerSegunCriterio(criterio);
		return poi_aux.estaCerca(ubicacionSolicitada);
	}

	public Poi obtenerSegunCriterio(String criterio) {
		List<Poi> poisEnBD = this.consultarBD();
		return poisEnBD.stream().filter(unPoi -> unPoi.cumpleCriterio(criterio)).findFirst().orElse(null);
	}

	public void cambiarPalabrasClaves(String palabraFantasia, List<String> palabrasClaves) {
		if (repositorioContienePoi(palabraFantasia)) {
			List<Poi> poisEnBD = this.consultarBD();
			poisEnBD.stream().forEach(poi -> poi.reemplazarPalabrasClaves(palabrasClaves));
		}
	}

	private boolean repositorioContienePoi(String palabraFantasia) {
		List<Poi> poisEnBD = this.consultarBD();
		return poisEnBD.stream().anyMatch(unPoi -> unPoi.getNombre().equalsIgnoreCase(palabraFantasia));
	}

	@SuppressWarnings("unchecked")
	private List<Poi> consultarBD() {
		return (List<Poi>) entityManager().createQuery("from Poi").getResultList();
	}
}
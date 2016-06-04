package grupo4;

import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.geodds.Point;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class RepositorioDePois {
	private List<Poi> listaDePois = new ArrayList<>();
	private List<Componente> listaComponentes= new ArrayList<>();
	
	public void setBancoTransformer(BancoTransformer bancoAdaptador){
		this.bancoAdaptador=bancoAdaptador;
	}

	public void setAdaptador(CGPAdapter adaptador){
		this.adaptador=adaptador;
	}
	public void agregarPoi(Poi unPoi) {
		if(!repositorioContienePoi(unPoi.getNombre())){			
			listaDePois.add(unPoi);
		}
	}

	public void bajaPoi(Poi unPoi) {
		if(repositorioContienePoi(unPoi.getNombre())){			
			listaDePois.remove(unPoi);
		}
	}
	
	public void modificarPoi(Poi unPoi){
		if(repositorioContienePoi(unPoi.getNombre())){
			Poi poiAux=listaDePois.stream().filter(poi->poi.getNombre().equalsIgnoreCase(unPoi.getNombre())).findFirst().get();
			bajaPoi(poiAux);
			agregarPoi(unPoi);
		}
	}
	
	private boolean repositorioContienePoi(String nombre) {
		return listaDePois.stream().anyMatch(unPoi->unPoi.getNombre().equalsIgnoreCase(nombre));
	}

	public List<Poi> busquedaLibre(String criterio) {
		List<Poi> listaAux = new ArrayList<>();
		listaAux = filtrarPorCriterio(criterio);
		listaDePois.addAll(listaAux);
		return listaAux;
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
//todos los CGP por defecto tiene un formato de nombre de tipo: "CGP(Nro de CGP)"
		return listaDePois.stream().filter(unPoi -> unPoi.getNombre().contains("CGP")).collect(Collectors.toList());
	}

	public List<Poi> filtrarPorCriterio(String criterio) {
		List<Poi> listaFiltrada= listaDePois.stream().filter(unPoi -> unPoi.encuentraNombre(criterio)).collect(Collectors.toList());
		if(listaFiltrada.isEmpty()){
			listaComponentes.stream().forEach(unComponente-> listaFiltrada.addAll((unComponente.buscarPois(criterio))));
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
}

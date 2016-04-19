package grupo4;

import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.geodds.Point;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Terminal {
	List<Poi> lista_de_pois = new ArrayList<>();
	Point ubicacion_terminal;
	
	public void setUnbicacionTerminal(Point ubicacion){
		this.ubicacion_terminal=ubicacion;
	}
	
	public void agregarPoi(Poi unPoi){
		lista_de_pois.add(unPoi);
	}

	public List<Poi> busquedaLibre(String criterio) {
		List<Poi> lista_aux = new ArrayList<>();
		lista_aux = filtrarPorCriterio(criterio);
		return lista_aux;
	}

	public boolean consultaDisponibilidad(LocalDateTime fecha, String x) {
		Poi poi_aux;
		poi_aux = obtenerSegunCriterio(x);
		return poi_aux.estaDisponible(fecha);
	}

	public List<Poi> filtrarPorCriterio(String criterio) {
		return lista_de_pois.stream().filter(unPoi -> unPoi.coincideCon(criterio)).collect(Collectors.toList());
	}

	public boolean consultaCercania(Point punto, String x) {
		Poi poi_aux;
		poi_aux = obtenerSegunCriterio(x);
		return poi_aux.estaCerca(punto);

	}

	public Poi obtenerSegunCriterio(String criterio) {
		return lista_de_pois.stream().filter(unPoi -> unPoi.coincideCon(criterio)).findFirst().get();
	}
}

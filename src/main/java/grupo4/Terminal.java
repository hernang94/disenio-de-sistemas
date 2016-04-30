package grupo4;

import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.geodds.Point;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Terminal {
	private List<Poi> listaDePois = new ArrayList<>();
	private Point ubicacionTerminal;

	public void setUnbicacionTerminal(Point ubicacion) {
		this.ubicacionTerminal = ubicacion;
	}

	public void agregarPoi(Poi unPoi) {
		listaDePois.add(unPoi);
	}

	public List<Poi> busquedaLibre(String criterio) {
		List<Poi> listaAux = new ArrayList<>();
		listaAux = filtrarPorCriterio(criterio);
		return listaAux;
	}

	public boolean consultaDisponibilidad(LocalDateTime fecha, String x) {
		Poi poiAux;
		poiAux = obtenerSegunCriterio(x);
		return poiAux.estaDisponible(fecha);
	}

	public boolean consultaDisponibilidad(LocalDateTime fecha) {// Consideramos
																// que todos los
																// CGP por
																// defecto tiene
																// un formato de
																// nombre de
																// tipo:
																// "CGP(Nro de
																// CGP)"
		List<Poi> lista_CGP = listaDePois.stream().filter(unPoi -> unPoi.getNombre().contains("CGP"))
				.collect(Collectors.toList());
		return lista_CGP.stream().anyMatch(unCGP -> unCGP.estaDisponible(fecha));
	}

	public List<Poi> filtrarPorCriterio(String criterio) {
		return listaDePois.stream().filter(unPoi -> unPoi.coincideCon(criterio)).collect(Collectors.toList());
	}

	public boolean consultaCercania(String x) {
		Poi poi_aux;
		poi_aux = obtenerSegunCriterio(x);
		return poi_aux.estaCerca(this.ubicacionTerminal);

	}

	public Poi obtenerSegunCriterio(String criterio) {
		return listaDePois.stream().filter(unPoi -> unPoi.coincideCon(criterio)).findFirst().get();
	}
}

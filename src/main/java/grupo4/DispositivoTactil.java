package grupo4;

import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.geodds.Point;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DispositivoTactil {
	private List<Poi> listaDePois = new ArrayList<>();

	public void agregarPoi(Poi unPoi) {
		listaDePois.add(unPoi);
	}

	public void altaBanco(String nombre,String servicio){
		List<Banco>listaAux=new BancoTransformer().obternerBancos(nombre, servicio);
		listaAux.stream().forEach(banco->aniadirCriteriosoBanco(banco));
	}
	
	public void modificacionBanco(String nombre, String servicio){
		List<Banco>listaAux=new BancoTransformer().obternerBancos(nombre, servicio);
		listaAux.stream().forEach(banco->modificarCriteriosoBanco(banco));
	}
	
	private void modificarCriteriosoBanco(Banco banco) {
		List<Poi> listaAux = listaDePois.stream().filter(bancoAux -> bancoAux.getNombre().equalsIgnoreCase(banco.getNombre()))
				.collect(Collectors.toList());
		if (!(listaAux.isEmpty())) {
			bajaPoi(listaAux.get(0));
			agregarPoi(banco);
		}
	}

	private void aniadirCriteriosoBanco(Banco banco) {
		if (!(listaDePois.stream().anyMatch(bancoAux -> bancoAux.getNombre().equalsIgnoreCase(banco.getNombre())))) {
			listaDePois.add(banco);
		}
	}

	public void altaCGP(String criterio) {
		List<CGP> listaAux = new CGPAdapter().solicitarCGPs(criterio);
		listaAux.stream().forEach(cgp -> aniadirCriterioso(cgp));
	}
	
	public void modificacionCGP(String criterio) {
		List<CGP> listaAux = new CGPAdapter().solicitarCGPs(criterio);
		listaAux.stream().forEach(cgp -> modificarCriterioso(cgp));
	}

	private void modificarCriterioso(CGP cgp) {
		List<Poi> listaAux = listaDePois.stream().filter(cgpAux -> cgpAux.getNombre().equalsIgnoreCase(cgp.getNombre()))
				.collect(Collectors.toList());
		if (!(listaAux.isEmpty())) {
			bajaPoi(listaAux.get(0));
			agregarPoi(cgp);
		}
	}

	private void aniadirCriterioso(CGP cgp) {
		if (!(listaDePois.stream().anyMatch(cgpAux -> cgpAux.getNombre().equalsIgnoreCase(cgp.getNombre())))) {
			listaDePois.add(cgp);
		}
	}

	public void bajaPoi(Poi unPoi) {
		listaDePois.remove(unPoi);
	}

	public List<Poi> busquedaLibre(String criterio) {
		List<Poi> listaAux = new ArrayList<>();
		listaAux = filtrarPorCriterio(criterio);
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

	public List<Poi> encontrarCGPS() {// Consideramos que todos los CGP por
										// defecto tiene un formato de nombre de
										// tipo: "CGP(Nro de CGP)"
		return listaDePois.stream().filter(unPoi -> unPoi.getNombre().contains("CGP")).collect(Collectors.toList());
	}

	public List<Poi> filtrarPorCriterio(String criterio) {
		return listaDePois.stream().filter(unPoi -> unPoi.encuentraNombre(criterio)).collect(Collectors.toList());
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

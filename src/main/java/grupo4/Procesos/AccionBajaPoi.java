package grupo4.Procesos;

import java.time.LocalDateTime;
import java.util.List;

import DTOexterno.BajaPoiExterna;
import grupo4.ComponentesExternos.BajaPoiAdapter;
import grupo4.Repositorios.RepositorioDePois;
import grupo4.Repositorios.ResultadosDeEjecucion;

public class AccionBajaPoi implements Accion {
	private BajaPoiAdapter adaptador;

	public AccionBajaPoi(BajaPoiAdapter adaptador) {
		this.adaptador = adaptador;
	}

	public ResultadosDeEjecucion ejecutar() {
		return bajarPois();
	}

	public ResultadosDeEjecucion bajarPois() {
		List<BajaPoiExterna> lista = adaptador.obtenerPoisABajar();
		lista.stream().forEach(elemento -> bajarPoi(elemento));
		return new ResultadosDeEjecucion(lista.size(), LocalDateTime.now(), "Pois eliminados con exito");
	}

	public void bajarPoi(BajaPoiExterna bajaPoi) {
		RepositorioDePois.getInstancia().bajaPoi(bajaPoi.getId());
	}

}

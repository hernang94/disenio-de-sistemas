package grupo4.Procesos;

import java.time.LocalDateTime;
import java.util.List;

import DTOexterno.BajaPoiExterna;
import grupo4.ComponentesExternos.BajaPoiAdapter;
import grupo4.Repositorios.RepositorioDePois;
import grupo4.Repositorios.RepositorioDeResultadosDeEjecucion;
import grupo4.Repositorios.ResultadosDeEjecucion;

public class AccionBajaPoi implements Accion {
	private BajaPoiAdapter adaptador;

	public AccionBajaPoi(BajaPoiAdapter adaptador) {
		this.adaptador = adaptador;
	}

	public void ejecutar() {
		bajarPois();
	}

	public void bajarPois() {
		List<BajaPoiExterna> lista = adaptador.obtenerPoisABajar();
		lista.stream().forEach(elemento -> bajarPoi(elemento));
	}

	public void bajarPoi(BajaPoiExterna bajaPoi) {
		String fecha = bajaPoi.getFecha().substring(0, (bajaPoi.getFecha().length()) - 1);
		RepositorioDePois.getInstancia().bajaPoi(bajaPoi.getId());
		RepositorioDeResultadosDeEjecucion.getInstancia()
		.agregarResultado(new ResultadosDeEjecucion(1, LocalDateTime.parse(fecha), "Poi eliminado con exito"));
	}

}

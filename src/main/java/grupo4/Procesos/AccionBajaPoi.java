package grupo4.Procesos;

import java.time.LocalDateTime;
import java.util.List;

import DTOexterno.BajaPoiExterna;
import grupo4.ComponentesExternos.BajaPoiAdapter;
import grupo4.Repositorios.RepositorioDePois;
import grupo4.Repositorios.RepositorioDeResultadosDeEjecucion;
import grupo4.Repositorios.ResultadosDeEjecucion;

public class AccionBajaPoi implements Accion {
	private RepositorioDePois repo;
	private BajaPoiAdapter adaptador;

	public AccionBajaPoi(RepositorioDePois repositorio, BajaPoiAdapter adaptador) {
		this.repo = repositorio;
		this.adaptador = adaptador;
	}

	public boolean ejecutar() {
		try{
			bajarPois();
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	public void bajarPois() {
		List<BajaPoiExterna> lista = adaptador.obtenerPoisABajar();
		lista.stream().forEach(elemento -> evaluarYBajarPoi(elemento));
	}
//No tiene que parsear el json que te devuelve 400(que tire antes el runTime
	public void evaluarYBajarPoi(BajaPoiExterna bajaPoi) {
		if (bajaPoi.getId() == 400) {
			RepositorioDeResultadosDeEjecucion.getInstancia()
					.agregarResultado(new ResultadosDeEjecucion(1, LocalDateTime.now(), "Error de Baja de Poi"));

		} else {
			String fecha = bajaPoi.getFecha().substring(0, (bajaPoi.getFecha().length()) - 1);
			RepositorioDeResultadosDeEjecucion.getInstancia().agregarResultado(
					new ResultadosDeEjecucion(1, LocalDateTime.parse(fecha), "Poi eliminado con exito"));
			repo.bajaPoi(bajaPoi.getId());
		}
	}
}

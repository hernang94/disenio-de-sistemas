package grupo4.Procesos;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import DTOexterno.BajaPoiExterna;
import grupo4.ComponentesExternos.BajaPoiAdapter;
import grupo4.Repositorios.RepositorioDePois;
import grupo4.Repositorios.RepositorioDeResultadosDeEjecucion;
import grupo4.Repositorios.ResultadosDeEjecucion;

public class AccionBajaPoi implements Accion {
	private RepositorioDePois repo;

	public AccionBajaPoi(RepositorioDePois repositorio) {
		this.repo = repositorio;
	}

	public void ejecutar() {
		bajarPois();
	}

	public void bajarPois() {
		int cantidadDeErrores = 0;
		ObjectMapper objectMapper = new ObjectMapper();
		BajaPoiAdapter adaptador = new BajaPoiAdapter(objectMapper);
		List<BajaPoiExterna> lista = adaptador.obtenerPoisABajar();
		lista.stream().forEach(elemento -> evaluarYBajarPoi(elemento, cantidadDeErrores));

	}

	public void evaluarYBajarPoi(BajaPoiExterna bajaPoi, int cantidadDeErrores) {
		if (bajaPoi.getId() == 400) {
			RepositorioDeResultadosDeEjecucion.getInstancia()
					.agregarResultado(new ResultadosDeEjecucion(1, LocalDateTime.now(), "Error de Baja de Poi"));
			return;
		}
		String fecha = bajaPoi.getFecha().substring(0, bajaPoi.getFecha().length() - 1);
		RepositorioDeResultadosDeEjecucion.getInstancia()
				.agregarResultado(new ResultadosDeEjecucion(1, LocalDateTime.parse(fecha), "Poi eliminado con exito"));
		repo.bajaPoi(bajaPoi.getId());
	}
}

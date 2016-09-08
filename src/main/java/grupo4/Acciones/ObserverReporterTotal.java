package grupo4.Acciones;

import grupo4.Repositorios.RepositorioDeBusquedas;
import grupo4.Repositorios.RepositorioDeReportes;
import grupo4.Repositorios.ResultadoDeBusqueda;

public class ObserverReporterTotal extends ObserverDeBusqueda {
	private IdObserver id = IdObserver.REPORTERTOTAL;

	@Override
	public void realizarAccion(ResultadoDeBusqueda resultado) {
		ReporteTotal reporte = new ReporteTotal(resultado.getTerminalDeLaBusqueda(),
				RepositorioDeBusquedas.getInstancia().reporteTotal());
		RepositorioDeReportes.getInstancia().agregarReporte(reporte);
	}

	public IdObserver getId() {
		return id;
	}

}

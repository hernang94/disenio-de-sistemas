package grupo4.Acciones;

import grupo4.Repositorios.RepositorioDeBusquedas;
import grupo4.Repositorios.RepositorioDeReportes;
import grupo4.Repositorios.ResultadoDeBusqueda;

public class ObserverReporterPorFecha extends ObserverDeBusqueda {
	private IdObserver id = IdObserver.REPORTERFECHA;

	@Override
	public void realizarAccion(ResultadoDeBusqueda resultado) {
		ReportePorFecha reporte = new ReportePorFecha(resultado.getTerminalDeLaBusqueda(),
				RepositorioDeBusquedas.getInstancia().getListaFechaCant(resultado.getTerminalDeLaBusqueda()));
		RepositorioDeReportes.getInstancia().agregarReporte(reporte);
	}

	public IdObserver getId() {
		return id;
	}

}

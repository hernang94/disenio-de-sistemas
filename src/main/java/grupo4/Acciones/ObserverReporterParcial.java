package grupo4.Acciones;

import grupo4.Repositorios.RepositorioDeBusquedas;
import grupo4.Repositorios.RepositorioDeReportes;
import grupo4.Repositorios.ResultadoDeBusqueda;

public class ObserverReporterParcial extends ObserverDeBusqueda {
	private IdObserver id = IdObserver.REPORTERPARCIAL;

	public IdObserver getId() {
		return id;
	}

	public void realizarAccion(ResultadoDeBusqueda resultado) {
		Reporte reporteParcial = new ReporteParcial(resultado.getTerminalDeLaBusqueda(),
				RepositorioDeBusquedas.getInstancia().getlistaBusquedas(resultado.getTerminalDeLaBusqueda()));
		RepositorioDeReportes.getInstancia().agregarReporte(reporteParcial);
	}

}

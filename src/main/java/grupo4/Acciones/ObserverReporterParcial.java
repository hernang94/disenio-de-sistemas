package grupo4.Acciones;

import grupo4.Repositorios.RepositorioDeBusquedas;
import grupo4.Repositorios.RepositorioDeReportes;
import grupo4.Repositorios.ResultadosDeBusquedas;

public class ObserverReporterParcial implements Observers {
	private EnumObservers id = EnumObservers.REPORTERPARCIAL;

	public ObserverReporterParcial() {
	}

	public EnumObservers getId() {
		return id;
	}

	public void realizarAccion(ResultadosDeBusquedas resultado) {
		Reporte reporteParcial = new ReporteParcial(resultado.getTerminalDeLaBusqueda(),
				RepositorioDeBusquedas.getInstancia().getlistaBusquedas(resultado.getTerminalDeLaBusqueda()));
		RepositorioDeReportes.getInstancia().agregarReporte(reporteParcial);
	}

}

package grupo4.Acciones;

import grupo4.Repositorios.RepositorioDeBusquedas;
import grupo4.Repositorios.RepositorioDeReportes;
import grupo4.Repositorios.ResultadosDeBusquedas;

public class ObserverReporterPorFecha implements Observers {
	private EnumObservers id = EnumObservers.REPORTERFECHA;

	@Override
	public void realizarAccion(ResultadosDeBusquedas resultado) {
		ReportePorFecha reporte = new ReportePorFecha(resultado.getTerminalDeLaBusqueda(),
				RepositorioDeBusquedas.getInstancia().getListaFechaCant(resultado.getTerminalDeLaBusqueda()));
		RepositorioDeReportes.getInstancia().agregarReporte(reporte);
	}

	public EnumObservers getId() {
		return id;
	}

}

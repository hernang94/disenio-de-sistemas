package grupo4.Acciones;

import grupo4.Repositorios.RepositorioDeBusquedas;
import grupo4.Repositorios.RepositorioDeReportes;
import grupo4.Repositorios.ResultadosDeBusquedas;

public class ObserverReporterTotal implements Observers {
	private EnumObservers id = EnumObservers.REPORTERTOTAL;

	@Override
	public void realizarAccion(ResultadosDeBusquedas resultado) {
		ReporteTotal reporte = new ReporteTotal(resultado.getTerminalDeLaBusqueda(),
				RepositorioDeBusquedas.getInstancia().reporteTotal());
		RepositorioDeReportes.getInstancia().agregarReporte(reporte);
	}

	public EnumObservers getId() {
		return id;
	}

}

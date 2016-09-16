package grupo4.Acciones;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import grupo4.Repositorios.RepositorioDeBusquedas;
import grupo4.Repositorios.RepositorioDeReportes;
import grupo4.Repositorios.ResultadoDeBusqueda;
@Entity
@DiscriminatorValue("REPORTE_FECHA")
public class ObserverReporterPorFecha extends ObserverDeBusqueda {



	public ObserverReporterPorFecha() {
		id = IdObserver.REPORTERFECHA;
	}

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

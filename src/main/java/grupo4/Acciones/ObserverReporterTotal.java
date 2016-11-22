package grupo4.Acciones;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import grupo4.Repositorios.RepositorioDeBusquedas;
import grupo4.Repositorios.RepositorioDeReportes;
import grupo4.Repositorios.ResultadoDeBusqueda;

@Entity
@DiscriminatorValue("REPORTE_TOTAL")
public class ObserverReporterTotal extends ObserverDeBusqueda {

	public ObserverReporterTotal() {
		id = IdObserver.REPORTERTOTAL;
	}

	@Override
	public void realizarAccion(ResultadoDeBusqueda resultado) {
		ReporteTotal reporte = new ReporteTotal(resultado.getTerminalDeLaBusqueda(),
				RepositorioDeBusquedas.getInstancia().reporteTotal());
		RepositorioDeReportes.getInstancia().agregarReporte(reporte);
	}

	public IdObserver getId() {
		return id;
	}
	public String getTipo(){
		return "Reporte Total";
	}
}

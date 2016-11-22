package grupo4.Acciones;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import grupo4.Repositorios.RepositorioDeBusquedas;
import grupo4.Repositorios.RepositorioDeReportes;
import grupo4.Repositorios.ResultadoDeBusqueda;

@Entity
@DiscriminatorValue("REPORTE_PARCIAL")
public class ObserverReporterParcial extends ObserverDeBusqueda {
	
	public ObserverReporterParcial() {
		id = IdObserver.REPORTERPARCIAL;
	}

	public IdObserver getId() {
		return id;
	}

	public void realizarAccion(ResultadoDeBusqueda resultado) {
		Reporte reporteParcial = new ReporteParcial(resultado.getTerminalDeLaBusqueda(),
				RepositorioDeBusquedas.getInstancia().getlistaBusquedas(resultado.getTerminalDeLaBusqueda()));
		RepositorioDeReportes.getInstancia().agregarReporte(reporteParcial);
	}

	public String getTipo(){
		return "Reporte Parcial";
	}
}

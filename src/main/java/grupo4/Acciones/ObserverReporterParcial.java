package grupo4.Acciones;

import java.time.LocalDateTime;

import grupo4.Repositorios.Reporte;
import grupo4.Repositorios.RepositorioDeBusquedas;
import grupo4.Repositorios.RepositorioDeReportes;
import grupo4.Repositorios.ResultadosDeBusquedas;

public class ObserverReporterParcial implements Observers {
	private EnumObservers id=EnumObservers.REPORTERPARCIAL;
	public ObserverReporterParcial() {
	}

	
	public EnumObservers getId() {
		return id;
	}


	public void realizarAccion(ResultadosDeBusquedas resultado) {
		RepositorioDeBusquedas.getInstancia().getlistaBusquedas();
	}

}

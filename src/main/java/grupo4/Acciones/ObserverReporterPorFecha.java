package grupo4.Acciones;

import java.time.LocalDateTime;

import grupo4.Repositorios.RepositorioDeBusquedas;
import grupo4.Repositorios.ResultadosDeBusquedas;

public class ObserverReporterPorFecha implements Observers {
	private EnumObservers id=EnumObservers.REPORTERFECHA;
	@Override
	public void realizarAccion(ResultadosDeBusquedas resultado) {
		RepositorioDeBusquedas.getInstancia().getListaFechaCant(resultado.getTerminalDeLaBusqueda());
	
	}
	public EnumObservers getId() {
		return id;
	}

	
	
}

package grupo4.Acciones;


import grupo4.Repositorios.RepositorioDeBusquedas;
import grupo4.Repositorios.ResultadosDeBusquedas;

public class ObserverReporterTotal implements Observers {
	private EnumObservers id=EnumObservers.REPORTERTOTAL;
	@Override
	public void realizarAccion(ResultadosDeBusquedas resultado) {
		RepositorioDeBusquedas.getInstancia().reporteTotal();
	}
	public EnumObservers getId() {
		return id;
	}

	
	
}

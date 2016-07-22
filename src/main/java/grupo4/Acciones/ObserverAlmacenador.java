package grupo4.Acciones;

import grupo4.Repositorios.RepositorioDeBusquedas;
import grupo4.Repositorios.ResultadosDeBusquedas;

public class ObserverAlmacenador implements Observers {
	private EnumObservers id = EnumObservers.ALMACENADOR;

	public void realizarAccion(ResultadosDeBusquedas resultado) {
		RepositorioDeBusquedas.getInstancia().agregarBusqueda(resultado);
	}

	public EnumObservers getId() {
		return id;
	}

}

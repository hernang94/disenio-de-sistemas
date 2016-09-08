package grupo4.Acciones;

import grupo4.Repositorios.RepositorioDeBusquedas;
import grupo4.Repositorios.ResultadoDeBusqueda;

public class ObserverAlmacenador implements ObserverDeBusqueda {
	private IdObserver id = IdObserver.ALMACENADOR;

	public void realizarAccion(ResultadoDeBusqueda resultado) {
		RepositorioDeBusquedas.getInstancia().agregarBusqueda(resultado);
	}

	public IdObserver getId() {
		return id;
	}

}

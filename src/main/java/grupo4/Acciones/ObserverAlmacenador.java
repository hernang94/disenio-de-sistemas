package grupo4.Acciones;

import grupo4.Repositorios.RepositorioDeBusquedas;
import grupo4.Repositorios.ResultadoDeBusqueda;

public class ObserverAlmacenador extends ObserverDeBusqueda {

	public void realizarAccion(ResultadoDeBusqueda resultado) {
		RepositorioDeBusquedas.getInstancia().agregarBusqueda(resultado);
		id = IdObserver.ALMACENADOR;
	}

	public IdObserver getId() {
		return id;
	}

}

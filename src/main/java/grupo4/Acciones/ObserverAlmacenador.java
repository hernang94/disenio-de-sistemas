package grupo4.Acciones;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import grupo4.Repositorios.RepositorioDeBusquedas;
import grupo4.Repositorios.ResultadoDeBusqueda;
@Entity
@DiscriminatorValue("BUSQUEDA")
public class ObserverAlmacenador extends ObserverDeBusqueda {

	public void realizarAccion(ResultadoDeBusqueda resultado) {
		RepositorioDeBusquedas.getInstancia().agregarBusqueda(resultado);
		id = IdObserver.ALMACENADOR;
	}

	public IdObserver getId() {
		return id;
	}

}

package grupo4.Acciones;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import grupo4.Repositorios.RepositorioDeBusquedas;
import grupo4.Repositorios.ResultadoDeBusqueda;

@Entity
@DiscriminatorValue(value = "ALMACENADOR")
public class ObserverAlmacenador extends ObserverDeBusqueda {

	public ObserverAlmacenador() {
		super.id = IdObserver.ALMACENADOR;
	}

	public void realizarAccion(ResultadoDeBusqueda resultado) {
		RepositorioDeBusquedas.getInstancia().agregarBusqueda(resultado);
	}

	public IdObserver getId() {
		return id;
	}

}

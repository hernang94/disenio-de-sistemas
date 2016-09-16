package grupo4.Acciones;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import grupo4.Repositorios.RepositorioDeBusquedas;
import grupo4.Repositorios.ResultadoDeBusqueda;
@Entity
@DiscriminatorValue(value="ALMACENADOR")
public class ObserverAlmacenador extends ObserverDeBusqueda {

	public void realizarAccion(ResultadoDeBusqueda resultado) {
		RepositorioDeBusquedas.getInstancia().agregarBusqueda(resultado);
		id = IdObserver.ALMACENADOR;
	}

	public IdObserver getId() {
		return id;
	}

}

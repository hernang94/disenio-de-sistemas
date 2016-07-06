package grupo4.Procesos;

import grupo4.Acciones.ObserverAlmacenador;
import grupo4.Repositorios.RepositorioDeBusquedas;

public class AgregarAlmacenar extends AccionGestionObserver {

	public AgregarAlmacenar(Criterio criterio) {
		super(criterio);
	}

	public void ejecutar() {
		ObserverAlmacenador observer = new ObserverAlmacenador(RepositorioDeBusquedas.getInstancia());
		agregarAccion(observer, criterio.obtenerLista());

	}

}

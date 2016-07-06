package grupo4.Procesos;

import grupo4.Acciones.ObserverReporter;

import grupo4.Repositorios.RepositorioDeBusquedas;

public class AgregarReportar extends AccionGestionObserver {

	public AgregarReportar(Criterio criterio) {
		super(criterio);
	}

	public void ejecutar() {
		ObserverReporter observer = new ObserverReporter(RepositorioDeBusquedas.getInstancia());
		agregarAccion(observer, criterio.obtenerLista());
	}

}

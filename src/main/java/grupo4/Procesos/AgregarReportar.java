package grupo4.Procesos;

import grupo4.Acciones.ObserverReporterParcial;

import grupo4.Repositorios.RepositorioDeBusquedas;

public class AgregarReportar extends AccionGestionObserver {

	public AgregarReportar(Criterio criterio) {
		super(criterio);
	}

	public void ejecutar() {
		ObserverReporterParcial observer = new ObserverReporterParcial();
		agregarAccion(observer, criterio.obtenerLista());
	}

}

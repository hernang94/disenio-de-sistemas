package grupo4.Procesos;

import grupo4.Acciones.ObserverReporter;

public class QuitarReportar extends AccionGestionObserver {

	public QuitarReportar(Criterio criterio) {
		super(criterio);
	}

	public void ejecutar() {
		quitarAccion(ObserverReporter.class, criterio.obtenerLista());
	}
}

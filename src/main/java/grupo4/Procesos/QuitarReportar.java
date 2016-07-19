package grupo4.Procesos;

import grupo4.Acciones.ObserverReporterParcial;

public class QuitarReportar extends AccionGestionObserver {

	public QuitarReportar(Criterio criterio) {
		super(criterio);
	}

	public void ejecutar() {
		quitarAccion(ObserverReporterParcial.class, criterio.obtenerLista());
	}
}

package grupo4.Procesos;


import grupo4.Acciones.ObserverNotificador;

public class QuitarNotificar extends AccionGestionObserver{
	
	public QuitarNotificar(Criterio criterio) {
		super(criterio);
	}
	@Override
	public void ejecutar() {
		quitarAccion(ObserverNotificador.class, criterio.obtenerLista());
	}
	
}

package grupo4.Procesos;

import grupo4.Acciones.ObserverNotificador;
import grupo4.ComponentesExternos.EmailSender;

public class AgregarNotificar extends AccionGestionObserver {

	private Long tiempoEstipulado;
	private EmailSender notificador;
	public AgregarNotificar(Criterio criterio,long tiempoEstipulado,EmailSender notificador) {
		super(criterio);
		this.tiempoEstipulado=tiempoEstipulado;
		this.notificador=notificador;
	}



	public void ejecutar() {
		ObserverNotificador observer = new ObserverNotificador(tiempoEstipulado, notificador);
		agregarAccion(observer, criterio.obtenerLista());
	}
}

package grupo4.Acciones;

import grupo4.ComponentesExternos.EmailSender;
import grupo4.Repositorios.ResultadoDeBusqueda;

public class ObserverNotificador extends ObserverDeBusqueda {

	private long tiempoEstipulado;
	private EmailSender notificador;

	public ObserverNotificador(long tiempoEstipulado, EmailSender notificador) {
		this.tiempoEstipulado = tiempoEstipulado;
		this.notificador = notificador;
		id = IdObserver.NOTIFICADOR;
	}

	public void evaluarNotificacion(long diferencia) {
		if (diferencia > tiempoEstipulado) {
			notificador.enviarMail("Tiempo de busqueda mayor al estipulado");
		} else {
			throw new RuntimeException("Tiempo de busqueda menor al estipulado");
		}
	}

	public IdObserver getId() {
		return id;
	}

	@Override
	public void realizarAccion(ResultadoDeBusqueda resultado) {
		evaluarNotificacion(resultado.getTiempoDeBusqueda());
	}
}

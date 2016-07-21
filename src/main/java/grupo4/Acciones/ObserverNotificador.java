package grupo4.Acciones;


import grupo4.ComponentesExternos.EmailSender;
import grupo4.Repositorios.ResultadosDeBusquedas;

public class ObserverNotificador implements Observers {

	private long tiempoEstipulado;
	private EmailSender notificador;
	private EnumObservers id=EnumObservers.NOTIFICADOR;
	public ObserverNotificador(long tiempoEstipulado, EmailSender notificador) {
		this.tiempoEstipulado = tiempoEstipulado;
		this.notificador = notificador;
	}

	public void evaluarNotificacion(long diferencia) {
		if (diferencia > tiempoEstipulado) {
			notificador.enviarMail("Tiempo de busqueda mayor al estipulado");
		} else {
			throw new RuntimeException("Tiempo de busqueda menor al estipulado");
		}
	}

	
	
	public EnumObservers getId() {
		return id;
	}

	@Override
	public void realizarAccion(ResultadosDeBusquedas resultado) {
		evaluarNotificacion(resultado.getTiempoDeBusqueda());
	}
}

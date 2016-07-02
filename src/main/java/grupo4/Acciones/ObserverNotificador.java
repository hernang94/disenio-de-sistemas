package grupo4.Acciones;

import java.time.LocalDateTime;

import grupo4.ComponentesExternos.EmailSender;

public class ObserverNotificador implements Observers {
	
	private long tiempoEstipulado;
	private EmailSender notificador;
	
	public ObserverNotificador(long tiempoEstipulado, EmailSender notificador) {
		this.tiempoEstipulado = tiempoEstipulado;
		this.notificador= notificador;
	}

	public void evaluarNotificacion(long diferencia){
		if(diferencia > tiempoEstipulado){
			notificador.enviarMail();
		}
	}

	public void agregarBusqueda( String criterio,long diferencia, LocalDateTime tiempoInicio, int size) {

	}

	public void reporteTotalPorFecha() {
		
	}

	public void reporteParcial() {

	}

	public void reporteTotal() {

	}
}

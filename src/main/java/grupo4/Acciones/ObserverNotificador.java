package grupo4.Acciones;

import java.time.LocalDateTime;

import grupo4.ComponentesExternos.EmailSender;

public class ObserverNotificador implements Observers {

	private long tiempoEstipulado;
	private EmailSender notificador;

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

	public void agregarBusqueda(String terminal, String criterio, long diferencia, LocalDateTime tiempoInicio,
			int size) {

	}

	public void reporteTotalPorFecha(String terminal) {

	}

	public void reporteParcial() {

	}

	public void reporteTotal() {

	}
}

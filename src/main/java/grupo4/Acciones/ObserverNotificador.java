package grupo4.Acciones;

import java.time.LocalDateTime;
import java.util.List;

import grupo4.ComponentesExternos.EmailSender;

public class ObserverNotificador implements Observers,EmailSender {
	
	private long tiempoEstipulado;
	
	public ObserverNotificador(long tiempoEstipulado) {
		this.tiempoEstipulado = tiempoEstipulado;
	}

	public void evaluarNotificacion(long diferencia){
		if(diferencia > tiempoEstipulado){
			enviarMail();
		}
	}
	
	public void enviarMail(){
		
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
